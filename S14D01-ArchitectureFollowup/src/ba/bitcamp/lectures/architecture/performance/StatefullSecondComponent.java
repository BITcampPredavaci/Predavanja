package ba.bitcamp.lectures.architecture.performance;

public class StatefullSecondComponent extends SecondComponent {

	long time;
	
	public StatefullSecondComponent(int numAdditions) {
		super(0, 0, numAdditions);
		time = System.nanoTime();
	}

	@Override
	public void run() {
		long prevTime = time;
		super.run();
		time = System.nanoTime();
		System.out.println(getId() + ": elapsed since last end: " + (time - prevTime) + " ns");
	}
}
