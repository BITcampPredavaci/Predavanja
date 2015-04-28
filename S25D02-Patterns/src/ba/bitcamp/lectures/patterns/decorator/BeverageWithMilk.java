package ba.bitcamp.lectures.patterns.decorator;

/**
 * Decorates beverage with milk, and adds 0.5 to price.
 * 
 * @author emir
 *
 */
public class BeverageWithMilk extends EnchancedBeverage {


	public BeverageWithMilk(Beverage beverage) {
		super(beverage);
	}

	@Override
	protected double getEnchancmentPrice() {
		return 0.5;
	}
}
