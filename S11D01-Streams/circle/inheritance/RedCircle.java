package circle.inheritance;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Klasa koja predstavlja krug crvene boje
 * 
 * Nacrtani krug se prikazuje crvene boje.
 * 
 * @author damir
 *
 */
public class RedCircle extends Circle {
	public RedCircle(Graphics g) {
		super(g);
	}

	/**
	 * Crta crveni krug
	 * 
	 * Interno ova metoda postavlja boju na crvenu, te zatim poziva drawAt()
	 * metodu superklase (Circle) čime dobijamo crveni krug.
	 */
	@Override
	public void drawAt(int x, int y) {
		// pamtimo prethodnu boju kako bismo je mogli vratiti
		Color previousColor = graphics.getColor();

		// postavljamo boju na crvenu, a zatim crtamo krug
		graphics.setColor(Color.RED);
		super.drawAt(x, y);
		
		// vraćamo boju na onu koja je bila postavljena prije crtanja linija. Na
		// ovaj način sljedeći korisnik istog Graphics objekta će imati
		// defaultnu boju, a ne crvenu
		graphics.setColor(previousColor);
	}
}
