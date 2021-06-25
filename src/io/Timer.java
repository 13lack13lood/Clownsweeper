package io;

public class Timer {
	private double currentTime;
	private double lastTime;
	private int seconds;
	private double timePassed;
	private int duration;
	private double startTime;
	
	public Timer() {
		currentTime = getTime();
		lastTime = 0;
		seconds = 0;
		timePassed = 0;
		duration = 0;
		startTime = 0;
	}
	
	public void startTimer(int duration) {
		this.duration = duration;
		startTime = getTime();
	}
	
	public void update() {
		currentTime = getTime();
		timePassed += currentTime - lastTime;
		
		if(timePassed >= 1.0) {
			seconds++;
			timePassed = 0;
		}
		
		lastTime = currentTime;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public boolean timerEnded() {
		double delta = getTime() - startTime;
		return delta >= duration;
	}
	
	public void resetClock() {
		seconds = 0;
	}
	
	public static double getTime() {
		return (double) System.nanoTime() / 1000000000.0;
	}
}
