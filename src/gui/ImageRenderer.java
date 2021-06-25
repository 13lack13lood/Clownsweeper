package gui;

import joml.Vector3f;
import render.Camera;
import render.Model;
import render.Texture;
import shaders.Shader;
import tools.Tool;

public class ImageRenderer {
	private Model model;
	
	public ImageRenderer() {
		model = new Model(Tool.SQUARE_VERTICES, Tool.TEXTURE_COORDS, Tool.INDICES);
	}
	
	public void renderImage(String filepath, int x, int y, int scale, Shader shader, Camera camera) {
		Transform transform = new Transform();
		transform.pos = new Vector3f(x, y, 1);
		transform.scale = new Vector3f(scale, scale, 1);
		
		shader.bind();
		
		Texture texture = new Texture(filepath);
		texture.bind(0);
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", transform.getProjection(camera.getProjection()));
		
		model.render();
	}
	
	public void renderImage(String filepath, int x, int y, int xScale, int yScale, Shader shader, Camera camera) {
		Transform transform = new Transform();
		transform.pos = new Vector3f(x, y, 1);
		transform.scale = new Vector3f(xScale, yScale, 1);
		
		shader.bind();
		
		Texture texture = new Texture(filepath);
		texture.bind(0);
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", transform.getProjection(camera.getProjection()));
		
		model.render();
	}
}
