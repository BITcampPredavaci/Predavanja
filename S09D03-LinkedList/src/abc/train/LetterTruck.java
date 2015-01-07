package abc.train;

/**
 * Train truck able to carry letter. Aware of truck that follows it.
 * 
 * @author emir
 *
 */
public class LetterTruck {
	private char letter;
	private LetterTruck nextTruck;
	
	/**
	 * Creates new truck that carries letter 
	 * @param letter truck's load
	 */
	public LetterTruck(char letter) {
		this.letter = letter;
	}
	
	/**
	 * Attach truck to this one
	 * @param next truck to attach
	 */
	public void attachNextTruck(LetterTruck next) {
		nextTruck = next;
	}
	
	/**
	 * @return truck that follows this truck or null if this truck is last one.
	 */
	public LetterTruck nextTruck() {
		return nextTruck;
	}
	
	/**
	 * @return letter carried by this truck
	 */
	public char getLetter() {
		return letter;
	}
	
	@Override
	public String toString() {
		return String.format("[%c]", letter);
	}
}
