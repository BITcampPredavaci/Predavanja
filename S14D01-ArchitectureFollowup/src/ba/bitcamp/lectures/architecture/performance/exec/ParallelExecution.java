package ba.bitcamp.lectures.architecture.performance.exec;

import java.util.concurrent.CountDownLatch;

import ba.bitcamp.lectures.architecture.performance.FirstComponent;
import ba.bitcamp.lectures.architecture.performance.SecondComponent;

public class ParallelExecution implements Runnable {
	private int numTasks;
	private int numOps;

	public ParallelExecution(int numTasks, int numOps) {
		this.numTasks = numTasks;
		this.numOps = numOps;
	}

	@Override
	public void run() {
		final CountDownLatch counter = new CountDownLatch(numTasks);
		for (int i=0; i<numTasks; i++) {
			new Thread() {
				public void run() {
					FirstComponent first = new FirstComponent(System.nanoTime(), numOps);
					first.run();
					SecondComponent second = new SecondComponent(first.getId(), first.getRes(), numOps);
					second.run();
					counter.countDown();
				};
			}.start();
		}
		// wait to finish
		try {
			counter.await();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}
