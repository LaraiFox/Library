package net.laraifox.lib.math;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Vector3i {
	public static final int ELEMENT_COUNT = 3;
	public static final int ELEMENT_SIZE = 4;
	public static final int VECTOR_SIZE = 12;

	private int x, y, z;

	public Vector3i() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vector3i(int s) {
		this.x = s;
		this.y = s;
		this.z = s;
	}

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i(Vector3i v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

	public Vector3i(Vector3f v) {
		this.x = (int) v.getX();
		this.y = (int) v.getY();
		this.z = (int) v.getZ();
	}

	public Vector3i add(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3i add(Vector3i v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
	}

	public Vector3i subtract(int x, int y, int z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector3i subtract(Vector3i v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
	}

	public Vector3i multiply(int x, int y, int z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vector3i multiply(Vector3i v) {
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		return this;
	}

	public Vector3i scale(int scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		return this;
	}

	public int dot(Vector3i v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	public Vector3i cross(Vector3i v) {
		int cx = this.getY() * v.getZ() - this.getZ() * v.getY();
		int cy = this.getZ() * v.getX() - this.getX() * v.getZ();
		int cz = this.getX() * v.getY() - this.getY() * v.getX();
		this.x = cx;
		this.y = cy;
		this.z = cz;
		return this;
	}

	public Vector3i rotate(int theta, Vector3i v) {
		int sineHalfTheta = (int) Math.sin(Math.toRadians(theta / 2));
		int cosineHalfTheta = (int) Math.cos(Math.toRadians(theta / 2));

		int rx = v.getX() * sineHalfTheta;
		int ry = v.getY() * sineHalfTheta;
		int rz = v.getZ() * sineHalfTheta;
		int rw = cosineHalfTheta;

		Quaternion rotation = new Quaternion(rx, ry, rz, rw);
		Quaternion conjugate = rotation.getConjugated();
		Quaternion result = rotation.multiply(new Vector3f(this)).multiply(conjugate);

		this.x = (int) result.getX();
		this.y = (int) result.getY();
		this.z = (int) result.getZ();

		return this;
	}

	public Vector3i rotate(Quaternion rotation) {
		Quaternion conjugate = rotation.conjugate();

		Quaternion result = rotation.multiply(new Vector3f(this)).multiply(conjugate);

		this.x = (int) result.getX();
		this.y = (int) result.getY();
		this.z = (int) result.getZ();

		return this;
	}

	public Vector3i negate() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		return this;
	}

	public Vector3i normalize() {
		int length = length();
		if (length == 0)
			return this;

		this.x /= length;
		this.y /= length;
		this.z /= length;
		return this;
	}

	public Vector3i absolute() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		return this;
	}

	public Vector3i reverse() {
		int x_ = this.z;
		int y_ = this.y;
		int z_ = this.x;
		this.x = x_;
		this.y = y_;
		this.z = z_;
		return this;
	}

	public boolean equals(Vector3i v) {
		if (this.x == v.getX() && this.y == v.getY() && this.z == v.getZ())
			return true;

		return false;
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y + z * z);
	}

	public int lengthSq() {
		return x * x + y * y + z * z;
	}

	public int distanceTo(int x, int y, int z) {
		int dx = x - this.x;
		int dy = y - this.y;
		int dz = z - this.z;
		return (int) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public int distanceTo(Vector3i v) {
		int dx = v.x - this.x;
		int dy = v.y - this.y;
		int dz = v.z - this.z;
		return (int) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public int distanceSqTo(int x, int y, int z) {
		int dx = x - this.x;
		int dy = y - this.y;
		int dz = z - this.z;
		return dx * dx + dy * dy + dz * dz;
	}

	public int distanceSqTo(Vector3i v) {
		int dx = v.x - this.x;
		int dy = v.y - this.y;
		int dz = v.z - this.z;
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
		int power = (int) Math.pow(10, dp);
		int xx = ((int) (x * power)) / power;
		int yy = ((int) (y * power)) / power;
		int zz = ((int) (z * power)) / power;
		return "[" + xx + ", " + yy + ", " + zz + "]";
	}

	public static Vector3i Zero() {
		return new Vector3i(0, 0, 0);
	}

	public static Vector3i One() {
		return new Vector3i(1, 1, 1);
	}

	public static Vector3i Up() {
		return new Vector3i(0, 1, 0);
	}

	public static Vector3i Down() {
		return new Vector3i(0, -1, 0);
	}

	public static Vector3i Left() {
		return new Vector3i(-1, 0, 0);
	}

	public static Vector3i Right() {
		return new Vector3i(1, 0, 0);
	}

	public static Vector3i Forward() {
		return new Vector3i(0, 0, 1);
	}

	public static Vector3i Backward() {
		return new Vector3i(0, 0, -1);
	}

	public static Vector3i add(Vector3i u, Vector3i v) {
		int x = u.getX() + v.getX();
		int y = u.getY() + v.getY();
		int z = u.getZ() + v.getZ();
		return new Vector3i(x, y, z);
	}

	public static Vector3i subtract(Vector3i u, Vector3i v) {
		int x = u.getX() - v.getX();
		int y = u.getY() - v.getY();
		int z = u.getZ() - v.getZ();
		return new Vector3i(x, y, z);
	}

	public static Vector3i multiply(Vector3i u, Vector3i v) {
		int x = u.getX() * v.getX();
		int y = u.getY() * v.getY();
		int z = u.getZ() * v.getZ();
		return new Vector3i(x, y, z);
	}

	public static Vector3i scale(Vector3i u, int scalar) {
		int x = u.getX() * scalar;
		int y = u.getY() * scalar;
		int z = u.getZ() * scalar;
		return new Vector3i(x, y, z);
	}

	public static int dot(Vector3i u, Vector3i v) {
		return u.getX() * v.getX() + u.getY() * v.getY() + u.getZ() * v.getZ();
	}

	public static Vector3i cross(Vector3i u, Vector3i v) {
		int x = u.getY() * v.getZ() - u.getZ() * v.getY();
		int y = u.getZ() * v.getX() - u.getX() * v.getZ();
		int z = u.getX() * v.getY() - u.getY() * v.getX();
		return new Vector3i(x, y, z);
	}

	public static Vector3i negate(Vector3i u) {
		int x = -u.getX();
		int y = -u.getY();
		int z = -u.getZ();
		return new Vector3i(x, y, z);
	}

	public static Vector3i normalize(Vector3i u) {
		int length = (int) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ());
		if (length == 0)
			return u;

		int x = u.x / length;
		int y = u.y / length;
		int z = u.z / length;
		return new Vector3i(x, y, z);
	}

	public static Vector3i absolute(Vector3i v) {
		int x = Math.abs(v.x);
		int y = Math.abs(v.y);
		int z = Math.abs(v.z);
		return new Vector3i(x, y, z);
	}

	public static Vector3i reverse(Vector3i u) {
		return new Vector3i(u.getZ(), u.getY(), u.getX());
	}

	public static int length(Vector3i u) {
		return (int) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ());
	}

	public static int lengthSq(Vector3i u) {
		return u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ();
	}

	public static int distanceBetween(Vector3i u, Vector3i v) {
		int dx = v.getX() - u.getX();
		int dy = v.getY() - u.getY();
		int dz = v.getZ() - u.getZ();
		return (int) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public static int distanceSqBetween(Vector3i u, Vector3i v) {
		int dx = v.getX() - u.getX();
		int dy = v.getY() - u.getY();
		int dz = v.getZ() - u.getZ();
		return dx * dx + dy * dy + dz * dz;
	}

	public static Vector3i sum(Vector3i... u) {
		int x = 0;
		int y = 0;
		int z = 0;
		for (int i = 0; i < u.length; i++) {
			x += u[i].getX();
			y += u[i].getY();
			z += u[i].getZ();
		}
		return new Vector3i(x, y, z);
	}

	public Vector3i get() {
		return new Vector3i(this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Vector2i getXY() {
		return new Vector2i(x, y);
	}

	public Vector2i getXZ() {
		return new Vector2i(x, z);
	}

	public Vector2i getYZ() {
		return new Vector2i(y, z);
	}

	public Vector3i set(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector3i set(Vector3i v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}

	public Vector3i set(Vector3f v) {
		this.x = (int) v.getX();
		this.y = (int) v.getY();
		this.z = (int) v.getZ();
		return this;
	}

	public Vector3i setX(int x) {
		this.x = x;
		return this;
	}

	public Vector3i setY(int y) {
		this.y = y;
		return this;
	}

	public Vector3i setZ(int z) {
		this.z = z;
		return this;
	}

	public Vector3i setXY(Vector2i v) {
		this.x = v.getX();
		this.y = v.getY();
		return this;
	}

	public Vector3i setXZ(Vector2i v) {
		this.x = v.getX();
		this.z = v.getY();
		return this;
	}

	public Vector3i setYZ(Vector2i v) {
		this.y = v.getX();
		this.z = v.getY();
		return this;
	}
}
