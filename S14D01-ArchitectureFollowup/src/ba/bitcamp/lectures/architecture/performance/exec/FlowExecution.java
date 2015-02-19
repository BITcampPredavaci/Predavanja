package ba.bitcamp.lectures.architecture.performance.exec;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ba.bitcamp.lectures.architecture.performance.StatefullFirstComponent;
import ba.bitcamp.lectures.architecture.performance.StatefullSecondComponent;

public class FlowExecution implements Runnable {
	private int numTasks;

	private StatefullFirstComponent firstComponent;
	private StatefullSecondComponent secondComponent;
	
	private BlockingQueue<Long> startTimes;
	private BlockingQueue<FirstStepRes> firstStepRes;

	public FlowExecution(int numTasks, int numOps) {
		this.numTasks = numTasks;
		
		firstComponent = new StatefullFirstComponent(numOps);
		secondComponent = new StatefullSecondComponent(numOps);
		
		startTimes = new LinkedBlockingQueue<Long>();
		firstStepRes = new LinkedBlockingQueue<FirstStepRes>();
	}

	@Override
	public void run() {
		// first component runner
		Thread firstRunner = new Thread() {
			public void run() {
				try {
					int id = 1;
					long start;
					while ((start = startTimes.take()) > 0) {
						firstComponent.setStart(start);
						firstComponent.setId(id++);
						firstComponent.run();
						firstStepRes.add(new FirstStepRes(firstComponent.getId(), firstComponent.getRes()));
					}
					// signal end
					firstStepRes.add(new FirstStepRes(-1, 0));
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			};
		};
		firstRunner.start();
		
		// second component runner
		Thread secondRunner = new Thread() {
			public void run() {
				try {
					FirstStepRes res;
					while ((res = firstStepRes.take()).id > 0) {
						secondComponent.setId(res.id);
						secondComponent.setRes(res.res);
						secondComponent.run();
					}
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			};
		};
		secondRunner.start();
		
		for (int i=0; i<numTasks; i++) {
			startTimes.add(System.nanoTime());
		}
		// signal end
		startTimes.add(-1L);
		
		synchronized (secondRunner) {
			try {
				secondRunner.wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	private class FirstStepRes {
		private int id;
		private double res;

		public FirstStepRes(int id, double res) {
			this.id = id;
			this.res = res;
		}
	}
}
