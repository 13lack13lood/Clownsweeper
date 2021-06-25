package run;

import audio.SoundEffects;
import gui.Background;
import gui.GuiManager;
import gui.ImageRenderer;
import gui.Score;
import io.FPSTimer;
import io.Input;
import io.Timer;
import io.Window;
import menus.Button;
import menus.EndMenu;
import menus.InstructionsMenu;
import menus.LevelMenu;
import menus.MenuManager;
import menus.StartMenu;
import render.Camera;
import shaders.Shader;
import tools.Tool;
import world.TileRenderer;
import world.World;

public class Main {

	public static void main(String[] args) {
		Window.glfwInit();

		Window window = new Window(Tool.TITLE, Tool.ICON);
		
		Input input = window.getInput();
		Camera camera = new Camera(window.getWidth(), window.getHeight());
		Shader shader = new Shader(Tool.SHADER_FILENAME);
		
		TileRenderer tileRenderer = new TileRenderer();	
		
		World world = new World(tileRenderer);
		
		ImageRenderer imageRenderer = new ImageRenderer();
		Background background = new Background(Tool.BACKGROUND);
		FPSTimer fpsTimer = new FPSTimer(Tool.FRAME_CAP);
		Timer clock = new Timer();
		
		Score fps = new Score(imageRenderer);
		Score score = new Score(imageRenderer);
		Score timer = new Score(imageRenderer);
		
		Button play = new Button(Tool.PLAY_BUTTON_COORDS, Tool.PLAY_BUTTON);
		Button instructions = new Button(Tool.INSTRUCTIONS_BUTTON_COORDS, Tool.INSTRUCTIONS_BUTTON);
		Button exit = new Button(Tool.EXIT_BUTTON_COORDS, Tool.EXIT_BUTTON);
		Button back = new Button(Tool.BACK_BUTTON_COORDS, Tool.BACK_BUTTON);
		Button normal = new Button(Tool.NORMAL_BUTTON_COORDS, Tool.NORMAL_BUTTON);
		Button hard = new Button(Tool.HARD_BUTTON_COORDS, Tool.HARD_BUTTON);
		Button extreme = new Button(Tool.EXTREME_BUTTON_COORDS, Tool.EXTREME_BUTTON);
		Button playAgain = new Button(Tool.PLAY_AGAIN_BUTTON_COORDS, Tool.PLAY_AGAIN_BUTTON);
		Button home = new Button(Tool.HOME_BUTTON_COORDS, Tool.HOME_BUTTON);
		
		StartMenu startMenu = new StartMenu(play, instructions, exit, Tool.START_MENU_TITLE, Tool.START_MENU_CLOWN, Tool.START_MENU_FLAG);
		InstructionsMenu instructionsMenu = new InstructionsMenu(back, Tool.INSTRUCTIONS_MENU_TITLE, Tool.INSTRUCTIONS_MENU_FLAG, Tool.INSTRUCTIONS_MENU_MINE);
		LevelMenu levelMenu = new LevelMenu(normal, hard, extreme, back);
		EndMenu endMenu = new EndMenu(home, playAgain, Tool.END_MENU_WIN, Tool.END_MENU_LOSE, Tool.FLAG_GUI, Tool.TIMER_GUI, Tool.COLON_GUI, score, timer);
		
		MenuManager menuManager = new MenuManager(startMenu, instructionsMenu, levelMenu, endMenu);

		SoundEffects.init();
		
		while(!window.shouldClose()) {
			SoundEffects.update();
			fpsTimer.update();
			clock.update();

			while(fpsTimer.hasUnprocessedFrames()) {	
				window.update();
				
				if(menuManager.currentMenu() == Tool.GAME_MENU && !input.isCursorOutsideWorld() && !world.isGameOver()) {
					
					if(input.isMousePressed(Tool.MOUSE_LEFT)) {
						world.mine(input.mouseSquareX(), input.mouseSquareY(), false);
					}
					
					if(input.isMousePressed(Tool.MOUSE_RIGHT)) {
						world.flag(input.mouseSquareX(), input.mouseSquareY());
					}
					
				}
				
				if(!input.isCursorOutsideWindow())
					menuManager.update(input, world);
				
				if(fpsTimer.canRender()) {
					background.renderBackground(shader, camera);
					
					if(menuManager.currentMenu() == Tool.GAME_MENU) {
						world.render(shader, camera);
						
						GuiManager.updateScores(score, fps, timer, world, fpsTimer, clock);
						GuiManager.render(imageRenderer, score, fps, timer, shader, camera);
						
						if(world.isGameOver()) {
							if(!world.minesShown()) {
								clock.startTimer(Tool.GAME_END_DELAY);
								world.showMines();
								
								if(world.win())
									SoundEffects.win();
								else
									SoundEffects.lose();
							}
							
							if(clock.timerEnded()) {

								
								menuManager.gameWon(world.win());
								menuManager.setMenu(Tool.END_MENU);
								clock.resetClock();
								world = new World(tileRenderer);
							}
						}

					} else {
						menuManager.showMenu(input, imageRenderer, shader, camera);
					}
										
					window.swapBuffers();
				}
			}
		}
		SoundEffects.cleanUp();
		window.terminate();
	}
}
