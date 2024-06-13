package ar.edu.unq.po2.Sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Zone.Zone;

class SEMSystemTest {

	SEMSystem sem;
	UserApp user;
	Parking parking;
	Parking parking2;
	Zone zone;
	Zone zone2;
	Infraccion infraccion;
	Inspector inspector;
	Inspector inspector2;
	String patente1;
	String patente2;
	String patente3;
	Clock clock;
	Entidad e1;
	Entidad e2;
	
	@BeforeEach
	void setUp() {
		user = new UserApp("22334455",155.5);
		e1 = mock(Entidad.class);
		e2 = mock(Entidad.class);
		clock = new Clock(LocalDateTime.of(2024, 6, 5, 20, 00));
		sem 	   = new SEMSystem(LocalDateTime.of(2024, 6, 5, 8, 00),LocalDateTime.of(2024, 6, 5, 20, 00),clock);
		patente1   = "patente1";
		patente2   = "patente2";
		patente3   = "patente3";
		parking    = new Parking(user,LocalDateTime.of(2024, 6, 5, 8, 00));
		parking2   = mock(Parking.class);
		zone 	   = new Zone("zone1");
		zone2 	   = mock(Zone.class);
		infraccion = mock(Infraccion.class);
		inspector  = mock(Inspector.class);
		inspector2  = mock(Inspector.class);
		parking.addPatente(patente1);
		parking.addPatente(patente2);
		parking.addPatente(patente3);
		sem.addParking(parking);
		sem.addParking(parking2);
		sem.addZone(zone);
		sem.addZone(zone2);
		sem.addUsuario("11223344", 150.5);
	}
	
	@Test
	void addZoneTest() {
		assertEquals(sem.getZones().size(),2);
	}
	
	@Test
	void addParkingTest() {
		assertEquals(sem.getParkings().size(),2);
	}
	
	@Test
	void addUsuarioTest() {
		assertEquals(sem.getUsuarios().size(),1);
	}
	
	@Test
	void removeUsuarioTest() {
		//Exercise
		sem.endUsuario("11223344");
		//Verify
		assertEquals(sem.getUsuarios().size(),0);
	}
	
	@Test
	void cantidadZoneTest() {
		assertEquals(sem.getZones().size(),2);
	}
	
	@Test
	void checkParkingTest() {
		assertTrue(sem.checkParking(patente1));
	}
	
	@Test
	void checkZoneTest() {
		assertTrue(sem.checkZone(zone));
	}
	
	@Test
	void issueFineTest() {
		//Exercise
		sem.issueFine(patente1, zone);
		//Verify
		assertEquals(sem.getInfracciones().size(),1);
	}
	
	@Test
	void checkearBalanceTest() {
		assertEquals(sem.consultarSaldo("11223344"),150.5);
	}
	
	@Test
	void eliminarUnParkingTest() {
		sem.endParking(parking);
		//Verify
		assertEquals(sem.getParkings().size(),1);
	}
	
	@Test
	void eliminarTodosLosParkingsTest() {
		sem.endAllParkings();
		//Verify
		assertEquals(sem.getParkings().size(),0);
	}
	
	@Test
	void agregandoUnInspectorTest() {
		sem.addInspector(inspector);
		sem.addInspector(inspector2);
		assertEquals(sem.getInspectors().size(),2);
	}
	
	@Test
	void timeStartTest() {
		assertEquals(sem.getStartTime(),LocalDateTime.of(2024, 6, 5, 8, 00));
	}
	
	@Test
	void timeEndTest() {
		assertEquals(sem.getEndTime(),LocalDateTime.of(2024, 6, 5, 20, 00));
	}
	
	@Test
	void recargarSaldoTest() {
		sem.recargarSaldo("11223344", 30.5);
		assertEquals(sem.consultarSaldo("11223344"),181.0);
	}
	
	@Test
	void suscribirUnaEntidadTest() {
		sem.suscribir(e1);
		assertEquals(sem.getEntidad().size(),1);
	}
	
	@Test
	void desuscribirUnaEntidadTest() {
		sem.suscribir(e2);
		sem.desuscribir(e2);
		assertEquals(sem.getEntidad().size(),0);
	}
	
	@Test
	void inicioDeEstacionamientoTest() {
		sem.addUsuario("22334455", 155.5);
		sem.inicioDeEstacionamiento(parking);
		assertEquals(sem.consultarSaldo("22334455"),155.5);
	}
	
	@Test
	void finDeEstacionamientoTest() {
		sem.finDeEstacionamiento(parking);
		assertEquals(sem.getParkings().size(),1);
	}
}
