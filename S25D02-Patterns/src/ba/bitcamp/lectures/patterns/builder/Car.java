package ba.bitcamp.lectures.patterns.builder;

public class Car {
	int doors = 4;
	String engineType = "N/A";
	int maxAutonomy;
	boolean roof = true;
	
	@Override
	public String toString() {
		return String.format("%s with %d doors, %s roof, autonomy %dkm", engineType, doors, roof ? "with" : "without", maxAutonomy);
	}
}
