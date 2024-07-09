package ar.edu.unq.po2.Zone;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.PuntoDeVenta;

public class Zone {

	
	private List<Parking> estacionamientos;
	private List<PuntoDeVenta> listaPuntosDeVenta;
	private Inspector inspector;
	 
	public Zone() {
		super();
		this.estacionamientos = new ArrayList<Parking>();
		this.listaPuntosDeVenta = new ArrayList<PuntoDeVenta>();
		
	} 

	public void agregarParking(Parking parking) {
		
		this.estacionamientos.add(parking);
		
	}
	
	public void removerParking(Parking parking) {
		
		this.estacionamientos.remove(parking);
		
	}

	public void agregarPuntoDeVenta(PuntoDeVenta pointOfSale) {
		
		this.getListaPuntosDeVenta().add(pointOfSale);
		
	}
	
	public void removerPuntoDeVenta(PuntoDeVenta pointOfSale) {
		
		this.getListaPuntosDeVenta().remove(pointOfSale);
		
	}
	
	
	public List<Parking> getEstacionamientos() {
		return estacionamientos;
	}

	public List<PuntoDeVenta> getListaPuntosDeVenta() {
		return listaPuntosDeVenta;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	
	
	
	
	
	
	
	
	
}
