package net.laraifox.lib.math;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Vector3f {
	public static final int ELEMENT_COUNT = 3;
	public static final int ELEMENT_SIZE = 4;
	public static final int VECTOR_SIZE = 12;

	private float x, y, z;

	public Vector3f() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vector3f(float s) {
		this.x = s;
		this.y = s;
		this.z = s;
	}

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(Vector3f v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

	public Vector3f(Vector3i v) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}

	public Vector3f add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3f add(Vector3f v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public Vector3f subtract(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector3f subtract(Vector3f v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector3f multiply(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vector3f multiply(Vector3f v) {
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		return this;
	}

	public Vector3f scale(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		return this;
	}

	public float dot(Vector3f v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	public Vector3f cross(Vector3f v) {
		float cx = this.getY() * v.getZ() - this.getZ() * v.getY();
		float cy = this.getZ() * v.getX() - this.getX() * v.getZ();
		float cz = this.getX() * v.getY() - this.getY() * v.getX();
		this.x = cx;
		this.y = cy;
		this.z = cz;
		return this;
	}

	public Vector3f rotate(float theta, Vector3f v) {
		float sineHalfTheta = (float) Math.sin(Math.toRadians(theta / 2.0f));
		float cosineHalfTheta = (float) Math.cos(Math.toRadians(theta / 2.0f));

		float rx = v.getX() * sineHalfTheta;
		float ry = v.getY() * sineHalfTheta;
		float rz = v.getZ() * sineHalfTheta;
		float rw = cosineHalfTheta;

		Quaternion rotation = new Quaternion(rx, ry, rz, rw);
		Quaternion conjugate = rotation.getConjugated();
		Quaternion result = rotation.multiply(this).multiply(conjugate);

		this.x = result.getX();
		this.y = result.getY();
		this.z = result.getZ();

		return this;
	}

	public Vector3f rotate(Quaternion rotation) {
		Quaternion conjugate = rotation.conjugate();

		Quaternion result = rotation.multiply(this).multiply(conjugate);

		this.x = result.getX();
		this.y = result.getY();
		this.z = result.getZ();

		return this;
	}

	public Vector3f negate() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		return this;
	}

	public Vector3f normalize() {
		float length = length();
		if (length == 0.0f)
			return this;

		this.x /= length;
		this.y /= length;
		this.z /= length;
		return this;
	}

	public Vector3f absolute() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		return this;
	}

	public Vector3f reverse() {
		float x_ = this.z;
		float y_ = this.y;
		float z_ = this.x;
		this.x = x_;
		this.y = y_;
		this.z = z_;
		return this;
	}

	public boolean equals(Vector3f v) {
		if (this.x == v.getX() && this.y == v.getY() && this.z == v.getZ())
			return true;

		return false;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float lengthSq() {
		return x * x + y * y + z * z;
	}

	public float distanceTo(float x, float y, float z) {
		float dx = x - this.x;
		float dy = y - this.y;
		float dz = z - this.z;
		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public float distanceTo(Vector3f v) {
		float dx = v.x - this.x;
		float dy = v.y - this.y;
		float dz = v.z - this.z;
		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public float distanceSqTo(float x, float y, float z) {
		float dx = x - this.x;
		float dy = y - this.y;
		float dz = z - this.z;
		return dx * dx + dy * dy + dz * dz;
	}

	public float distanceSqTo(Vector3f v) {
		float dx = v.x - this.x;
		float dy = v.y - this.y;
		float dz = v.z - this.z;
		return dx * dx + dy * dy + dz * dz;
	}

	public FloatBuffer toFloatBuffer() {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(ELEMENT_COUNT);
		buffer.put(x);
		buffer.put(y);
		buffer.put(z);
		return buffer;
	}

	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	public String toString(int dp) {
		if (dp < 0)
			dp = 0;
		float power = (int) Math.pow(10, dp);
		float xx = ((int) (x * power)) / power;
		float yy = ((int) (y * power)) / power;
		float zz = ((int) (z * power)) / power;
		return "[" + xx + ", " + yy + ", " + zz + "]";
	}

	public static Vector3f Zero() {
		return new Vector3f(0.0f, 0.0f, 0.0f);
	}

	public static Vector3f One() {
		return new Vector3f(1.0f, 1.0f, 1.0f);
	}

	public static Vector3f Up() {
		return new Vector3f(0.0f, 1.0f, 0.0f);
	}

	public static Vector3f Down() {
		return new Vector3f(0.0f, -1.0f, 0.0f);
	}

	public static Vector3f Left() {
		return new Vector3f(-1.0f, 0.0f, 0.0f);
	}

	public static Vector3f Right() {
		return new Vector3f(1.0f, 0.0f, 0.0f);
	}

	public static Vector3f Forward() {
		return new Vector3f(0.0f, 0.0f, 1.0f);
	}

	public static Vector3f Backward() {
		return new Vector3f(0.0f, 0.0f, -1.0f);
	}

	public static Vector3f add(Vector3f u, Vector3f v) {
		float x = u.getX() + v.getX();
		float y = u.getY() + v.getY();
		float z = u.getZ() + v.getZ();
		return new Vector3f(x, y, z);
	}

	public static Vector3f subtract(Vector3f u, Vector3f v) {
		float x = u.getX() - v.getX();
		float y = u.getY() - v.getY();
		float z = u.getZ() - v.getZ();
		return new Vector3f(x, y, z);
	}

	public static Vector3f multiply(Vector3f u, Vector3f v) {
		float x = u.getX() * v.getX();
		float y = u.getY() * v.getY();
		float z = u.getZ() * v.getZ();
		return new Vector3f(x, y, z);
	}

	public static Vector3f scale(Vector3f u, float scalar) {
		float x = u.getX() * scalar;
		float y = u.getY() * scalar;
		float z = u.getZ() * scalar;
		return new Vector3f(x, y, z);
	}

	public static float dot(Vector3f u, Vector3f v) {
		return u.getX() * v.getX() + u.getY() * v.getY() + u.getZ() * v.getZ();
	}

	public static Vector3f cross(Vector3f u, Vector3f v) {
		float x = u.getY() * v.getZ() - u.getZ() * v.getY();
		float y = u.getZ() * v.getX() - u.getX() * v.getZ();
		float z = u.getX() * v.getY() - u.getY() * v.getX();
		return new Vector3f(x, y, z);
	}

	public static Vector3f negate(Vector3f u) {
		float x = -u.getX();
		float y = -u.getY();
		float z = -u.getZ();
		return new Vector3f(x, y, z);
	}

	public static Vector3f normalize(Vector3f u) {
		float length = (float) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ());
		if (length == 0.0f)
			return u;

		float x = u.x / length;
		float y = u.y / length;
		float z = u.z / length;
		return new Vector3f(x, y, z);
	}

	public static Vector3f absolute(Vector3f v) {
		float x = Math.abs(v.x);
		float y = Math.abs(v.y);
		float z = Math.abs(v.z);
		return new Vector3f(x, y, z);
	}

	public static Vector3f reverse(Vector3f u) {
		return new Vector3f(u.getZ(), u.getY(), u.getX());
	}

	public static float length(Vector3f u) {
		return (float) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ());
	}

	public static float lengthSq(Vector3f u) {
		return u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ();
	}

	public static float distanceBetween(Vector3f u, Vector3f v) {
		float dx = v.getX() - u.getX();
		float dy = v.getY() - u.getY();
		float dz = v.getZ() - u.getZ();
		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public static float distanceSqBetween(Vector3f u, Vector3f v) {
		float dx = v.getX() - u.getX();
		float dy = v.getY() - u.getY();
		float dz = v.getZ() - u.getZ();
		return dx * dx + dy * dy + dz * dz;
	}

	public static Vector3f sum(Vector3f... u) {
		float x = 0.0f;
		float y = 0.0f;
		float z = 0.0f;
		for (int i = 0; i < u.length; i++) {
			x += u[i].getX();
			y += u[i].getY();
			z += u[i].getZ();
		}
		return new Vector3f(x, y, z);
	}

	public Vector3f get() {
		return new Vector3f(this);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public Vector2f getXY() {
		return new Vector2f(x, y);
	}

	public Vector2f getXZ() {
		return new Vector2f(x, z);
	}

	public Vector2f getYZ() {
		return new Vector2f(y, z);
	}

	public Vector3f set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector3f set(Vector3f v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	public Vector3f set(Vector3i v) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
		return this;
	}

	public Vector3f setX(float x) {
		this.x = x;
		return this;
	}

	public Vector3f setY(float y) {
		this.y = y;
		return this;
	}

	public Vector3f setZ(float z) {
		this.z = z;
		return this;
	}

	public Vector3f setXY(Vector2f v) {
		this.x = v.getX();
		this.y = v.getY();
		return this;
	}

	public Vector3f setXZ(Vector2f v) {
		this.x = v.getX();
		this.z = v.getY();
		return this;
	}

	public Vector3f setYZ(Vector2f v) {
		this.y = v.getX();
		this.z = v.getY();
		return this;
	}
}
