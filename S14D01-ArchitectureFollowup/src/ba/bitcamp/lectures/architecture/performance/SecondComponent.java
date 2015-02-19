package ba.bitcamp.lectures.architecture.performance;


public class SecondComponent implements Runnable {
	private int id;
	private double res;
	private long numAdditions;

	public SecondComponent(int id, double res, int numAdditions) {
		this.id = id;
		this.res = res;
		this.numAdditions = numAdditions;
	}

	@Override
	public void run() {
		System.out.println(id + ": starting additions");
		long taskStart = System.nanoTime();
		for (int i=0; i<numAdditions; i++) {
			res += Math.random();
		}
		System.out.println(id + ": addition time: " + (System.nanoTime() - taskStart) + " ns");
	}
	
	public int getId() {
		return id;
	}
	
	public double getRes() {
		return res;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setRes(double res) {
		this.res = res;
	}
}
