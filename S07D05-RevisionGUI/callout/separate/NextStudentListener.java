package callout.separate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

/**
 * Primjer izolovane listener klase.
 * 
 * Kako bi listener mogao smisleno reagovati na akciju, on mora imati pristup
 * objektima s kojima treba vršiti kolaboraciju. U ovom slučaju, sve potrebne
 * objekte predajemo kroz konstruktor.
 * 
 * Ovo je nepraktično, te ovakav pristup jako rijetko koristimo u praksi.
 * 
 * @author damir
 *
 */
public class NextStudentListener implements ActionListener {

	private JLabel nameLabel;
	private Students students;

	/**
	 * Konstruktor prima objekte s kojima listener radi kao parametre te ih
	 * dodjeljuje u privatne atribute.
	 * 
	 * @param nameLabel
	 *            labela čiji tekst treba promijeniti na "okidanju" akcije
	 * @param students
	 *            objekat iz kojeg možemo dobaviti ime studentice/studenta koje
	 *            treba prozvati sljedeće
	 */
	public NextStudentListener(JLabel nameLabel, Students students) {
		this.nameLabel = nameLabel;
		this.students = students;
	}

	/**
	 * Kada je akcija okinuta (npr. dugme pritisnuto), mijenjamo tekst labele na
	 * koju imamo rerenecu na ime koje nam daje getRandomStudent() metoda
	 * objekta students.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		nameLabel.setText(students.getRandomStudent());
	}

}
