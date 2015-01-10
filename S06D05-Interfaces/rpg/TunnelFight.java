package rpg;

public class TunnelFight {

	public static void main(String[] args) {
		Dwarf defender = new Dwarf("Obrim, sin Agammov");
		
		GameObject[] attackers = new GameObject[] {
			new Orc(),
			new Orc(),
			new GameObject() {
				int health = 3;
				
				@Override
				public boolean isAlive() {
					return health > 0;
				}
				
				@Override
				public void handleAttack(int attackStrength) {
					health -= attackStrength * 2;
				}
				
				@Override
				public void attack(GameObject other) {
					other.handleAttack(3);
				}
				
				@Override
				public String toString() {
					return "Anonimni goblin";
				}
			},
			new Orc(),
			new Orc(),
			new Orc(),
			new Orc(),
			new Orc(),
			new Orc()
		};
		
		for (int i = 0; i < attackers.length && defender.isAlive(); i++) {
			System.out.printf("Duel %d: %s u borbi protiv %s\n", i+1, defender, attackers[i]);
			Duel duel = new Duel(defender, attackers[i]);
			duel.fight();
		}
		
		if (defender.isAlive()) {
			System.out.printf("Selo je uspješno odbranjeno! %s je heroj!\n", defender);
		} else {
			System.out.printf("%s je stradao pod navalom... Selu nema spasa\n", defender);
		}
	}

}
