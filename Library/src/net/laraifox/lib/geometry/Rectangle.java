package net.laraifox.lib.geometry;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Rectangle {
	private int displayListID;

	private float x, y;
	private float width, height;
	private float theta;

	public Rectangle() {
		this(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
	}

	public Rectangle(float width, float height) {
		this(0.0f, 0.0f, width, height, 0.0f);
	}

	public Rectangle(float width, float height, float theta) {
		this(0.0f, 0.0f, width, height, theta);
	}

	public Rectangle(float x, float y, float width, float height) {
		this(x, y, width, height, 0.0f);
	}

	public Rectangle(float x, float y, float width, float height, float theta) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.theta = theta;
	}

	public Rectangle generateDisplayList() {
		this.displayListID = glGenLists(1);

		float halfWidth = width / 2.0f;
		float halfHeight = height / 2.0f;

		glNewList(displayListID, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glVertex2f(-halfWidth, halfHeight);
		glVertex2f(halfWidth, halfHeight);
		glVertex2f(-halfWidth, -halfHeight);

		glVertex2f(halfWidth, halfHeight);
		glVertex2f(halfWidth, -halfHeight);
		glVertex2f(-halfWidth, -halfHeight);
		glEnd();
		glEndList();

		return this;
	}

	public Rectangle generateDisplayListUV(float u, float v) {
		this.displayListID = glGenLists(1);

		float halfWidth = width / 2.0f;
		float halfHeight = height / 2.0f;

		glNewList(displayListID, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glVertex2f(-halfWidth, halfHeight);
		glVertex2f(halfWidth, halfHeight);
		glVertex2f(-halfWidth, -halfHeight);

		glVertex2f(halfWidth, halfHeight);
		glVertex2f(halfWidth, -halfHeight);
		glVertex2f(-halfWidth, -halfHeight);
		glEnd();
		glEndList();

		return this;
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

	public float getTheta() {
		return theta;
	}

	public void setTheta(float theta) {
		this.theta = theta;
	}

	public void glDrawImmediate() {

	}

	public void glDrawImmediateUV() {

	}
}
