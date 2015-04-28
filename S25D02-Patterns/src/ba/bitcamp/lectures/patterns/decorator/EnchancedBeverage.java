package ba.bitcamp.lectures.patterns.decorator;

/**
 * Abstract decorator used for base class for other decarators. Enhances beverage and its price by enchancment price. 
 * 
 * @author emir
 *
 */
public abstract class EnchancedBeverage implements Beverage {

	private Beverage beverage;

	public EnchancedBeverage(Beverage beverage) {
		this.beverage = beverage;
	}
	
	@Override
	public double price() {
		return beverage.price() + getEnchancmentPrice();
	}

	protected abstract double getEnchancmentPrice();

}
