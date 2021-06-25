package world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import audio.SoundEffects;
import joml.Matrix4f;
import joml.Vector3f;
import render.Camera;
import shaders.Shader;
import tools.Tool;

public class World {
	private Tile[] tiles;
	private boolean[] mines;
	private List<TileAnimation> tileAnimations;
	private int squares;
	private boolean isGameOver;
	private int flags;
	private boolean minesShown;
	private int numberOfMines;
	
	private Matrix4f world;
	private TileRenderer render;
	
	public World(TileRenderer render) {
		this.render = render;
		
		isGameOver = false;
		flags = 0;
		numberOfMines = 0;

		tileAnimations = new ArrayList<TileAnimation>();
	}
	
	public void setWorldDifficulty(int squares, int scale) {
		this.squares = squares;
		tiles = new Tile[squares * squares];
		mines = new boolean[squares * squares];
		world = new Matrix4f().setTranslation(new Vector3f(-(Tool.WIDTH / 2) + Tool.SCALE, Tool.HEIGHT / 2 - Tool.SCALE, 0));
		world.scale(scale);
	}
	
	public void render(Shader shader, Camera camera) {
		updateAnimations();
		
		for(int i = 0; i < squares; i++) {
			for(int j = 0; j < squares; j++) {
				render.renderTile(tiles[convertXYCoordstoArrayIndex(j, i)], j, -i, shader, world, camera);
			}
		}
	}
	
	private int convertXYCoordstoArrayIndex(int x, int y) {
		return x + y * squares;
	}
	
	public void setTile(Tile tile, int x, int y) {
		tiles[convertXYCoordstoArrayIndex(x, y)] = tile;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[convertXYCoordstoArrayIndex(x, y)];
	}
	
	public void resetWorld() {
		startWorld();
		isGameOver = false;
		minesShown = false;
	}
	
	public void startWorld() {
		setWorld();
		setMines();
	}
	
	public void setWorld() {
		tiles = new Tile[squares * squares];
		
		for(int i = 0; i < Tool.WORLD_SQUARES; i++) {
			for(int j = 0; j < Tool.WORLD_SQUARES; j++) {
				if(i % 2 == 0) {
					if(j % 2 == 0) {
						this.setTile(Tool.GREEN_LIGHT_TILE, i, j);
					} else {
						this.setTile(Tool.GREEN_DARK_TILE, i, j);
					}
				} else {
					if(j % 2 == 0) {
						this.setTile(Tool.GREEN_DARK_TILE, i, j);
					} else {
						this.setTile(Tool.GREEN_LIGHT_TILE, i, j);
					}
				}
			}
		}
	}
	
	public void setMines() {
		mines = new boolean[squares * squares];
		
		int number = (int) (Tool.PERCENT_OF_MINES * mines.length);
		
		Random random = new Random();
		int rand = random.nextInt(2 * Tool.RANDOM_MINES) + 1;
		
		number += (rand - Tool.RANDOM_MINES);
		
		for(int i = 0; i < number; i++) {
			int pos = random.nextInt(mines.length);
			
			mines[pos] = true;
		}
		
		for(boolean mine : mines) {
			if(mine)
				numberOfMines++;
		}
	}
	
	public void showMines() {
		Random random = new Random();
		
		for(int i = 0; i < mines.length; i++) {
			if(mines[i]) {
				tiles[i] = Tool.COLORED_CLOWNS_TILES[random.nextInt(Tool.COLORED_CLOWNS_TILES.length)];
			}
		}
		
		minesShown = true;
	}
	
	public void mine(int x, int y, boolean clearEmpty) {
		int count = nearbyMines(x, y);
		
		if(mines[convertXYCoordstoArrayIndex(x, y)] && (getTile(x, y).getId() != Tool.GREEN_DARK_FLAG_TILE.getId() || getTile(x, y).getId() != Tool.GREEN_LIGHT_FLAG_TILE.getId())) {
			setTile(Tool.CLOWN_TILE, x, y);
			isGameOver = true;
		} else if(getTile(x, y).getId() == Tool.GREEN_DARK_TILE.getId()) {
			setTileNumber(Tool.GREEN_DARK_TILE, count, x, y);
			if(!clearEmpty)
				SoundEffects.mine();
		} else if(getTile(x, y).getId() == Tool.GREEN_LIGHT_TILE.getId()) {	
			setTileNumber(Tool.GREEN_LIGHT_TILE, count, x, y);
			if(!clearEmpty)
				SoundEffects.mine();
		}
	}
	
	private void setTileNumber(Tile tile, int n, int x, int y) {
		if(n >= 1 && n <= 8) {
			if(tile.getId() == Tool.GREEN_DARK_TILE.getId())
				setTile(Tool.BROWN_DARK_NUMBERED_TILES[n - 1], x, y);
			else if(tile.getId() == Tool.GREEN_LIGHT_TILE.getId())
				setTile(Tool.BROWN_LIGHT_NUMBERED_TILES[n - 1], x, y);
		} else {
			if(tile.getId() == Tool.GREEN_DARK_TILE.getId()) {
				setTile(Tool.BROWN_DARK_TILE, x, y);
				clearEmpty(x, y);
			} else if(tile.getId() == Tool.GREEN_LIGHT_TILE.getId()) {
				setTile(Tool.BROWN_LIGHT_TILE, x, y);
				clearEmpty(x, y);			
			}
		}
	}
	
