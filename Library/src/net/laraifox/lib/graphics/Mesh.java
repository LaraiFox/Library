package net.laraifox.lib.graphics;

import net.laraifox.lib.graphics.Vertex3f;
import net.laraifox.lib.util.BufferUtil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class Mesh {
	private int vbo;
	private int ibo;
	private int size;

	public Mesh() {
		this.vbo = GL15.glGenBuffers();
		this.ibo = GL15.glGenBuffers();
		this.size = 0;
	}

	public void addVertices(Vertex3f[] vertices, int[] indices) {
		this.size = indices.length;

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtil.createFlippedFloatBuffer(vertices), GL15.GL_STATIC_DRAW);

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, BufferUtil.createFlippedIntBuffer(indices), GL15.GL_STATIC_DRAW);
	}

	public void render() {
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, Vertex3f.SIZE * 4, 0);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, Vertex3f.SIZE * 4, 12);

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL11.glDrawElements(GL11.GL_TRIANGLES, size, GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
	}
}
