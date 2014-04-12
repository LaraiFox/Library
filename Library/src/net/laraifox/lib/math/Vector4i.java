package net.laraifox.lib.math;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Vector4i {
	public static final int ELEMENT_COUNT = 4;
	public static final int ELEMENT_SIZE = 4;
	public static final int VECTOR_SIZE = 16;

	private int x, y, z, w;

	public Vector4i() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}

	public Vector4i(int s) {
		this.x = s;
		this.y = s;
		this.z = s;
		this.w = s;
	}

	public Vector4i(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vector4i(Vector3i v, int w) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
		this.w = w;
	}

	public Vector4i(Vector4i v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = v.w;
	}

	public Vector4i(Vector4f v) {
		this.x = (int) v.getX();
		this.y = (int) v.getY();
		this.z = (int) v.getZ();
		this.w = (int) v.getW();
	}

	public Vector4i add(int x, int y, int z, int w) {
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		return this;
	}

	public Vector4i add(Vector4i v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		this.w += v.w;
		return this;
	}

	public Vector4i subtract(int x, int y, int z, int w) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;
		return this;
	}

	public Vector4i subtract(Vector4i v) {
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		this.w -= v.w;
		return this;
	}

	public Vector4i multiply(int x, int y, int z, int w) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		this.w *= w;
		return this;
	}

	public Vector4i multiply(Vector4i v) {
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		this.w *= v.w;
		return this;
	}

	public Vector4i scale(int scalar) {
		this.x *= scalar;
		this.y *= scalar;
		this.z *= scalar;
		this.w *= scalar;
		return this;
	}

	public int dot(Vector4i v) {
		return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
	}

	/**
	 * WARNING: This method is not currently implemented and will always return null. Do not use!
	 * 
	 * @param v
	 * @return
	 */
	public Vector4i cross(Vector4i v) {
		return null;
	}

	/**
	 * WARNING: This method is not currently implemented and will always return null. Do not use!
	 * 
	 * @param theta
	 * @param v
	 * @return
	 */
	public Vector4i rotate(int theta, Vector4i v) {
		return null;
	}

	public Vector4i negate() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		this.w = -this.w;
		return this;
	}

	public Vector4i normalize() {
		int length = length();
		if (length == 0)
			return this;

		this.x /= length;
		this.y /= length;
		this.z /= length;
		this.w /= length;
		return this;
	}

	public Vector4i absolute() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		this.w = Math.abs(this.w);
		return this;
	}

	public Vector4i reverse() {
		int x_ = this.w;
		int y_ = this.z;
		int z_ = this.y;
		int w_ = this.x;
		this.x = x_;
		this.y = y_;
		this.z = z_;
		this.w = w_;
		return this;
	}

	public boolean equals(Vector4i v) {
		if (this.x == v.getX() && this.y == v.getY() && this.z == v.getZ() && this.w == v.getW())
			return true;

		return false;
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public int lengthSq() {
		return x * x + y * y + z * z + w * w;
	}

	public int distanceTo(int x, int y, int z, int w) {
		int dx = x - this.x;
		int dy = y - this.y;
		int dz = z - this.z;
		int dw = w - this.w;
		return (int) Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
	}

	public int distanceTo(Vector4i v) {
		int dx = v.x - this.x;
		int dy = v.y - this.y;
		int dz = v.z - this.z;
		int dw = v.w - this.w;
		return (int) Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
	}

	public int distanceSqTo(int x, int y, int z, int w) {
		int dx = x - this.x;
		int dy = y - this.y;
		int dz = z - this.z;
		int dw = w - this.w;
		return dx * dx + dy * dy + dz * dz + dw * dw;
	}

	public int distanceSqTo(Vector4i v) {
		int dx = v.x - this.x;
		int dy = v.y - this.y;
		int dz = v.z - this.z;
		int dw = v.w - this.w;
		return dx * dx + dy * dy + dz * dz + dw * dw;
	}

	public FloatBuffer toFloatBuffer() {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(ELEMENT_COUNT);
		buffer.put(x);
		buffer.put(y);
		buffer.put(z);
		buffer.put(w);
		return buffer;
	}

	public String toString() {
		return "[" + x + ", " + y + ", " + z + ", " + w + "]";
	}

	public String toString(int dp) {
		if (dp < 0)
			dp = 0;
		int power = (int) Math.pow(10, dp);
		int xx = ((int) (x * power)) / power;
		int yy = ((int) (y * power)) / power;
		int zz = ((int) (z * power)) / power;
		int ww = ((int) (w * power)) / power;
		return "[" + xx + ", " + yy + ", " + zz + ", " + ww + "]";
	}

	public static Vector4i Zero() {
		return new Vector4i(0, 0, 0, 0);
	}

	public static Vector4i One() {
		return new Vector4i(1, 1, 1, 1);
	}

	public static Vector4i Up() {
		return new Vector4i(0, 1, 0, 0);
	}

	public static Vector4i Down() {
		return new Vector4i(0, -1, 0, 0);
	}

	public static Vector4i Left() {
		return new Vector4i(-1, 0, 0, 0);
	}

	public static Vector4i Right() {
		return new Vector4i(1, 0, 0, 0);
	}

	public static Vector4i Forward() {
		return new Vector4i(0, 0, 1, 0);
	}

	public static Vector4i Backward() {
		return new Vector4i(0, 0, -1, 0);
	}

	public static Vector4i Ana() {
		return new Vector4i(0, 0, 0, 1);
	}

	public static Vector4i Kata() {
		return new Vector4i(0, 0, 0, -1);
	}

	public static Vector4i add(Vector4i u, Vector4i v) {
		int x = u.getX() + v.getX();
		int y = u.getY() + v.getY();
		int z = u.getZ() + v.getZ();
		int w = u.getW() + v.getW();
		return new Vector4i(x, y, z, w);
	}

	public static Vector4i subtract(Vector4i u, Vector4i v) {
		int x = u.getX() - v.getX();
		int y = u.getY() - v.getY();
		int z = u.getZ() - v.getZ();
		int w = u.getW() - v.getW();
		return new Vector4i(x, y, z, w);
	}

	public static Vector4i multiply(Vector4i u, Vector4i v) {
		int x = u.getX() * v.getX();
		int y = u.getY() * v.getY();
		int z = u.getZ() * v.getZ();
		int w = u.getW() * v.getW();
		return new Vector4i(x, y, z, w);
	}

	public static Vector4i scale(Vector4i u, int scalar) {
		int x = u.getX() * scalar;
		int y = u.getY() * scalar;
		int z = u.getZ() * scalar;
		int w = u.getW() * scalar;
		return new Vector4i(x, y, z, w);
	}

	public static int dot(Vector4i u, Vector4i v) {
		return u.getX() * v.getX() + u.getY() * v.getY() + u.getZ() * v.getZ() + u.getW() * v.getW();
	}

	/**
	 * WARNING: This method is not currently implemented and will always return null. Do not use!
	 * 
	 * @param u
	 * @param v
	 * @return
	 */
	public static Vector4i cross(Vector4i u, Vector4i v) {
		return null;
	}

	public static Vector4i negate(Vector4i u) {
		int x = -u.getX();
		int y = -u.getY();
		int z = -u.getZ();
		int w = -u.getW();
		return new Vector4i(x, y, z, w);
	}

	public static Vector4i normalize(Vector4i u) {
		int length = (int) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ());
		if (length == 0)
			return u;

		int x = u.x / length;
		int y = u.y / length;
		int z = u.z / length;
		int w = u.z / length;
		return new Vector4i(x, y, z, w);
	}

	public static Vector4i absolute(Vector4i v) {
		int x = Math.abs(v.x);
		int y = Math.abs(v.y);
		int z = Math.abs(v.z);
		int w = Math.abs(v.w);
		return new Vector4i(x, y, z, w);
	}

	public static Vector4i reverse(Vector4i u) {
		return new Vector4i(u.getW(), u.getZ(), u.getY(), u.getX());
	}

	public static int length(Vector4i u) {
		return (int) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ() + u.getW() * u.getW());
	}

	public static int lengthSq(Vector4i u) {
		return u.getX() * u.getX() + u.getY() * u.getY() + u.getZ() * u.getZ() + u.getW() * u.getW();
	}

	public static int distanceBetween(Vector4i u, Vector4i v) {
		int dx = v.getX() - u.getX();
		int dy = v.getY() - u.getY();
		int dz = v.getZ() - u.getZ();
		int dw = v.getW() - u.getW();
		return (int) Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
	}

	public static int distanceSqBetween(Vector4i u, Vector4i v) {
		int dx = v.getX() - u.getX();
		int dy = v.getY() - u.getY();
		int dz = v.getZ() - u.getZ();
		int dw = v.getW() - u.getW();
		return dx * dx + dy * dy + dz * dz + dw * dw;
	}

	public static Vector4i sum(Vector4i... u) {
		int x = 0;
		int y = 0;
		int z = 0;
		int w = 0;
		for (int i = 0; i < u.length; i++) {
			x += u[i].getX();
			y += u[i].getY();
			z += u[i].getZ();
			w += u[i].getW();
		}
		return new Vector4i(x, y, z, w);
	}

	public Vector4i get() {
		return new Vector4i(this);
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

	public int getW() {
		return w;
	}

	public Vector2i getXY() {
		return new Vector2i(x, y);
	}

	public Vector2i getXZ() {
		return new Vector2i(x, z);
	}

	public Vector2i getXW() {
		return new Vector2i(x, w);
	}

	public Vector2i getYZ() {
		return new Vector2i(y, z);
	}

	public Vector2i getYW() {
		return new Vector2i(y, w);
	}

	public Vector2i getZW() {
		return new Vector2i(z, w);
	}

	public Vector3i getXYZ() {
		return new Vector3i(x, y, z);
	}

	public Vector3i getXYW() {
		return new Vector3i(x, y, w);
	}

	public Vector3i getXZW() {
		return new Vector3i(x, z, w);
	}

	public Vector3i getYZW() {
		return new Vector3i(y, z, w);
	}

	public Vector4i set(int s) {
		this.x = s;
		this.y = s;
		this.z = s;
		this.w = s;
		return this;
	}

	public Vector4i set(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	public Vector4i set(Vector3i v, int w) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
		this.w = w;
		return this;
	}

	public Vector4i set(Vector4i v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = v.w;
		return this;
	}

	public Vector4i set(Vector4f v) {
		this.x = (int) v.getX();
		this.y = (int) v.getY();
		this.z = (int) v.getZ();
		this.w = (int) v.getW();
		return this;
	}

	public Vector4i setX(int x) {
		this.x = x;
		return this;
	}

	public Vector4i setY(int y) {
		this.y = y;
		return this;
	}

	public Vector4i setZ(int z) {
		this.z = z;
		return this;
	}

	public Vector4i setW(int w) {
		this.w = w;
		return this;
	}

	public Vector4i setXY(Vector2i v) {
		this.x = v.getX();
		this.y = v.getY();
		return this;
	}

	public Vector4i setXZ(Vector2i v) {
		this.x = v.getX();
		this.z = v.getY();
		return this;
	}

	public Vector4i setXW(Vector2i v) {
		this.x = v.getX();
		this.w = v.getY();
		return this;
	}

	public Vector4i setYZ(Vector2i v) {
		this.y = v.getX();
		this.z = v.getY();
		return this;
	}

	public Vector4i setYW(Vector2i v) {
		this.y = v.getX();
		this.w = v.getY();
		return this;
	}

	public Vector4i setZW(Vector2i v) {
		this.z = v.getX();
		this.w = v.getY();
		return this;
	}

	public Vector4i setXYZ(Vector3i v) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
		return this;
	}

	public Vector4i setXYW(Vector3i v) {
		this.x = v.getX();
		this.y = v.getY();
		this.w = v.getZ();
		return this;
	}

	public Vector4i setXZW(Vector3i v) {
		this.x = v.getX();
		this.z = v.getY();
		this.w = v.getZ();
		return this;
	}

	public Vector4i setYZW(Vector3i v) {
		this.y = v.getX();
		this.z = v.getY();
		this.w = v.getZ();
		return this;
	}
}
