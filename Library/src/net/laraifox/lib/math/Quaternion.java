package net.laraifox.lib.math;

public class Quaternion {
	private float x, y, z, w;

	public Quaternion() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
		this.w = 1.0f;
	}

	public Quaternion(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Quaternion(Vector3f axis, float angle) {
		float sinHalfAngle = (float) Math.sin(angle / 2);
		float cosHalfAngle = (float) Math.cos(angle / 2);

		this.x = axis.getX() * sinHalfAngle;
		this.y = axis.getY() * sinHalfAngle;
		this.z = axis.getZ() * sinHalfAngle;
		this.w = cosHalfAngle;
	}

	public Quaternion(Quaternion quaternion) {
		this.x = quaternion.getX();
		this.y = quaternion.getY();
		this.z = quaternion.getZ();
		this.w = quaternion.getW();
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public Quaternion normalize() {
		float length = length();

		this.x /= length;
		this.y /= length;
		this.z /= length;
		this.w /= length;

		return this;
	}

	public Quaternion conjugate() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;

		return this;
	}

	public Quaternion multiply(Quaternion quaternion) {
		float w_ = this.w * quaternion.getW() - this.x * quaternion.getX() - this.y * quaternion.getY() - this.z * quaternion.getZ();
		float x_ = this.x * quaternion.getW() + this.w * quaternion.getX() + this.y * quaternion.getZ() - this.z * quaternion.getY();
		float y_ = this.y * quaternion.getW() + this.w * quaternion.getY() + this.z * quaternion.getX() - this.x * quaternion.getZ();
		float z_ = this.z * quaternion.getW() + this.w * quaternion.getZ() + this.x * quaternion.getY() - this.y * quaternion.getX();

		return new Quaternion(x_, y_, z_, w_);
	}

	public Quaternion multiply(Vector3f vector) {
		float w_ = -this.x * vector.getX() - this.y * vector.getY() - this.z * vector.getZ();
		float x_ = this.w * vector.getX() + this.y * vector.getZ() - this.z * vector.getY();
		float y_ = this.w * vector.getY() + this.z * vector.getX() - this.x * vector.getZ();
		float z_ = this.w * vector.getZ() + this.x * vector.getY() - this.y * vector.getX();

		return new Quaternion(x_, y_, z_, w_);
	}

	public Quaternion getNormalized() {
		float length = length();
		return new Quaternion(this.x / length, this.y / length, this.z / length, this.w / length);
	}

	public Quaternion getConjugated() {
		return new Quaternion(-this.x, -this.y, -this.z, this.w);
	}

	public Matrix4f toRotationMatrix() {
		Vector3f forward = new Vector3f(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0f - 2.0f * (x * x + y * y));
		Vector3f up = new Vector3f(2.0f * (x * y + w * z), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x));
		Vector3f right = new Vector3f(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));

		return Matrix4f.initializeViewRotation(forward, up);
	}

	public Vector3f getForward() {
		return new Vector3f(0, 0, 1).rotate(this);
	}

	public Vector3f getBackward() {
		return new Vector3f(0, 0, -1).rotate(this);
	}

	public Vector3f getUpward() {
		return new Vector3f(0, 1, 0).rotate(this);
	}

	public Vector3f getDownward() {
		return new Vector3f(0, -1, 0).rotate(this);
	}

	public Vector3f getRight() {
		return new Vector3f(1, 0, 0).rotate(this);
	}

	public Vector3f getLeft() {
		return new Vector3f(-1, 0, 0).rotate(this);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
}
