package rpg;

public class Orc implements GameObject {
	private int health = 10;
	
	@Override
	public boolean isAlive() {
		return this.health > 0;
	}

	@Override
	public void attack(GameObject other) {
		other.handleAttack(5);
	}

	@Override
	public void handleAttack(int attackStrength) {
		health -= attackStrength;
	}

	@Override
	public String toString() {
		return "Orc (health=" + health + ")";
	}
}
