package square;

import javax.swing.JFrame;

/**
 * Program koji prikazuje prozor unutar kojeg korisnik može crtati kvadratiće
 * 
 * Prozoru programa je moguće promijeniti dimenzije bez da se crtež izgubi, te
 * je omogućen i "undo", tj. brisanje zadnje nacrtane tačke.
 * 
 * @author damir
 *
 */
public class DrawSquaresApp {

	/**
	 * U main metodi pravimo novi prozor (DrawSquaresFrame) i prikazujemo ga
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new DrawSquaresFrame();
		frame.setVisible(true);
	}

}
