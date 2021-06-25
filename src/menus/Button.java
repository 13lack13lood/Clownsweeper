package menus;

import gui.ImageRenderer;
import render.Camera;
import shaders.Shader;
import tools.Tool;

public class Button {
	private int x;
	private int y;
	private int width;
	private int height;
	private String button;
	private String buttonHovered;
	
	public Button(int[] coords, String button) {
		this.x = coords[0];
		this.y = coords[1];
		this.width = coords[2];
		this.height = coords[3];
		this.button = button;
		this.buttonHovered = button.substring(0, button.indexOf(".png")) + "Hovered.png";
	}
	
	public void renderButton(int mouseX, int mouseY, ImageRenderer imageRenderer, Shader shader, Camera camera) {
		if(buttonHovered(mouseX, mouseY)) {
			imageRenderer.renderImage(buttonHovered, x, y, width, height, shader, camera);
		} else {
			imageRenderer.renderImage(button, x, y, width, height, shader, camera);
		}
	}
	
	public boolean buttonHovered(int mouseX, int mouseY) {
		return mouseX > convertCoords(x - width, true) && mouseX < convertCoords(x + width, true) && mouseY > convertCoords(-y - height, false) && mouseY < convertCoords(-y + height, false);
	}
	
	private int convertCoords(int n, boolean isX) {
		if(isX)
			return (Tool.WIDTH / 2) + n;
		else
			return (Tool.HEIGHT / 2) + n;
	}
}
