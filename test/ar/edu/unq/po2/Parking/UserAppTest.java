package ar.edu.unq.po2.Parking;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

class UserAppTest {
	
	private SEMSystem sistemaCentralMock;
	private UserApp app;
	private EstadoSensor estadoSMock;
	private EstadoParking estadoMock;
	
	@BeforeEach
	public void setUp() {
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		app = new UserApp("111234567", sistemaCentralMock, "BO 012 CA");
		estadoSMock = Mockito.mock(EstadoSensor.class);
		estadoMock = Mockito.mock(EstadoParking.class);
	}
	
	@Test
	void unUserAppConoceElSistemaCentralConElCualColabora() {
		//assert
		assertEquals(app.getSistemaCentral(), sistemaCentralMock);
	}
	
	@Test
	void unUserAppSeInicializaConUnNumeroDeTelefonoAsociado() {
		//assert
		assertEquals(app.getNumeroAsociado(), "111234567");
	}
	
	@Test 
	void unUserAppSeInicializaConSaldoIgualACero() {
	//assert
	assertEquals(0.0, app.consultarSaldo(), 0.001);
	}
	
	@Test 
	void unUserAppSeIncializaConUnaPatentePredeterminada() {
		//assert 
		assertEquals(app.getPatentePredeterminada(), "BO 012 CA");
	}
	
	@Test
	void unUserAppSeInicializaEnModoManualPorDefecto() {
		//assert
		assertTrue(app.getModo() instanceof ModoManual);
	}
	
	@Test
	void unUserAppSeInicializaEnEstadoNoVigentePorDefecto() {
		//assert
		assertTrue(app.getEstado() instanceof EstadoParkingNoVigente);
	}
	
	@Test 
	void cuandoUnUserAppSeInicializaSeRegistraEnElSistemaCentralDado() {
		//verify
		verify(sistemaCentralMock, times(1)).registrarUsuario(app);
	}
	
	@Test
	void unUserAppPuedeCambiarDePatentePredeterminadaEnCualquierMomento() {
		//assert
		assertEquals(app.getPatentePredeterminada(), "BO 012 CA");
		app.cambiarPatentePredetermianda("BO 006 CA");
		assertEquals(app.getPatentePredeterminada(), "BO 006 CA");
	}
	
	@Test
	void unUserAppPuedeConsultarSuSaldoMedianteElSistemaCentral() {
		//exercise
		app.consultarSaldo();
		//verify
		verify(sistemaCentralMock, times(1)).consultarSaldo("111234567");
	}
	
	@Test
	void unUserAppCuandoConsultaSaldoLeRetornaElSaldoDisponible() {
		//setup
		when(sistemaCentralMock.consultarSaldo("111234567")).thenReturn(100.0);
		double saldo = app.consultarSaldo();
		//assert
		assertEquals(100.0, saldo, 0.001); //0.001 la tolerancia permitida para comparacion de valores.
	}
	
	@Test
	void aUnUserAppSeLePuedeConsultarSiTieneSaldoMinimoParaUnEstacionamientoSegunElPrecioPorHoraDelSistema() {
		 //setup 
		 when(sistemaCentralMock.consultarSaldo("111234567")).thenReturn(50.0);
		 when(sistemaCentralMock.getPrecioPorHora()).thenReturn(40.0);
		 //assert
	     assertTrue(app.tieneSaldoMinimoParaEstacionamiento());
	}
	
	@Test
	void aUnUserAppSeLePuedeConsultarSiNoTieneSaldoMinimoParaUnEstacionamientoSegunElPrecioPorHoraDelSistema() {
		//setup
		when(sistemaCentralMock.consultarSaldo("111234567")).thenReturn(30.0);
		when(sistemaCentralMock.getPrecioPorHora()).thenReturn(40.0);
		//assert
	    assertFalse(app.tieneSaldoMinimoParaEstacionamiento());
	}
	
	@Test
	void aUnUserAppSeLePuedeConsultarPorSuEstadoActualSiEsVigente() {
		//setup
		EstadoParkingVigente estadoActual = new EstadoParkingVigente();
		app.setEstado(estadoActual);
		//assert
		assertEquals(estadoActual, app.getEstado());
	}
	
	@Test
	void aUnUserAppSeLePuedeConsultarPorSuEstadoActualSiEsNoVigente() {
		//setup
		EstadoParkingNoVigente estadoActual = new EstadoParkingNoVigente();
		app.setEstado(estadoActual);
		//assert
		assertEquals(estadoActual, app.getEstado());
	}
	
	@Test 
	void aUnUserAppSeLePuedeConsultarPorSuModoActualSiEsManual() {
		//setup
		ModoManual modoActual = new ModoManual();
		app.setModo(modoActual);
		//assert
		assertEquals(modoActual, app.getModo());
	}
	
