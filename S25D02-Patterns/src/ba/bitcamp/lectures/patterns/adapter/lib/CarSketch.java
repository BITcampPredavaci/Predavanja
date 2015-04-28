package ba.bitcamp.lectures.patterns.adapter.lib;

/**
 * Simulation of library that use different interface for representing car.
 * 
 * @author emir
 *
 */
public class CarSketch {
	
	public static void skatch(DrawableCar drawableCar) {
		System.out.println(drawableCar.isConvertableRoof() ? "Cabrio" : "Sedan");
		System.out.println("Doors: " + drawableCar.getDoorNumber() );
	}
}
