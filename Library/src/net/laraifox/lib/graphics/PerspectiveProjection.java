package net.laraifox.lib.graphics;

import java.nio.FloatBuffer;

import net.laraifox.lib.math.Matrix4f;
import net.laraifox.lib.util.BufferUtil;

import org.lwjgl.opengl.GL11;

public class PerspectiveProjection {
	private float fieldOfView;
	private float width, height;
	private float zNear, zFar;

	public PerspectiveProjection() {
		this(45.0f, 1.0f, 1.0f, 0.0f, 1.0f);
	}

	public PerspectiveProjection(float fieldOfView, float width, float height, float zNear, float zFar) {
		this.set(fieldOfView, width, height, zNear, zFar);
	}

	public PerspectiveProjection(PerspectiveProjection perspectiveProjection) {
		this.set(perspectiveProjection);
	}

	public void glApplyProjection() {
		Matrix4f projectionMatrix = Matrix4f.initializeProjection(fieldOfView, width, height, zNear, zFar);
		FloatBuffer buffer = BufferUtil.createFlippedFloatBuffer(projectionMatrix);
		GL11.glMultMatrix(buffer);
	}

	public Matrix4f getMatrix() {
		return Matrix4f.initializeProjection(fieldOfView, width, height, zNear, zFar);
	}

	public PerspectiveProjection get() {
		return new PerspectiveProjection(this);
	}

	public void set(PerspectiveProjection perspectiveProjection) {
		this.fieldOfView = perspectiveProjection.getFieldOfView();
		this.width = perspectiveProjection.getWidth();
		this.height = perspectiveProjection.getHeight();
		this.zNear = perspectiveProjection.getZNear();
		this.zFar = perspectiveProjection.getZFar();
	}

	public void set(float fieldOfView, float width, float height, float zNear, float zFar) {
		this.fieldOfView = fieldOfView;
		this.width = width;
		this.height = height;
		this.zNear = zNear;
		this.zFar = zFar;
	}

	public float getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(float fieldOfView) {
		this.fieldOfView = fieldOfView;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getZNear() {
		return zNear;
	}

	public void setZNear(float zNear) {
		this.zNear = zNear;
	}

	public float getZFar() {
		return zFar;
	}

	public void setZFar(float zFar) {
		this.zFar = zFar;
	}
}
