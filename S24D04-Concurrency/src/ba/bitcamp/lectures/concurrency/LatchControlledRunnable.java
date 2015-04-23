package ba.bitcamp.lectures.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Runnable decorator that enables starting runnables at the same time using latch to control its start.
 * 
 * @author emir
 *
 */
public class LatchControlledRunnable implements Runnable {
	private Runnable toRun;
	private CountDownLatch latch;

	public LatchControlledRunnable(Runnable toRun, CountDownLatch latch) {
		this.toRun = toRun;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			latch.await();
			toRun.run();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
