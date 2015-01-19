package circle.decorators;

import java.awt.Color;

/**
 * Dekorator koji preko kruga dodaje prekrižene linije
 * 
 * @author damir
 *
 */
public class CrossedCircleDecorator extends Circle {

	private Circle decoratedCircle;

	/**
	 * Konstruktor dekoratora prima krug koji treba nacrtati prekrižen
	 * 
	 * Proslijeđeni krug se dodjeljuje instance varijabli koju koristimo u
	 * drawAt() metodi.
	 * 
	 * @param decoratedCircle
	 *            krug koji treba prekrižiti
	 */
	public CrossedCircleDecorator(Circle decoratedCircle) {
		super(decoratedCircle.graphics);
		this.decoratedCircle = decoratedCircle;
	}

	/**
	 * Dodaje dijagonalne ukrštene linije nacrtanom krugu
	 * 
	 * Metoda prvo poziva drawAt() metodu kruga koji je proslijeđen konstruktoru
	 * kao parametar, a zatim koristi graphics objekat iz dekorisanog kruga da
	 * nacrta prekrižene linije.
	 */
	@Override
	public void drawAt(int x, int y) {
		// crtamo krug koji dekorišemo
		decoratedCircle.drawAt(x, y);
		
		// pamtimo prethodnu boju kako bismo je mogli vratiti
		Color previousColor = graphics.getColor();
		
		// postavljamo boju linija na bijelu
		graphics.setColor(Color.WHITE);
		
		// crtamo linije
		decoratedCircle.graphics.drawLine(
				x - decoratedCircle.size / 2,
				y - decoratedCircle.size / 2,
				x + decoratedCircle.size / 2,
				y + decoratedCircle.size / 2);
		
		decoratedCircle.graphics.drawLine(
				x + decoratedCircle.size / 2,
				y - decoratedCircle.size / 2,
				x - decoratedCircle.size / 2,
				y + decoratedCircle.size / 2);
		
		// vraćamo boju na onu koja je bila postavljena prije crtanja linija. Na
		// ovaj način sljedeći korisnik istog Graphics objekta će imati
		// defaultnu boju, a ne bijelu
		graphics.setColor(previousColor);
	}
}
