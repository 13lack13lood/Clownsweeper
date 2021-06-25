package menus;

import gui.ImageRenderer;
import io.Input;
import render.Camera;
import shaders.Shader;
import tools.Tool;

public class InstructionsMenu {
	private Button back;
	private String title;
	private String flag;
	private String mine;
	
	public InstructionsMenu(Button back, String title, String flag, String mine) {
		this.back = back;
		this.title = title;
		this.flag = flag;
		this.mine = mine;
	}
	
	public void render(ImageRenderer imageRenderer, int mouseX, int mouseY, Shader shader, Camera camera) {
		imageRenderer.renderImage(title, Tool.INSTRUCTIONS_MENU_TITLE_COORDS[0], Tool.INSTRUCTIONS_MENU_TITLE_COORDS[1], Tool.INSTRUCTIONS_MENU_TITLE_COORDS[2], Tool.INSTRUCTIONS_MENU_TITLE_COORDS[3], shader, camera);
		imageRenderer.renderImage(mine, Tool.INSTRUCTIONS_MENU_MINE_COORDS[0], Tool.INSTRUCTIONS_MENU_MINE_COORDS[1], Tool.INSTRUCTIONS_MENU_MINE_COORDS[2], shader, camera);
		imageRenderer.renderImage(flag, Tool.INSTRUCTIONS_MENU_FLAG_COORDS[0], Tool.INSTRUCTIONS_MENU_FLAG_COORDS[1], Tool.INSTRUCTIONS_MENU_FLAG_COORDS[2], shader, camera);
		
		back.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
	}
	
	public int switchMenu(Input input) {
		if(input.isMousePressed(Tool.MOUSE_LEFT) && back.buttonHovered(input.mouseX() , input.mouseY())) {
			return Tool.START_MENU;
		}
		
		return Tool.NO_SWITCH;
	}
}
