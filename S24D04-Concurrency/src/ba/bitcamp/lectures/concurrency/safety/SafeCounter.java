package ba.bitcamp.lectures.concurrency.safety;

import java.util.concurrent.CountDownLatch;

import ba.bitcamp.lectures.concurrency.LatchControlledRunnable;

/**
 * Thread safe counter that shows safety hazard check-then-act.
 * If we want to prevent it from happening we should put while loop in syncronized block
 * <pre>
 * syncronized(safe) {
 * 	while (safe.get() < max) {
 *		safe.increment();
 *	}
 * }
 * </pre>
 * 
 * 
 * @author emir
 *
 */
public class SafeCounter {
	private int cnt;
	
	public synchronized int increment() {
		return ++cnt;
	}
	
	public synchronized int get() {
		return cnt;
	}
	
	
	public static void main(String[] args) throws Exception {
		final int threads = 20;
		final int max = 1000;
		final SafeCounter safe = new SafeCounter();
		
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(threads);
		
		for (int i=0; i<threads; i++) {
			Thread th = new Thread(new LatchControlledRunnable(new Runnable() {
				@Override
				public void run() {
					while (safe.get() < max) {
						safe.increment();
					}
					endLatch.countDown();
				}
				
			}, startLatch));
			th.start();
		}
		
		System.out.println("Expected " + max);
		startLatch.countDown();
		endLatch.await();
		System.out.println("Got " + safe.get());
	}
}
