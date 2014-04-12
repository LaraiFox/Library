package net.laraifox.lib.display;

import net.laraifox.lib.graphics.OrthographicProjection;
import net.laraifox.lib.math.MathHelper;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public abstract class OpenGLDisplay {
	protected String title;
	protected float width, height;
	protected boolean isFullscreen;
	protected boolean isResizable;
	protected boolean isVSyncEnabled;

	protected PixelFormat pixelFormat;

	protected OrthographicProjection orthographicProjection;

	private boolean isInitialized;
	private boolean isRunning;

	private int framerate, updaterate;
	private int updates, ups;
	private int frames, fps;

	public OpenGLDisplay() {
		this("OpenGL Display", 800, 600, false, false, 60, 60);
	}

	public OpenGLDisplay(String title) {
		this(title, 800, 600, false, false, 60, 60);
	}

	public OpenGLDisplay(float width, float height) {
		this("OpenGL Display", width, height, false, false, 60, 60);
	}

	public OpenGLDisplay(String title, float width, float height) {
		this(title, width, height, false, false, 60, 60);
	}

	public OpenGLDisplay(String title, float width, float height, boolean fullscreen) {
		this(title, width, height, fullscreen, false, 60, 60);
	}

	public OpenGLDisplay(String title, float width, float height, boolean fullscreen, boolean vSync) {
		this(title, width, height, fullscreen, vSync, 60, 60);
	}

	public OpenGLDisplay(String title, float width, float height, boolean fullscreen, boolean vSync, int framerate) {
		this(title, width, height, fullscreen, vSync, framerate, 60);
	}

	public OpenGLDisplay(String title, float width, float height, boolean fullscreen, boolean vSync, int framerate, int updaterate) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.isFullscreen = fullscreen;
		this.isResizable = false;
		this.isVSyncEnabled = vSync;

		this.pixelFormat = new PixelFormat();

		this.orthographicProjection = new OrthographicProjection();

		this.isInitialized = false;
		this.isRunning = false;

		this.framerate = framerate;
		this.updaterate = updaterate;
		this.updates = 0;
		this.frames = 0;
		this.ups = 0;
		this.fps = 0;
	}

	public void intitialize() throws LWJGLException {
		if (isInitialized)
			return;

		isInitialized = true;
		initializeDisplay();
		initializeOpenGL();
		initializeResources();
		initializeVariables();
	}

	protected void initializeDisplay() throws LWJGLException {
		Display.setTitle(title);
		Display.setDisplayMode(new DisplayMode((int) width, (int) height));
		Display.setFullscreen(isFullscreen);
		Display.setResizable(isResizable);
		Display.setVSyncEnabled(isVSyncEnabled);
		Display.create(pixelFormat);
	}

	protected void initializeOpenGL() throws LWJGLException {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		orthographicProjection.glApplyProjection();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	protected abstract void initializeResources();

	protected abstract void initializeVariables();

	public void start() {
		if (isRunning)
			return;

		if (isInitialized) {
			isRunning = true;
			gameLoop();
		}
	}

	public void stop() {
		if (!isRunning)
			return;

		isRunning = false;
	}

	private void gameLoop() {
		long previousTick = System.nanoTime();
		long previousUpdate = System.nanoTime();
		long nanosecondsPerUpdate = (long) ((float) MathHelper.MILLIARD / (float) updaterate);

		while (!Display.isCloseRequested() && isRunning) {
			long currentTime = System.nanoTime();
			long deltaTime = currentTime - previousUpdate;

			if (currentTime - previousTick >= MathHelper.MILLIARD) {
				previousTick += MathHelper.MILLIARD;
				Display.setTitle(title);
				ups = updates;
				updates = 0;
				fps = frames;
				frames = 0;
				tick();
			}

			if (deltaTime >= nanosecondsPerUpdate) {
				previousUpdate += nanosecondsPerUpdate;
				double delta = (double) deltaTime / (double) nanosecondsPerUpdate;
				update(delta);
				updates++;
			}

			render();
			frames++;

			Display.update();
			Display.sync(framerate);
		}

		isInitialized = false;
		Display.destroy();
	}

	protected abstract void tick();

	protected abstract void update(double delta);

	protected abstract void render();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isFullscreen() {
		return isFullscreen;
	}

	public void setFullscreen(boolean isFullscreen) {
		this.isFullscreen = isFullscreen;
	}

	public boolean isResizable() {
		return isResizable;
	}

	public void setResizable(boolean isResizable) {
		this.isResizable = isResizable;
	}

	public boolean isVSyncEnabled() {
		return isVSyncEnabled;
	}

	public void setVSyncEnabled(boolean isVSyncEnabled) {
		this.isVSyncEnabled = isVSyncEnabled;
	}

	public PixelFormat getPixelFormat() {
		return pixelFormat;
	}

	public void setPixelFormat(PixelFormat pixelFormat) {
		this.pixelFormat = pixelFormat;
	}

	public OrthographicProjection getOrthographicProjection() {
		return orthographicProjection;
	}

	public void setOrthographicProjection(OrthographicProjection orthographicProjection) {
		this.orthographicProjection = orthographicProjection;
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public int getFramerate() {
		return framerate;
	}

	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}

	public int getUpdaterate() {
		return updaterate;
	}

	public void setUpdaterate(int updaterate) {
		this.updaterate = updaterate;
	}

	public int getCurrentUPS() {
		return ups;
	}

	public int getCurrentFPS() {
		return fps;
	}
}
