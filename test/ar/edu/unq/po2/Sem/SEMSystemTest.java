package ar.edu.unq.po2.Sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.ParkingViaApp;
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
		e1 = mock(Entidad.class);
		e2 = mock(Entidad.class);
		clock = new Clock(LocalTime.now());
		sem 	   = new SEMSystem(LocalTime.of(07, 00),LocalTime.of(20, 00),clock);
		sem.setPrecioPorHora(40D);
		patente1   = "patente1";
		patente2   = "patente2";
		patente3   = "patente3";
		user = new UserApp("22334455",sem,patente1);
		parking    = new ParkingViaApp(patente1,user);
		parking.setHoraDeInicio(LocalTime.of(8, 00));
		parking2   = mock(Parking.class);
		zone 	   = new Zone("zone1");
		zone2 	   = mock(Zone.class);
		infraccion = mock(Infraccion.class);
		inspector  = mock(Inspector.class);
		inspector2  = mock(Inspector.class);
		sem.addParking(parking);
		sem.addParking(parking2);
		sem.addZone(zone);
		sem.addZone(zone2);
		sem.addUsuario("11223344", 150.5);
		sem.addUserPatente(user.getNumeroAsociado(), patente1);
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
		sem.darAltaInfraccion(patente1, zone);
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
	void eliminarUnParkingMockTest() {
		sem.endParking(parking2);
		//Verify
		assertEquals(sem.getParkings().size(),1);
	}
	
	@Test
	void eliminarTodosLosParkingsTest() {
		clock.setCurrentTime(LocalTime.of(21, 00));
		//Mock
		when(parking2.getHoraDeInicio()).thenReturn(LocalTime.of(12, 00));
		//
		parking.setHoraDeInicio(LocalTime.of(13, 00));
		sem.finDeFranjaHoraria();
		
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
		assertEquals(sem.getStartTime(),LocalTime.of(7, 00));
	}
	
	@Test
	void timeEndTest() {
		assertEquals(sem.getEndTime(),LocalTime.of(20, 00));
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
		sem.iniciarParking(user,patente1);
		assertEquals(sem.consultarSaldo("22334455"),155.5);
	}
	
	@Test
	void finDeParkingTest() {
		//Mock
		when(parking2.getPatente()).thenReturn(patente1);
		when(parking2.getUserApp()).thenReturn(user);
		//
		
		sem.finDeParking(user);
		assertEquals(sem.getParkings().size(),1);
	}
}
