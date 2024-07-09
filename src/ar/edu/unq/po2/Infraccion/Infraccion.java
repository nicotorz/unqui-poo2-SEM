package ar.edu.unq.po2.Infraccion;

import java.time.LocalDateTime;

import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Zone.Zone;

public class Infraccion {

	private String patente;
	private Inspector inspector;
	private LocalDateTime fechaYHora;
	private Zone zona;
	
	
	public Infraccion(String patente, Inspector inspector, LocalDateTime fechaYHora, Zone zona) {
		super();
		this.patente = patente;
		this.inspector = inspector;
		this.fechaYHora = fechaYHora;
		this.zona = zona;
	}
	
	
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Inspector getInspector() {
		return inspector;
	}
	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}
	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(LocalDateTime fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	public Zone getZona() {
		return zona;
	}
	public void setZona(Zone zona) {
		this.zona = zona;
	}
	
	
	
	
	
	
	
}
