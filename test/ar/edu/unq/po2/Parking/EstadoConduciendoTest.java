package ar.edu.unq.po2.Parking;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EstadoConduciendoTest {

	private EstadoConduciendo estadoConduciendo;
	private UserApp appMock;
	private ModoDeOperacion modoMock;
	
	@BeforeEach
	public void setUp() {
		appMock = Mockito.mock(UserApp.class);
		modoMock = Mockito.mock(ModoDeOperacion.class);
		estadoConduciendo = new EstadoConduciendo();
	}
	
	@Test
	void cuandoUnEstadoDeSensorConduciendoDetectaQueElDesplazamientoEsAVehiculoNoHaceNada() {
		//exercise 
		estadoConduciendo.driving(appMock);
		//verify 
		verifyNoInteractions(appMock);
	}
	
	@Test
	void cuandoUnEstadoDeSensorConduciendoDetectaQueElDesplazamientoCambioACamiandoDelegaElTrabajoAlModo() {
		//setup
		when(appMock.getModo()).thenReturn(modoMock);
		//exercise
		estadoConduciendo.walking(appMock);
		//verify
		verify(modoMock, times(1)).walking(appMock);
	}
	
	@Test
	void cuandoUnEstadoDeSensorConduciendoDetectaQueElDesplazamientoCambioACamiandoCambiaElEstadoDelSensor() {
		//setup
		when(appMock.getModo()).thenReturn(modoMock);
		//exercise
		estadoConduciendo.walking(appMock);
		//verify
		 verify(appMock, times(1)).setEstadoSensor(any(EstadoCaminando.class));
	}

}
