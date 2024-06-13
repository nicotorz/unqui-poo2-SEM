package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;


public class ParkingPuntual extends Parking {

	private int horasCompradas;
	private PuntoDeVenta puntoDeVenta;
	
	public ParkingPuntual(String patente, int horasCompradas, PuntoDeVenta puntoDeVenta) {
		super(patente);
		this.horasCompradas = horasCompradas;
		this.puntoDeVenta   = puntoDeVenta;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return this.puntoDeVenta;
	}

	@Override
	public int calcularHorasMaximas() {
		return this.horasCompradas;
	}

	@Override
	public LocalDateTime calcularHoraDeFin() {
		return this.horaDeInicio.plusHours(horasCompradas);
	}

}
