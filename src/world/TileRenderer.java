package world;

import java.util.HashMap;
import java.util.Map;

import joml.Matrix4f;
import joml.Vector3f;
import render.Camera;
import render.Model;
import render.Texture;
import shaders.Shader;
import tools.Tool;

public class TileRenderer {
	private Map<String, Texture> tileTextures;
	private Model model;
	
	public TileRenderer() {
		tileTextures = new HashMap<String, Texture>();
		
		model = new Model(Tool.SQUARE_VERTICES, Tool.TEXTURE_COORDS, Tool.INDICES);
		
		for(int i = 0; i < Tool.TILES.length; i++) {
			if(Tool.TILES[i] != null && !tileTextures.containsKey(Tool.TILES[i].getTexture())) {
				String tex = Tool.TILES[i].getTexture();
				tileTextures.put(tex, new Texture(tex + ".png"));
			}
		}
	}
	
	public void renderTile(Tile tile, int x, int y, Shader shader, Matrix4f world, Camera camera) {
		shader.bind();
		
		if(tileTextures.containsKey(tile.getTexture())) {
			tileTextures.get(tile.getTexture()).bind(0);
		}
		
		Matrix4f tilePos = new Matrix4f().translate(new Vector3f(x * 2, y * 2, 0));
		Matrix4f target = new Matrix4f();
		
		camera.getProjection().mul(world, target);
		target.mul(tilePos);
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);
		
		model.render();
	}
}