	private void clearEmpty(int x, int y) {
		if((x > 0 && y > 0) && !mines[convertXYCoordstoArrayIndex(x - 1, y - 1)])
			mine(x - 1, y - 1, true);
		if(y > 0 && !mines[convertXYCoordstoArrayIndex(x, y - 1)])
			mine(x, y - 1, true);
		if((x < squares - 1 && y > 0) && !mines[convertXYCoordstoArrayIndex(x + 1, y - 1)])
			mine(x + 1, y - 1, true);
		if(x < squares - 1 && !mines[convertXYCoordstoArrayIndex(x + 1, y)])
			mine(x + 1, y, true);
		if((x < squares - 1 && y < squares - 1) && !mines[convertXYCoordstoArrayIndex(x + 1, y + 1)])
			mine(x + 1, y + 1, true);
		if(y < squares - 1 && !mines[convertXYCoordstoArrayIndex(x, y + 1)])
			mine(x, y + 1, true);
		if((x > 0 && y < squares - 1) && !mines[convertXYCoordstoArrayIndex(x - 1, y + 1)])
			mine(x - 1, y + 1, true);
		if(x > 0 && !mines[convertXYCoordstoArrayIndex(x - 1, y)])
			mine(x - 1, y, true);
	}
	
	private int nearbyMines(int x, int y) {
		int count = 0;
		
		if((x > 0 && y > 0) && mines[convertXYCoordstoArrayIndex(x - 1, y - 1)])
			count++;
		if(y > 0 && mines[convertXYCoordstoArrayIndex(x, y - 1)])
			count++;
		if((x < squares - 1 && y > 0) && mines[convertXYCoordstoArrayIndex(x + 1, y - 1)])
			count++;
		if(x < squares - 1 && mines[convertXYCoordstoArrayIndex(x + 1, y)])
			count++;
		if((x < squares - 1 && y < squares - 1) && mines[convertXYCoordstoArrayIndex(x + 1, y + 1)])
			count++;
		if(y < squares - 1 && mines[convertXYCoordstoArrayIndex(x, y + 1)])
			count++;
		if((x > 0 && y < squares - 1) && mines[convertXYCoordstoArrayIndex(x - 1, y + 1)])
			count++;
		if(x > 0 && mines[convertXYCoordstoArrayIndex(x - 1, y)])
			count++;
		
		return count;
	}
	
	public void flag(int x, int y) {
		if(getTile(x, y).getId() == Tool.GREEN_DARK_TILE.getId()) {
			tileAnimations.add(new TileAnimation(Tool.GREEN_DARK_FLAG_ANIMATION_ARRAY, Tool.GREEN_DARK_FLAG_TILE, x, y, this, Tool.GREEN_DARK_FLAG_ANIMATION_ARRAY.length, 45, false));
			flags++;
			SoundEffects.flag();
		} else if(getTile(x, y).getId() == Tool.GREEN_LIGHT_TILE.getId()) {
			tileAnimations.add(new TileAnimation(Tool.GREEN_LIGHT_FLAG_ANIMATION_ARRAY, Tool.GREEN_LIGHT_FLAG_TILE, x, y, this, Tool.GREEN_LIGHT_FLAG_ANIMATION_ARRAY.length, 60, false));
			flags++;
			SoundEffects.flag();
		} else if(getTile(x, y).getId() == Tool.GREEN_DARK_FLAG_TILE.getId()) {
			tileAnimations.add(new TileAnimation(Tool.GREEN_DARK_FLAG_ANIMATION_ARRAY, Tool.GREEN_DARK_TILE, x, y, this, Tool.GREEN_DARK_FLAG_ANIMATION_ARRAY.length, 45, true));
			flags--;
			SoundEffects.unflag();
		} else if(getTile(x, y).getId() == Tool.GREEN_LIGHT_FLAG_TILE.getId()) {
			tileAnimations.add(new TileAnimation(Tool.GREEN_LIGHT_FLAG_ANIMATION_ARRAY, Tool.GREEN_LIGHT_TILE, x, y, this, Tool.GREEN_LIGHT_FLAG_ANIMATION_ARRAY.length, 60, true));
			flags--;
			SoundEffects.unflag();
		}
	}
	
	public void updateAnimations() {
		List<TileAnimation> remove = new ArrayList<TileAnimation>();
		
		for(TileAnimation animation : tileAnimations) {
			if(animation.completedAnimation()) {
				remove.add(animation);
			} else {
				animation.animate();
			}
		}
		
		for(TileAnimation animation : remove) {
			tileAnimations.remove(animation);
		}
	}
	
	public Matrix4f getWorld() {
		return world;
	}
	
	public boolean isGameOver() {
		if(isGameOver)
			return true;
		
		if(flags == numberOfMines) {
			for(Tile tile : tiles) {
				if(tile.getId() == Tool.GREEN_DARK_TILE.getId() || tile.getId() == Tool.GREEN_LIGHT_TILE.getId()) {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean win() {
		return !isGameOver && isGameOver();
	}
	
	public boolean minesShown() {
		return minesShown;
	}
	
	public int flags() {
		return flags;
	}
}
