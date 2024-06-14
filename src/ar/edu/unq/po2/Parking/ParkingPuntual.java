package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;


public class ParkingPuntual extends Parking {

	private int horasCompradas;
	
	public ParkingPuntual(String patente, int horasCompradas) {
		super(patente);
		this.horasCompradas = horasCompradas;
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
