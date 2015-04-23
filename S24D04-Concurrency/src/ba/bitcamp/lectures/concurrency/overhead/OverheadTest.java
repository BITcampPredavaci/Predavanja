package ba.bitcamp.lectures.concurrency.overhead;

import java.util.LinkedList;

import ba.bitcamp.lectures.concurrency.CPUIntensiveTask;

/**
 * Test that shows how threads will increase number of work done if there are enough resources - number of multiplications
 * will increase until number of threads reaches number of cores. After that it will be stabile for some number of threads and
 * than decrease.
 * 
 * @author emir
 *
 */
public class OverheadTest {
	public static void main(String[] args) throws Exception {
		LinkedList<CPUIntensiveTask> tasks = new LinkedList<>();
		while (true) {
			CPUIntensiveTask task = new CPUIntensiveTask(-1);
			tasks.add(task);
			new Thread(task).start();
			Thread.sleep(5000);
			// print stats
			int min = Integer.MAX_VALUE, max = 0, total = 0;
			for (CPUIntensiveTask t : tasks) {
				int last = t.lastSecond();
				if (last < min) {
					min = last;
				}
				if (last > max) {
					max = last;
				}
				total += last;
			}
			System.out.println(String.format("Threads:%d\tTotal:%d\tAvg:%d\tMin:%d\tMax:%d\t", tasks.size(), total, total/tasks.size(), min, max));
		}
	}
}
