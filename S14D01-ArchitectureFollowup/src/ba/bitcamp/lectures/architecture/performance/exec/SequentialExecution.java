package ba.bitcamp.lectures.architecture.performance.exec;

import ba.bitcamp.lectures.architecture.performance.FirstComponent;
import ba.bitcamp.lectures.architecture.performance.SecondComponent;

public class SequentialExecution implements Runnable {
	private int numTasks;
	private int numOps;

	public SequentialExecution(int numTasks, int numOps) {
		this.numTasks = numTasks;
		this.numOps = numOps;
	}

	@Override
	public void run() {
		for (int i=0; i<numTasks; i++) {
			FirstComponent first = new FirstComponent(System.nanoTime(), numOps);
			first.run();
			SecondComponent second = new SecondComponent(first.getId(), first.getRes(), numOps);
			second.run();
		}
	}
}
