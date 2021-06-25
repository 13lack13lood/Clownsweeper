package menus;

import gui.ImageRenderer;
import io.Input;
import render.Camera;
import shaders.Shader;
import tools.Tool;
import world.World;

public class MenuManager {
	private int currentMenu;
	private StartMenu startMenu;
	private InstructionsMenu instructionsMenu;
	private LevelMenu levelMenu;
	private EndMenu endMenu;
	private boolean gameWon;
	
	public MenuManager(StartMenu startMenu, InstructionsMenu instructionsMenu, LevelMenu levelMenu, EndMenu endMenu) {
		currentMenu = Tool.START_MENU;
		this.startMenu = startMenu;
		this.instructionsMenu = instructionsMenu;
		this.levelMenu = levelMenu;
		this.endMenu = endMenu;
		gameWon = false;
	}
	
	public int currentMenu() {
		return currentMenu;
	}
	
	public void setMenu(int menu) {
		currentMenu = menu;
	}
	
	public void gameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
	
	public void showMenu(Input input, ImageRenderer imageRenderer, Shader shader, Camera camera) {
		int x = input.mouseX();
		int y = input.mouseY();
		
		if(currentMenu == Tool.START_MENU) {
			startMenu.render(imageRenderer, x, y, shader, camera);
			gameWon = false;
		} else if(currentMenu == Tool.INSTRUCTIONS_MENU) {
			instructionsMenu.render(imageRenderer, x, y, shader, camera);
			gameWon = false;
		} else if(currentMenu == Tool.LEVEL_MENU) {
			levelMenu.render(imageRenderer, x, y, shader, camera);
			gameWon = false;
		} else if(currentMenu == Tool.END_MENU) {
			endMenu.render(imageRenderer, x, y, shader, camera, gameWon);
		}
	}
	
	public void update(Input input, World world) {
		if(currentMenu == Tool.START_MENU) {
			int switchMenu = startMenu.switchMenu(input);

			if(switchMenu != Tool.NO_SWITCH) {
				currentMenu = switchMenu;
			}
		} else if(currentMenu == Tool.INSTRUCTIONS_MENU) {
			int switchMenu = instructionsMenu.switchMenu(input);
			
			if(switchMenu != Tool.NO_SWITCH) {
				currentMenu = switchMenu;
			}
		} else if(currentMenu == Tool.LEVEL_MENU) {
			int switchMenu = levelMenu.switchMenu(input, world);
			
			if(switchMenu != Tool.NO_SWITCH) {
				currentMenu = switchMenu;
			}
		} else if(currentMenu == Tool.END_MENU) {
			int switchMenu = endMenu.switchMenu(input);
			
			if(switchMenu != Tool.NO_SWITCH) {
				currentMenu = switchMenu;
			}
		}
	}
}
