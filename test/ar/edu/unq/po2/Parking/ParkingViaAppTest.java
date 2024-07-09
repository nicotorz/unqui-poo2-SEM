package ar.edu.unq.po2.Parking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

class ParkingViaAppTest {
	
	private UserApp appMock;
	private SEMSystem sistemaCentralMock;
	private ParkingViaApp parkingPorApp;
	private Zone zonaMock;
	
	@BeforeEach
	public void setUp() {
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		appMock = Mockito.mock(UserApp.class);
        zonaMock = Mockito.mock(Zone.class);
        parkingPorApp = new ParkingViaApp("BO 012 CA", appMock);
        when(appMock.zonaActual()).thenReturn(zonaMock);
        when(appMock.getSistemaCentral()).thenReturn(sistemaCentralMock);
	}
	
	@Test
	void unParkingViaAppConoceLaAplicacionConLaCualColabora() {
		//assert
		assertEquals(parkingPorApp.getApp(), appMock);
	}
	
	@Test
	void unParkingViaAppTieneUnaPatentePredeterminada() {
		//assert
		assertEquals(parkingPorApp.getPatente(), "BO 012 CA");
	}
	
	@Test 
	void unParkingViaAppSabeCuantasHorasMaximasDeParkingTieneSegunElSaldoDeLaAplicacionConLaCualColabora() {
		//setup
		when(appMock.consultarSaldo()).thenReturn(90.00);
		//assert
		assertEquals(parkingPorApp.calcularHorasMaximas(), 2);
	}
	
	@Test
	void aUnParkingViaAppSeLePuedeConsultarSobreLaHoraMaximaDeParkingSegunElSaldoDeLaAplicacionConLaCualColabora() {
		//setup
		when(appMock.consultarSaldo()).thenReturn(90.00);
		LocalTime horaEsperada = parkingPorApp.getHoraDeInicio().plusHours(2);
		//assert
		assertEquals(parkingPorApp.calcularHoraDeFin(), horaEsperada);
	}
}