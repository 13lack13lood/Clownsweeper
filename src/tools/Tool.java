package tools;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import world.Tile;

public class Tool {
	public static final int WIDTH = 960;
	public static final int HEIGHT = 720;
	public static final int WORLD_WIDTH = HEIGHT;
	
	public static int SCALE = 24;
	public static int SQUARE_WIDTH = SCALE * 2;
	public static int WORLD_SQUARES = HEIGHT / SQUARE_WIDTH;
	
	public static final int NORMAL_SCALE = 24;
	public static final int HARD_SCALE = 12;
	public static final int EXTREME_SCALE = 8;
	
	public static final int FRAME_RATE = 90;
	public static final int GREEN_DARK_FLAG_ANIMATION_FPS = 45;
	public static final int GREEN_LIGHT_FLAG_ANIMATION_FPS = 60;
	
	public static final double FRAME_CAP = 1.0 / (double) FRAME_RATE;
	public static final double PERCENT_OF_MINES = 0.15;
	public static final int RANDOM_MINES = 10;
	public static final int LIGHT_BROWN = 1;
	public static final int DARK_BROWN = 2;
	public static final int LIGHT_GREEN = 3;
	public static final int DARK_GREEN = 4;
	public static final int MOUSE_LEFT = GLFW.GLFW_MOUSE_BUTTON_LEFT;
	public static final int MOUSE_RIGHT = GLFW.GLFW_MOUSE_BUTTON_RIGHT;
	public static final int GAME_END_DELAY = 3;
	
	public static final int FLAG_BACKGROUND_SCALE = 24;
	public static final int COLON_BACKGROUND_SCALE = 12;
	public static final int FLAG_BACKGROUND_POS_X = (Tool.WORLD_WIDTH / 2) - FLAG_BACKGROUND_SCALE * 3;
	public static final int FLAG_BACKGROUND_POS_Y = (Tool.WORLD_WIDTH / 2) - FLAG_BACKGROUND_SCALE * 5;
	public static final int FLAG_BACKGROUND_COLON_POS_X = (Tool.WORLD_WIDTH / 2) - FLAG_BACKGROUND_SCALE;
	public static final int FLAG_BACKGROUND_SCORE_POS_X = (Tool.WORLD_WIDTH / 2) + FLAG_BACKGROUND_SCALE;
	
	public static final int FPS_BACKGROUND_SCALE = 24;
	public static final int FPS_BACKGROUND_COLON_SCALE = 12;
	public static final int FPS_BACKGROUND_NUMBERS_SCALE = 12;
	public static final int FPS_BACKGROUND_POS_X = (Tool.WORLD_WIDTH / 2) - FPS_BACKGROUND_SCALE * 3;
	public static final int FPS_BACKGROUND_POS_Y = -(Tool.WORLD_WIDTH / 2) + FPS_BACKGROUND_SCALE * 3;
	public static final int FPS_BACKGROUND_COLON_POS_X = (Tool.WORLD_WIDTH / 2) - FPS_BACKGROUND_SCALE;
	public static final int FPS_BACKGROUND_SCORE_POS_X = (Tool.WORLD_WIDTH / 2) + (FPS_BACKGROUND_SCALE / 2);
	
	public static final int TIMER_BACKGROUND_SCALE = 24;
	public static final int TIMER_BACKGROUND_NUMBERS_SCALE = 12;
	public static final int TIMER_BACKGROUND_POS_X = (Tool.WORLD_WIDTH / 2) - TIMER_BACKGROUND_SCALE * 3;
	public static final int TIMER_BACKGROUND_POS_Y = (Tool.WORLD_WIDTH / 4) - FLAG_BACKGROUND_SCALE * 2 + (FLAG_BACKGROUND_SCALE / 2);
	public static final int TIMER_BACKGROUND_COLON_POS_X = (Tool.WORLD_WIDTH / 2) - TIMER_BACKGROUND_SCALE;
	public static final int TIMER_BACKGROUND_SCORE_POS_X = (Tool.WORLD_WIDTH / 2) + (TIMER_BACKGROUND_SCALE / 2);
	
	public static final String TITLE = "Clownsweeper";
	public static final String ICON = "clownbig.png";
	public static final String BACKGROUND = "background.png";
	public static final String SHADER_FILENAME = "shader";
	
	public static final String FLAG_GUI = "flagbackground.png";
	public static final String COLON_GUI = "colon.png";
	public static final String FPS_GUI = "fps.png";
	public static final String TIMER_GUI = "timer.png";
	
