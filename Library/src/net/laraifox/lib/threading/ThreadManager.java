package net.laraifox.lib.threading;

public class ThreadManager {
	private Thread[] threads;

	public ThreadManager(int i) {
		this.threads = new Thread[i];
	}

	public ThreadManager(int i, Thread thread) {
		this.threads = new Thread[i];
		for (int j = 0; j < threads.length; j++)
			threads[j] = thread;
	}

	public ThreadManager(int i, Thread[] threads) {
		this.threads = new Thread[i];
		System.arraycopy(threads, 0, this.threads, 0, this.threads.length);
	}

	public void startThread(int i) {
		if (threads[i].isAlive())
			return;

		threads[i].start();
	}

	public void startAllThreads() {
		for (Thread thread : threads) {
			if (thread.isAlive())
				continue;

			thread.start();
		}
	} 

	public void setThreads(Thread thread) {
		for (int j = 0; j < threads.length; j++)
			threads[j] = thread;
	}

	public void setThreads(Thread[] threads) {
		System.arraycopy(threads, 0, this.threads, 0, this.threads.length);
	}
}
