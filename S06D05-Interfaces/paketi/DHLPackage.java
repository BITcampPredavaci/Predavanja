package paketi;

public class DHLPackage extends Package {
	public double getDimensionalWeight() {
		return width * height * length / 5000;
	}
	
	@Override
	public double getWeight() {
		return Math.max(weight, getDimensionalWeight());
	}
}
