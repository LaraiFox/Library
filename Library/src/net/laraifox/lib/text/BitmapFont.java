package net.laraifox.lib.text;

import net.laraifox.lib.graphics.Texture;

public class BitmapFont {
	public static final String DEFAULT_CHARACTER_SET = new String(" !\"#$%&'()*+,-./" + "0123456789:;<=>?" + "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZ[\\]^_`"
			+ "abcdefghijklmnop" + "qrstuvwxyz{|}~@¡" + "¢£¤¥¦§¨©ª«¬­®¯°±" + "²³´µ¶·¸¹º»¼½¾¿ÀÁ" + "ÂÃÄÅÆÇÈÉÊËÌÍÎÏĞÑ" + "ÒÓÔÕÖ×ØÙÚÛÜİŞßàá" + "âãäåæçèéêëìíîïğñ"
			+ "òóôõö÷øùúûüışÿ‹›");

	private Texture fontTexture;
	private String characterSet;
	private int[] characterWidths;
	private int characterHeight;

	public BitmapFont(Texture fontTexture, String characterSet, int[] characterWidths, int characterHeight) {
		this.fontTexture = fontTexture;
		this.characterSet = characterSet;
		this.characterWidths = characterWidths;
		this.characterHeight = characterHeight;
	}

	public void bindFontTexture() {
		fontTexture.bindTexture();
	}

	public Texture getFontTexture() {
		return fontTexture;
	}

	public char getCharacterAt(int i) {
		return characterSet.charAt(i);
	}

	public String getCharacterSet() {
		return characterSet;
	}

	public int getCharacterCount() {
		return characterSet.length();
	}

	public int getCharacterWidth(int i) {
		return characterWidths[i];
	}

	public int[] getCharacterWidths() {
		return characterWidths;
	}

	public int getCharacterHeight() {
		return characterHeight;
	}

	public int indexOf(char character) {
		return characterSet.indexOf(character);
	}
}
