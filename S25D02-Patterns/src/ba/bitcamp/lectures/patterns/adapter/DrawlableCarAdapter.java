package ba.bitcamp.lectures.patterns.adapter;

import ba.bitcamp.lectures.patterns.adapter.lib.DrawableCar;

/**
 * Adapter for {@link NondrawableCar} to {@link DrawableCar}. It use composition to implement adapter.
 * 
 * @author emir
 *
 */
public class DrawlableCarAdapter implements DrawableCar {

	private NondrawableCar car;

	public DrawlableCarAdapter(NondrawableCar car) {
		this.car = car;
	}
	
	@Override
	public int getDoorNumber() {
		return car.doors;
	}

	@Override
	public boolean isConvertableRoof() {
		return !car.roof;
	}

}
