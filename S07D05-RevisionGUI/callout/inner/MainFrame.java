package callout.inner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Glavni prozor aplikacije.
 * 
 * Napravili smo vlastitu klasu MainFrame koja nasljeđuje JFrame, kako bismo na
 * jednom mjestu grupisali sav kôd koji se tiče prikaza aplikacije.
 * 
 * U ovoj implementaciji, koristimo inner klase kao event listenere
 * (NextStudentListener i ExitListener). Ovo je primjer koji pokazuje kako je u
 * opštem slučaju najpraktičnije definisati listener.
 * 
 * @author damir
 *
 */
public class MainFrame extends JFrame {
	private JLabel nameLabel;
	private JButton newNameButton;
	private JButton exitButton;
	private JPanel mainPanel;
	private Students students;

	/**
	 * Konstruktor glavnog prozora.
	 * 
	 * U konstruktoru glavnog prozora podešavamo atribute prozora i
	 * inicijaliziramo sve komponente koje će se nalaziti u prozoru.
	 * 
	 * @param s
	 */
	public MainFrame(Students s) {
		super("Prozivka");

		this.students = s;

		nameLabel = new JLabel();
		nameLabel.setText(s.getRandomStudent());

		// Postavljamo tekst labele u centar labele
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

		NextStudentListener nsl = new NextStudentListener();

		newNameButton = new JButton("Dalje...");
		newNameButton.addActionListener(nsl);

		ExitListener el = new ExitListener();

		exitButton = new JButton("Izlaz");
		exitButton.addActionListener(el);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(nameLabel, BorderLayout.CENTER);
		mainPanel.add(newNameButton, BorderLayout.NORTH);
		mainPanel.add(exitButton, BorderLayout.SOUTH);

		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainPanel);
	}

	/**
	 * Primjer inner listener klase.
	 * 
	 * Inner klase po prirodi imaju direktan pristup svim atributima i metodama
	 * objekta kojem pripadaju.
	 * 
	 * @author damir
	 *
	 */
	private class NextStudentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// nameLabel i students su privatni atributi klase MainFrame, te im
			// imamo pristup iz ove inner klase
			nameLabel.setText(students.getRandomStudent());
		}
	}

	/**
	 * Još jedan primjer inner klase.
	 * 
	 * Ova klasa ne mora biti inner (zasad nema potrebe da pristupa atributima i
	 * metodama objekta kojem pripada.
	 * 
	 * @author damir
	 *
	 */
	private class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
