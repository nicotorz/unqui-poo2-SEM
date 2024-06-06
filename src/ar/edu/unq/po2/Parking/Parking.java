package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;

import ar.edu.unq.po2.Zone.Zona;

public class Parking {
	private String patente;
	private LocalDateTime horaInicio;
	private Zona zona;
	
	public Parking (String patente, LocalDateTime horaInicio, Zona zona) {
		this.patente = patente;
		this.horaInicio = horaInicio;
		this.zona = zona;
	}
		
	public String getPatente() {
		return patente;
	}	
	
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}	
	
	// Getter  
	public Zona getZona() {
		return zona;
	}
	
}	


