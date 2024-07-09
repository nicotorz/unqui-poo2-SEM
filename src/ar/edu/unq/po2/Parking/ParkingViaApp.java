package ar.edu.unq.po2.Parking;

import java.time.LocalTime;

public class ParkingViaApp extends Parking {
	
	private UserApp app;
	
	public ParkingViaApp(String patente, UserApp app) {
		super(patente);
		this.app = app;
	}
	
	public UserApp getApp() {
		return this.app;
	}
	
	@Override
	public int calcularHorasMaximas() {
		return (int) (app.consultarSaldo() / 40);
	}

	@Override
	public LocalTime calcularHoraDeFin() {
		return this.horaDeInicio.plusHours(this.calcularHorasMaximas());
	}
}
