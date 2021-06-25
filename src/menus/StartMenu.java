package menus;

import gui.ImageRenderer;
import io.Input;
import render.Camera;
import shaders.Shader;
import tools.Tool;

public class StartMenu {
	private Button play;
	private Button instructions;
	private Button exit;
	private String title;
	private String clown;
	private String flag;
	
	public StartMenu(Button play, Button instructions, Button exit, String title, String clown, String flag) {
		this.play = play;
		this.instructions = instructions;
		this.exit = exit;
		this.title = title;
		this.clown = clown;
		this.flag = flag;
	}
	
	public void render(ImageRenderer imageRenderer, int mouseX, int mouseY, Shader shader, Camera camera) {
		imageRenderer.renderImage(title, Tool.START_MENU_TITLE_COORDS[0], Tool.START_MENU_TITLE_COORDS[1], Tool.START_MENU_TITLE_COORDS[2], Tool.START_MENU_TITLE_COORDS[3], shader, camera);
		imageRenderer.renderImage(flag, Tool.START_MENU_FLAG_COORDS[0], Tool.START_MENU_FLAG_COORDS[1], Tool.START_MENU_FLAG_COORDS[2], shader, camera);
		
		imageRenderer.renderImage(clown, Tool.START_MENU_CLOWN1_COORDS[0], Tool.START_MENU_CLOWN1_COORDS[1], Tool.START_MENU_CLOWN1_COORDS[2], shader, camera);
		imageRenderer.renderImage(clown, Tool.START_MENU_CLOWN2_COORDS[0], Tool.START_MENU_CLOWN2_COORDS[1], Tool.START_MENU_CLOWN2_COORDS[2], shader, camera);
		imageRenderer.renderImage(clown, Tool.START_MENU_CLOWN3_COORDS[0], Tool.START_MENU_CLOWN3_COORDS[1], Tool.START_MENU_CLOWN3_COORDS[2], shader, camera);
		imageRenderer.renderImage(clown, Tool.START_MENU_CLOWN4_COORDS[0], Tool.START_MENU_CLOWN4_COORDS[1], Tool.START_MENU_CLOWN4_COORDS[2], shader, camera);
		
		play.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
		instructions.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
		exit.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
	}
	
	public int switchMenu(Input input) {
		if(input.isMousePressed(Tool.MOUSE_LEFT) && play.buttonHovered(input.mouseX(), input.mouseY())) {
			return Tool.LEVEL_MENU;
		} else if(input.isMousePressed(Tool.MOUSE_LEFT) && instructions.buttonHovered(input.mouseX(), input.mouseY())) {
			return Tool.INSTRUCTIONS_MENU;
		} else if(input.isMousePressed(Tool.MOUSE_LEFT) && exit.buttonHovered(input.mouseX(), input.mouseY())) {
			System.exit(0);
		}
		
		return Tool.NO_SWITCH;
	}
}
