package net.laraifox.lib.util;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import net.laraifox.lib.graphics.Vertex3f;
import net.laraifox.lib.math.Matrix4f;

import org.lwjgl.BufferUtils;

public class BufferUtil {
	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}

	public static FloatBuffer createFlippedFloatBuffer(Vertex3f[] vertices) {
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex3f.SIZE);
		for (int i = 0; i < vertices.length; i++) {
			buffer.put(vertices[i].getPosition().getX());
			buffer.put(vertices[i].getPosition().getY());
			buffer.put(vertices[i].getPosition().getZ());

			buffer.put(vertices[i].getTextureCoord().getX());
			buffer.put(vertices[i].getTextureCoord().getY());
		}
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer createFlippedFloatBuffer(Matrix4f matrix) {
		FloatBuffer buffer = createFloatBuffer(16);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buffer.put(matrix.getDataAt(i, j));
			}
		}
		buffer.flip();
		return buffer;
	}

	public static IntBuffer createIntBuffer(int size) {
		return BufferUtils.createIntBuffer(size);
	}

	public static IntBuffer createFlippedIntBuffer(int... indices) {
		IntBuffer buffer = createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();
		return buffer;
	}
}
