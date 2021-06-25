package io;

import org.lwjgl.glfw.GLFW;

import tools.Tool;

public class Input {
	private long window;
	
	private boolean[] keys;
	private boolean[] mouse;
	
	public Input(long window) {
		this.window = window;
		keys = new boolean[GLFW.GLFW_KEY_LAST];
		mouse = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
		
		for(int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
			keys[i] = false;
		}
	}
	
	public void update() {
		for(int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
			keys[i] = isKeyDown(i);
		}
		
		for(int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
			mouse[i] = isMouseDown(i);
		}
	}
	
	public boolean isKeyDown(int key) {
		try {
			if(GLFW.glfwGetKey(window, key) == 1) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}
	
	public boolean isKeyPressed(int key) {
		if(isKeyDown(key) && !keys[key]) {
			System.out.println(true);
			return true;
		}
		
		return false;
	}
	
	public boolean isKeyReleased(int key) {
		return !isKeyDown(key) && keys[key];
	}
	
	public boolean isMousePressed(int button) {
		return isMouseDown(button) && !mouse[button];
	}
	
	public boolean isMouseDown(int button) {
		try {
			if(GLFW.glfwGetMouseButton(window, button) == 1) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
	}
	
	public int mouseX() {
		double[] posx = new double[1];
		GLFW.glfwGetCursorPos(window, posx, null);
		return (int) Math.round(posx[0]);
	}
	
	public int mouseY() {
		double[] posy = new double[1];
		GLFW.glfwGetCursorPos(window, null, posy);
		return (int) Math.round(posy[0]);
	}
	
	public int mouseSquareX() {
		return Math.abs(mouseX() / Tool.SQUARE_WIDTH);
	}
	
	public int mouseSquareY() {
		return Math.abs(mouseY() / Tool.SQUARE_WIDTH);
	}
	
	public boolean isCursorOutsideWindow() {
		return mouseX() < 0 || mouseX() > Tool.WIDTH || mouseY() < 0 || mouseY() > Tool.HEIGHT;
	}
	
	public boolean isCursorOutsideWorld() {
		return mouseX() < 0 || mouseX() > Tool.HEIGHT - (Tool.SCALE / 2) || mouseY() < 0 || mouseY() > Tool.HEIGHT - (Tool.SCALE / 2);
	}
}
