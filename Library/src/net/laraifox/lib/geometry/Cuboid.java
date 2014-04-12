package net.laraifox.lib.geometry;

import net.laraifox.lib.graphics.Mesh;
import net.laraifox.lib.graphics.Vertex3f;
import net.laraifox.lib.math.Vector3f;
import net.laraifox.lib.util.NumberUtil;

public class Cuboid {
	private Vector3f position;
	private Vector3f size;

	public Cuboid() {
		this(new Vector3f(), new Vector3f());
	}

	public Cuboid(float width, float height, float length) {
		this(new Vector3f(), new Vector3f(width, height, length));
	}

	public Cuboid(Vector3f size) {
		this(new Vector3f(), size);
	}

	public Cuboid(float x, float y, float z, float width, float height, float length) {
		this(new Vector3f(x, y, z), new Vector3f(width, height, length));
	}

	public Cuboid(Vector3f position, Vector3f size) {
		this.position = position;
		this.size = size;
	}

	public Cuboid(Cuboid cuboid) {
		this(cuboid.getPosition(), cuboid.getSize());
	}

	public boolean intersects(Vector3f vector) {
		return false;
	}

	public boolean intersects(Cuboid cuboid) {
		return false;
	}

	public boolean intersects(Ellipse ellipse) {
		return false;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(float x, float y, float z) {
		this.position = new Vector3f(x, y, z);
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getX() {
		return position.getX();
	}

	public void setX(float x) {
		this.position.setX(x);
	}

	public float getY() {
		return position.getY();
	}

	public void setY(float y) {
		this.position.setY(y);
	}

	public float getZ() {
		return position.getZ();
	}

	public void setZ(float z) {
		this.position.setZ(z);
	}

	public Vector3f getSize() {
		return size;
	}

	public void setSize(float width, float height, float length) {
		this.size = new Vector3f(width, height, length);
	}

	public void setSize(Vector3f size) {
		this.size = size;
	}

	public float getWidth() {
		return size.getX();
	}

	public void setWidth(float width) {
		this.size.setX(width);
	}

	public float getHeight() {
		return size.getY();
	}

	public void setHeight(float height) {
		this.size.setY(height);
	}

	public float getLength() {
		return size.getZ();
	}

	public void setLength(float length) {
		this.size.setZ(length);
	}

	public static Mesh getBasicMesh() {
		Mesh mesh = new Mesh();
		Vertex3f[] vertices = new Vertex3f[] {
				new Vertex3f(new Vector3f(0.5f, -0.5f, -0.5f)), new Vertex3f(new Vector3f(0.5f, -0.5f, 0.5f)), new Vertex3f(new Vector3f(-0.5f, -0.5f, 0.5f)),
				new Vertex3f(new Vector3f(-0.5f, -0.5f, -0.5f)), new Vertex3f(new Vector3f(0.5f, 0.5f, -0.5f)), new Vertex3f(new Vector3f(0.5f, 0.5f, 0.5f)),
				new Vertex3f(new Vector3f(-0.5f, 0.5f, 0.5f)), new Vertex3f(new Vector3f(-0.5f, 0.5f, -0.5f)) };
		Integer[] indices = new Integer[] { 0, 1, 2, 4, 7, 6, 0, 4, 5, 1, 5, 2, 2, 6, 3, 4, 0, 3, 3, 0, 2, 5, 4, 6, 1, 0, 5, 5, 6, 2, 6, 7, 3, 7, 4, 3 };
		mesh.addVertices(vertices, NumberUtil.toIntArray(indices));
		return mesh;
	}
}
