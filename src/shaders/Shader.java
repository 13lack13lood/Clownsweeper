package shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import joml.Matrix4f;

public class Shader {
	private int program;
	private int vs; //vertex shader
	private int fs; //fragment shader
	
	public Shader(String filename) {
		program = GL20.glCreateProgram();
		
		vs = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vs, readFile(filename + ".vert"));
		GL20.glCompileShader(vs);
		
		if(GL20.glGetShaderi(vs, GL20.GL_COMPILE_STATUS) != 1) {
			System.err.println(GL20.glGetShaderInfoLog(vs));
			System.exit(1);
		}
		
		fs = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fs, readFile(filename + ".frag"));
		GL20.glCompileShader(fs);
		
		if(GL20.glGetShaderi(fs, GL20.GL_COMPILE_STATUS) != 1) {
			System.err.println(GL20.glGetShaderInfoLog(fs));
			System.exit(1);
		}
		
		GL20.glAttachShader(program, vs);
		GL20.glAttachShader(program, fs);
		
		GL20.glBindAttribLocation(program, 0, "vertices");
		GL20.glBindAttribLocation(program, 1, "textures");
		
		GL20.glLinkProgram(program);
		
		if(GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) != 1) {
			System.err.println(GL20.glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		if(GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS) != 1) {
			System.err.println(GL20.glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		GL20.glValidateProgram(program);
	}
	
	public void bind() {
		GL20.glUseProgram(program);
	}
	
	protected void finalize() {
		GL20.glDetachShader(program, vs);
		GL20.glDetachShader(program, fs);
		GL20.glDeleteShader(vs);
		GL20.glDeleteShader(fs);
		GL20.glDeleteProgram(program);
	}
	
	private String readFile(String filename) {
		StringBuilder string = new StringBuilder();
		BufferedReader br;
		
		try {
			InputStream in = this.getClass().getResourceAsStream("/shaders/" + filename);
			
			br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			
			while((line = br.readLine()) != null) {
				string.append(line);
				string.append("\n");
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return string.toString();
	}
	
	public void setUniform(String name, int value) {
		int location = GL20.glGetUniformLocation(program, name);
		
		if(location != -1) {
			GL20.glUniform1i(location, value);
		}
	}
	
	public void setUniform(String name, Matrix4f value) {
		int location = GL20.glGetUniformLocation(program, name);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		value.get(buffer);
		
		if(location != -1) {
			GL20.glUniformMatrix4fv(location, false, buffer);
		}
	}
}
