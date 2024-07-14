package ar.edu.unq.po2.Sem;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.CompraDeEstacionamiento;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.ParkingViaApp;
import ar.edu.unq.po2.Zone.Zone;
import ar.edu.unq.po2.Parking.UserApp;

public class SEMSystem {

	private List<Zone> zones;
	private List<Inspector> inspectors;
	private List<UserApp> users;
	private List<Parking> parkings;
	private List<CompraDeEstacionamiento> comprasDeEstacionamiento;
	private List<NotifyEntidad> entidades;
	private List<Infraccion> infracciones;
	private Map<String,Double> nroUsuarios;	 //<NroCelular, saldo>
	private Map<String,String> usersPatentes; // <NroCelular,Patente>
	private Double precioPorHora;
	private LocalTime startTime;
	private LocalTime endTime;
	
	//Constructor
	
	public SEMSystem(LocalTime startTime, LocalTime endTime) {
		this.nroUsuarios 	    = new HashMap<String,Double>();
		this.usersPatentes  = new HashMap<String,String>();
		this.zones 		    = new ArrayList<Zone>();
		this.inspectors     = new ArrayList<Inspector>();
		this.users			= new ArrayList<UserApp>();
		this.parkings 	    = new ArrayList<Parking>();
		this.infracciones   = new ArrayList<Infraccion>();
		this.entidades 	    = new ArrayList<NotifyEntidad>();
		this.comprasDeEstacionamiento = new ArrayList<CompraDeEstacionamiento>();
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	public void darAltaInfraccion(String patente,Zone zone) {
		if(this.checkParking(patente) && this.checkZone(zone)) {
			Infraccion nuevaInfraccion = new Infraccion(patente,zone.getInspector(),LocalDateTime.now(), zone);
			this.addInfraccion(nuevaInfraccion);
		}else { }
	}
	
	public void recargarSaldo(Double monto, String numeroCelular) { 
		if(this.existeElNumero(numeroCelular)) {
			Double saldoActual = this.nroUsuarios.get(numeroCelular);
			this.nroUsuarios.put(numeroCelular,saldoActual + monto);
			this.notificarCargaDeSaldo();
		}else {
			System.out.println("No existe el número.");
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
	
	public void notificarInicioDeEstacionamiento() {
		for(NotifyEntidad entidad : this.entidades) {
			entidad.notificarInicio();
		}
	}
	
	public void notificarFinDeEstacionamiento() {
		for(NotifyEntidad entidad : this.entidades) {
			entidad.notificarFin();
		}
	}
	
	public void notificarCargaDeSaldo() {
		for(NotifyEntidad entidad : this.entidades) {
			entidad.notificarCarga();
		}
	}
	
	public boolean estaEnZonaDeEstacionamiento() {
		return true;
	}
	
	//Suscribir o desuscribir
	
	public void suscribir(NotifyEntidad entidad) {
		if(!(entidades.contains(entidad))) {
			this.entidades.add(entidad);
			System.out.println("Usted se ha suscripto correctamente.");		
		}
	}
	
	public void desuscribir(NotifyEntidad entidad) {
		if(entidades.contains(entidad)) {
			this.entidades.remove(entidad);
			System.out.println("Usted se ha desuscripto correctamente.");
		}
	}
	
	//Verifica si está en la lista
	
	public boolean checkZone(Zone zone) {
		return this.zones.contains(zone);
	}
	
	public boolean checkParking(String patente) {
		return this.parkings.stream().anyMatch(p -> p.getPatente().equals(patente));
	}
	
	public Double consultarSaldo(String number) {
		Double saldo = 0D;		
		if(this.existeElNumero(number)) {
			saldo = this.nroUsuarios.get(number);
		}
		return saldo;
	} 
	
	public boolean existeElNumero(String number) {
		return this.nroUsuarios.containsKey(number);
	}
	
	//Finalizar
	
	public void endUsuario(String number) {
		for(String numero : this.getNroUsuarios().keySet()) {
			if(numero.equals(number)){
				this.getNroUsuarios().remove(number);				
			}else {
				System.out.println("El usuario no está disponible.");
			}
		}
	}
	
	public void endParking(Parking parking) {
		//String patenteDelUsuario = this.getUsersPatentes().get(nroAsociado);
		//Parking parkingAFinalizar = this.getParkings().stream().filter(p -> p.getPatente().equals(patenteDelUsuario)).findFirst().orElse(null);
			if(this.getParkings().contains(parking)) {				
				this.parkings.remove(parking); 
				this.notificarFinDeEstacionamiento();
			}else {
				System.out.println("No se encuentra en el parking del SEM.");
			}
	}
	
	public void finDeFranjaHoraria() {
		if(LocalTime.now().isAfter(this.getEndTime())) {
			for(Parking parking : this.getParkings()) {
				this.endParking(parking);	
			}
		}
	}
	
	
	
	//AGREGAR.
	
	public void registrarUsuario(UserApp userVigente) {
		this.getUsers().add(userVigente);
	}
	
	public void registrarCompra(CompraDeEstacionamiento compra) {
		comprasDeEstacionamiento.add(compra);
	}
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
		this.getNroUsuarios().put(number,saldo);
	}
	
	//GET and SET
	
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public List<NotifyEntidad> getEntidad() {
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
	public Map<String, Double> getNroUsuarios() {
		return nroUsuarios;
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

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public List<Inspector> getInspectors() {
		return inspectors;
	}

	public List<CompraDeEstacionamiento> getComprasDeEstacionamiento() {
		return comprasDeEstacionamiento;
	}
	
	
	
}
