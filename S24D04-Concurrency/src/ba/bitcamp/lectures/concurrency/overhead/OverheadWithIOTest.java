package ba.bitcamp.lectures.concurrency.overhead;

import java.util.LinkedList;

import ba.bitcamp.lectures.concurrency.ImageUrlExtractor;
import ba.bitcamp.lectures.concurrency.RepeatingRunnable;

/**
 * Attempt to show how tasks with IO will increase performance even when number of threads reaches number of cores.
 * Since IO is loding same file every time, it will most likely not be the case. 
 * 
 * @author emir
 *
 */
public class OverheadWithIOTest {
	public static void main(String[] args) throws Exception {
		LinkedList<RepeatingRunnable> tasks = new LinkedList<>();
		while (true) {
			RepeatingRunnable task = new RepeatingRunnable(new ImageUrlExtractor(null), -1);
			tasks.add(task);
			new Thread(task).start();
			Thread.sleep(5000);
			// print stats
			long min = Integer.MAX_VALUE, max = 0, total = 0;
			for (RepeatingRunnable t : tasks) {
				long last = t.lastRunNanoTime();
				if (last < min) {
					min = last;
				}
				if (last > max) {
					max = last;
				}
				total += last;
			}
			System.out.println(String.format("Threads:%d\tAvg:%d\tMin:%d\tMax:%d\t", tasks.size(), total/tasks.size(), min, max));
		}
	}
}
