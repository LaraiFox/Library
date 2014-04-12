package net.laraifox.lib.util;

import java.util.ArrayList;

public class StringUtil {
	public static String[] removeEmptyStrings(String[] data) {
		ArrayList<String> nonEmptyStrings = new ArrayList<String>();
		
		for (int i = 0; i < data.length; i++) {
			if (!data[i].equals("") && data[i] != null) {
				nonEmptyStrings.add(data[i]);
			}
		}
		
		String[] result = new String[nonEmptyStrings.size()];
		nonEmptyStrings.toArray(result);
		
		return result;
	}

}
