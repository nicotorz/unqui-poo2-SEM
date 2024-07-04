package ar.edu.unq.po2.Parking;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EstadoCaminandoTest {

	private EstadoCaminando estadoCaminando;
	private UserApp appMock;
	private ModoDeOperacion modoMock;
	
	@BeforeEach
	public void setUp() {
		appMock = Mockito.mock(UserApp.class);
		modoMock = Mockito.mock(ModoDeOperacion.class);
		estadoCaminando = new EstadoCaminando();
	}
	
	@Test
	void cuandoUnEstadoDeSensorConduciendoDetectaQueElDesplazamientoEsCaminandoNoHaceNada() {
		//exercise 
		estadoCaminando.walking(appMock);
		//verify 
		verifyNoInteractions(appMock);
	}
	
	@Test
	void cuandoUnEstadoDeSensorConduciendoDetectaQueElDesplazamientoCambioAVehiculoDelegaElTrabajoAlModo() {
		//setup
		when(appMock.getModo()).thenReturn(modoMock);
		//exercise
		estadoCaminando.driving(appMock);
		//verify
		verify(modoMock, times(1)).driving(appMock);
	}
	
	@Test
	void cuandoUnEstadoDeSensorConduciendoDetectaQueElDesplazamientoCambioACamiandoCambiaElEstadoDelSensor() {
		//setup
		when(appMock.getModo()).thenReturn(modoMock);
		//exercise
		estadoCaminando.driving(appMock);
		//verify
		 verify(appMock, times(1)).setEstadoSensor(any(EstadoConduciendo.class));
	}

}