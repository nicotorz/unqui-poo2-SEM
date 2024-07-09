package ar.edu.unq.po2.Parking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CompraDeEstacionamientoTest {
	
	private CompraDeEstacionamiento compraTest;
	private PuntoDeVenta puntoDeVentaMock;
	private LocalDate dia;
	private LocalTime hora;
	
	@BeforeEach
	public void setup() {
		dia = LocalDate.of(2023, Month.JUNE, 15);
		hora = LocalTime.of(14, 30, 15);
		puntoDeVentaMock = Mockito.mock(PuntoDeVenta.class);
		compraTest = new CompraDeEstacionamiento(1, puntoDeVentaMock, dia, hora, 400.00);
	}
	
	@Test
	void unaCompraDeEstacionamientoTieneSuId() {
		//assert
		assertEquals(compraTest.getId(), 1);
	}
	
	@Test
	void unaCompraDeEstacionameintoTieneAsociadoAlPuntoDeVentaDesdeElCualSeRealizoLaCompra() {
		//assert
		assertEquals(compraTest.getPuntoDeVenta(), puntoDeVentaMock);
	}
	
	@Test
	void deUnaCompraDeEstacionamientoSeSabeElDiaQueFueRealizado() {
		//assert
		assertEquals(compraTest.getFecha(), dia);
	}
	
	@Test
	void deUnaCompraDeEstacionamientoSeSabeLaHoraQueFueRealizado() {
		//assert
		assertEquals(compraTest.getHora(), hora);
	}
	
	@Test
	void deUnaCompraDeEstacionamientoSeSabeElMontoDeLaCompra() {
		//assert
		assertEquals(compraTest.getMonto(), 400.00);
	}

}
