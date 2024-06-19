package ar.edu.unq.po2.Sem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.internal.stubbing.answers.ThrowsException;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.ParkingViaApp;
import ar.edu.unq.po2.Parking.PuntoDeVenta;
import ar.edu.unq.po2.Zone.Zone;

public class SEMSystem {

	private List<Zone> zones;
	private List<Inspector> inspectors;
	private List<UserApp> users;
	private List<Parking> parkings;
	private List<Entidad> entidades;
	private List<Infraccion> infracciones;
	private Map<String,Double> usuarios;	 //<NroCelular, saldo>
	private Map<String,String> usersPatentes; // <NroCelular,Patente>
	private List<PuntoDeVenta> puntosDeVentas;
	private Double precioPorHora;
	private LocalTime startTime;
	private LocalTime endTime;
	private Clock clock;	
	
	//Constructor
	
	public SEMSystem(LocalTime startTime, LocalTime endTime,Clock clock) {
		this.usuarios 	    = new HashMap<String,Double>();
		this.usersPatentes  = new HashMap<String,String>();
		this.zones 		    = new ArrayList<Zone>();
		this.inspectors     = new ArrayList<Inspector>();
		this.users			= new ArrayList<UserApp>();
		this.parkings 	    = new ArrayList<Parking>();
		this.infracciones   = new ArrayList<Infraccion>();
		this.entidades 	    = new ArrayList<Entidad>();
		this.puntosDeVentas = new ArrayList<PuntoDeVenta>();
		this.clock          = clock;
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	public void darAltaInfraccion(String patente,Zone zone) {
		if(this.checkParking(patente) && this.checkZone(zone)) {
			Infraccion nuevaInfraccion = new Infraccion(patente,zone.getInspector(),LocalTime.now(),zone);
			this.addInfraccion(nuevaInfraccion);
		}
	}
	
	public void recargarSaldo(String numeroCelular,Double cargar) { 
		if(this.existeElNumero(numeroCelular)) {
			Double numero = this.usuarios.get(numeroCelular) + cargar;
			this.usuarios.put(numeroCelular,numero);
		}
	}
	
	public void notificarInicioDeEstacionamiento() {
		for(Entidad entidad : this.entidades) {
			entidad.notificarInicio();
			System.out.println("Inicio del estacionamiento");
		}
	}
	
	public void notificarFinDeEstacionamiento() {
		for(Entidad entidad : this.entidades) {
			entidad.notificarFin();
			System.out.println("Fin del estacionamiento");
		}
	}
	
	public void iniciarParking(UserApp app,String patente) {
		Parking nuevoParking = new ParkingViaApp(patente,app);
		LocalTime horaActual = LocalTime.now();
		int cantidadMaxima = (int) (app.consultarSaldo() / this.getPrecioPorHora());
		LocalTime horaFin = horaActual.plusHours(cantidadMaxima);
		this.addParking(nuevoParking);
		app.notificarInicioDeParking(horaActual,horaFin);
	}
	
	public void finDeParking(UserApp app) {
		String patente = this.getUsersPatentes().get(app.getNumeroAsociado());
		Parking parkingFinalizado = this.getParkings().stream().filter(p -> p.getPatente().equals(patente)).findFirst().orElseThrow(() -> new RuntimeException("Parking no encontrado"));
		LocalTime horaInicio = parkingFinalizado.getHoraDeInicio();
		LocalTime horaFin    = LocalTime.now();
		int cantHora     = horaFin.getHour() - horaInicio.getHour();
		Double costoAPagar = cantHora * this.getPrecioPorHora();
		this.endParking(parkingFinalizado);
		app.notificarFinDeParking(horaInicio,horaFin,costoAPagar,cantHora);
	}
	
	//Suscribir o desuscribir
	
	public void suscribir(Entidad entidad) {
		if(!(entidades.contains(entidad))) {
			this.entidades.add(entidad);
			System.out.println("Usted se ha suscripto correctamente.");		
		}
	}
	
	public void desuscribir(Entidad entidad) {
		if(entidades.contains(entidad)) {
			this.entidades.remove(entidad);
			System.out.println("Usted se ha desuscripto correctamente.");
		}
	}
	
	//Verifica si está en la lista
	
	public boolean checkZone(Zone zone) {
		return this.zones.stream().anyMatch(z -> z.getNombre().equals(zone.getNombre()));
	}
	
	public boolean checkParking(String patente) {
		return this.parkings.stream().anyMatch(p -> p.getPatente().equals(patente));
	}
	
	public Double consultarSaldo(String number) {
		Double saldo = 0D;		
		if(this.existeElNumero(number)) {
			saldo = this.usuarios.get(number);
		}
		return saldo;
	} 
	
	public boolean existeElNumero(String number) {
		return this.usuarios.containsKey(number);
	}
	
	//Finalizar
	
	public void endUsuario(String number) {
		for(String numero : this.getUsuarios().keySet()) {
			if(numero.equals(number)){
				this.getUsuarios().remove(number);				
			}
		}
	}
	
	public void endParking(Parking parking) {
			if(this.getParkings().contains(parking)) {				
				this.parkings.remove(parking); 
				this.notificarFinDeEstacionamiento();
			}
	}
	
	public void finDeFranjaHoraria() {
		//if(clock.getCurrentTime().isAfter(this.getEndTime())) {
			for(Parking parking : this .getParkings()) {
				this.endParking(parking);
			}
			//this.getUsers().stream().forEach(u -> u.finalizarParking());
		//}
	}
	
	
	//AGREGAR.
	
	public void addZone(Zone zone) {
		zones.add(zone);
	}
	
	public void addInspector(Inspector inspector) {
		inspectors.add(inspector);
	}
	
	public void addParking(Parking parking) {
		parkings.add(parking);
		this.notificarInicioDeEstacionamiento();
	}
	
	public void addInfraccion(Infraccion infraccion) {
		infracciones.add(infraccion);
	}
	
	public void addUserPatente(String nroCelular,String patente) {
		this.getUsersPatentes().put(nroCelular, patente);
	}
	
	public void addUsuario(String number,Double saldo) {
		this.getUsuarios().put(number,saldo);
	}
	
	//GET and SET
	
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public List<Entidad> getEntidad() {
		return entidades;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public List<Zone> getZones() {
		return zones;
	}

	public List<Parking> getParkings() {
		return parkings;
	}

	public Map<String, Double> getUsuarios() {
		return usuarios;
	}

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public List<Inspector> getInspectors() {
		return inspectors;
	}

	public Map<String, String> getUsersPatentes() {
		return usersPatentes;
	}
	

	public Double getPrecioPorHora() {
		return precioPorHora;
	}
	
	public void setPrecioPorHora(Double precioPorHora) {
		 this.precioPorHora = precioPorHora;
	}

	public List<UserApp> getUsers() {
		return users;
	}
	
	
	
	
}
