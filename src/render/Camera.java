package render;

import joml.Matrix4f;
import joml.Vector3f;

public class Camera {
	private Vector3f position;
	private Matrix4f projection;
	
	public Camera(int width, int height) {
		position = new Vector3f(0, 0, 0);
		projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);
	}
	
	public Matrix4f getProjection() {
		return projection.translate(position, new Matrix4f());
	}
}
