package circle.decorators;

import java.awt.Color;

/**
 * Dekorator koji krugu daje crvenu boju
 * 
 * @author damir
 *
 */
public class RedCircleDecorator extends Circle {

	private Circle decoratedCircle;

	/**
	 * Konstruktor dekoratora prima krug koji treba nacrtati crvene boje
	 * 
	 * Proslijeđeni krug se dodjeljuje instance varijabli koju koristimo u
	 * drawAt() metodi.
	 * 
	 * @param decoratedCircle
	 *            krug koji treba nacrtati crvenom bojom
	 */
	public RedCircleDecorator(Circle decoratedCircle) {
		super(decoratedCircle.graphics);
		this.decoratedCircle = decoratedCircle;
	}

	/**
	 * Postavlja boju na crvenu prije crtanja kruga
	 * 
	 * Metoda prvo koristi graphics objekat iz dekorisanog kruga da postavi
	 * crvenu boju, a zatim poziva drawAt() metodu kruga koji je proslijeđen
	 * konstruktoru kao parametar da nacrta krug.
	 */
	@Override
	public void drawAt(int x, int y) {
		// pamtimo prethodnu boju kako bismo je mogli vratiti
		Color previousColor = decoratedCircle.graphics.getColor();

		// postavljamo boju na crvenu, a zatim crtamo krug
		decoratedCircle.graphics.setColor(Color.RED);
		decoratedCircle.drawAt(x, y);

		// vraćamo boju na onu koja je bila postavljena prije crtanja linija. Na
		// ovaj način sljedeći korisnik istog Graphics objekta će imati
		// defaultnu boju, a ne crvenu
		decoratedCircle.graphics.setColor(previousColor);
	}
}
