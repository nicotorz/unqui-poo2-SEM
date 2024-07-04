package ar.edu.unq.po2.Sem;

<<<<<<< HEAD
import java.time.LocalTime;

import ar.edu.unq.po2.Parking.CompraDeEstacionamiento;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.ParkingViaApp;
import ar.edu.unq.po2.Parking.UserApp;

public class SEMSystem {

	private double precioPorHora;

	public void addParking(Parking parking) {
		
	}

	public void endParking(String phoneNumber) {
		// TODO Auto-generated method stub
		
	}

	public void topUpBalance(String phoneNumber) {
		// TODO Auto-generated method stub
		
	}

	public void recargarSaldo(Double monto, String phoneNumber) {
		// TODO Auto-generated method stub
		
	}

	public Boolean contieneEstacionamientoConPatente(String patente) {
		// TODO Auto-generated method stub
		return true;
	}

	public double consultarSaldo(String numeroAsociado) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void iniciarParking(UserApp app, String patente) {
		LocalTime horaActual = LocalTime.now();
		int cantidadDeHorasMaximas = (int) (app.consultarSaldo() / this.precioPorHora);
		LocalTime horaMaxima = horaActual.plusHours(cantidadDeHorasMaximas);
		
		ParkingViaApp parkingNuevo = new ParkingViaApp (patente, app);
		parkingNuevo.setZone(app.zonaActual());
		
		this.addParking(parkingNuevo);
		app.notificarInicioDeParking(horaActual, horaMaxima);
=======
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.PuntoDeVenta;
import ar.edu.unq.po2.Zone.Zone;

public class SEMSystem {

	private List<Zone> zones;
	private List<Inspector> inspectors;
	private List<Parking> parkings;
	private List<Entidad> entidades;
	private List<Infraccion> infracciones;
	private Map<String,Double> usuarios;
	private List<PuntoDeVenta> puntosDeVentas;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Clock clock;	
	
	//Constructor
	
	public SEMSystem(LocalDateTime startTime, LocalDateTime endTime,Clock clock) {
		this.usuarios 	    = new HashMap<String,Double>();
		this.zones 		    = new ArrayList<Zone>();
		this.inspectors     = new ArrayList<Inspector>();
		this.parkings 	    = new ArrayList<Parking>();
		this.infracciones   = new ArrayList<Infraccion>();
		this.entidades 	    = new ArrayList<Entidad>();
		this.puntosDeVentas = new ArrayList<PuntoDeVenta>();
		this.clock          = clock;
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	public void issueFine(String patente,Zone zone) {
		if(this.checkParking(patente) && this.checkZone(zone)) {
			Infraccion nuevaInfraccion = new Infraccion(patente,zone.getInspector(),LocalDateTime.now(),zone);
			this.addInfraccion(nuevaInfraccion);
		}
	}
	
	public void recargarSaldo(String numeroCelular,Double cargar) { 
		if(this.existeElNumero(numeroCelular)) {
			Double numero = this.usuarios.get(numeroCelular) + cargar;
			this.usuarios.put(numeroCelular,numero);
		}
	}
	
	public void inicioDeEstacionamiento(Parking parking) {
		Double saldo = this.consultarSaldo(parking.getUserApp().getNumber());
		LocalDateTime horaActual = clock.getCurrentTime();
		LocalDateTime horaMaxima = LocalDateTime.of(24, 10 , 2, 20, 0);
		this.addParking(parking);
		if(saldo > 0) {
			LocalDateTime time =(saldo >= parking.calcularHorasMaximas()) ? horaMaxima : (horaActual.plusHours(horaMaxima.getHour() - horaActual.getHour()));
			System.out.println( "Inicio de Estacionamiento: " + horaActual + "Fin de estacionamiento: " + time);
		} else {
			System.out.println("Saldo insuficiente. Estacionamiento no permitido.");
		}
		this.notificarInicioDeEstacionamiento();
	}
	
	
	public void notificarInicioDeEstacionamiento() {
		for(Entidad entidad : this.entidades) {
			entidad.notificarInicio();
			System.out.println("Inicio del estacionamiento");
		}
	}
	
	public void finDeEstacionamiento(Parking parking) {
		LocalDateTime horaExacta = parking.getHoraDeInicio();
		if(!(this.getParkings().contains(parking))) {
			System.out.println("El parking no existe.");
		}else {
			System.out.println("La hora exacta del estacionamiento es: " + horaExacta + "La hora final del estacionamiento: " 
					+ parking.calcularHorasMaximas() + "El costo del estacionamiento es: " + parking.calcularHorasMaximas());
			this.endParking(parking);
		}
		this.notificarFinDeEstacionamiento();
	}
	
	public void notificarFinDeEstacionamiento() {
		for(Entidad entidad : this.entidades) {
			entidad.notificarFin();
			System.out.println("Fin del estacionamiento");
		}
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
		return this.parkings.stream().anyMatch(p -> p.tienePatente(patente));
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
		for(Parking p : this.getParkings()) {
			if(p.calcularHorasMaximas() == parking.calcularHorasMaximas()) {				
				this.parkings.remove(parking); 
			}
		}
	}
	
	public void endAllParkings() { 
		if(clock.getCurrentTime().equals(this.getEndTime())) {			
			this.parkings.removeAll(parkings);
		}
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
	}
	
	public void addInfraccion(Infraccion infraccion) {
		infracciones.add(infraccion);
	}
	
	public void addUsuario(String number,Double saldo) {
		this.getUsuarios().put(number,saldo);
	}
	
	//GET and SET
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	public List<Entidad> getEntidad() {
		return entidades;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
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
>>>>>>> main
	}
	
	public void finalizarParking(UserApp app) {
		
	}

	public Double getPrecioPorHora() {
		// TODO Auto-generated method stub
		return 40.0;
	}

	public void registrarUsuario(UserApp app) {
		// TODO Auto-generated method stub
		
	}

	public void registrarCompra(CompraDeEstacionamiento compra) {
		// TODO Auto-generated method stub
		
	}

	public boolean estaEnZonaDeEstacionamiento() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
