package ba.bitcamp.lectures.concurrency.liveness;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Deadlock showcase - starving philosophers example. By setting smart to true, philosophers will not be able to deadlock.
 * 
 * @author emir
 *
 */
public class StarvingPhilosophers {
	public static void main(String[] args) {
		int numPhilozophers = 5;
		boolean smart = false;
		// set up table
		Chopstick[] chopsticks = new Chopstick[numPhilozophers];
		for (int i=0; i<chopsticks.length; i++) {
			chopsticks[i] = new Chopstick(i);
		}
		// sit philosophers
		final Philosopher[] philosophers = new Philosopher[numPhilozophers];
		for (int i=0; i<numPhilozophers; i++) {
			philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1)%numPhilozophers], smart);
		}
		// start eating / thinking
		ExecutorService executor = Executors.newFixedThreadPool(numPhilozophers + 1);
		for (Philosopher ph : philosophers) {
			executor.submit(ph);
		}
		// speed up thread - every 2 sec reduce think/eat times
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					for (Philosopher ph : philosophers) {
						ph.speedUp();
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// ignore
					}
				}
			}
		});
	}
	
	private static class Philosopher implements Runnable {
		private int id;
		private Chopstick left;
		private Chopstick right;
		
		private long maxThinkingTime = 2000;
		private long maxEatingTime = 2000;

		private Random rand;

		public Philosopher(int id, Chopstick left, Chopstick right, boolean isSmart) {
			this.id = id;
			this.left = left;
			this.right = right;
			if (isSmart) {
				// smart philosopher will first pick up chopstick with smaller id so deadlock is prevented
				if (left.id > right.id) {
					this.left = right;
					this.right = left;
				}
			}
			this.rand = new Random();
		}
		
		public synchronized void speedUp() {
			this.maxThinkingTime = maxThinkingTime / 2;
			this.maxEatingTime = maxEatingTime / 2;
		}

		@Override
		public void run() {
			while (true) {
				think();
				eat();
			}
		}
		
		private void think() {
			doAction("Thinking", maxThinkingTime);
		}

		private void eat() {
			System.out.println(toString() + ": Hungry...");
			long start = System.currentTimeMillis();
			left.pick();
			System.out.println(toString() + ": Took left " + left + " after " + (System.currentTimeMillis() - start));
			right.pick();
			System.out.println(toString() + ": Took right " + right + " after " + (System.currentTimeMillis() - start));
			doAction("Eating", maxEatingTime);
			left.leave();
			right.leave();
		}
		
		private void doAction(String action, long maxTime) {
			long actionTime = maxTime > 0 ? Math.abs(rand.nextLong() % maxTime) : 0;
			System.out.println(String.format("%s : %s for next %d ms", toString(), action, actionTime));
			try {
				if (actionTime > 0) {
					Thread.sleep(actionTime);
				}
			} catch (InterruptedException e) {
				System.err.println(toString() + ": Interrupted in " + action);
			}
		}
		
		@Override
		public String toString() {
			return "Philosopher " + id;
		}
	}
	
	private static class Chopstick {
		private int id;
		private ReentrantLock handle = new ReentrantLock();

		public Chopstick(int id) {
			this.id = id;
		}
		
		public void pick() {
			handle.lock();
		}
		
		public void leave() {
			handle.unlock();
		}
		
		@Override
		public String toString() {
			return "chopstick " + id;
		}
	}
}
