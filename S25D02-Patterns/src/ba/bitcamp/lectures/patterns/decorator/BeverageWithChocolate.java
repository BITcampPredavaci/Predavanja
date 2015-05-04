package ba.bitcamp.lectures.patterns.decorator;

/**
 * Decorates beverage with chocolate, and adds 0.7 to price.
 * 
 * @author emir
 *
 */
public class BeverageWithChocolate extends EnchancedBeverage {

	public BeverageWithChocolate(Beverage beverage) {
		super(beverage);
	}

	@Override
	protected double getEnchancmentPrice() {
		return 0.7;
	}

}
