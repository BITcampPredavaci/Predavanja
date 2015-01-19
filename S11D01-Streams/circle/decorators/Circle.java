package circle.decorators;

import java.awt.Graphics;

/**
 * Klasa koja određuje kako se krug crta na ekranu
 * 
 * @author damir
 *
 */
public class Circle {
	protected Graphics graphics;
	protected int size = 50;

	/**
	 * Konstruktor klase prima Graphics objekat koji koristi za crtanje
	 * 
	 * @param graphics
	 *            Graphics objekat koji koristimo za crtanje.
	 */
	public Circle(Graphics graphics) {
		if (graphics == null) {
			throw new IllegalArgumentException(
					"Argument graphics must not be null.");
		}

		this.graphics = graphics;
	}

	/**
	 * Draws circle centered at the specified coordinates
	 * 
	 * @param x
	 *            x coordinate of the circle's center
	 * @param y
	 *            y coordinate of the circle's center
	 */
	public void drawAt(int x, int y) {
		graphics.fillOval(x - size / 2, y - size / 2, size, size);
	}

}
