package ba.bitcamp.lectures.patterns.decorator;

public class BeverageWithChocolate extends EnchancedBeverage {

	public BeverageWithChocolate(Beverage beverage) {
		super(beverage);
	}

	@Override
	protected double getEnchancmentPrice() {
		return 0.7;
	}

}
