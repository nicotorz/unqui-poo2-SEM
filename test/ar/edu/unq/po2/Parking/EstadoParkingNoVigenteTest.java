package ar.edu.unq.po2.Parking;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;

class EstadoParkingNoVigenteTest {

	private UserApp appMock;
	private SEMSystem sistemaCentralMock;
	private EstadoParkingNoVigente estadoNoVigente;
	
	@BeforeEach
	public void setup() {
		appMock = Mockito.mock(UserApp.class);
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		estadoNoVigente = new EstadoParkingNoVigente();
	}
	
	@Test
	void cuandoEsEstadoNoVigenteAlFinalizarParkingNotificaQueNoSePuedeFinalizar() {
		//setup
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		//exercise
		estadoNoVigente.finalizarParking(appMock);
		//assert
		assertEquals("No se puede finalizar el parking ya que no existen parkings vigentes", outContent.toString().trim());
	}
	
	@Test
	void cuandoEsEstadoNoVigenteAlIniciarParkingSiLaAppNoTieneSaldoSuficienteNotificaAlUsuario() {
		//setup
		when(appMock.tieneSaldoMinimoParaEstacionamiento()).thenReturn(false);
		//exercise 
		estadoNoVigente.iniciarParking(appMock, "BO 012 CA");
		//verify
		verify(appMock, times(1)).notificarSaldoInsuficiente();
	}
	
	@Test
	void cuandoEsEstadoNoVigenteAlIniciarParkingSiLaAppTieneSaldoSuficienteInteractuaConLaAppYElSEMParaDarInicio() {
		//setup 
		when(appMock.tieneSaldoMinimoParaEstacionamiento()).thenReturn(true);
		when(appMock.getSistemaCentral()).thenReturn(sistemaCentralMock);
		//exercise 
		estadoNoVigente.iniciarParking(appMock, "BO 012 CA");
		//verify
		verify(sistemaCentralMock, times(1)).iniciarParking(appMock, "BO 012 CA");
		verify(appMock, times(1)).setEstado(argThat(estado -> estado instanceof EstadoParkingVigente));
	}
	
	@Test
	void cuandoEsEstadoNoVigenteNoHaceNadaAlNotificarPosibleFinDeParking() {
		//exercise 
		estadoNoVigente.notificarFinParkingPosible(appMock);
		// no hay nada en especifico que verificar ya que el metodo invocado no hace nada.
		// es llamado para verficiar que no lanza exepciones.
	}
	
	@Test
	void cuandoEsEstadoNoVigenteNotificaLaPosibleIniciacionDeParking() {
		//exercise
		estadoNoVigente.notificarInicioParkingPosible(appMock);
		//verify
		verify(appMock, times(1)).notificarInicioParkingPosible();
	}

}
