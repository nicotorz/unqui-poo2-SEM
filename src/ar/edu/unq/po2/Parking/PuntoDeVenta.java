package ar.edu.unq.po2.Parking;

import java.time.LocalDate;
import java.time.LocalTime;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

public class PuntoDeVenta {
	
	private SEMSystem sistemaCentral;
	private Zone zona;
	private int id;
	
	public PuntoDeVenta (SEMSystem sistemaCentral, Zone zona) {
		this.sistemaCentral = sistemaCentral;
		this.zona = zona;
		this.id = 0;
	}
	
	public void venderHorasDeParking(String patente, int horasCompradas) {
		Parking parking = new ParkingPuntual(patente, horasCompradas);
		parking.setZone(this.zona);
		this.sistemaCentral.addParking(parking);
		this.registrarCompra(horasCompradas);
	}
	
	void registrarCompra(int horasCompradas) {
		LocalTime horaDeCompra  = LocalTime.now();
		LocalDate fechaDeCompra   = LocalDate.now();
		double monto = horasCompradas * (this.sistemaCentral.getPrecioPorHora());
		CompraDeEstacionamiento compra = new CompraDeEstacionamiento(this.asignarId(), this, fechaDeCompra, horaDeCompra, monto);
		this.sistemaCentral.registrarCompra(compra);
	}

	int asignarId() {
		if (this.id < 9999) {
			int numeroActual = this.id;
			this.id = (numeroActual + 1);
			return numeroActual;
		}
		this.id = 0;
		return id;

	}

	public void recargarSaldo(Double monto, String phoneNumber) {
		sistemaCentral.recargarSaldo(monto, phoneNumber);
	}
	public Zone getZona() {
		return zona;
	}

	public SEMSystem getSystem() {
		return sistemaCentral;
	}

	public int getId() {
		return id;
	}

	void setId(int nId) {
		this.id = nId;
	}
}
