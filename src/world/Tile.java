package world;

import tools.Tool;

public class Tile {
	private int id;
	private String texture;
	
	public Tile(int id, String texture) {
		this.id = id;
		this.texture = texture;
		
		if(Tool.TILES[id] != null) {
			throw new IllegalStateException("Tiles at: [" + id + "] is already being used.");
		}
		
		Tool.TILES[id] = this;
		
	}
	
	public int getId() {
		return id;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String tex) {
		Tool.TILES[id].texture = tex;
	}
}
