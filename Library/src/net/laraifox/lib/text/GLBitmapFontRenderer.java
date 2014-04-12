package net.laraifox.lib.text;

import java.util.ArrayList;

import net.laraifox.lib.math.Vector2f;

import org.lwjgl.opengl.GL11;

public class GLBitmapFontRenderer {
	private BitmapFont font;
	private int[] displayListIDs;
	private ArrayList<GraphicsString> drawList;

	public GLBitmapFontRenderer(BitmapFont font) {
		this.font = font;
		this.displayListIDs = new int[0];
		this.drawList = new ArrayList<GraphicsString>();
	}

	public GLBitmapFontRenderer initialize() {
		int characterCount = font.getCharacterCount();
		this.displayListIDs = new int[characterCount];

		float tLeft = 0.0f;
		float tTop = 0.0f;
		float tHeight = (float) font.getCharacterHeight() / (float) font.getFontTexture().getHeight();

		for (int i = 0; i < characterCount; i++) {
			float width = font.getCharacterWidth(i);
			float height = font.getCharacterHeight();

			float tRight = tLeft + (float) font.getCharacterWidth(i) / (float) font.getFontTexture().getWidth();
			float tBottom = tTop + tHeight;

			displayListIDs[i] = GL11.glGenLists(1);
			GL11.glNewList(displayListIDs[i], GL11.GL_COMPILE);
			GL11.glBegin(GL11.GL_TRIANGLES);
			GL11.glTexCoord2f(tLeft, tBottom);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(tRight, tBottom);
			GL11.glVertex2f(width, 0);
			GL11.glTexCoord2f(tRight, tTop);
			GL11.glVertex2f(width, height);

			GL11.glTexCoord2f(tLeft, tBottom);
			GL11.glVertex2f(0, 0);
			GL11.glTexCoord2f(tRight, tTop);
			GL11.glVertex2f(width, height);
			GL11.glTexCoord2f(tLeft, tTop);
			GL11.glVertex2f(0, height);
			GL11.glEnd();
			GL11.glEndList();

			if (i + 1 == characterCount) {
				break;
			} else if (tRight + (float) font.getCharacterWidth(i + 1) / (float) font.getFontTexture().getWidth() > 1.0f) {
				tLeft = 0.0f;
				tTop = tBottom;
			} else {
				tLeft = tRight;
			}
		}

		return this;
	}

	public void addToDrawList(GraphicsString string) {
		drawList.add(string);
	}

	public void addToDrawList(String text, Vector2f position) {
		drawList.add(new GraphicsString(text, position));
	}

	public void addToDrawList(String text, Vector2f position, float theta) {
		drawList.add(new GraphicsString(text, position, theta));
	}

	public void addToDrawList(String text, Vector2f position, Vector2f anchor) {
		drawList.add(new GraphicsString(text, position, anchor));
	}

	public void addToDrawList(String text, Vector2f position, float theta, Vector2f anchor) {
		drawList.add(new GraphicsString(text, position, theta, anchor));
	}

	public void addToDrawList(String text, float x, float y) {
		drawList.add(new GraphicsString(text, new Vector2f(x, y)));
	}

	public void addToDrawList(String text, float x, float y, float theta) {
		drawList.add(new GraphicsString(text, new Vector2f(x, y), theta));
	}

	public void addToDrawList(String text, float x, float y, Vector2f anchor) {
		drawList.add(new GraphicsString(text, new Vector2f(x, y), anchor));
	}

	public void addToDrawList(String text, float x, float y, float theta, Vector2f anchor) {
		drawList.add(new GraphicsString(text, new Vector2f(x, y), theta, anchor));
	}

	public void renderDrawList() {
		renderDrawList(0, drawList.size());
	}

	public void renderDrawList(int start) {
		renderDrawList(start, drawList.size());
	}

	public void renderDrawList(int start, int end) {
		if (start < 0)
			start = 0;
		if (end > drawList.size())
			end = drawList.size();

		for (int i = start; i < end; i++) {
			renderString(drawList.get(start));
			drawList.remove(start);
		}
	}

	public void renderString(GraphicsString string) {
		renderString(string.getText(), string.getPosition().getX(), string.getPosition().getY(), string.getTheta(), string.getAnchor());
	}

	public void renderString(String text, Vector2f position) {
		renderString(text, position.getX(), position.getY(), 0.0f, GraphicsString.ANCHOR_MIDDLE_CENTER);
	}

	public void renderString(String text, Vector2f position, float theta) {
		renderString(text, position.getX(), position.getY(), theta, GraphicsString.ANCHOR_MIDDLE_CENTER);
	}

	public void renderString(String text, Vector2f position, Vector2f anchor) {
		renderString(text, position.getX(), position.getY(), 0.0f, anchor);
	}

	public void renderString(String text, Vector2f position, float theta, Vector2f anchor) {
		renderString(text, position.getX(), position.getY(), theta, anchor);
	}

	public void renderString(String text, float x, float y) {
		renderString(text, x, y, 0.0f, GraphicsString.ANCHOR_MIDDLE_CENTER);
	}

	public void renderString(String text, float x, float y, float theta) {
		renderString(text, x, y, theta, GraphicsString.ANCHOR_MIDDLE_CENTER);
	}

	public void renderString(String text, float x, float y, Vector2f anchor) {
		renderString(text, x, y, 0.0f, anchor);
	}

	public void renderString(String text, float x, float y, float theta, Vector2f anchor) {
		font.bindFontTexture();
		GL11.glPushMatrix();

		float width = 0.0f;
		float height = font.getCharacterHeight();
		for (int i = 0; i < text.length(); i++) {
			width += font.getCharacterWidth(font.indexOf(text.charAt(i)));
		}

		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(theta, 0, 0, 1);
		GL11.glTranslatef(-width * anchor.getX(), -height * anchor.getY(), 0);
		GL11.glCallList(displayListIDs[font.indexOf(text.charAt(0))]);
		for (int i = 1; i < text.length(); i++) {
			int j = font.indexOf(text.charAt(i));

			GL11.glTranslatef(font.getCharacterWidth(j), 0, 0);
			GL11.glCallList(displayListIDs[j]);
		}

		GL11.glPopMatrix();
	}
}
