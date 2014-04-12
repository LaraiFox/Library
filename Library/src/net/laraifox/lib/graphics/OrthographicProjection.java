package net.laraifox.lib.graphics;

import java.nio.FloatBuffer;

import net.laraifox.lib.math.Matrix4f;
import net.laraifox.lib.util.BufferUtil;

import org.lwjgl.opengl.GL11;

public class OrthographicProjection {
	private float left, right;
	private float bottom, top;
	private float near, far;

	public OrthographicProjection() {
		this(-1, 1, -1, 1, -1, 1);
	}

	public OrthographicProjection(float near, float far) {
		this(-1, 1, -1, 1, near, far);
	}

	public OrthographicProjection(float width, float height, boolean flip) {
		this(0, width, 0, height, -1, 1);
	}

	public OrthographicProjection(float left, float right, float bottom, float top, float near, float far) {
		this.set(left, right, bottom, top, near, far);
	}

	public OrthographicProjection(OrthographicProjection orthographicProjection) {
		this.set(orthographicProjection);
	}

	public void glApplyProjection() {
		Matrix4f projectionMatrix = Matrix4f.initializeProjection(left, right, bottom, top, near, far);
		FloatBuffer buffer = BufferUtil.createFlippedFloatBuffer(projectionMatrix);
		GL11.glMultMatrix(buffer);
	}

	public Matrix4f getMatrix() {
		return Matrix4f.initializeProjection(left, right, bottom, top, near, far);
	}

	public OrthographicProjection get() {
		return new OrthographicProjection(this);
	}

	public void set(OrthographicProjection orthographicProjection) {
		this.left = orthographicProjection.getLeft();
		this.right = orthographicProjection.getRight();
		this.bottom = orthographicProjection.getBottom();
		this.top = orthographicProjection.getTop();
		this.near = orthographicProjection.getNear();
		this.far = orthographicProjection.getFar();
	}

	public void set(float left, float right, float bottom, float top, float near, float far) {
		this.left = left;
		this.right = right;
		this.bottom = bottom;
		this.top = top;
		this.near = near;
		this.far = far;
	}

	public float getLeft() {
		return left;
	}

	public void setLeft(float left) {
		this.left = left;
	}

	public float getRight() {
		return right;
	}

	public void setRight(float right) {
		this.right = right;
	}

	public float getBottom() {
		return bottom;
	}

	public void setBottom(float bottom) {
		this.bottom = bottom;
	}

	public float getTop() {
		return top;
	}

	public void setTop(float top) {
		this.top = top;
	}

	public float getNear() {
		return near;
	}

	public void setNear(float near) {
		this.near = near;
	}

	public float getFar() {
		return far;
	}

	public void setFar(float far) {
		this.far = far;
	}
}
