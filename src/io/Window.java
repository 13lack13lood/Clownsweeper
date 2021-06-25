package io;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWImage.Buffer;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import tools.Tool;

public class Window {
	private long window;
	private int width;
	private int height;
	private Input input;
	
	public static void glfwInit() {
		setCallBacks();
		checkGLFWInit();
	}
	
	private static void setCallBacks() {
		GLFW.glfwSetErrorCallback(new GLFWErrorCallback() {
			@Override
			public void invoke(int error, long description) {
				throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
			}
		});	
	}
	
	private static void checkGLFWInit() {
		if(!GLFW.glfwInit()) {
			throw new IllegalStateException("Failed to initialized GLFW.");
		}
	}
	
	public Window(String title, String icon) {
		setWidth(Tool.WIDTH);
		setHeight(Tool.HEIGHT);
		
		createWindow(title, icon);
	}
	
	private void createWindow(String title, String icon) {
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);	
		
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		
		
		if(window == 0) {
			throw new IllegalStateException("Failed to create window.");
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		
		GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - width) / 2);
	
		GLFW.glfwShowWindow(window);
		
		GLFW.glfwMakeContextCurrent(window);

		GLFW.glfwSetWindowIcon(window, loadIcon(icon));
		
		input = new Input(window);
		
		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		Tool.setTileArrays();
	}
	
	private Buffer loadIcon(String icon) {
        try {
        	InputStream in = this.getClass().getResourceAsStream("/res/" + icon);
        	BufferedImage image	= ImageIO.read(in);
			
        	ByteBuffer byteBuffer = Tool.convertImageToByteBuffer(image, image.getWidth(), image.getHeight());
			
			GLFWImage glfwImage = GLFWImage.malloc();
			glfwImage.set(image.getWidth(), image.getHeight(), byteBuffer);
			
			Buffer b = GLFWImage.malloc(1);
			b.put(0, glfwImage);
			
			return b;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
	}

	public boolean shouldClose() {
		if(GLFW.glfwWindowShouldClose(window))
			return true;
		if(input.isKeyPressed(GLFW.GLFW_KEY_ESCAPE))
			return true;
		
		return false;
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	public void update() {
		input.update();
		GLFW.glfwPollEvents();
	}
	
	public void terminate() {
		GLFW.glfwTerminate();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public long getWindow() {
		return window;
	}
	
	public Input getInput() {
		return input;
	}
}
