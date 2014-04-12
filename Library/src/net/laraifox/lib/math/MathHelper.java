package net.laraifox.lib.math;

public class MathHelper {
	public static final long MILLIARD = 1000000000L;
	public static final double TAU = 6.283185307179586;

	public static int clamp(int value, int min, int max) {
		if (max < min)
			return value;

		if (value < min)
			value = min;
		else if (value > max)
			value = max;

		return value;
	}

	public static float clamp(float value, float min, float max) {
		if (max < min)
			return value;

		if (value < min)
			value = min;
		else if (value > max)
			value = max;

		return value;
	}

	public static double clamp(double value, double min, double max) {
		if (max < min)
			return value;

		if (value < min)
			value = min;
		else if (value > max)
			value = max;

		return value;
	}

	public static int lerp(int a, int b, float value) {
		return (int) (a + (b - a) * value);
	}

	public static float lerp(float a, float b, float value) {
		return a + (b - a) * value;
	}

	public static double lerp(double a, double b, double value) {
		return a + (b - a) * value;
	}

	public static int nextPowerOf2(int i) {
		i -= 1;
		i |= (i >> 1);
		i |= (i >> 2);
		i |= (i >> 4);
		i |= (i >> 8);
		i |= (i >> 16);

		return i + 1;
	}
}
