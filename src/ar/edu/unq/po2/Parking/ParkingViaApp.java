package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;

public class ParkingViaApp extends Parking {
	
	private UserApp app;
	
	public ParkingViaApp(String patente, UserApp app) {
		super(patente);
		this.app = app;
	}
	
	public UserApp getApp() {
		return this.app;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	@Override
	public int calcularHorasMaximas() {
		return (int) (app.consultarSaldo() / 40);
	}

	@Override
	public LocalDateTime calcularHoraDeFin() {
		return this.horaDeInicio.plusHours(this.calcularHorasMaximas());
	}
}
