package rpg;

public class Dwarf implements GameObject {

	private String name;
	private int health = 30;

	public Dwarf(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getHealth() {
		return this.health;
	}
	
	@Override
	public boolean isAlive() {
		return this.health > 0;
	}

	@Override
	public void attack(GameObject other) {
		other.handleAttack(9);
	}

	@Override
	public void handleAttack(int attackStrength) {
		this.health -= (attackStrength - 2);
	}
	
	@Override
	public String toString() {
		return getName() + " (health=" + getHealth() + ")";
	}

}
