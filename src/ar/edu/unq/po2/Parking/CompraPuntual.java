package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;

import ar.edu.unq.po2.Zone.Zona;

public class CompraPuntual extends Compra {
	
	private int horasCompradas;
	private PuntoDeVenta puntoDeVenta;
	
	public CompraPuntual(PuntoDeVenta puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}

	@Override
	public void validarCompra() {
		if (horasCompradas <= 0 || patente == null) {
            throw new IllegalArgumentException("Datos de compra puntual inválidos");
        }
	}

	@Override
	public void registrarCompra() {
		Zona zona = puntoDeVenta.getZona();
		Parking parking = new Parking(patente, LocalDateTime.now(), zona);
		puntoDeVenta.getSystem().addParking(parking);
	}

	public void setPatente(String patente) {
		this.patente = patente;
		
	}

	public void setHorasCompradas(int horasCompradas) {
		this.horasCompradas = horasCompradas;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return this.puntoDeVenta;
	}

	
}
