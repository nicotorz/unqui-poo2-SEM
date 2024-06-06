package ar.edu.unq.po2.Sem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Zone.Zone;

public class SEMSystem {

	private List<Zone> zones;
	private List<Inspector> inspectors;
	private List<Parking> parkings;
	private List<Infraccion> infracciones;
	//private List<UserApp,Double> usuarios; VER COMO ARREGLAR
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	//Constructor
	
	public SEMSystem(LocalDateTime startTime, LocalDateTime endTime) {
		this.zones = new ArrayList<Zone>();
		this.inspectors = new ArrayList<Inspector>();
		this.parkings = new ArrayList<Parking>();
		this.infracciones = new ArrayList<Infraccion>();
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	public void issueFine(String patente,Zone zone) {
		if(this.checkParking(patente) && this.checkZone(zone)) {
			//Infraccion nuevaInfraccion = new Infraccion(patente,zone.getInspector(),LocalDateTime.now(),zone);
			//this.addInfraccion(nuevaInfraccion);
		}
	}
	
	public void notifyEntities() { //HACER
		
	}
	
	//Verifica si está en la lista
	
	public boolean checkZone(Zone zone) {
		return this.zones.contains(zone);
	}
	
	public boolean checkParking(String patente) {
		return true;
		//return this.parkings.stream().anyMatch(p -> p.getPatente().equals(patente));
	}
	
	/*public Double checkBalance(String number,UserApp user) { //VER COMO SE HACE
		if(user.getPhoneHumber().equals(number)) {	
			return user.getBalance();
		}
	} */
	
	//Finaliza los parkings
	
	public void endParking(Parking parking) {
		this.parkings.remove(parking); 
	}
	
	public void endAllParkings() { 
		this.parkings.removeAll(parkings);
	}
	
	//AGREGAR zone, inspector, parking, infraccion.
	
	public void addZone(Zone zone) {
		zones.add(zone);
	}
	
	public void addInspector(Inspector inspector) {
		inspectors.add(inspector);
	}
	
	public void addParking(Parking parking) {
		parkings.add(parking);
	}
	
	public void addInfraccion(Infraccion infraccion) {
		infracciones.add(infraccion);
	}
	
	//GET and SET
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	
	
}
