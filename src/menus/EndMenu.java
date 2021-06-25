package menus;

import gui.ImageRenderer;
import gui.Score;
import io.Input;
import render.Camera;
import shaders.Shader;
import tools.Tool;

public class EndMenu {
	private Button home;
	private Button playAgain;
	private String win;
	private String lose;
	private String flag;
	private String timer;
	private String colon;
	private Score score;
	private Score time;
	
	public EndMenu(Button home, Button playAgain, String win, String lose, String flag, String timer, String colon, Score score, Score time) {
		this.home = home;
		this.playAgain = playAgain;
		this.win = win;
		this.lose = lose;
		this.flag = flag;
		this.timer = timer;
		this.colon = colon;
		this.score = score;
		this.time = time;
	}

	public void render(ImageRenderer imageRenderer, int mouseX, int mouseY, Shader shader, Camera camera, boolean won) {
		if(won)
			imageRenderer.renderImage(win, Tool.END_MENU_WIN_LOSE_COORDS[0], Tool.END_MENU_WIN_LOSE_COORDS[1], Tool.END_MENU_WIN_LOSE_COORDS[2], Tool.END_MENU_WIN_LOSE_COORDS[3], shader, camera);
		else
			imageRenderer.renderImage(lose, Tool.END_MENU_WIN_LOSE_COORDS[0], Tool.END_MENU_WIN_LOSE_COORDS[1], Tool.END_MENU_WIN_LOSE_COORDS[2], Tool.END_MENU_WIN_LOSE_COORDS[3], shader, camera);

		imageRenderer.renderImage(flag, Tool.END_MENU_FLAG_COORDS[0], Tool.END_MENU_FLAG_COORDS[1], Tool.END_MENU_FLAG_COORDS[2], shader, camera);
		imageRenderer.renderImage(timer, Tool.END_MENU_TIMER_COORDS[0], Tool.END_MENU_TIMER_COORDS[1], Tool.END_MENU_TIMER_COORDS[2], shader, camera);
		imageRenderer.renderImage(colon, Tool.END_MENU_FLAG_COLON_COORDS[0], Tool.END_MENU_FLAG_COLON_COORDS[1], Tool.END_MENU_FLAG_COLON_COORDS[2], shader, camera);
		imageRenderer.renderImage(colon, Tool.END_MENU_TIMER_COLON_COORDS[0], Tool.END_MENU_TIMER_COLON_COORDS[1], Tool.END_MENU_TIMER_COLON_COORDS[2], shader, camera);
		
		home.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
		playAgain.renderButton(mouseX, mouseY, imageRenderer, shader, camera);
		
		score.renderScore(Tool.END_MENU_SCORE_COORDS[0], Tool.END_MENU_SCORE_COORDS[1], Tool.END_MENU_SCORE_COORDS[2], shader, camera);
		time.renderScore(Tool.END_MENU_TIME_COORDS[0], Tool.END_MENU_TIME_COORDS[1], Tool.END_MENU_TIME_COORDS[2], shader, camera);
	}
	
	public int switchMenu(Input input) {
		if(input.isMousePressed(Tool.MOUSE_LEFT) && home.buttonHovered(input.mouseX(), input.mouseY())) {
			return Tool.START_MENU;
		} else if(input.isMousePressed(Tool.MOUSE_LEFT) && playAgain.buttonHovered(input.mouseX(), input.mouseY())) {
			return Tool.LEVEL_MENU;
		}
		
		return Tool.NO_SWITCH;
	}
}
