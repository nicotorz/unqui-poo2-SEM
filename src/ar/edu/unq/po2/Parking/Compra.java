package ar.edu.unq.po2.Parking;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Compra {
	private int id;
	private PuntoDeVenta puntoDeVenta;
	private LocalDate fecha;
	private LocalTime hora;
	private double monto;
	
	public Compra(int id, PuntoDeVenta puntoDeVenta, LocalDate fecha, LocalTime hora, double monto) {
		this.id = id;
		this.puntoDeVenta = puntoDeVenta;
		this.fecha = fecha;
		this.hora = hora;
		this.monto = monto;
	}

	public int getId() {
		return id;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return puntoDeVenta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public double getMonto() {
		return monto;
	}
	
	
}
