package gui;

import joml.Matrix4f;
import joml.Vector3f;
import render.Camera;
import render.Model;
import render.Texture;
import shaders.Shader;
import tools.Tool;

public class Background {
	private Model model;
	private Texture texture;
	
	public Background(String texture) {
		model = new Model(Tool.SQUARE_VERTICES, Tool.TEXTURE_COORDS, Tool.INDICES);
		this.texture = new Texture(texture);
	}
	
	public void renderBackground(Shader shader, Camera camera) {
		shader.bind();
		
		texture.bind(0);
		
		Matrix4f position = new Matrix4f();
		Matrix4f background = new Matrix4f().setTranslation(new Vector3f(-(Tool.WIDTH / 2) + Tool.SCALE, Tool.HEIGHT / 2 - Tool.SCALE, 0));
		background.scale(Tool.WIDTH);
		
		camera.getProjection().mul(background, position);
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", position);
		
		model.render();
	}
}
