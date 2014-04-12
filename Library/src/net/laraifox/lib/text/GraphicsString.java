package net.laraifox.lib.text;

import net.laraifox.lib.math.Vector2f;

public class GraphicsString {
	public static final Vector2f ANCHOR_TOP_LEFT = new Vector2f(0.0f, 1.0f);
	public static final Vector2f ANCHOR_TOP_CENTER = new Vector2f(0.5f, 1.0f);
	public static final Vector2f ANCHOR_TOP_RIGHT = new Vector2f(1.0f, 1.0f);
	public static final Vector2f ANCHOR_MIDDLE_LEFT = new Vector2f(0.0f, 0.5f);
	public static final Vector2f ANCHOR_MIDDLE_CENTER = new Vector2f(0.5f, 0.5f);
	public static final Vector2f ANCHOR_MIDDLE_RIGHT = new Vector2f(1.0f, 0.5f);
	public static final Vector2f ANCHOR_BOTTOM_LEFT = new Vector2f(0.0f, 0.0f);
	public static final Vector2f ANCHOR_BOTTOM_CENTER = new Vector2f(0.5f, 0.0f);
	public static final Vector2f ANCHOR_BOTTOM_RIGHT = new Vector2f(1.0f, 0.0f);
	
	private String text;
	private Vector2f position;
	private float theta;
	private Vector2f anchor;

	public GraphicsString() {
		this(new String(), new Vector2f(), 0.0f, ANCHOR_MIDDLE_CENTER);
	}

	public GraphicsString(String text, Vector2f position) {
		this(text, position, 0.0f, ANCHOR_MIDDLE_CENTER);
	}

	public GraphicsString(String text, Vector2f position, float theta) {
		this(text, position, theta, ANCHOR_MIDDLE_CENTER);
	}

	public GraphicsString(String text, Vector2f position, Vector2f anchor) {
		this(text, position, 0.0f, anchor);
	}

	public GraphicsString(String text, Vector2f position, float theta, Vector2f anchor) {
		this.text = text;
		this.position = position;
		this.theta = theta;
		this.anchor = anchor;
	}

	public char getCharAt(int i) {
		return text.charAt(i);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public float getTheta() {
		return theta;
	}

	public void setTheta(float theta) {
		this.theta = theta;
	}

	public Vector2f getAnchor() {
		return anchor;
	}

	public void setAnchor(Vector2f anchor) {
		this.anchor = anchor;
	}
}
