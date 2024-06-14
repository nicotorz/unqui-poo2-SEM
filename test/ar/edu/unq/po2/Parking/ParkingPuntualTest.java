package ar.edu.unq.po2.Parking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

class ParkingPuntualTest {

	private UserApp appMock;
	private SEMSystem sistemaCentralMock;
	private ParkingPuntual parkingPuntual;
	private Zone zonaMock;
	
	@BeforeEach
	public void setUp() {
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		appMock = Mockito.mock(UserApp.class);
        zonaMock = Mockito.mock(Zone.class);
        parkingPuntual = new ParkingPuntual("BO 012 CA", 6);
        when(appMock.zonaActual()).thenReturn(zonaMock);
        when(appMock.getSistemaCentral()).thenReturn(sistemaCentralMock);
	}
	
	@Test
	void unParkingPuntualSabeCuantasHorasDeParkingPosee() {
		//assert
		assertEquals(6, parkingPuntual.calcularHorasMaximas());
	}
	
	@Test
	void unParkingPuntualTieneLaPatenteDelAutoAlCualHaceReferenciaElParking() {
		//assert
		assertEquals(parkingPuntual.getPatente(), "BO 012 CA");
	}
	
	@Test
	void aUnParkingViaAppSeLePuedeConsultarSobreLaHoraMaximaDeParkingSegunElSaldoDeLaAplicacionConLaCualColabora() {
		//setup
		LocalDateTime horaEsperada = parkingPuntual.getHoraDeInicio().plusHours(6);
		//assert
		assertEquals(parkingPuntual.calcularHoraDeFin(), horaEsperada);
	}

}
