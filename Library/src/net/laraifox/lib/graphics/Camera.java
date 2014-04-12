package net.laraifox.lib.graphics;

import net.laraifox.lib.math.Matrix4f;
import net.laraifox.lib.math.Quaternion;
import net.laraifox.lib.math.Vector3f;

public class Camera {
	private Transform transform;

	private PerspectiveProjection perspectiveProjection;

	public Camera() {
		this(new Transform(), new PerspectiveProjection());
	}

	public Camera(Transform transform) {
		this(transform, new PerspectiveProjection());
	}

	public Camera(PerspectiveProjection perspectiveProjection) {
		this(new Transform(), perspectiveProjection);
	}

	public Camera(Transform transform, PerspectiveProjection perspectiveProjection) {
		this.transform = transform;
		this.perspectiveProjection = perspectiveProjection;
	}

	public void move(Vector3f direction, float value) {
		transform.translate(direction.scale(value));
	}

	public void rotate(Vector3f axis, float theta) {
		transform.rotate(axis, theta);
	}

	public Matrix4f getViewMatrix() {
		Matrix4f translationMatrix = Matrix4f.initializeTranslation(Vector3f.negate(transform.getTranslation()));
		Matrix4f rotationMatrix = transform.getRotation().conjugate().toRotationMatrix();

		return rotationMatrix.multiply(translationMatrix);
	}

	public Matrix4f getProjectionMatrix() {
		return perspectiveProjection.getMatrix();
	}

	public Matrix4f getViewProjectionMatrix() {
		return getProjectionMatrix().multiply(getViewMatrix());
	}

	public Vector3f getPosition() {
		return new Vector3f(transform.getTranslation());
	}

	public void setPosition(Vector3f position) {
		this.transform.setTranslation(position);
	}

	public Quaternion getRotation() {
		return new Quaternion(transform.getRotation());
	}

	public void setRotation(Quaternion rotation) {
		this.transform.setRotation(rotation);
	}

	public Vector3f getForward() {
		return transform.getRotation().getForward();
	}

	public Vector3f getUpward() {
		return transform.getRotation().getUpward();
	}

	public Vector3f getRight() {
		return transform.getRotation().getRight();
	}

	public PerspectiveProjection getPerspectiveProjection() {
		return perspectiveProjection;
	}

	public void setPerspectiveProjection(PerspectiveProjection perspectiveProjection) {
		this.perspectiveProjection = perspectiveProjection;
	}
}
