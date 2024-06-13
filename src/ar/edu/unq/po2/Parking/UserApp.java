package ar.edu.unq.po2.Parking;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

public class UserApp implements MovementSensor {
	
	private String numeroAsociado;
	private SEMSystem sistemaCentral;
	private String patentePredeterminada;
	Modo modo;
	Boolean hayParkingVigente;
	
	public enum Modo {
		MANUAL, AUTOMATICO
	}
	
	public UserApp (String numeroAsociado, SEMSystem sistemaCentral, String patentePredeterminada) {
		this.numeroAsociado = numeroAsociado;
		this.sistemaCentral = sistemaCentral;
		this.hayParkingVigente = false;
		this.modo = Modo.MANUAL;
		this.patentePredeterminada = patentePredeterminada;
	}
	
	public void iniciarParking (String patente) {
		Parking parking = new ParkingViaApp(patente, this);
		parking.setZone(this.zonaActual());
		this.sistemaCentral.addParking(parking);
	}
	
	public void finalizarParking () {
		this.sistemaCentral.endParking(this.numeroAsociado);
	}
	
	public Zone zonaActual() {
		return null;
	}
	
	public double consultarSaldo() {
		return this.sistemaCentral.consultarSaldo(this.numeroAsociado);
	}

	public SEMSystem getSistemaCentral() {
		return this.sistemaCentral;
	}
	
	public String getNumeroAsociado() {
		return this.numeroAsociado;
	}
	
	@Override
	public void driving() {
		if (this.modo == Modo.AUTOMATICO && this.hayParkingVigente) {
			finalizarParking();
			this.hayParkingVigente = false;
		} // Deberia preguntar ademas si se encuentra dentro de una de las zonas de parking.
	}

	@Override
	public void walking() {
		if (this.modo == Modo.AUTOMATICO && !this.hayParkingVigente) {
            iniciarParking(this.patentePredeterminada);
            this.hayParkingVigente = true;
		} // Deberia preguntar ademas si se encuentra dentro de una de las zonas de parking.
	}
    
	public void setModo(Modo nuevoModo) {
		this.modo = nuevoModo;
	}
	
	public Modo getModo() {
		return this.modo;
	}
	
	public String getPatentePredeterminada() {
		return this.patentePredeterminada;
	}
	
	public void cambiarPatentePredetermianda (String patenteNueva) {
		this.patentePredeterminada = patenteNueva;
	}
	
	public void setHayParkingVigente(Boolean bool) {
		this.hayParkingVigente = bool;
	}
}
