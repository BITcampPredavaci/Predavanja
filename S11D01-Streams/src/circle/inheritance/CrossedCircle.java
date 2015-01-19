package circle.inheritance;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Klasa koja predstavlja prekriženi krug
 * 
 * Nacrtani krug se prikazuje s dvije dijagonalne linije koje se sjeku u centru
 * kruga.
 * 
 * @author damir
 *
 */
public class CrossedCircle extends Circle {
	public CrossedCircle(Graphics g) {
		super(g);
	}

	/**
	 * Crta prekrižen krug
	 * 
	 * Interno ova metoda poziva drawAt metodu superklase (Circle), a zatim crta
	 * dvije dijagonalne linije bijele boje.
	 */
	@Override
	public void drawAt(int x, int y) {
		// crtamo obični krug
		super.drawAt(x, y);

		// pamtimo prethodnu boju kako bismo je mogli vratiti
		Color previousColor = graphics.getColor();

		// postavljamo boju linija na bijelu
		graphics.setColor(Color.WHITE);

		// crtamo linije
		graphics.drawLine(
				x - size / 2,
				y - size / 2,
				x + size / 2,
				y + size / 2);
		graphics.drawLine(
				x + size / 2,
				y - size / 2,
				x - size / 2,
				y + size / 2);

		// vraćamo boju na onu koja je bila postavljena prije crtanja linija. Na
		// ovaj način sljedeći korisnik istog Graphics objekta će imati
		// defaultnu boju, a ne bijelu
		graphics.setColor(previousColor);
	}
}
