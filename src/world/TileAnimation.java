package world;

import io.Timer;

public class TileAnimation {
	private int index;
	private int size;
	private boolean completedAnimation;
	private World world;
	private int x;
	private int y;
	private Tile[] animation;
	private Tile originalTile;
	private boolean invert;
	
	private double elapsedTime;
	private double currentTime;
	private double lastTime;
	private double fps;
	
	public TileAnimation(Tile[] animation, Tile originalTile, int x, int y, World world, int size, int fps, boolean invert) {
		index = 0;
		completedAnimation = false;
		this.x = x;
		this.y = y;
		this.world = world;
		this.size = size;
		this.originalTile = originalTile;
		this.animation = animation;
		this.invert = invert;
		
		elapsedTime = 0;
		currentTime = 0;
		lastTime = Timer.getTime();
		this.fps = 1.0 / (double) fps;
		
		if(invert)
			index = size - 1;
	}
	
	public void animate() {
		if(index >= size || index < 0) {
			world.setTile(originalTile, x, y);
			completedAnimation = true;
		} else {
			if(update()) {
				world.setTile(animation[index], x, y);
			}
		}
	}
	
	private boolean update() {
		currentTime = Timer.getTime();
		elapsedTime += currentTime - lastTime;
		int prevIndex = index;
		
		if(elapsedTime >= fps) {
			elapsedTime -= fps;
			
			if(invert) {
				index--;
			} else {
				index++;
			}
			
		}
		
		lastTime = currentTime;
		
		return prevIndex == index;
	}
	
	public boolean completedAnimation() {
		return completedAnimation;
	}
	
	public int getIndex() {
		return index;
	}
}
