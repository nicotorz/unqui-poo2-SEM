package ar.edu.unq.po2.Parking;

import java.time.LocalTime;

import ar.edu.unq.po2.Zone.Zone;

public abstract class Parking {
	
	protected String patente;
	protected LocalTime horaDeInicio;
	protected Zone zona;
	
	public Parking(String patente) {
		this.patente = patente;
		this.horaDeInicio = LocalTime.now();
	}
	
	public abstract int calcularHorasMaximas();
	public abstract LocalTime calcularHoraDeFin();
	
	//Getters y Setters
		public String getPatente() {
			return this.patente;
		}
		
		public LocalTime getHoraDeInicio() {
			return this.horaDeInicio;
		}
		
		public Zone getZona() {
			return this.zona;
		}
		
		public void setZone(Zone zona) {
			this.zona = zona;
		}
}
