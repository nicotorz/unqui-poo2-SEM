package ar.edu.unq.po2.Zone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.PuntoDeVenta;

 
class ZonaTest {

	Zone zona;
	
	Inspector insepctor1;
	Inspector insepctor2;
	
	
	PuntoDeVenta puntoDeVenta;
	Parking parking;
	
	
	
	@BeforeEach
	void setUp() {
		
	
		
		parking = mock(Parking.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
		
		zona = new Zone();
		insepctor1 = new Inspector("Gabriel", zona);
		
		zona.setInspector(insepctor1); 
		
		
	}
	
	
	@Test
	void testSeteoDeNombreDeZona() {
		
		
		
	}
	
	
	@Test
	void testSeteoInspectorEnZona() {
		
		assertEquals(insepctor1, zona.getInspector());
		
		insepctor2 = new Inspector("Dario", zona);
		
		zona.setInspector(insepctor2); // cambio de inspector
		
		assertEquals(insepctor2, zona.getInspector());
		
	
	}
	
	@Test
	void testAgregoPuntoDeVentaEnZona() {
	
		assertEquals(zona.getListaPuntosDeVenta().size(), 0);
		
		zona.agregarPuntoDeVenta(puntoDeVenta);
		
		assertEquals(zona.getListaPuntosDeVenta().size(), 1);
	
	
	}
	
	@Test
	void testAgregoParkingEnZona() {
		
		assertEquals(zona.getEstacionamientos().size(), 0);
		
		zona.agregarParking(parking);
		
		assertEquals(zona.getEstacionamientos().size(),1);
		
		
	}
	
	
	@Test
	void testEliminoPuntoDeVentaEnZona() {
	
		assertEquals(zona.getListaPuntosDeVenta().size(), 0);
		
		zona.agregarPuntoDeVenta(puntoDeVenta);
		
		assertEquals(zona.getListaPuntosDeVenta().size(), 1);
		
		zona.removerPuntoDeVenta(puntoDeVenta);
		
		assertEquals(zona.getEstacionamientos().size(), 0);
		
		
		
	}
	
	@Test
	void testEliminoParkingEnZona() {
		
		assertEquals(zona.getEstacionamientos().size(), 0);
		
		zona.agregarParking(parking);
		
		assertEquals(zona.getEstacionamientos().size(), 1);
		
		zona.removerParking(parking);
		
		assertEquals(zona.getEstacionamientos().size(), 0);
		
		
		
	}
	
	
	

}
