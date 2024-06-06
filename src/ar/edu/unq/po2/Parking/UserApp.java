package ar.edu.unq.po2.Parking;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zona;

public class UserApp {
	
	private String phoneNumber;
	private double saldo;
	private SEMSystem sistemaCentral;
	private CompraPorApp tipoDeCompra;
	
	public void inciarParking (String patente) {
		this.tipoDeCompra = new CompraPorApp(this);
		this.tipoDeCompra.setPatente(patente);
		tipoDeCompra.procesarCompra();
	}
	
	public void finalizarParking () {
		sistemaCentral.endParking(phoneNumber);
	}
	
	public Zona zonaActual() {
		return null;
	}
	public double getSaldo() {
		return saldo;
	}

	public SEMSystem getSistemaCentral() {
		return this.sistemaCentral;
	}

}
