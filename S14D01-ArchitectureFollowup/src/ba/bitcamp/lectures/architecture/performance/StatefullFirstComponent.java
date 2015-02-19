package ba.bitcamp.lectures.architecture.performance;

public class StatefullFirstComponent extends FirstComponent {
	private double prevRes;
	
	public StatefullFirstComponent(long numMultiplications) {
		super(0, numMultiplications);
	}

	@Override
	public void run() {
		prevRes = getRes();
		super.run();
		
		System.out.println(getId() + ": prevRes > res :" + (prevRes > getRes()));
	}
}
