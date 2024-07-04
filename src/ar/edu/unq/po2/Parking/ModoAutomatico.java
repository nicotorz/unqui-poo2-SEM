package ar.edu.unq.po2.Parking;

public class ModoAutomatico implements ModoDeOperacion {

	@Override
	public void driving(UserApp app) {
		if (app.getSistemaCentral().estaEnZonaDeEstacionamiento()) {
			app.getEstado().finalizarParking(app);
		}
	}

	@Override
	public void walking(UserApp app) {
		if (app.getSistemaCentral().estaEnZonaDeEstacionamiento()) {
			app.getEstado().iniciarParking(app, app.getPatentePredeterminada());
		}
	}

}
