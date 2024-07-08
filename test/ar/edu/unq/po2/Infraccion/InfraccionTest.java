package ar.edu.unq.po2.Infraccion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Zone.Zone;
 
class InfraccionTest {

	Infraccion infraccion;
	Inspector inspector1;
	Inspector inspector2;
	
	LocalDateTime fecha1;
	LocalDateTime fecha2;
	
	Zone zona1;
	Zone zona2;
	
	@BeforeEach
	void setUp() {
		
		inspector1 = mock(Inspector.class);
		zona1 = mock(Zone.class);
		
		fecha1 = LocalDateTime.of(2024, 6, 5, 8, 20);
		
		infraccion = new Infraccion("ABC123", inspector1, fecha1, zona1);

		
		
	} 
	
	
	
	@Test
	void testSeteoInspector() {
		
		assertEquals(inspector1, infraccion.getInspector());	
		
		
		// Cambio de inspector
		
		inspector2 = mock(Inspector.class);
		
		infraccion.setInspector(inspector2);
		
		assertEquals(inspector2, infraccion.getInspector());	
		
		
		
	}
	
	@Test
	void testSeteoPatente() {
		
		assertEquals("ABC123", infraccion.getPatente());
		
		// Otra patente
		
		infraccion.setPatente("GHF587");
		
		assertEquals(infraccion.getPatente(), "GHF587");;
		
		
	}
	
	@Test
	void testSeteoFecha() {
		
		assertEquals(fecha1, infraccion.getFechaYHora());
		
		// Otra fecha
		
		fecha2 =  LocalDateTime.of(2023, 12, 28, 8, 20);
		
		infraccion.setFechaYHora(fecha2);
		
		assertEquals(fecha2, infraccion.getFechaYHora());
		
		
	}
	
	@Test
	void testSeteoZona() {
	
		assertEquals(zona1, infraccion.getZona());
	
		// Otra zona
		
		zona2 = mock(Zone.class);
		
		infraccion.setZona(zona2);
		
		assertEquals(zona2, infraccion.getZona());
		
	
	
	}
	
	
	
	
	
	
	
	

}
