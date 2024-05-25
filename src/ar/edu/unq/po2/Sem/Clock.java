package ar.edu.unq.po2.Sem;

import java.time.LocalDateTime;

public class Clock {
	private LocalDateTime currentTime;
	
	public Clock(LocalDateTime currentTime) {
		this.setCurrentTime(currentTime);
	}

	public LocalDateTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}
}
