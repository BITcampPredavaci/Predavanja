package ba.bitcamp.lectures.concurrency;

/**
 * Runnable decorator that enables repeating same task provided number of times.
 * It also tracks last run duration in nanoseconds so it could be used to compare performance in different circumstances.
 * 
 * @author emir
 *
 */
public class RepeatingRunnable implements Runnable {

	private Runnable toRepeat;
	private int times;
	
	private volatile long lastRun;

	public RepeatingRunnable(Runnable toRepeat, int times) {
		this.toRepeat = toRepeat;
		this.times = times;
	}
	
	@Override
	public void run() {
		int cnt = 0;
		while (!Thread.currentThread().isInterrupted() && (times < 0 || times < cnt)) {
			long start = System.nanoTime();
			toRepeat.run();
			lastRun = System.nanoTime() - start;
		}
	}
	
	public long lastRunNanoTime() {
		return lastRun;
	}

}
