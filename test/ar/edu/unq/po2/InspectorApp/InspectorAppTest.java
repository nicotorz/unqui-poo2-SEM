package ar.edu.unq.po2.InspectorApp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Inspector.InspectorApp;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Sem.SEMSystem;
import ar.edu.unq.po2.Zone.Zone;

class InspectorAppTest {

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
		
		parkingConInfraccion = mock(Parking.class);
		parkingSinInfraccion = mock(Parking.class);
		
		
		
	}
	
	
	@Test
	void testSeteoDeSem() {
		
		assertEquals(sem1, inspectorApp.getSem());
		
		// Otro sem
		
		sem2 = mock(SEMSystem.class);
		
		inspectorApp.setSem(sem2);
		
		assertEquals(sem2,inspectorApp.getSem());
		
		
	}

	
	@Test
	void testInspectorAppConsultaDeEstacionamiento() {
		
		inspectorApp.checkParking("FKH895");
		
		verify(sem1).checkParking("FKH895");
		
		
		
	}
	
	@Test
	void testInspectorAppHaceUnaInfraccion() {
		
		inspectorApp.issueFine("FKH895", zona1);
		
		verify(sem1).issueFine("FKH895", inspectorApp.getInspector().getZona(), inspector);
		
	}
	
	
	
}
