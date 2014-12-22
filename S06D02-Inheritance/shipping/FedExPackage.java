package shipping;

public class FedExPackage extends DimensionalWeightPackage {
	@Override
	public double getFactor() {
		return 6000;
	}
}
