package ar.edu.unq.po2.Parking;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zona;

public class PuntoDeVenta {
	
	private SEMSystem sistemaCentral;
	private Zona zona;
	private CompraPuntual tipoDeCompra;
	
	public PuntoDeVenta (SEMSystem sistemaCentral, Zona zona) {
		this.sistemaCentral = sistemaCentral;
		this.zona = zona;
		this.tipoDeCompra = new CompraPuntual(this);
	}
	
	public void venderHorasDeParking(String patente, int horasCompradas) {
		this.tipoDeCompra.setPatente(patente);
		this.tipoDeCompra.setHorasCompradas(horasCompradas);
		tipoDeCompra.procesarCompra();
	}
	
	public void recargarSaldo(String phoneNumber) {
		sistemaCentral.recargarSaldo(phoneNumber);
	}
	public Zona getZona() {
		return zona;
	}

	public SEMSystem getSystem() {
		return sistemaCentral;
	}

	public void setTipoDeCompra(CompraPuntual tipoDeCompra) {
		this.tipoDeCompra = tipoDeCompra;
	}
	
}
