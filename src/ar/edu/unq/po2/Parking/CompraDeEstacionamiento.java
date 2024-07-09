package ar.edu.unq.po2.Parking;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompraDeEstacionamiento extends Compra {

	public CompraDeEstacionamiento(int id, PuntoDeVenta puntoDeVenta, LocalDate fecha, LocalTime hora, double monto) {
		super(id, puntoDeVenta, fecha, hora, monto);
	}
}
