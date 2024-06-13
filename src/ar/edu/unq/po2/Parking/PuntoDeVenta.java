package ar.edu.unq.po2.Parking;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

public class PuntoDeVenta {
	
	private SEMSystem sistemaCentral;
	private Zone zona;
	
	public PuntoDeVenta (SEMSystem sistemaCentral, Zone zona) {
		this.sistemaCentral = sistemaCentral;
		this.zona = zona;
	}
	
	public void venderHorasDeParking(String patente, int horasCompradas) {
		Parking parking = new ParkingPuntual(patente, horasCompradas, this);
		parking.setZone(this.zona);
		this.sistemaCentral.addParking(parking);
	}
	
	public void recargarSaldo(String phoneNumber) {
		sistemaCentral.recargarSaldo(phoneNumber);
	}
	public Zone getZona() {
		return zona;
	}

	public SEMSystem getSystem() {
		return sistemaCentral;
	}
}
