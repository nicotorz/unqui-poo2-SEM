package ar.edu.unq.po2.Parking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;

class ModoManualTest {
	
	private ModoManual modoManual;
	private UserApp appMock;
	private EstadoParking estadoMock;
	private SEMSystem sistemaMock;
	
	@BeforeEach
	public void setup() {
		sistemaMock = Mockito.mock(SEMSystem.class);
		appMock = Mockito.mock(UserApp.class);
		modoManual = new ModoManual();
		estadoMock = Mockito.mock(EstadoParking.class);
		when(appMock.getEstado()).thenReturn(estadoMock);
	}
	
	@Test
	void unModoManualCuandoSeDetectaQueElDesplazamientoEsCaminadoSiEstaFueraDeUnaZonaDeEstacionamientoNoHaceNada() {
		//setup
		when(appMock.getSistemaCentral()).thenReturn(sistemaMock);
		when(sistemaMock.estaEnZonaDeEstacionamiento()).thenReturn(false);
		//exercise
		modoManual.walking(appMock);
		//verify
		verify(appMock.getEstado(), times(0)).notificarInicioParkingPosible(appMock);
	}
	
	@Test 
	void unModoManualCuandoSeDetectaQueElDesplazamientoEsCaminadoSiEstaDentroDeUnaZonaDeEstacionamientoNotficaAlUsuarioDeUnPosibleInicioDeParking() {
		//setup
		when(appMock.estaEnZonaDeEstacionamientoMedido()).thenReturn(true);
		when(appMock.notificacionesActivas()).thenReturn(true);
		//exercise 
		modoManual.walking(appMock);
		//verify
		verify(appMock.getEstado(), times(1)).notificarInicioParkingPosible(appMock);
	}
	
	@Test
	void unModoManualCuandoSeDetectaQueElDesplazamientoEsEnVehiculoSiEstaFueraDeUnaZonaDeEstacionamientoNoHaceNada() {
		//setup
		when(appMock.getSistemaCentral()).thenReturn(sistemaMock);
		when(sistemaMock.estaEnZonaDeEstacionamiento()).thenReturn(false);
		//exercise
		modoManual.driving(appMock);
		//verify
		verify(appMock.getEstado(), times(0)).notificarFinParkingPosible(appMock);
	}
	
	@Test 
	void unModoManualCuandoSeDetectaQueElDesplazamientoEsEnVehiculoSiEstaDentroDeUnaZonaDeEstacionamientoNotficaAlUsuarioDeUnPosibleFinDeParking() {
		//setup
		when(appMock.estaEnZonaDeEstacionamientoMedido()).thenReturn(true);
		when(appMock.notificacionesActivas()).thenReturn(true);
		//exercise 
		modoManual.driving(appMock);
		//verify
		verify(appMock.getEstado(), times(1)).notificarFinParkingPosible(appMock);
	}
	
	
	
}
