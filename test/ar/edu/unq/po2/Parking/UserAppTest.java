package ar.edu.unq.po2.Parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import ar.edu.unq.po2.Parking.UserApp.Modo;
import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

class UserAppTest {
	
	private SEMSystem sistemaCentralMock;
	private Zone zonaMock;
	private UserApp app;
	private UserApp appSpy;
	
	@BeforeEach
	public void setUp() {
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		zonaMock = Mockito.mock(Zone.class);
		app = new UserApp("111234567", sistemaCentralMock, "BO 012 CA");
		appSpy = Mockito.spy(app);
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
	void unUserAppTSeIncializaConUnaPatentePredeterminada() {
		//assert 
		assertEquals(app.getPatentePredeterminada(), "BO 012 CA");
	}
	
	@Test
	void unUserAppPuedeCambiarDePatentePredeterminadaEnCualquierMomento() {
		//assert
		assertEquals(app.getPatentePredeterminada(), "BO 012 CA");
		app.cambiarPatentePredetermianda("BO 006 CA");
		assertEquals(app.getPatentePredeterminada(), "BO 006 CA");
	}
	
	@Test
	void unUserAppPorDefectoSeEncuentraEnModoManual() {
		//assert
		assertEquals(app.modo, Modo.MANUAL);
	}
	
	@Test
	void unUserAppPorDefectoNoTieneParkingsVigentes() {
		assertFalse(app.hayParkingVigente);
	}
	
	@Test
	void unUserAppPuedeConsultarSuSaldoMedianteElSistemaCentral() {
		//exercise
		app.consultarSaldo();
		//verify
		verify(sistemaCentralMock).consultarSaldo(app.getNumeroAsociado());
	}
	
	@Test
	void unUserAppPuedeIniciarEstacionamientosDeFormaManual() {
		//exercise 
		app.iniciarParking("BO 012 CA");
		//verify
		ArgumentCaptor<Parking> parking = ArgumentCaptor.forClass(Parking.class);
		verify(sistemaCentralMock).addParking(parking.capture());
	}
	
	@Test 
	void unUserAppCuandoInciaEstacionamientosInstanciaUnParkingConLosDatosDados() {
		//exercise
		app.iniciarParking("BO 012 CA");
		//verify
		ArgumentCaptor<Parking> captor = ArgumentCaptor.forClass(Parking.class);
		verify(sistemaCentralMock).addParking(captor.capture());
		Parking parking = captor.getValue(); // Parking capturado
		assertNotNull(parking); // Se verifica que se haya creado el Parking
		assertEquals("BO 012 CA", parking.getPatente()); //Se verifica que la patente sea la del vehiculo correcto
		assertEquals(null, parking.getZona()); //Se verifica que la zona se haya seteado correctamente
		assertNotNull(parking.getHoraDeInicio()); // Se verifica que la fecha de entrada se haya establecido
	}
	
	@Test
	void unUserAppPuedeFinalizarEstacionamientosDeFormaManual() {
		//exercise
		app.finalizarParking();
		//verify
		verify(sistemaCentralMock).endParking(app.getNumeroAsociado());
	}
	
	@Test
	void cuandoElSensorDeMovimientoDetectaQueElDesplazamientoSeRealizaCaminandoSiLaAppEstaEnModoManualNoHaceNada() {
		//exercise 
		appSpy.walking();
		//verify
		verify(appSpy, never()).iniciarParking(app.getPatentePredeterminada());
	}
	
	@Test 
	void cuandoElSensorDeMovimientoDetectaQueElDesplazamientoSeRealizaCaminandoSiLaAppEstaEnModoAutomaticoYHayParkingVigenteNoHaceNada() {
		//setUp
		appSpy.setModo(Modo.AUTOMATICO);
		appSpy.setHayParkingVigente(true);
		//exercise
		appSpy.walking();
		//verify
		verify(appSpy, never()).iniciarParking(app.getPatentePredeterminada());
	}
	
	@Test 
	void cuandoElSensorDeMovimientoDetectaQueElDesplazamientoSeRealizaCaminandoSiLaAppEstaEnModoAutomaticoIniciaElEstacionamieto() {
		//setUp
		appSpy.setModo(Modo.AUTOMATICO);
		//exercise
		appSpy.walking();
		//verify
		verify(appSpy).iniciarParking(app.getPatentePredeterminada());
	}
	
	@Test
	void cuandoElSensorDeMovimientoDetectaQueElDesplazamientoSeRealizaABordoDeUnVehiculoSiLaAppEstaEnModoManualNoHaceNada() {
		//exercise 
		appSpy.driving();
		//verify
		verify(appSpy, never()).iniciarParking(app.getPatentePredeterminada());
	}
	
	@Test
	void cuandoElSensorDeMovimientoDetectaQueElDesplazamientoSeRealizaABordoDeUnVehiculoSiLaAppEstaEnModoAutomaticoFinalizaElEstacionamiento() {
		//setUp
		appSpy.setModo(Modo.AUTOMATICO);
		//exercise
		appSpy.driving();
		//verify
		verify(appSpy).finalizarParking();
	}
}
