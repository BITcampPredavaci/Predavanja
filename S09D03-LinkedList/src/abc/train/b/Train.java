package abc.train.b;

import abc.train.LetterTruck;

/**
 * Train with ignored engine. Aware of first and last truck.
 * Provides only method to attach new truck at the end.
 * 
 * @author emir
 *
 */
public class Train {
	private LetterTruck firstTruck;
	private LetterTruck lastTruck;
	
	/**
	 * Attach truck to last truck in train.
	 * 
	 * @param truck to attach at the end
	 */
	public void attachTruck(LetterTruck truck) {
		if (lastTruck != null) {
			lastTruck.attachNextTruck(truck);
			lastTruck = truck;
		} else {
			firstTruck = lastTruck = truck;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder trainString = new StringBuilder("[START]");
		LetterTruck truck = firstTruck;
		while (truck != null) {
			trainString.append("-->").append(truck.getLetter());
			truck = truck.nextTruck();
		}
		return trainString.toString();
	}
}
