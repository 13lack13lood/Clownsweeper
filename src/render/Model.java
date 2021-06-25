package render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import static tools.Tool.*;

public class Model {
	private int draw_count; //how many triangles
	private int v_id; //vertex id
	private int t_id; //texture id
	private int i_id; //index id
	
	public Model(float[] vertices, float[] tex_coords, int[] indices) {
		draw_count = indices.length;
		
		v_id = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, v_id);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, createFloatBuffer(vertices), GL15.GL_STATIC_DRAW);
		
		t_id = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, t_id);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, createFloatBuffer(tex_coords), GL15.GL_STATIC_DRAW);
		
		i_id = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, i_id);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, createIntBuffer(indices), GL15.GL_STATIC_DRAW);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	protected void finalize() {
		GL20.glDeleteBuffers(v_id);
		GL20.glDeleteBuffers(t_id);
		GL20.glDeleteBuffers(i_id);
	}
	
	public void render() {
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, v_id);
		GL20.glVertexAttribPointer(0, 3, GL20.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, t_id);
		GL20.glVertexAttribPointer(1, 2, GL15.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, i_id);
		GL15.glDrawElements(GL11.GL_TRIANGLES, draw_count, GL11.GL_UNSIGNED_INT, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, draw_count);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
	}
}
