package ar.edu.unq.po2.Parking;

import java.time.LocalTime;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

public class UserApp implements MovementSensor {
	
	private String numeroAsociado;
	private SEMSystem sistemaCentral;
	private String patentePredeterminada;
	private ModoDeOperacion modo;
	private EstadoParking estado;
	
	public UserApp (String numeroAsociado, SEMSystem sistemaCentral, String patentePredeterminada) {
		this.numeroAsociado = numeroAsociado;
		this.sistemaCentral = sistemaCentral;
		this.modo = new ModoManual();
		this.estado = new EstadoParkingNoVigente();
		this.patentePredeterminada = patentePredeterminada;
		this.sistemaCentral.registrarUsuario(this);
	}
	
	// Service 
	public void iniciarParking (String patente) {
		this.estado.iniciarParking(this, patente);
	}
	
	public void finalizarParking () {
		this.estado.finalizarParking(this);
	}
	
	public double consultarSaldo() {
		return this.sistemaCentral.consultarSaldo(this.numeroAsociado);
	}
	
	public boolean tieneSaldoMinimoParaEstacionamiento() {
		Double saldoActual = this.consultarSaldo();
		Double precioPorHoraS = this.sistemaCentral.getPrecioPorHora();
		return saldoActual > precioPorHoraS;
	}
	
	@Override
	public void driving() {
		this.modo.driving(this);
	}
	
	@Override
	public void walking() {
		this.modo.walking(this);
	}
	
	public Zone zonaActual() {
		// Implementar con GPS
		return null;
	}
	
	public boolean estaEnZonaDeEstacionamiento() {
		// Implementar con GPS
		return true;
	}
	
	// Getters y Setters
	public SEMSystem getSistemaCentral() {
		return this.sistemaCentral;
	}
	
	public String getNumeroAsociado() {
		return this.numeroAsociado;
	}
	
	
	public void setModo(ModoDeOperacion nuevoModo) {
		this.modo = nuevoModo;
	}
	
	public ModoDeOperacion getModo() {
		return this.modo;
	}
	
	public String getPatentePredeterminada() {
		return this.patentePredeterminada;
	}
	
	public void cambiarPatentePredetermianda (String patenteNueva) {
		this.patentePredeterminada = patenteNueva;
	}
	
	public EstadoParking getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoParking nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	//Notification
	public void notificarSaldoInsuficiente() {
		System.out.print("Saldo insuficiente");		
	}
	
	public void notificarInicioDeParking(LocalTime horaInicio, LocalTime horaMaximaDeFin) {
		System.out.print(
				"Estacionamiento iniciado con exito." + "Hora de inicio: " + horaInicio.toString()
						+ "Hora de maxima de fin: " + horaMaximaDeFin.toString());
	}
	
	public void notificarFinDeParking(LocalTime horaInicio, LocalTime horaFin, Double costo, Integer horasDeParking) {
		System.out.print(
				"Estacionamiento finalizado con exito." + "Hora de Inicio: " + horaInicio.toString()
						+ "Hora de fin: " + horaFin.toString() + "Costo: "
						+ costo.toString() + "Duracion: " + horasDeParking.toString());
	}
	
	public void notificarInicioParkingPosible() {
		System.out.print("Atención: No detectamos que hayas iniciado el modo de estacionamiento. Por favor, verifica y activa el parking");
	}
	
	

	public void notificarFinParkingPosible() {
		System.out.print("Atención: No detectamos que hayas finalizado el parking. Por favor, verifica y finaliza el parking");
	}
	
}
