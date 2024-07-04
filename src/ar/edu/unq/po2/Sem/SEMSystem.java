package ar.edu.unq.po2.Sem;

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
