package net.laraifox.lib.threading;

import net.laraifox.lib.math.MathHelper;

import org.lwjgl.opengl.Display;

public abstract class OpenGLRenderThread extends Thread {
	private boolean isRunning;
	
	private float tickrate;
	private int framerate;
	private int frames, fps;
	
	public OpenGLRenderThread() {
		this(60, 1.0f);
	}
	
	public OpenGLRenderThread(int framerate) {
		this(framerate, 1.0f);
	}
	
	public OpenGLRenderThread(float tickrate) {
		this(60, tickrate);
	}
	
	public OpenGLRenderThread(int framerate, float tickrate) {
		super("Game Render Thread");
		
		this.isRunning = false;
		
		this.tickrate = tickrate;
		this.framerate = framerate;
	}
	
	public void start() {
		if (isRunning)
			return;
		
		isRunning = true;
		super.start();
	}
	
	public void exit() throws InterruptedException {
		exit(0);
	}
	
	public void exit(int millis) throws InterruptedException {
		if (!isRunning)
			return;
		
		isRunning = false;
		super.join(millis);
	}
	
	protected int getCurrentFPS() {
		return (int) (fps / (1.0 / tickrate));
	}
	
	public final void run() {
		long previousTick = System.nanoTime();
		double nanosecondsPerTick = (double) MathHelper.MILLIARD / tickrate;

		while (!Display.isCloseRequested() && isRunning) {
			long currentTime = System.nanoTime();

			if (currentTime - previousTick >= nanosecondsPerTick) {
				previousTick += MathHelper.MILLIARD;
				fps = frames;
				frames = 0;
				tick();
			}

			render();
			frames++;
			
			Display.update();
			Display.sync(framerate);
		}
		
		isRunning = false;
		Display.destroy();
	}
	
	protected abstract void tick();
	
	protected abstract void render();
}
