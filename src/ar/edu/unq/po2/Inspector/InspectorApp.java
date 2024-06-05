package ar.edu.unq.po2.Inspector;

import java.time.LocalDateTime;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

public class InspectorApp {

	private Inspector inspector;
	private SEMSystem sem;

	
	public InspectorApp(Inspector inspector, SEMSystem sem) {
		super();
		this.inspector = inspector;
		this.sem = sem;
	}
	
	public Inspector getInspector() {
		return inspector;
	}
	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	public SEMSystem getSem() {
		return sem;
	}
	public void setSem(SEMSystem sem) {
		this.sem = sem;
	}
	
	public void issueFine(String patente, Zone zona) {
		// TODO Auto-generated method stub
		
		this.sem.issueFine(patente, zona, this.getInspector());
			
		
	}
	
	public boolean checkParking(String patente){
	
		// podria recibir la zona, para verificar que existe un estacionameinto con esa patente
		// para esa zona, por si el conductor movio el auto hacia otra zona.
		
		return this.sem.checkParking(patente);
		
	}
	
	
	
	
	
	
}
