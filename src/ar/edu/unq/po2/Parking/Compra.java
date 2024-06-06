package ar.edu.unq.po2.Parking;

public abstract class Compra {
	
	protected String patente;
	
	public Compra() {
		
	}
	
	public void procesarCompra() {
		validarCompra();
		registrarCompra();
	}
	
	public String getPatente() {
		// TODO Auto-generated method stub
		return this.patente;
	}
	
	public abstract void validarCompra();
	public abstract void registrarCompra();
	
}
