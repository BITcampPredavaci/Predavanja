package ba.bitcamp.lectures.patterns.builder;

/**
 * Demonstrates builder pattern. 
 * Enables creating new object by chaining part building methods.
 * It can either collect info and build when build invoked, or modify object. 
 * 
 * Build method returns finished object and initiate new one - it is this implementation logic - not general rule. 
 * 
 * @author emir
 *
 */
public class CarBuilder {

	Car car;

	public CarBuilder() {
		car = new Car();
	}

	public CarBuilder diesel() {
		car.engineType = "Diesel";
		car.maxAutonomy = 500;
		return this;
	}

	public CarBuilder limo() {
		car.doors = 4;
		return this;
	}

	public CarBuilder coupe() {
		car.doors = 2;
		return this;
	}

	public CarBuilder cabrio() {
		car.roof = false;
		return this;
	}

	public Car build() {
		Car toReturn = car;
		car = new Car();
		return toReturn;
	}

	public static void main(String[] args) {
		// build new car by chaining methods and calling build at the end
		Car car = new CarBuilder().diesel().coupe().cabrio().build();
		System.out.println(car);
	}
}
