package ar.edu.unq.po2.Parking;

public class EstadoParkingNoVigente extends EstadoParking {

	@Override
	public void iniciarParking(UserApp app, String patente) {
		if (app.tieneSaldoMinimoParaEstacionamiento()) {
			app.getSistemaCentral().iniciarParking(app, patente);
			app.setEstado(new EstadoParkingVigente());
		}
		else {
			app.notificarSaldoInsuficiente();
		}
	}

	@Override
	public void finalizarParking(UserApp app) {
		System.out.println("No se puede finalizar el parking ya que no existen parkings vigentes");
	}
	
	@Override
	public void notificarInicioParkingPosible(UserApp app) {
		app.notificarInicioParkingPosible();
	}
	
}
