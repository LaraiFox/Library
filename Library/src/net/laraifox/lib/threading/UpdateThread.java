package net.laraifox.lib.threading;

import net.laraifox.lib.math.MathHelper;

public abstract class UpdateThread extends Thread {
	private boolean isRunning;

	private float tickrate;
	private int updaterate;
	private int updates, ups;

	public UpdateThread() {
		this(60, 1.0f);
	}

	public UpdateThread(int updaterate) {
		this(updaterate, 1.0f);
	}

	public UpdateThread(float tickrate) {
		this(60, tickrate);
	}

	public UpdateThread(int updaterate, float tickrate) {
		super("Game Update Thread");

		this.isRunning = false;

		this.tickrate = tickrate;
		this.updaterate = updaterate;
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
	
	public boolean isRunning() {
		return isRunning;
	}

	protected int getCurrentUPS() {
		return (int) (ups / (1.0 / tickrate));
	}

	public final void run() {
		long previousTick = System.nanoTime();
		long previousTime = System.nanoTime();
		double nanosecondsPerTick = (double) MathHelper.MILLIARD / tickrate;
		double nanosecondsPerUpdate = (double) MathHelper.MILLIARD / (double) updaterate;

		while (isRunning) {
			long currentTime = System.nanoTime();
			double delta = (currentTime - previousTime);

			if (currentTime - previousTick >= nanosecondsPerTick) {
				previousTick += MathHelper.MILLIARD;
				ups = updates;
				updates = 0;
				tick();
			}

			if (delta >= nanosecondsPerUpdate) {
				previousTime += nanosecondsPerUpdate;
				delta /= nanosecondsPerUpdate;
				update(delta);
				updates++;
			}
		}
	}

	protected abstract void tick();

	protected abstract void update(double delta);
}
