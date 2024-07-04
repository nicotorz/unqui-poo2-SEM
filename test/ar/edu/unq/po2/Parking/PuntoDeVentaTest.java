package ar.edu.unq.po2.Parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

class PuntoDeVentaTest {

	private SEMSystem centralSystem;
	private Zone quilmesCentro;
	private PuntoDeVenta kiosko;
	
	@BeforeEach
	public void setUp() {
		centralSystem = Mockito.mock(SEMSystem.class);
		quilmesCentro = Mockito.mock(Zone.class);
		kiosko = new PuntoDeVenta(centralSystem, quilmesCentro);
	}
	
	@Test
	void unPuntoDeVentaTieneAsociadoUnSEMSystem() {
		//assert
		assertEquals (kiosko.getSystem(), centralSystem);
	}
	
	@Test
	void unPuntoDeVentaConoceLaZonaEnLaQueEstaUbicado() {
		//assert
		assertEquals (kiosko.getZona(), quilmesCentro);
	}

	@Test
	void unPuntoDeVentaPuedeVenderHorasDeEstacionamientoAUnConductor() {
		//exercise
		kiosko.venderHorasDeParking("BO 012 CA", 6);
		//verify
		ArgumentCaptor<Parking> parkingCaptor = ArgumentCaptor.forClass(Parking.class);
		verify(centralSystem).addParking(parkingCaptor.capture());
	}
	
	@Test
	void unPuntoDeVentaCuandoVendeHorasDeEstacionamientoCreaUnaInstanciaDeParkingConLosDatosDados() {
		//exercise 
		kiosko.venderHorasDeParking("BO 012 CA", 6);
		//verify
		ArgumentCaptor<Parking> captor = ArgumentCaptor.forClass(Parking.class);
		verify(centralSystem).addParking(captor.capture());
		Parking parking = captor.getValue(); // Parking capturado
		assertNotNull(parking); // Se verifica que se haya creado el Parking
		assertEquals("BO 012 CA", parking.getPatente()); //Se verifica que la patente sea la del vehiculo correcto
		assertEquals(quilmesCentro, parking.getZona()); //Se verifica que la zona se haya seteado correctamente
		assertNotNull(parking.getHoraDeInicio()); // Se verifica que la fecha de entrada se haya establecido
	}
	
	@Test
	void unPuntoDeVentaSeComunicaConElSemParaIndicarleElCelularAlQueSeDebeCargarCredito() {
		//exercise
		kiosko.recargarSaldo(90.0, "1121746553");
		//verify
		verify(centralSystem).recargarSaldo(90.0, "1121746553");
	}
	
	@Test
	void enUnPuntoDeVentaElIDParaLaCompraEs0PorDefecto() {
		//assert
		assertEquals(kiosko.getId(), 0);
	}
	
	@Test 
	void enUnPuntoDeVentaSeAsignaIDsDiferentes() {
		//assert 
		assertEquals(kiosko.getId(), 0);
		//exercise
		kiosko.asignarId();
		int nuevoID = kiosko.asignarId();
		//assert
		assertEquals(nuevoID, 1);
	}
	
	@Test
	void cuandoElIDDelPuntoDeVentaLlegaA9999SeReseteaA0() {
		// setup
		kiosko.setId(9999); 
		//exercisie 
		kiosko.asignarId();
		int nuevoID = kiosko.asignarId();
		//assert
		assertEquals(nuevoID, 0);
	}
}
