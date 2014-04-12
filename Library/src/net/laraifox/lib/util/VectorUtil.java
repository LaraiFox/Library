package net.laraifox.lib.util;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import net.laraifox.lib.math.Vector2f;
import net.laraifox.lib.math.Vector3f;
import net.laraifox.lib.math.Vector4f;

public class VectorUtil {
	public static Vector3f toVector3f(Color color) {
		float x = color.getRed() / 255.0f;
		float y = color.getGreen() / 255.0f;
		float z = color.getBlue() / 255.0f;
		return new Vector3f(x, y, z);
	}

	public static Color toColor(Vector3f v) {
		int red = (int) (v.getX() * 255.0f);
		int green = (int) (v.getY() * 255.0f);
		int blue = (int) (v.getZ() * 255.0f);
		return new Color((red << 16) & (green << 8) & blue);
	}

	public static Vector4f toVector4f(Color color) {
		float x = color.getRed() / 255.0f;
		float y = color.getGreen() / 255.0f;
		float z = color.getBlue() / 255.0f;
		float w = color.getAlpha() / 255.0f;
		return new Vector4f(x, y, z, w);
	}

	public static Color toColor(Vector4f v) {
		int red = (int) (v.getX() * 255.0f);
		int green = (int) (v.getY() * 255.0f);
		int blue = (int) (v.getZ() * 255.0f);
		int alpha = (int) (v.getW() * 255.0f);
		return new Color((alpha << 24) & (red << 16) & (green << 8) & blue);
	}

	public static Point toPoint(Vector2f u) {
		return new Point((int) u.getX(), (int) u.getY());
	}

	public static Point2D toPoint2D(Vector2f v) {
		return new Point2D.Float(v.getX(), v.getY());
	}

	public static Line2D toLine2D(Vector2f v) {
		return new Line2D.Float(0.0f, 0.0f, v.getX(), v.getY());
	}

	public static Line2D toLine2D(Vector2f u, Vector2f v) {
		return new Line2D.Float(u.getX(), u.getY(), v.getX(), v.getY());
	}
}
