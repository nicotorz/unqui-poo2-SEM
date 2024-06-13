package ar.edu.unq.po2.Parking;

import java.time.LocalDateTime;

import ar.edu.unq.po2.Zone.Zone;

public abstract class Parking {
	
	protected String patente;
	protected LocalDateTime horaDeInicio;
	protected Zone zona;
	
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
		
		public Zone getZona() {
			return this.zona;
		}
		
		public void setZone(Zone zona) {
			this.zona = zona;
		}
}
