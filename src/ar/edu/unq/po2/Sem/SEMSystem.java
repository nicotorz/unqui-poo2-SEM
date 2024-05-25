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
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public SEMSystem(LocalDateTime startTime, LocalDateTime endTime) {
		this.zones = new ArrayList<Zone>();
		this.inspectors = new ArrayList<Inspector>();
		this.parkings = new ArrayList<Parking>();
		this.infracciones = new ArrayList<Infraccion>();
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	public void addZone(Zone zone) {
		zones.add(zone);
	}
	
	public void addInspector(Inspector inspector) {
		inspectors.add(inspector);
	}
	
	public void addParking(Parking parking) {
		parkings.add(parking);
	}
	
	public void endParking(Parking parking) {
		
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
