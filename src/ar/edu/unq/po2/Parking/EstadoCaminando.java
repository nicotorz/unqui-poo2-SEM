package ar.edu.unq.po2.Parking;

public class EstadoCaminando implements EstadoSensor {

	@Override
	public void driving(UserApp app) {
		
		app.getModo().driving(app);
		app.setEstadoSensor(new EstadoConduciendo());
	}

	@Override
	public void walking(UserApp app) {
		// No hace nada, este metodo se invoca de manera constante mientras el sensor este detectando que el desplazamiento es camiando.

	}

}
