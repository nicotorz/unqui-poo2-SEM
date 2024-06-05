package ar.edu.unq.po2.Sem;

import java.util.List;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Inspector.InspectorApp;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Zone.Zone;

public class SEMSystem {

	private Clock clock;
	private List<Infraccion> infracciones;
	private List<Parking> parkings;
	
	
	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public void agregarInfraccion(Infraccion infraccion) {
		// TODO Auto-generated method stub
		
		this.infracciones.add(infraccion);
		
	}

	public List<Parking> getParkings() {
		return parkings;
	}

	public void setParkings(List<Parking> parkings) {
		this.parkings = parkings;
	}

	public boolean checkParking(String patente) {
		// TODO Auto-generated method stub
		return false;
	}

	public void issueFine(String patente, Zone zona, Inspector inspector) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
}
