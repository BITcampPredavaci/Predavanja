package abc.train.a;

import abc.train.LetterTruck;

/**
 * Trains engine. Aware of first truck.
 * 
 * @author emir
 *
 */
public class Engine {
	private LetterTruck firstTruck;
	
	/**
	 * Attach truck to engine. Does not care if something is previously attached.
	 * 
	 * @param firstTruck to attach to engine
	 */
	public void attachTruck(LetterTruck firstTruck) {
		this.firstTruck = firstTruck;
	}
	
	/**
	 * @return truck attached to engine or null if no attached trucks
	 */
	public LetterTruck getFirstTruck() {
		return firstTruck;
	}
	
	@Override
	public String toString() {
		return "[START]";
	}
}
