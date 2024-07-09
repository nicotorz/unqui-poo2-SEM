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

class EstadoParkingVigenteTest {
	
	private UserApp appMock;
	private SEMSystem sistemaCentralMock;
	private EstadoParkingVigente estadoVigente;
	
	@BeforeEach
	public void setup() {
		appMock = Mockito.mock(UserApp.class);
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		estadoVigente = new EstadoParkingVigente();
	}
	
	@Test
	void cuandoEsEstadoVigenteAlIniciarParkingNotificaQueNoSePuedeInciar() {
		//setup
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		//exercise
		estadoVigente.iniciarParking(appMock, "BO 012 CA");
		//assert
		assertEquals("No se puede iniciar parking, ya se encuentra uno vigente", outContent.toString().trim());
	}
	
	@Test
	void cuandoEsEstadoVigenteAlFinalizarParkingInteractuaConLaAppYElSEMParaDarFin() {
		//setup
		when(appMock.getSistemaCentral()).thenReturn(sistemaCentralMock);
		//exercise 
		estadoVigente.finalizarParking(appMock);
		//verify
		verify(sistemaCentralMock, times(1)).finDeParking(appMock);
		verify(appMock, times(1)).setEstado(argThat(estado -> estado instanceof EstadoParkingNoVigente));
	}
	
	@Test
	void cuandoEsEstadoVigenteNoHaceNadaAlNotificarPosibleInicioDeParking() {
		//exercise 
		estadoVigente.notificarInicioParkingPosible(appMock);
		// no hay nada en especifico que verificar ya que el metodo invocado no hace nada.
		// es llamado para verficiar que no lanza exepciones.
	}
	
	@Test
	void cuandoEsEstadoVigenteNotificaLaPosibleFinalizacionDeParking() {
		//exercise
		estadoVigente.notificarFinParkingPosible(appMock);
		//verify
		verify(appMock, times(1)).notificarFinParkingPosible();
	}

}
