package net.laraifox.lib.graphics;

import net.laraifox.lib.math.Matrix4f;
import net.laraifox.lib.math.Quaternion;
import net.laraifox.lib.math.Vector3f;

public class Transform {
	private Vector3f position;
	private Quaternion rotation;
	private Vector3f scale;

	public Transform() {
		this.position = Vector3f.Zero();
		this.rotation = new Quaternion();
		this.scale = Vector3f.One();
	}

	public Matrix4f getTransformationMatrix() {
		Matrix4f translationMatrix = Matrix4f.initializeTranslation(position);
		Matrix4f rotationMatrix = rotation.toRotationMatrix();
		Matrix4f scaleMatrix = Matrix4f.initializeScale(scale.getX(), scale.getY(), scale.getZ());

		return translationMatrix.multiply(rotationMatrix.multiply(scaleMatrix));
	}

	public void translate(float x, float y, float z) {
		position.add(x, y, z);
	}

	public void translate(Vector3f value) {
		position.add(value);
	}

	public void rotate(Vector3f axis, float theta) {
		rotation = new Quaternion(axis, theta).multiply(rotation).normalize();
	}

	public Vector3f getTranslation() {
		return position;
	}

	public void setTranslation(Vector3f translation) {
		this.position = translation;
	}

	public void setTranslation(float x, float y, float z) {
		this.position = new Vector3f(x, y, z);
	}

	public Quaternion getRotation() {
		return rotation;
	}

	public void setRotation(Quaternion rotation) {
		this.rotation = rotation;
	}

	public void setRotation(float x, float y, float z, float w) {
		this.rotation = new Quaternion(x, y, z, w);
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}

	public void setScale(float scale) {
		this.scale = new Vector3f(scale, scale, scale);
	}
}
