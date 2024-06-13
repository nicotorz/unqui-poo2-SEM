package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;

import ar.edu.unq.po2.Zone.Zona;

public abstract class Parking {
	
	protected String patente;
	protected LocalDateTime horaDeInicio;
	protected Zona zona;
	
	public Parking(String patente) {
		this.patente = patente;
		this.horaDeInicio = LocalDateTime.now();
	}
	
	public abstract int calcularHorasMaximas();
	public abstract LocalDateTime calcularHoraDeFin();
	
	//Getters y Setters
		public String getPatente() {
			return this.patente;
		}
		
		public void setPatente(String patente) {
			this.patente = patente;
		}
		
		public LocalDateTime getHoraDeInicio() {
			return this.horaDeInicio;
		}
		
		public void setHoraDeInicio(LocalDateTime horaDeInicio) {
			this.horaDeInicio = horaDeInicio;
		}
		
		public Zona getZona() {
			return this.zona;
		}
		
		public void setZona(Zona zona) {
			this.zona = zona;
		}
}
