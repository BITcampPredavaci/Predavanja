package callout.inner;

import java.util.Random;

/**
 * Klasa koja implementira prozivanje studenata.
 * 
 * @author damir
 *
 */
public class Students {

	/**
	 * Lista imena studenata koji mogu biti prozvani.
	 */
	private String[] names = new String[] { "Sanela", "Jesenko", "Emir",
			"Davor", "Adnan", "Selma", "Gordan", "Haris K", "Mirza",
			"Nermin V", "Vedad", "Emina", "Nedim", "Hikmet", "Edib", "Mustafa",
			"Nermin G", "Neldin", "Haris", "Nedžad", "Gorjan", "Amra" };

	/**
	 * Metoda vraća ime slučajno odabrane studetnice ili studenta.
	 * 
	 * @return ime studentice ili studenta koje treba sljedeće prozvati.
	 */
	public String getRandomStudent() {
		Random r = new Random();
		return names[r.nextInt(names.length)];
	}

}
