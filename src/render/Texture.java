package render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;

import tools.Tool;

public class Texture {
	private int id, width, height;
	private BufferedImage image;
	private ByteBuffer pixels;
	
	public Texture(String filepath) {
		try {
			InputStream in = this.getClass().getResourceAsStream("/res/" + filepath);
			image = ImageIO.read(in);
			width = image.getWidth();
			height = image.getHeight();
						
			pixels = Tool.convertImageToByteBuffer(image, width, height);
			
			id = GL11.glGenTextures();
			
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
			
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void finalized() throws Throwable {
		GL11.glDeleteTextures(id);
	}
	
	public void bind(int sampler) {
		if(sampler >= 0 && sampler <= 31) {
			GL14.glActiveTexture(GL13.GL_TEXTURE0 + sampler);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		}
	}
	

}
