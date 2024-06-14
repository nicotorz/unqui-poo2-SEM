package ar.edu.unq.po2.Parking;

public abstract class EstadoParking {
	
	public abstract void iniciarParking(UserApp app, String patente);
	public abstract void finalizarParking(UserApp app);
	
	public void notificarInicioParkingPosible(UserApp app) {
		
	}
	
	public void notificarFinParkingPosible(UserApp app) {
		
	}
	
}
