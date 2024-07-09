package ar.edu.unq.po2.Parking;

public class ModoAutomatico implements ModoDeOperacion {

	@Override
	public void driving(UserApp app) {
		if (app.estaEnZonaDeEstacionamientoMedido()) {
			app.getEstado().finalizarParking(app);
			System.out.println("Alerta: Se inicio el estacionamiento de forma automatica.");
		}
	}

	@Override
	public void walking(UserApp app) {
		if (app.estaEnZonaDeEstacionamientoMedido()) {
			app.getEstado().iniciarParking(app, app.getPatentePredeterminada());
			System.out.println("Alerta: Se finalizo el estacionamiento de forma automatica.");
		}
	}

}
