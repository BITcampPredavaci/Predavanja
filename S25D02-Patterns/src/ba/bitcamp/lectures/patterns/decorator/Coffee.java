package ba.bitcamp.lectures.patterns.decorator;

/** 
 * Concreete implementation of beverage.
 * 
 * @author emir
 *
 */
public class Coffee implements Beverage {

	@Override
	public double price() {
		return 1.0;
	}
	
}
