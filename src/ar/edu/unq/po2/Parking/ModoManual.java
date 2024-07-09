package ar.edu.unq.po2.Parking;

public class ModoManual implements ModoDeOperacion {

	@Override
	public void driving (UserApp app) {
		if (app.estaEnZonaDeEstacionamientoMedido() && app.notificacionesActivas()) {
			app.getEstado().notificarFinParkingPosible(app);
		}
	}

	@Override
	public void walking (UserApp app) {
		if (app.estaEnZonaDeEstacionamientoMedido() && app.notificacionesActivas()) {
			app.getEstado().notificarInicioParkingPosible(app);
		}
	}

}
