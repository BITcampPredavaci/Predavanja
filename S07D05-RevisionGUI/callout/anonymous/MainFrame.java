package callout.anonymous;

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
 * U ovoj implementaciji, koristimo anonimne klase kao event listenere. Ovo je
 * primjer koji pokazuje najlakši način da dodamo jednostavne listenere (od par
 * linija kôda).
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

		newNameButton = new JButton("Dalje...");

		// koristimo anonimnu klasu koja implementira interface ActionListener
		// kao listener za klik dumgeta newNameButton.
		newNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nameLabel.setText(students.getRandomStudent());
			}
		});

		exitButton = new JButton("Izlaz");

		// koristimo anonimnu klasu koja implementira interface ActionListener
		// kao listener za klik dugmeta exitButton
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(nameLabel, BorderLayout.CENTER);
		mainPanel.add(newNameButton, BorderLayout.NORTH);
		mainPanel.add(exitButton, BorderLayout.SOUTH);

		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainPanel);
	}
}
