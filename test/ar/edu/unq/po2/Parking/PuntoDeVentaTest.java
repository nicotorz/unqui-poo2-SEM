package ar.edu.unq.po2.Parking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zona;

class PuntoDeVentaTest {

	private SEMSystem centralSystem;
	private Zona quilmesCentro;
	private PuntoDeVenta kiosko;
	private CompraPuntual mockPurchaseType;
	@BeforeEach
	public void setUp() {
		centralSystem = Mockito.mock(SEMSystem.class);
		quilmesCentro = Mockito.mock(Zona.class);
		kiosko = new PuntoDeVenta(centralSystem, quilmesCentro);
        mockPurchaseType = Mockito.mock(CompraPuntual.class);
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
	void cuandoUnPuntoDeVentaVendeHorasDeEstacionamientoLeIndicaLaPatenteProporcionadaPorElConductorAlProcesoDeCompra() {
		//setup
		kiosko.setTipoDeCompra(mockPurchaseType);
		//exercise
		kiosko.venderHorasDeParking("BO 012 CA", 6);
		//
		verify(mockPurchaseType).setPatente("BO 012 CA");
	}
	@Test
	void cuandoUnPuntoDeVentaVendeHorasDeEstacionamientoLeIndicaLasHorasSolicitadasPorElCompradorAlProcesoDeCompra() {
		//setup
		kiosko.setTipoDeCompra(mockPurchaseType);
		//exercise
		kiosko.venderHorasDeParking("BO 012 CA", 6);
		//verify
		verify(mockPurchaseType).setHorasCompradas(6);
	}
	@Test
	void cuandoUnPuntoDeVentaVendeHorasDeEstacionamientoLeIndicaLaPatenteAdministradaPorElCompradorAlProcesoDeCompra() {
		//setup
		kiosko.setTipoDeCompra(mockPurchaseType);
		//exercise
		kiosko.venderHorasDeParking("BO 012 CA", 6);
		//verify
		verify(mockPurchaseType).setPatente("BO 012 CA");
	}
	@Test
	void unPuntoDeVentaPuedeVenderHorasDeEstacionamientoAUnConductor() {
		//setup
		kiosko.setTipoDeCompra(mockPurchaseType);
		//exercise
		kiosko.venderHorasDeParking("BO 012 CA", 6);
		//verify
		verify(mockPurchaseType).procesarCompra();
	}
	@Test
	void unPuntoDeVentaSeComunicaConElSemParaIndicarleElCelularAlQueSeDebeCargarCredito() {
		//exercise
		kiosko.recargarSaldo("1121746553");
		//verify
		verify(centralSystem).recargarSaldo("1121746553");
	}

}
