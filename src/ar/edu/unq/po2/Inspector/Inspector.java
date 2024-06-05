package ar.edu.unq.po2.Inspector;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Sem.Clock;
import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

public class Inspector {

	private String name;
	private Zone zona;
	private InspectorApp app;
	
	public Inspector(String name, Zone zona) {
		super();
		this.name = name;
		this.zona = zona;
	}
	
	
	public boolean checkParking(String patente) {
		
		return this.app.checkParking(patente);
		
	}
	
	public void issueFine(String patente) {
		
		this.app.issueFine(patente, this.getZona());
		
	}
	
	
	public void recorrerEstacionamientosDeZona() {
		
		
		List<Parking> parkingsDeZona = this.getZona().getEstacionamientos();
		
		for (Parking parking : parkingsDeZona) {
			
			String patente = parking.getPatente();
			
			if (!this.checkParking(patente)) {
				
				this.issueFine(patente);
				
			}
			
			
		}
		
		
	}
	
	
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public Zone getZona() {
		return zona;
	}
	private void setZona(Zone zona) {
		this.zona = zona;
	}


	public InspectorApp getApp() {
		return app;
	}


	public void setApp(InspectorApp app) {
		this.app = app;
	}
	
	
	
	
	
}
