package callout.implemented;

import javax.swing.JFrame;

/**
 * Program koji prikazuje slučajno odabrano ime polaznika kojeg treba prozvati.
 * 
 * Ova implementacija koristi jednu inner klase kao listener za više objekat.
 * Ovdje demonstriramo zašto je ovakav način pisanja kôda nepraktičan i zašto bismo
 * uglavnom trebali preferirati inner ili anonimne klase.
 * 
 * @author damir
 *
 */
public class Callout {

	/**
	 * Ulazna tačka programa.
	 * 
	 * Ovdje pravimo objekat klase Students,te glavni prozor aplikacije koji
	 * odmah prikazujemo.
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Students students = new Students();

		JFrame mainWindow = new MainFrame(students);
		mainWindow.setVisible(true);
	}

}