	public static final String START_MENU_TITLE = "startmenu/title.png";
	public static final String START_MENU_CLOWN = "startmenu/clownbig.png";
	public static final String START_MENU_FLAG = "startmenu/flag.png";
	public static final String INSTRUCTIONS_MENU_TITLE = "instructionsmenu/title.png";
	public static final String INSTRUCTIONS_MENU_FLAG = "instructionsmenu/flag.png";
	public static final String INSTRUCTIONS_MENU_MINE = "instructionsmenu/mine.png";
	public static final String END_MENU_WIN = "endmenu/win.png";
	public static final String END_MENU_LOSE = "endmenu/lose.png";
	
	public static final String PLAY_BUTTON = "startmenu/play.png";
	public static final String INSTRUCTIONS_BUTTON = "startmenu/instructions.png";
	public static final String EXIT_BUTTON = "startmenu/exit.png";
	public static final String BACK_BUTTON = "instructionsmenu/back.png";
	public static final String NORMAL_BUTTON = "levelsmenu/normal.png";
	public static final String HARD_BUTTON = "levelsmenu/hard.png";
	public static final String EXTREME_BUTTON = "levelsmenu/extreme.png";
	public static final String PLAY_AGAIN_BUTTON = "endmenu/playagain.png";
	public static final String HOME_BUTTON = "endmenu/home.png";
	
	public static final int[] PLAY_BUTTON_COORDS = {0, 50, 100, 50};
	public static final int[] INSTRUCTIONS_BUTTON_COORDS = {0, -100, 100, 50};
	public static final int[] EXIT_BUTTON_COORDS = {0, -250, 100, 50};
	public static final int[] BACK_BUTTON_COORDS = {0, -250, 100, 50};
	public static final int[] NORMAL_BUTTON_COORDS = {0, 250, 100, 50};
	public static final int[] HARD_BUTTON_COORDS = {0, 100, 100, 50};
	public static final int[] EXTREME_BUTTON_COORDS = {0, -50, 100, 50};
	public static final int[] PLAY_AGAIN_BUTTON_COORDS = {-150, -225, 100, 50};
	public static final int[] HOME_BUTTON_COORDS = {150, -225, 100, 50};

	public static final int[] START_MENU_TITLE_COORDS = {0, 250, 400, 100};
	public static final int[] START_MENU_FLAG_COORDS = {-300, -100, 100};
	public static final int[] START_MENU_CLOWN1_COORDS = {250, 100, 50};
	public static final int[] START_MENU_CLOWN2_COORDS = {350, -25, 50};
	public static final int[] START_MENU_CLOWN3_COORDS = {225, -150, 50};
	public static final int[] START_MENU_CLOWN4_COORDS = {375, -225, 50};
	
	public static final int[] INSTRUCTIONS_MENU_TITLE_COORDS = {0, 250, 400, 100};
	public static final int[] INSTRUCTIONS_MENU_MINE_COORDS = {-200, 0, 150};
	public static final int[] INSTRUCTIONS_MENU_FLAG_COORDS = {200, 0, 150};

	public static final int[] END_MENU_WIN_LOSE_COORDS = {0, 250, 400, 100};
	public static final int[] END_MENU_FLAG_COORDS = {-100, 75, Tool.FLAG_BACKGROUND_SCALE * 2};
	public static final int[] END_MENU_TIMER_COORDS = {-100, -75, Tool.TIMER_BACKGROUND_SCALE * 2};
	public static final int[] END_MENU_FLAG_COLON_COORDS = {0, 75, Tool.COLON_BACKGROUND_SCALE * 2};
	public static final int[] END_MENU_TIMER_COLON_COORDS = {0, -75, Tool.COLON_BACKGROUND_SCALE * 2};
	public static final int[] END_MENU_SCORE_COORDS = {100, 75, (Tool.FLAG_BACKGROUND_SCALE * 3) / 2};
	public static final int[] END_MENU_TIME_COORDS = {100, -75, Tool.TIMER_BACKGROUND_NUMBERS_SCALE * 2};
	
	public static final int NO_SWITCH = 0;
	public static final int START_MENU = 1;
	public static final int INSTRUCTIONS_MENU = 2;
	public static final int LEVEL_MENU = 3;
	public static final int GAME_MENU = 4;
	public static final int END_MENU = 5;
	
	public static final String[] START_MENU_PLAY = {"startmenu/play.png", "startmenu/playHovered.png"};
	public static final String[] START_MENU_INSTRUCTIONs = {"startmenu/instructions.png", "startmenu/instructionsHovered.png"};
	public static final String[] START_MENU_EXIT = {"startmenu/exit.png", "startmenu/exitHovered.png"};
	
