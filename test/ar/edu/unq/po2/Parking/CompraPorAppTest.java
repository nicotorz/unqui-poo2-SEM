package ar.edu.unq.po2.Parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zona;


class CompraPorAppTest {
	
	private UserApp appMock;
	private SEMSystem sistemaCentralMock;
	private CompraPorApp compraPorApp;
	private Zona zonaMock;
	
	@BeforeEach
	public void setUp() {
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		appMock = Mockito.mock(UserApp.class);
        zonaMock = Mockito.mock(Zona.class);
        compraPorApp = new CompraPorApp(appMock);
        when(appMock.zonaActual()).thenReturn(zonaMock);
        when(appMock.getSistemaCentral()).thenReturn(sistemaCentralMock);
	}
	@Test
	void unaCompraPorAppConoceLaAplicacionConLaCualColabora() {
		//assert
		assertEquals(compraPorApp.getApp(), appMock);
	}
	@Test
	void unaCompraPorAppNoValidaLaCompraAUsuariosQueTienenMenosDe40DeSaldo() {
		//setup
		compraPorApp.setPatente("BO 012 CA");
		//assert
		 try {
				compraPorApp.validarCompra();
				fail("Se esperaba una excepción IllegalArgumentException");
		 	} catch (IllegalArgumentException e) {
			         assertEquals("Insuficiente saldo para procesar la compra", e.getMessage());
		 }
	}
	
	@Test
	void unaCompraPorAppCuandoRegistraComprasDeEstacionamientoCreaUnaInstanciaDeParkingConLosDatosDados() {
		//setup
		when(appMock.getSaldo()).thenReturn(80.0);
		compraPorApp.setPatente("BO 012 CA");
		//exercise 
		compraPorApp.registrarCompra();
		//verify
		ArgumentCaptor<Parking> captor = ArgumentCaptor.forClass(Parking.class);
		verify(sistemaCentralMock).addParking(captor.capture());
		Parking parking = captor.getValue(); // Parking capturado
		assertNotNull(parking); // Se verifica que se haya creado el Parking
		assertEquals(compraPorApp.getPatente(), parking.getPatente()); // Se verifica que la patente sea la esperada
		assertEquals("BO 012 CA", parking.getPatente()); //Se verifica que la patente sea la del vehiculo correcto
		assertEquals(zonaMock, parking.getZona()); //Se verifica que la zona se haya seteado correctamente
		assertNotNull(parking.getHoraInicio()); // Se verifica que la fecha de entrada se haya establecido
	}
	@Test
	void unaCompraPorAppCuandoRegistraComprasDeEstacionamientoSeComunicaConElSistemaCentralParaRegistrarElParking() {
		//setup
		compraPorApp.setPatente("BO 012 CA");
		//exercise 
		compraPorApp.registrarCompra();
		//verify
		ArgumentCaptor<Parking> parkingCaptor = ArgumentCaptor.forClass(Parking.class);
		verify(sistemaCentralMock).addParking(parkingCaptor.capture());
	}
	
	@Test
	void unaCompraPorAppCuandoProcesaUnaCompraVerificaYLuegoRegistraLaCompra() {
		//setup 
		CompraPorApp compraPorAppSpy = Mockito.spy(new CompraPorApp(appMock));
		compraPorAppSpy.setPatente("BO 012 CA");
		when(appMock.getSaldo()).thenReturn(80.0);
		//exercise 
		compraPorAppSpy.procesarCompra();
		//verify
		 verify(compraPorAppSpy, times(1)).validarCompra();
	     verify(compraPorAppSpy, times(1)).registrarCompra();
	}
}
