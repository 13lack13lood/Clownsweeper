package gui;

import io.FPSTimer;
import io.Timer;
import render.Camera;
import shaders.Shader;
import tools.Tool;
import world.World;

public class GuiManager {
	public static void updateScores(Score score, Score fps, Score timer, World world, FPSTimer fpsTimer, Timer clock) {
		fps.updateScore(fpsTimer.getFPS());
		score.updateScore(world.flags());
		
		if(!world.isGameOver())
			timer.updateScore(clock.getSeconds());
	}
	
	public static void render(ImageRenderer imageRenderer, Score score, Score fps, Score timer, Shader shader, Camera camera) {
		imageRenderer.renderImage(Tool.FLAG_GUI, Tool.FLAG_BACKGROUND_POS_X, Tool.FLAG_BACKGROUND_POS_Y, Tool.FLAG_BACKGROUND_SCALE, shader, camera);
		imageRenderer.renderImage(Tool.COLON_GUI, Tool.FLAG_BACKGROUND_COLON_POS_X, Tool.FLAG_BACKGROUND_POS_Y, Tool.COLON_BACKGROUND_SCALE, shader, camera);
		
		imageRenderer.renderImage(Tool.FPS_GUI, Tool.FPS_BACKGROUND_POS_X, Tool.FPS_BACKGROUND_POS_Y, Tool.FPS_BACKGROUND_SCALE, shader, camera);
		imageRenderer.renderImage(Tool.COLON_GUI, Tool.FPS_BACKGROUND_COLON_POS_X, Tool.FPS_BACKGROUND_POS_Y, Tool.COLON_BACKGROUND_SCALE / 2, shader, camera);
		
		imageRenderer.renderImage(Tool.TIMER_GUI, Tool.TIMER_BACKGROUND_POS_X, Tool.TIMER_BACKGROUND_POS_Y, Tool.TIMER_BACKGROUND_SCALE, shader, camera);
		imageRenderer.renderImage(Tool.COLON_GUI, Tool.TIMER_BACKGROUND_COLON_POS_X, Tool.TIMER_BACKGROUND_POS_Y, Tool.COLON_BACKGROUND_SCALE, shader, camera);
		
		score.renderScore(Tool.FLAG_BACKGROUND_SCORE_POS_X, Tool.FLAG_BACKGROUND_POS_Y, Tool.FLAG_BACKGROUND_SCALE, shader, camera);
		timer.renderScore(Tool.TIMER_BACKGROUND_SCORE_POS_X, Tool.TIMER_BACKGROUND_POS_Y, Tool.TIMER_BACKGROUND_NUMBERS_SCALE, shader, camera);
		fps.renderScore(Tool.FPS_BACKGROUND_SCORE_POS_X, Tool.FPS_BACKGROUND_POS_Y, Tool.FPS_BACKGROUND_NUMBERS_SCALE, shader, camera);
	}
}