	public static Tile[] TILES = new Tile[100];
	public static final Tile GREEN_LIGHT_TILE = new Tile(0, "greenlight");
	public static final Tile GREEN_DARK_TILE = new Tile(1, "greendark");
	public static final Tile BROWN_LIGHT_TILE = new Tile(2, "brownlight");
	public static final Tile BROWN_DARK_TILE = new Tile(3, "browndark");
	public static final Tile GREEN_LIGHT_FLAG_TILE = new Tile(4, "greenlightflag");
	public static final Tile GREEN_DARK_FLAG_TILE = new Tile(5, "greendarkflag");
	public static final Tile CLOWN_TILE = new Tile(6, "clownbig");
	
	public static Tile[] BROWN_LIGHT_NUMBERED_TILES = new Tile[8];
	public static Tile[] BROWN_DARK_NUMBERED_TILES = new Tile[8];
	public static Tile[] GREEN_DARK_FLAG_ANIMATION_ARRAY = new Tile[15];
	public static Tile[] GREEN_LIGHT_FLAG_ANIMATION_ARRAY = new Tile[27];
	public static Tile[] COLORED_CLOWNS_TILES = new Tile[16];
	
	public static void updateValues() {
		SQUARE_WIDTH = SCALE * 2;
		WORLD_SQUARES = HEIGHT / SQUARE_WIDTH;
	}
	
	public static final float[] SQUARE_VERTICES = new float[] {
			-1f, 1f, 0, //top left
			1f, 1f, 0, //top right
			1f, -1f, 0, //bottom right
			-1f, -1f, 0, //bottom left	
	};
	
	public static final float[] TEXTURE_COORDS = new float[] {
			0, 0,
			1, 0,
			1, 1, 
			0, 1
	};
	
	public static final int[] INDICES = new int[] {
			0, 1, 2,
			2, 3, 0,
	};
	
	public static final float[] RECTANGLE_VERTICES = new float[] {
			-1f, 0.5f, 0, //top left
			1f, 0.5f, 0, //top right
			1f, -0.5f, 0, //bottom right
			-1f, -0.5f, 0, //bottom left	
	};
	
	public static void setTileArrays() {
		int start1 = 7;
		int start2 = start1 + BROWN_LIGHT_NUMBERED_TILES.length;
		int start3 = start2 + BROWN_DARK_NUMBERED_TILES.length;
		int start4 = start3 + GREEN_DARK_FLAG_ANIMATION_ARRAY.length;
		int start5 = start4 + GREEN_LIGHT_FLAG_ANIMATION_ARRAY.length;
		int start6 = start5 + COLORED_CLOWNS_TILES.length;
		
		for(int i = start1; i < start2; i++) {
			BROWN_LIGHT_NUMBERED_TILES[i - start1] = new Tile(i, "brownlightnumbers/brownlight" + (i - start1 + 1));
		}
		
		for(int i = start2; i < start3; i++) {
			BROWN_DARK_NUMBERED_TILES[i - start2] = new Tile(i, "browndarknumbers/browndark" + (i - start2 + 1));
		}
		
		for(int i = start3; i < start4; i++) {
			GREEN_DARK_FLAG_ANIMATION_ARRAY[i - start3] = new Tile(i, "greendarkflaganimations/greendarkflag" + (i - start3 + 1));
		}

		for(int i = start4; i < start5; i++) {
			GREEN_LIGHT_FLAG_ANIMATION_ARRAY[i - start4] = new Tile(i, "greenlightflaganimations/greenlightflag" + (i - start4 + 1));
		}
		
		for(int i = start5; i < start6; i++) {
			COLORED_CLOWNS_TILES[i - start5] = new Tile(i, "clowns/clownbig" + (i - start5 + 1));
		}
	}
	
	public static FloatBuffer createFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	public static IntBuffer createIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	public static ByteBuffer convertImageToByteBuffer(BufferedImage image, int width, int height) {
		int[] pixels_raw = new int[width * height * 4];
		pixels_raw = image.getRGB(0, 0, width, height, null, 0, width);
		
		ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int pixel = pixels_raw[i * width + j];
				pixels.put((byte) (pixel >> 16 & 0xFF)); //red
				pixels.put((byte) (pixel >> 8 & 0xFF)); //green
				pixels.put((byte) (pixel & 0xFF)); //blue
				pixels.put((byte) (pixel >> 24 & 0xFF)); //alpha
			}
		}
		
		pixels.flip();
		
		return pixels;
	}
	
}
