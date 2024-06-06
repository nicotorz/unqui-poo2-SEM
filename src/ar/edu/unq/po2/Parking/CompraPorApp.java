package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;

import ar.edu.unq.po2.Zone.Zona;

public class CompraPorApp extends Compra {
	
	private UserApp app;
	
	public CompraPorApp(UserApp app) {
		this.app = app;
	}

	@Override
	public void validarCompra() {
		double saldo = app.getSaldo();
		if ( saldo < 40) {
            throw new IllegalArgumentException("Insuficiente saldo para procesar la compra");
        }
	}

	@Override
	public void registrarCompra() {
		Zona zonaActual = app.zonaActual();
		Parking parking = new Parking (patente, LocalDateTime.now(), zonaActual);
		app.getSistemaCentral().addParking(parking);
	}
	
	public UserApp getApp() {
		return this.app;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}
}
