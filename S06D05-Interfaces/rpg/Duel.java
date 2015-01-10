package rpg;

public class Duel {
	
	private GameObject first;
	private GameObject second;

	public Duel(GameObject first, GameObject second) {
		this.first = first;
		this.second = second;
	}
	
	public GameObject fight() {		
		for (int turnNumber = 1; first.isAlive() && second.isAlive(); turnNumber++) {
			System.out.printf("Potez %d:\n", turnNumber);

			System.out.printf("%s napada %s\n", first, second);
			first.attack(second);
			
			if (second.isAlive()) {
				System.out.printf("%s uzvraća\n", second);
				second.attack(first);
			} else {
				System.out.printf("%s je mrtav\n", second);
			}
		}
		
		GameObject winner = first.isAlive() ? first : second;
		
		System.out.printf("Pobjednik duela je %s\n", winner);
		System.out.println();
		
		return winner;
	}
	
}
