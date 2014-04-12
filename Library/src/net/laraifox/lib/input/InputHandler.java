package net.laraifox.lib.input;

import net.laraifox.lib.math.Vector2f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class InputHandler {
	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSEBUTTONS = 5;

	private static boolean[] lastKeys = new boolean[NUM_KEYCODES];
	private static boolean[] lastMouse = new boolean[NUM_MOUSEBUTTONS];

	public static void update() {
		while (Keyboard.next()) {
			int eventKey = Keyboard.getEventKey();

			if (0 <= eventKey && eventKey < NUM_KEYCODES) {
				lastKeys[eventKey] = Keyboard.getEventKeyState();
			}
		}

		while (Mouse.next()) {
			int eventButton = Mouse.getEventButton();

			if (0 <= eventButton && eventButton < NUM_MOUSEBUTTONS) {
				lastMouse[eventButton] = Mouse.getEventButtonState();
			}
		}
	}

	public static boolean isKeyDown(int keyCode) {
		return Keyboard.isKeyDown(keyCode);
	}

	public static boolean isKeyPressed(int keyCode) {
		return isKeyDown(keyCode) && !lastKeys[keyCode];
	}

	public static boolean isKeyReleased(int keyCode) {
		return !isKeyDown(keyCode) && lastKeys[keyCode];
	}

	public static boolean isButtonDown(int mouseButton) {
		return Mouse.isButtonDown(mouseButton);
	}

	public static boolean isButtonPressed(int mouseButton) {
		return isButtonDown(mouseButton) && !lastMouse[mouseButton];
	}

	public static boolean isButtonReleased(int mouseButton) {
		return !isButtonDown(mouseButton) && lastMouse[mouseButton];
	}

	public static Vector2f getMousePosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}

	public static void setMousePosition(Vector2f pos) {
		Mouse.setCursorPosition((int) pos.getX(), (int) pos.getY());
	}

	// public static void setCursor(boolean enabled) {
	// Mouse.setGrabbed(!enabled);
	// }
}
