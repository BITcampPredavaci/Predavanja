package ba.bitcamp.lectures.concurrency.safety;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import ba.bitcamp.lectures.concurrency.LatchControlledRunnable;

/**
 * Test that shows safety hazard load-update-store. 
 * In order to make it thread safe, counter methods should be synchronized.
 * 
 * It also shows that AtomicInteger is proper implementation for this task.
 * 
 * @author emir
 *
 */
public class UnsafeCounter {
	private int cnt;
	
	public int increment() {
		return ++cnt;
	}
	
	public int get() {
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		final int threads = 20;
		final int increments = 1000;
		final UnsafeCounter unsafe = new UnsafeCounter();
		final AtomicInteger safe = new AtomicInteger();
		
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(threads);
		
		for (int i=0; i<threads; i++) {
			Thread th = new Thread(new LatchControlledRunnable(new Runnable() {
				@Override
				public void run() {
					for (int i=0; i<increments; i++) {
						unsafe.increment();
						safe.incrementAndGet();
					}
					endLatch.countDown();
				}
				
			}, startLatch));
			th.start();
		}
		
		System.out.println("Expected " + (threads * increments));
		startLatch.countDown();
		endLatch.await();
		System.out.println("Unsafe " + unsafe.get());
		System.out.println("Atomic " + safe.get());
	}
}
