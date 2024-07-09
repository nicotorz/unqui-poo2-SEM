package ar.edu.unq.po2.Parking;

public class EstadoParkingVigente extends EstadoParking {

	@Override
	public void iniciarParking(UserApp app, String Patente) {
		System.out.println("No se puede iniciar parking, ya se encuentra uno vigente");
	}

	@Override 
	public void finalizarParking(UserApp app) {
		app.getSistemaCentral().endParking(app.getNumeroAsociado());;	
		app.setEstado(new EstadoParkingNoVigente()); 
	}
	
	@Override
	public void notificarFinParkingPosible(UserApp app) {
		app.notificarFinParkingPosible();
	}
}
