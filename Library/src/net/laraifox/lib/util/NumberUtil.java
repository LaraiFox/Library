package net.laraifox.lib.util;

public class NumberUtil {
	public static int[] toIntArray(Integer[] data) {
		int[] result = new int[data.length];
		for (int i = 0; i < data.length; i++)
			result[i] = data[i].intValue();
		return result;
	}
}
