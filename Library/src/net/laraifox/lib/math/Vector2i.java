package net.laraifox.lib.math;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Vector2i {
	public static final int ELEMENT_COUNT = 2;
	public static final int ELEMENT_SIZE = 4;
	public static final int VECTOR_SIZE = 8;

	private int x, y;

	public Vector2i() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2i(int s) {
		this.x = s;
		this.y = s;
	}

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2i(Vector2i v) {
		this.x = v.x;
		this.y = v.y;
	}

	public Vector2i(Vector2f v) {
		this.x = (int) v.getX();
		this.y = (int) v.getY();
	}

	public Vector2i add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2i add(Vector2i v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}

	public Vector2i subtract(int x, int y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector2i subtract(Vector2i v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}

	public Vector2i multiply(int x, int y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public Vector2i multiply(Vector2i v) {
		this.x *= v.x;
		this.y *= v.y;
		return this;
	}

	public Vector2i scale(int scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}

	public int dot(Vector2i v) {
		return this.x * v.x + this.y + v.y;
	}

	public Vector2i cross() {
		int cx = this.y;
		int cy = -this.x;
		this.x = cx;
		this.y = cy;
		return this;
	}

	public Vector2i negate() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}

	public Vector2i normalize() {
		int length = length();
		if (length == 0)
			return this;

		this.x /= length;
		this.y /= length;
		return this;
	}

	public Vector2i absolute() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		return this;
	}

	public Vector2i reverse() {
		int x_ = this.y;
		int y_ = this.x;
		this.x = x_;
		this.y = y_;
		return this;
	}

	public boolean equals(Vector2i v) {
		if (this.x == v.getX() && this.y == v.getY())
			return true;

		return false;
	}

	public int length() {
		return (int) Math.sqrt(x * x + y * y);
	}

	public int lengthSq() {
		return x * x + y * y;
	}

	public int distanceTo(int x, int y) {
		int dx = x - this.x;
		int dy = y - this.y;
		return (int) Math.sqrt(dx * dx + dy * dy);
	}

	public int distanceTo(Vector2i v) {
		int dx = v.x - this.x;
		int dy = v.y - this.y;
		return (int) Math.sqrt(dx * dx + dy * dy);
	}

	public int distanceSqTo(int x, int y) {
		int dx = x - this.x;
		int dy = y - this.y;
		return dx * dx + dy * dy;
	}

	public int distanceSqTo(Vector2i v) {
		int dx = v.x - this.x;
		int dy = v.y - this.y;
		return dx * dx + dy * dy;
	}

	public FloatBuffer toFloatBuffer() {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(ELEMENT_COUNT);
		buffer.put(x);
		buffer.put(y);
		return buffer;
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public static Vector2i Zero() {
		return new Vector2i(0, 0);
	}

	public static Vector2i One() {
		return new Vector2i(1, 1);
	}

	public static Vector2i Up() {
		return new Vector2i(0, 1);
	}

	public static Vector2i Down() {
		return new Vector2i(0, -1);
	}

	public static Vector2i Left() {
		return new Vector2i(-1, 0);
	}

	public static Vector2i Right() {
		return new Vector2i(1, 0);
	}

	public static Vector2i add(Vector2i u, Vector2i v) {
		int x = u.getX() + v.getX();
		int y = u.getY() + v.getY();
		return new Vector2i(x, y);
	}

	public static Vector2i subtract(Vector2i u, Vector2i v) {
		int x = u.getX() - v.getX();
		int y = u.getY() - v.getY();
		return new Vector2i(x, y);
	}

	public static Vector2i multiply(Vector2i u, Vector2i v) {
		int x = u.getX() * v.getX();
		int y = u.getY() * v.getY();
		return new Vector2i(x, y);
	}

	public static Vector2i scale(Vector2i u, int scalar) {
		int x = u.getX() * scalar;
		int y = u.getY() * scalar;
		return new Vector2i(x, y);
	}

	public static int dot(Vector2i u, Vector2i v) {
		return u.getX() * v.getX() + u.getY() * v.getY();
	}

	public static Vector2i cross(Vector2i u) {
		int x = u.getY();
		int y = -u.getX();
		return new Vector2i(x, y);
	}

	public static Vector2i negate(Vector2i u) {
		int x = -u.getX();
		int y = -u.getY();
		return new Vector2i(x, y);
	}

	public static Vector2i normalize(Vector2i u) {
		int length = (int) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY());
		if (length == 0)
			return u;

		int x = u.getX() * length;
		int y = u.getY() * length;
		return new Vector2i(x, y);
	}

	public static Vector2i absolute(Vector2i u) {
		int x = Math.abs(u.x);
		int y = Math.abs(u.y);
		return new Vector2i(x, y);
	}

	public static Vector2i reverse(Vector2i u) {
		return new Vector2i(u.getY(), u.getX());
	}

	public static int length(Vector2i u) {
		return (int) Math.sqrt(u.getX() * u.getX() + u.getY() * u.getY());
	}

	public static int lengthSq(Vector2i u) {
		return u.getX() * u.getX() + u.getY() * u.getY();
	}

	public static int distanceBetween(Vector2i u, Vector2i v) {
		int dx = v.getX() - u.getX();
		int dy = v.getY() - u.getY();
		return (int) Math.sqrt(dx * dx + dy * dy);
	}

	public static int distanceSqBetween(Vector2i u, Vector2i v) {
		int dx = v.getX() - u.getX();
		int dy = v.getY() - u.getY();
		return dx * dx + dy * dy;
	}

	public static Vector2i sum(Vector2i... u) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < u.length; i++) {
			x += u[i].getX();
			y += u[i].getY();
		}
		return new Vector2i(x, y);
	}

	public Vector2i get() {
		return new Vector2i(this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Vector2i set(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector2i set(Vector2i v) {
		this.x = v.getX();
		this.y = v.getY();
		return this;
	}

	public Vector2i set(Vector2f v) {
		this.x = (int) v.getX();
		this.y = (int) v.getY();
		return this;
	}

	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}

	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}
}
