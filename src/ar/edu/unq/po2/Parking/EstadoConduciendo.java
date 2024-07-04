package ar.edu.unq.po2.Parking;

public class EstadoConduciendo implements EstadoSensor {

	@Override
	public void driving(UserApp app) {
		// No hace nada, este metodo se invoca de manera constante mientras el sensor este detectando que el desplazamiento es a vehiculo.

	}

	@Override
	public void walking(UserApp app) {
		app.getModo().walking(app);
		app.setEstadoSensor(new EstadoCaminando());
	}

}