	@Test 
	void aUnUserAppSeLePuedeConsultarPorSuModoActualSiEsAutomatico() {
		//setup
		ModoAutomatico modoActual = new ModoAutomatico();
		app.setModo(modoActual);
		//assert
		assertEquals(modoActual, app.getModo());
	}
	
	@Test
	void enUserAppCuandoSeDetectaUnDespalzamientoCaminandoSeDelegaElTrabajoAlEstadoDelSensor() {
		//setup
		app.setEstadoSensor(estadoSMock);
		//exercise 
		app.walking();
		//verify
		verify(estadoSMock, times(1)).walking(app);
	}
	
	@Test
	void enUserAppCuandoSeDetectaUnDespalzamientoEnVehiculoSeDelegaElTrabajoAlModoActual() {
		//setup
		app.setEstadoSensor(estadoSMock);
		//exercise 
		app.driving();
		//verify
		verify(estadoSMock, times(1)).driving(app);
	}
	
	@Test
	void enUserAppCuandoSeIniciaUnParkingSeDelegaElTrabajoAlEstadoActualDeLaApp() {
		//setup
		String patente = "BO 012 CA";
		app.setEstado(estadoMock);
		//exercise
		app.iniciarParking(patente);
		//verify
		verify(estadoMock, times(1)).iniciarParking(app, patente);
	}
	
	@Test
	void enUserAppCuandoSeFinalizaUnParkingSeDelegaElTrabajoAlEstadoActualDeLaApp() {
		//setup
		app.setEstado(estadoMock);
		//exercise
		app.finalizarParking();
		//verify
        verify(estadoMock, times(1)).finalizarParking(app);
	}
	
	@Test
	void unUserAppConoceSiEstaEnZonaDeEstacionamientoMedidoMedianteElSistemaCentral() {
		//exercise
		app.estaEnZonaDeEstacionamientoMedido();
		//assert
		verify(sistemaCentralMock, times(1)).estaEnZonaDeEstacionamiento();
	}
	
	@Test
	void unUserAppTieneLasNotificacionesActivadasPorDefecto() {
		//assert 
		assertTrue(app.notificacionesActivas());
	}
	
	@Test 
	void unUserAppPuedeDesactivarLasNotificaciones() {
		//exercise
		app.activarODesactivarNotificaciones();
		//assert
		assertFalse(app.notificacionesActivas());
	}
	
	@Test
	void testNotificarSaldoInsuficienteApp() {
		//setup
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		//exercise
		app.notificarSaldoInsuficiente();
		//assert
		assertEquals("Saldo insuficiente", outContent.toString().trim());
	}
	
	@Test
	void testNotificarInicioDeEstacionamientoApp() {
		//setup
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));
		LocalTime horaInicio = LocalTime.of(10, 0);
		LocalTime horaFin = LocalTime.of(13, 0);
		//exercise
		app.notificarInicioDeParking(horaInicio, horaFin);
		System.setOut(originalOut);
		String mensajeEsperado = "Estacionamiento iniciado con exito." + "Hora de inicio: "
				+ horaInicio.toString() + "Hora de maxima de fin: " + horaFin.toString();
		//assert
		assertEquals(mensajeEsperado, outContent.toString().trim());
	}
	
	@Test
	void testNotificarFinDeEstacionamientoApp() {
		//setup
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));
		LocalTime horaInicio = LocalTime.of(10, 0);
		LocalTime horaFin = LocalTime.of(13, 0);
		//exercise
		app.notificarFinDeParking(horaInicio, horaFin, 120.0, 3);
		System.setOut(originalOut);
		String mensajeEsperado = "Estacionamiento finalizado con exito." 
									+ "Hora de Inicio: " + horaInicio.toString()
									+ "Hora de fin: " + horaFin.toString() + "Costo: "
									+ "120.0" + "Duracion: " + "3";
		//assert
		assertEquals(mensajeEsperado, outContent.toString().trim());
	}
	
	@Test
	void testNotificarInicioParkingPosibleApp() {
		//setup
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		//exercise
		app.notificarInicioParkingPosible();
		//assert
		assertEquals("Atención: No detectamos que hayas iniciado el modo de estacionamiento. Por favor, verifica y activa el parking", outContent.toString().trim());
	}
	
	@Test
	void testNotificarFinParkingPosibleApp() {
		//setup
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		//exercise
		app.notificarFinParkingPosible();
		//assert
		assertEquals("Atención: No detectamos que hayas finalizado el parking. Por favor, verifica y finaliza el parking", outContent.toString().trim());
	}
	
}
