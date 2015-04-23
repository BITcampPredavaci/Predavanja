package ba.bitcamp.lectures.concurrency;

import java.util.Random;


/**
 * Dummy task that ocupies CPU with double multiplications. 
 * It also tracks number of operations in last second so it could be used to compare performance in different circumstances.
 * 
 * NOTE: every thread has its own copy of Random since Random uses AtomicLong to generate random numbers. 
 * If you change rand to be static you will notice performance drop with even two threads. Good way of noticing how
 * synchronization affects performances.
 *   
 * @author emir
 *
 */
public class CPUIntensiveTask implements Runnable {

	private long taskDuration;
	private volatile int lastSecond;
	private Random rand = new Random();
	
	/**
	 * Creates new task that will last at least provided duration.
	 * In case of negative duration, it will run until interrupted.
	 * 
	 * @param taskDuration min duration of task in ms
	 */
	public CPUIntensiveTask(long taskDuration) {
		this.taskDuration = taskDuration;
	}
	
	@Override
	public void run() {
		int numLastSecond = 0;
		double res = Math.random();
		long start = System.currentTimeMillis();
		long second = start;
		while (!Thread.currentThread().isInterrupted() && (taskDuration < 0 || System.currentTimeMillis() - start < taskDuration)) {
			res *= rand.nextDouble();
			if (res == 0) {
				res = rand.nextDouble();
			}
			numLastSecond++;
			if (System.currentTimeMillis() - second >= 1000) {
//				System.out.println(Thread.currentThread().getName() + ":" + lastSecond + " -> " + numLastSecond);
				lastSecond = numLastSecond;
				numLastSecond = 0;
				second = System.currentTimeMillis();
			}
		}
	}
	
	public int lastSecond() {
		return lastSecond;
	}
	
	public static void main(String[] args) throws Exception {
		double start = System.currentTimeMillis();
		CPUIntensiveTask task = new CPUIntensiveTask(2000);
		task.run();
		System.out.println("Finished in " + (System.currentTimeMillis() - start));
		System.out.println("Last second operations: " + task.lastSecond());
	}

}
