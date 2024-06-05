package ar.edu.unq.po2.Inspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

class InspectorTest {

	
	Inspector inspector;
	InspectorApp inspectorApp;
	
	SEMSystem sem1;
	SEMSystem sem2;
	
	Zone zona1;
	Zone zona2;
	
	Parking parkingConInfraccion;
	Parking parkingSinInfraccion;
	
	List<Parking> parkings;
	
	
	@BeforeEach
	void setUp() {
		
		sem1 = mock(SEMSystem.class);
		
		zona1 = new Zone("Zona 1");
		
		inspector = new Inspector("Gabriel", zona1);
		
		zona1.setInspector(inspector);
		
		inspectorApp = new InspectorApp(inspector, sem1);
		inspector.setApp(inspectorApp);
		
		
		parkingConInfraccion = mock(Parking.class);
		parkingSinInfraccion = mock(Parking.class);
		
		
		
	}
	
	
	
	
	
	@Test
	void testInspectorRecorreZonaYNoEncuentraEstacionamientoEnInfraccion() {
		
		parkings = Arrays.asList(parkingSinInfraccion);
		
		when(parkingSinInfraccion.getPatente()).thenReturn("GHJ-123");
		
		zona1.agregarParking(parkingSinInfraccion);
		
		when(sem1.checkParking("GHJ-123")).thenReturn(true);  // hay un parking vigente para esa patente
		
		inspector.recorrerEstacionamientosDeZona();
		
		verify(sem1, never()).issueFine("GHJ-123", inspector.getZona(), inspector); // nunca llamo a este metodo
		
		
	}
	

	
	@Test
	void testInspectorRecorreZonaYEncuentraEstacionamientoEnInfraccion() {
		
		parkings = Arrays.asList(parkingConInfraccion);
		
		when(parkingConInfraccion.getPatente()).thenReturn("GHJ-123");
		
		zona1.agregarParking(parkingConInfraccion);
		
		when(sem1.checkParking("GHJ-123")).thenReturn(false);  // no hay parking vigente para esa patente
		
		inspector.recorrerEstacionamientosDeZona();
		
		verify(sem1).issueFine("GHJ-123", inspector.getZona(), inspector); // llamo a este metodo

		
		
		
		
	}
	
	
	
	
	
	
	
	

}
