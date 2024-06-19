package ar.edu.unq.po2.Sem;

import java.time.LocalTime;

public class Clock {
	
	private LocalTime currentTime;
	
	public Clock(LocalTime currentTime) {
		this.setCurrentTime(currentTime);
	}

	public LocalTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(LocalTime currentTime) {
		this.currentTime = currentTime;
	}
}
