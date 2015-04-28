package ba.bitcamp.lectures.patterns.adapter;

import ba.bitcamp.lectures.patterns.adapter.lib.CarSketch;

public class CarSkatchRunner {
	public static void main(String[] args) {
		NondrawableCar c = new NondrawableCar();
		c.doors = 2;
		c.roof = true;
		
		CarSketch.skatch(new DrawlableCarAdapter(c));
	}
}
