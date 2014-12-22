package shipping;

public class DHLPackage extends DimensionalWeightPackage {
	@Override
	public double getFactor() {
		return 5000;
	}
}
