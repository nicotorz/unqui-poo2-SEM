package ar.edu.unq.po2.Zone;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.PointOfSale;

public class Zone {

	private String nombre;
	private List<Parking> estacionamientos;
	private List<PointOfSale> listaPuntosDeVenta;
	private Inspector inspector;
	
	public Zone(String nombre) {
		super();
		this.nombre = nombre;
		this.estacionamientos = new ArrayList<Parking>();
		this.listaPuntosDeVenta = new ArrayList<PointOfSale>();
		
	} 

	public void agregarParking(Parking parking) {
		
		this.estacionamientos.add(parking);
		
	}
	
	public void removerParking(Parking parking) {
		
		this.estacionamientos.remove(parking);
		
	}

	public void agregarPuntoDeVenta(PointOfSale pointOfSale) {
		
		this.getListaPuntosDeVenta().add(pointOfSale);
		
	}
	
	public void removerPuntoDeVenta(PointOfSale pointOfSale) {
		
		this.getListaPuntosDeVenta().remove(pointOfSale);
		
	}
	
	
	public List<Parking> getEstacionamientos() {
		return estacionamientos;
	}

	
	public String getNombre() {
		return nombre;
	}

	public List<PointOfSale> getListaPuntosDeVenta() {
		return listaPuntosDeVenta;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	
	
	
	
	
	
	
	
	
}
