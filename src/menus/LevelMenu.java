package menus;

import gui.ImageRenderer;
import io.Input;
import render.Camera;
import shaders.Shader;
import tools.Tool;
import world.World;

public class LevelMenu {
	public Button normal;
	public Button hard;
	public Button extreme;
	public Button back;
	
	public LevelMenu(Button normal, Button hard, Button extreme, Button back) {
		this.normal = normal;
		this.hard = hard;
		this.extreme = extreme;
		this.back = back;
	}
	
	public void render(ImageRenderer imageRenderer, int mouseX, int mouseY, Shader shader, Camera camera) {
		normal.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
		hard.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
		extreme.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
		back.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
	}
	
	public int switchMenu(Input input, World world) {
		if(input.isMousePressed(Tool.MOUSE_LEFT) && normal.buttonHovered(input.mouseX(), input.mouseY())) {
			Tool.SCALE = Tool.NORMAL_SCALE;
			Tool.updateValues();
			
			world.setWorldDifficulty(Tool.WORLD_SQUARES, Tool.SCALE);
			world.startWorld();
			return Tool.GAME_MENU;
		} else if(input.isMousePressed(Tool.MOUSE_LEFT) && hard.buttonHovered(input.mouseX(), input.mouseY())) {
			Tool.SCALE = Tool.HARD_SCALE;
			Tool.updateValues();

			world.setWorldDifficulty(Tool.WORLD_SQUARES, Tool.SCALE);
			world.startWorld();
			return Tool.GAME_MENU;
		} else if(input.isMousePressed(Tool.MOUSE_LEFT) && extreme.buttonHovered(input.mouseX(), input.mouseY())) {
			Tool.SCALE = Tool.EXTREME_SCALE;
			Tool.updateValues();

			world.setWorldDifficulty(Tool.WORLD_SQUARES, Tool.SCALE);
			world.startWorld();
			return Tool.GAME_MENU;
		} else if(input.isMousePressed(Tool.MOUSE_LEFT) && back.buttonHovered(input.mouseX() , input.mouseY())) {
			return Tool.START_MENU;
		}
		
		return Tool.NO_SWITCH;
	}
}
