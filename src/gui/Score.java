package gui;

import render.Camera;
import shaders.Shader;

public class Score {
	private int number;
	private ImageRenderer imageRenderer;
	
	public Score(ImageRenderer imageRenderer) {
		number = 0;
		this.imageRenderer = imageRenderer;
	}
	
	public void updateScore(int n) {
		number = n;
	}
	
	public void renderScore(int x, int y, int scale, Shader shader, Camera camera) {
		if(number < 10) {
			imageRenderer.renderImage("backgroundnumbers/background" + number + ".png", x, y, scale, shader, camera);
		} else if(number < 100){
			imageRenderer.renderImage("backgroundnumbers/background" + (number / 10) + ".png", x, y, scale, shader, camera);
			imageRenderer.renderImage("backgroundnumbers/background" + (number % 10) + ".png", x + scale + (scale / 2) + (scale / 4), y, scale, shader, camera);
		} else {
			scale /= 3;
			scale *= 2;
			number %= 999;
			imageRenderer.renderImage("backgroundnumbers/background" + (number / 100) + ".png", x, y, scale, shader, camera);
			imageRenderer.renderImage("backgroundnumbers/background" + ((number / 10) % 10) + ".png", x + scale + (scale / 2) + (scale / 4), y, scale, shader, camera);
			imageRenderer.renderImage("backgroundnumbers/background" + (number % 10) + ".png", x + scale * 3 + (scale / 2) + (scale / 4), y, scale, shader, camera);
		}
	}
}
