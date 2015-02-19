package ba.bitcamp.lectures.architecture.performance;

import java.util.concurrent.atomic.AtomicInteger;

public class FirstComponent implements Runnable {
	private static final AtomicInteger ID_FACTORY = new AtomicInteger();
	
	private int id;
	private long start;
	private long numMultiplications;
	
	private double res;

	public FirstComponent(long start, long numMultiplications) {
		this.start = start;
		this.numMultiplications = numMultiplications;
		this.id = ID_FACTORY.incrementAndGet();
	}

	@Override
	public void run() {
		System.out.println(id + ": starting multiplication");
		System.out.println(id + ": time since created: " + (System.nanoTime() - start) + " ns");
		long taskStart = System.nanoTime();
		res = 1; 
		for (int i=0; i<numMultiplications; i++) {
			res *= Math.random();
			if (res == 0) {
				res = Math.random();
			}
		}
		System.out.println(id + ": multiplication time: " + (System.nanoTime() - taskStart) + " ns");
	}
	
	public void setStart(long start) {
		this.start = start;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public double getRes() {
		return res;
	}
}
