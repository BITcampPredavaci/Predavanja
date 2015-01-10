package rpg;

public interface GameObject {
	
	boolean isAlive();
	void attack(GameObject other);
	void handleAttack(int attackStrength);
	
}
