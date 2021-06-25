package io;

import org.lwjgl.opengl.GL11;

public class FPSTimer {
	private double lastTime;
	private double unprocessedTime;
	private boolean canRender;
	private int frames;
	private double frameTime;
	private double frameCap;
	private int lastFPS;
	
	public FPSTimer(double frameCap) {
		lastTime = Timer.getTime();
		unprocessedTime = 0;
		canRender = false;
		frames = 0;
		lastFPS = 0;
		this.frameCap = frameCap;
	}
	
	public void update() {
		canRender = false;
		double currentTime = Timer.getTime();
		double timePassed = currentTime - lastTime;
		unprocessedTime += timePassed;
		frameTime += timePassed;
		lastTime = currentTime;
	}
	
	public boolean hasUnprocessedFrames() {
		if(unprocessedTime >= frameCap) {
			unprocessedTime -= frameCap;
			canRender = true;
			return true;
		}
		
		return false;
	}
	
	public int getFPS() {
		if(frameTime >= 1.0) {
			frameTime = 0;
			lastFPS = frames;
			frames = 0;
		}
		
		return lastFPS;
	}
	
	public boolean canRender() {
		if(canRender) {
			frames++;
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		}
		
		return canRender;
	}
}
