package net.laraifox.lib.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import net.laraifox.lib.math.Vector4f;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class Texture {
	private static final int BYTES_PER_PIXEL = 4;

	private int textureID;
	private int width, height;

	public Texture() {
		this(0, 0, 0);
	}

	public Texture(Vector4f color) {
		int[] pixels = new int[] { (int) (color.getX() * 0xFF), (int) (color.getY() * 0xFF), (int) (color.getZ() * 0xFF), (int) (color.getW() * 0xFF) };

		this.textureID = Texture.generateTexture(Texture.createByteBuffer(pixels), 1, 1);
		this.width = 1;
		this.height = 1;
	}

	public Texture(Texture texture) {
		this(texture.getTextureID(), texture.getWidth(), texture.getHeight());
	}

	private Texture(int textureID, int width, int height) {
		this.textureID = textureID;
		this.width = width;
		this.height = height;
	}

	public void bindTexture() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}

	public void setTexture(Texture texture) {
		this.textureID = texture.getTextureID();
		this.width = texture.getWidth();
		this.height = texture.getHeight();
	}

	public int getTextureID() {
		return textureID;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static Texture getTextureFrom(BufferedImage image) {
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		return Texture.getTextureFrom(pixels, image.getWidth(), image.getHeight());
	}

	public static Texture getTextureFrom(ByteBuffer buffer, int width, int height) {
		return new Texture(Texture.generateTexture(buffer, width, height), width, height);
	}

	public static Texture getTextureFrom(int[] pixels, int width, int height) {
		return Texture.getTextureFrom(Texture.createByteBuffer(pixels), width, height);
	}

	public static Texture getTextureFrom(String filename) throws IOException {
		return Texture.getTextureFrom(ImageIO.read(new File(filename)));
	}

	private static ByteBuffer createByteBuffer(int[] pixels) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length * BYTES_PER_PIXEL);
		for (int i = 0; i < pixels.length; i++) {
			int pixel = pixels[i];

			buffer.put((byte) ((pixel >> 16) & 0xFF));
			buffer.put((byte) ((pixel >> 8) & 0xFF));
			buffer.put((byte) (pixel & 0xFF));
			buffer.put((byte) ((pixel >> 24) & 0xFF));
		}

		buffer.flip();

		return buffer;
	}

	private static int generateTexture(ByteBuffer buffer, int width, int height) {
		int textureID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

		return textureID;
	}
}
