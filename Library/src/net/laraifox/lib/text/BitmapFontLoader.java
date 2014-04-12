package net.laraifox.lib.text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.laraifox.lib.graphics.Texture;

public class BitmapFontLoader {
	/**
	 * WARNING: This method is currently unimplemented and with always return null if used. Please wait until implementation is complete.
	 * 
	 * Attempts to load a Bitmap Font File (.bff) from the file name provided and return the compiled BitmapFont.
	 * 
	 * @param fileName
	 * @return
	 */
	public static BitmapFont loadBitmapFont(String fileName) {
		return null;
	}

	public static BitmapFont loadBitmapFont(String fontTexture, String characterFile, int characterWidth, int characterHeight)
			throws FileNotFoundException, IOException {
		Texture texture = Texture.getTextureFrom(fontTexture);

		BufferedReader reader = new BufferedReader(new FileReader(characterFile));
		String characterSet = new String(reader.readLine());

		int[] characterWidths = new int[characterSet.length()];
		for (int i = 0; i < characterWidths.length; i++)
			characterWidths[i] = characterWidth;

		reader.close();

		return new BitmapFont(texture, characterSet, characterWidths, characterHeight);
	}

	public static BitmapFont loadBitmapFont(String fontTexture, String characterFile, int[] characterWidths, int characterHeight)
			throws FileNotFoundException, IOException {
		Texture texture = Texture.getTextureFrom(fontTexture);

		BufferedReader reader = new BufferedReader(new FileReader(characterFile));
		String characterSet = new String(reader.readLine());

		reader.close();

		return new BitmapFont(texture, characterSet, characterWidths, characterHeight);
	}

	public static BitmapFont loadBitmapFont(String fontTexture, String characterFile, String characterSize) throws FileNotFoundException, IOException {
		Texture texture = Texture.getTextureFrom(fontTexture);

		BufferedReader reader = new BufferedReader(new FileReader(characterFile));
		String characterSet = new String(reader.readLine());
		reader.close();

		reader = new BufferedReader(new FileReader(characterSize));
		String characterSizeString = reader.readLine();
		int[] characterWidths = new int[characterSet.length()];
		for (int i = 0; i < characterWidths.length; i++)
			characterWidths[i] = Integer.parseInt(characterSizeString.substring(i, i + 1));

		int characterHeight = Integer.parseInt(characterSizeString.substring(characterSizeString.length() - 1, characterSizeString.length()));

		reader.close();

		return new BitmapFont(texture, characterSet, characterWidths, characterHeight);
	}
}
