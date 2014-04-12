package net.laraifox.lib.graphics;

import net.laraifox.lib.math.Vector2f;
import net.laraifox.lib.math.Vector3f;

public class Vertex3f {
	public static final int SIZE = 5;

	private Vector3f position;
	private Vector2f textureCoord;

	public Vertex3f(Vector3f position) {
		this(position, Vector2f.Zero());
	}

	public Vertex3f(Vector3f position, Vector2f textureCoord) {
		this.position = position;
		this.textureCoord = textureCoord;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector2f getTextureCoord() {
		return textureCoord;
	}

	public void setTextureCoord(Vector2f textureCoord) {
		this.textureCoord = textureCoord;
	}
}
