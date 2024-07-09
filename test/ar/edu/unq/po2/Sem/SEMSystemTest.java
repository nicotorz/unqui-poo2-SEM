package ar.edu.unq.po2.Sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.Infraccion.Infraccion;
import ar.edu.unq.po2.Inspector.Inspector;
import ar.edu.unq.po2.Parking.CompraDeEstacionamiento;
import ar.edu.unq.po2.Parking.Parking;
import ar.edu.unq.po2.Parking.ParkingViaApp;
import ar.edu.unq.po2.Parking.UserApp;
import ar.edu.unq.po2.Zone.Zone;

class SEMSystemTest {

	SEMSystem sem;
	UserApp user;
	UserApp user2;
	Parking parking;
	Parking parking2;
	Parking parking3;
	ParkingViaApp pVia;
	Zone zone;
	Zone zone2;
	Infraccion infraccion;
	Inspector inspector;
	Inspector inspector2;
	String patente1;
	String patente2;
	String patente3;
	NotifyEntidad e1;
	NotifyEntidad e2;
	CompraDeEstacionamiento compra;
	
	@BeforeEach
	void setUp() {
		//Patentes
		patente1   = "patente1";
		patente2   = "patente2";
		patente3   = "patente3";
		//
		sem 	   = new SEMSystem(LocalTime.of(07, 00),LocalTime.of(20, 00));
		e1 = mock(NotifyEntidad.class);
		e2 = mock(NotifyEntidad.class);
		user = mock(UserApp.class);
		user2 = mock(UserApp.class);
		parking    = mock(Parking.class);
		parking2   = mock(Parking.class);
		parking3   = mock(Parking.class);
		pVia	   = mock(ParkingViaApp.class);
		zone 	   = mock(Zone.class);
		zone2 	   = mock(Zone.class);
		infraccion = mock(Infraccion.class);
		inspector  = mock(Inspector.class);
		inspector2 = mock(Inspector.class);
		sem.setPrecioPorHora(40D);
		compra 	   = mock(CompraDeEstacionamiento.class);
		
		//user = new UserApp("22334455",sem,patente1)
		sem.addParking(parking);
		sem.addParking(parking2);
		sem.addParking(pVia);
		sem.addZone(zone);
		sem.addZone(zone2);
		sem.addUsuario("11223344", 150.5);
		sem.addUserPatente(user.getNumeroAsociado(), patente1);
	}
	
	@Test
	void cantidadDeParkingsTest() {
		assertEquals(sem.getParkings().size(),3);
	}
	
	@Test
	void cantidadDeUsuariosTest() {
		assertEquals(sem.getNroUsuarios().size(),1);
	}
	
	@Test
	void removeUsuarioTest() {
		//Exercise
		sem.endUsuario("11223344");
		//Verify
		assertEquals(sem.getNroUsuarios().size(),0);
	}
	
	@Test
	void cantidadZoneTest() {
		assertEquals(sem.getZones().size(),2);
	}
	
	@Test
	void checkParkingTest() {
		//Mock
		when(parking.getPatente()).thenReturn(patente1);
		when(parking2.getPatente()).thenReturn(patente2);
		when(pVia.getPatente()).thenReturn(patente3);
		//
		assertTrue(sem.checkParking(patente1));
	}
	
	@Test
	void checkZoneTest() {
		assertTrue(sem.checkZone(zone));
	}
	
	@Test
	void darDeAltaUnaInfraccionTest() {
		//Mock
		when(parking.getPatente()).thenReturn(patente1);
		//
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
		assertEquals(sem.getParkings().size(),2);
	}
	
	@Test
	void finDeFranjaHorariaTest() {
		//Mock
		when(parking2.getHoraDeInicio()).thenReturn(LocalTime.of(10, 00));
		when(parking.getHoraDeInicio()).thenReturn(LocalTime.of(9, 0));
		when(pVia.getHoraDeInicio()).thenReturn(LocalTime.of(8, 0));
		//
		
		sem.finDeFranjaHoraria();
		
		//Verify
		assertEquals(sem.getParkings().size(),3);
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
		sem.recargarSaldo(30.5,"11223344");
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
		when(parking.getPatente()).thenReturn(patente2);
		when(parking.getHoraDeInicio()).thenReturn(LocalTime.of(8, 0));
		when(parking2.getPatente()).thenReturn(patente1);
		when(parking2.getHoraDeInicio()).thenReturn(LocalTime.of(21, 0));
		when(pVia.getApp()).thenReturn(user);
		when(pVia.getHoraDeInicio()).thenReturn(LocalTime.of(9, 0));
		//
		
		sem.finDeParking(user);
		assertEquals(sem.getParkings().size(),2);
	}
	
	@Test
	void registrarUsuarioTest() {
		sem.registrarUsuario(user2);
		assertEquals(sem.getUsers().size(),1);
	}
	
	@Test
	void noExisteElUsuarioParaFinalizarTest() {
		//Mock
		when(user2.getNumeroAsociado()).thenReturn("15215121");
		//
		sem.endUsuario(user2.getNumeroAsociado());
		assertEquals(sem.getNroUsuarios().size(),1);
	}
	
	@Test
	void noExisteParkingParaFinalizarTest() {
		sem.endParking(parking3);
		assertEquals(sem.getParkings().size(),3);
	}
	
	@Test
	void noEsPosibleRecargarSaldoTest() {
		//Mock
		when(user2.getNumeroAsociado()).thenReturn("15215121");
		//
		
		sem.recargarSaldo(300D,user2.getNumeroAsociado());
		assertEquals(sem.consultarSaldo("15215121"),0.0);
		
	}
	
	@Test
	void estaEnZonaDeEstacionamientoTeste() {
		assertTrue(sem.estaEnZonaDeEstacionamiento());
	}
	
	@Test
	void registrarCompraDeEstacionamiento() {
		sem.registrarCompra(compra);
		assertEquals(sem.getComprasDeEstacionamiento().size(),1);
	}
	
}
