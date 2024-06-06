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

class CompraPuntualTest {

	private CompraPuntual compraPuntual;
	private PuntoDeVenta puntoDeVentaMock;
	private SEMSystem sistemaCentralMock;
	private Zona zonaMock;
	
	@BeforeEach
	public void setUp() {
		sistemaCentralMock = Mockito.mock(SEMSystem.class);
		puntoDeVentaMock = Mockito.mock(PuntoDeVenta.class);
		zonaMock = Mockito.mock(Zona.class);
		when(puntoDeVentaMock.getZona()).thenReturn(zonaMock);
        when(puntoDeVentaMock.getSystem()).thenReturn(sistemaCentralMock);
        compraPuntual = new CompraPuntual(puntoDeVentaMock);
	}
	@Test
	void unaCompraPuntualConoceAlPuntoDeVentaConElQueColabora() {
		//assert
		assertEquals(compraPuntual.getPuntoDeVenta(), puntoDeVentaMock);
	}
	@Test
	void unaCompraPuntualNoPuedeValidarComprasDeMenoresOIgualesA0() {
		//setup
		compraPuntual.setHorasCompradas(0);
		compraPuntual.setPatente("BO 012 CA");
		//assert
		 try {
	        compraPuntual.validarCompra();
	         fail("Se esperaba una excepción IllegalArgumentException");
	     } catch (IllegalArgumentException e) {
	         assertEquals("Datos de compra puntual inválidos", e.getMessage());
	       }
	}
	@Test
	void unaCompraPuntualNoPuedeValidarComprasQueNoTenganUnPatenteRelacionada() {
		//setup
		compraPuntual.setHorasCompradas(6);
		//assert 
		try {
	        compraPuntual.validarCompra();
	         fail("Se esperaba una excepción IllegalArgumentException");
	     } catch (IllegalArgumentException e) {
	         assertEquals("Datos de compra puntual inválidos", e.getMessage());
	       }
	}
	
	@Test
	void unaCompraPuntualCuandoResgistraUnaCompraDeEstacionamientoCreaUnaInstanciaDeParkingConLosDatosDados() {	
		//setup
		compraPuntual.setPatente("BO 012 CA");
		compraPuntual.setHorasCompradas(6);
		//exercise
		compraPuntual.registrarCompra();
		//verify
		ArgumentCaptor<Parking> captor = ArgumentCaptor.forClass(Parking.class);
		verify(sistemaCentralMock).addParking(captor.capture());
        Parking parking = captor.getValue(); // Parking capturado
        assertNotNull(parking); // Se verifica que se haya creado el Parking
        assertEquals(compraPuntual.getPatente(), parking.getPatente()); // Se verifica que la patente sea la esperada
        assertEquals("BO 012 CA", parking.getPatente()); //Se verifica que la patente sea la del vehiculo correcto
        assertEquals(zonaMock, parking.getZona()); //Se verifica que la zona se haya seteado correctamente
        assertNotNull(parking.getHoraInicio()); // Se verifica que la fecha de entrada se haya establecido
	}
	@Test
	void unaCompraPuntualCuandoRegistraUnaCompraDeEstacionamientoSeComunicaConElSistemaCentralParaRegistrarElParking() {
		//setup
		String patente = "BO 012 CA"; 
        compraPuntual.setPatente(patente);
        //exercise 
        compraPuntual.registrarCompra();
        //verify
        ArgumentCaptor<Parking> parkingCaptor = ArgumentCaptor.forClass(Parking.class);
        verify(sistemaCentralMock).addParking(parkingCaptor.capture());
	}
	@Test
	void unaCompraPuntualCuandoProcesaUnaCompraVerificaYLuegoRegistraLaCompra() {
		//setup 
		CompraPuntual compraPuntualSpy = Mockito.spy(new CompraPuntual(puntoDeVentaMock));
		compraPuntualSpy.setPatente("BO 012 CA");
		compraPuntualSpy.setHorasCompradas(6);
		//exercise 
		compraPuntualSpy.procesarCompra();
		//verify
		 verify(compraPuntualSpy, times(1)).validarCompra();
	     verify(compraPuntualSpy, times(1)).registrarCompra();
	}
	
}
