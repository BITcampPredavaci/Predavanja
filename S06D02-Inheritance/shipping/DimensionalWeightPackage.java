package shipping;

public class DimensionalWeightPackage extends Package {
	@Override
	public double getWeight() {
		return Math.max(weight, dimensionalWeight());
	}
	
	public double getFactor() {
		return 1;
	}
	
	public double dimensionalWeight() {
		return width * height * length / getFactor();
	}
}
