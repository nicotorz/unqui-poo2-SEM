package ar.edu.unq.po2.Parking;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;

class ModoAutomaticoTest {

	private ModoAutomatico modoAutomatico;
	private UserApp appMock;
	private EstadoParking estadoMock;
	private SEMSystem sistemaMock;
	
	@BeforeEach
	public void setup() {
		appMock = Mockito.mock(UserApp.class);
		modoAutomatico = new ModoAutomatico();
		estadoMock = Mockito.mock(EstadoParking.class);
		when(appMock.getEstado()).thenReturn(estadoMock);
		sistemaMock = Mockito.mock(SEMSystem.class);
	}
	
	@Test
	void unModoAutomaticoCuandoSeDetectaQueElDesplazamientoEsCaminadoSiEstaFueraDeUnaZonaDeEstacionamientoNoHaceNada() {
		//setup
		when(appMock.getSistemaCentral()).thenReturn(sistemaMock);
		when(sistemaMock.estaEnZonaDeEstacionamiento()).thenReturn(false);
		//exercise
		modoAutomatico.walking(appMock);
		//verify
		verify(appMock.getEstado(), times(0)).iniciarParking(appMock, appMock.getPatentePredeterminada());
	}
	
	@Test 
	void unModoManualCuandoSeDetectaQueElDesplazamientoEsCaminadoSiEstaDentroDeUnaZonaDeEstacionamientoNotficaAlUsuarioDeUnPosibleInicioDeParking() {
		//setup
		when(appMock.getSistemaCentral()).thenReturn(sistemaMock);
		when(sistemaMock.estaEnZonaDeEstacionamiento()).thenReturn(true);
		//exercise
		modoAutomatico.walking(appMock);
		//verify
		verify(appMock.getEstado(), times(1)).iniciarParking(appMock, appMock.getPatentePredeterminada());
	}
	
	@Test
	void unModoManualCuandoSeDetectaQueElDesplazamientoEsEnVehiculoSiEstaFueraDeUnaZonaDeEstacionamientoNoHaceNada() {
		//setup
		when(appMock.getSistemaCentral()).thenReturn(sistemaMock);
		when(sistemaMock.estaEnZonaDeEstacionamiento()).thenReturn(false);
		//exercise
		modoAutomatico.driving(appMock);
		//verify
		verify(appMock.getEstado(), times(0)).finalizarParking(appMock);
	}
	
	@Test 
	void unModoManualCuandoSeDetectaQueElDesplazamientoEsEnVehiculoSiEstaDentroDeUnaZonaDeEstacionamientoNotficaAlUsuarioDeUnPosibleFinDeParking() {
		//setup
		when(appMock.getSistemaCentral()).thenReturn(sistemaMock);
		when(sistemaMock.estaEnZonaDeEstacionamiento()).thenReturn(true);
		//exercise
		modoAutomatico.driving(appMock);
		//verify
		verify(appMock.getEstado(), times(1)).finalizarParking(appMock);
	}

}
