package callout.implemented;

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
 * U ovoj implementaciji, MainFrame implementira interface ActionListener, tj.
 * sam prozor postaje listener za sve događaje koji se mogu desiti u prozoru.
 * Detaljniju diskusiju o nedostacima ovog pristupa pogledajte u komentarima na metodu
 * actionPerformed().
 * 
 * @author damir
 *
 */
public class MainFrame extends JFrame implements ActionListener {
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
		// prosljeđujemo trenutni objekat pomoću specijalne `this` varijable
		newNameButton.addActionListener(this);

		exitButton = new JButton("Izlaz");
		// prosljeđujemo trenutni objekat pomoću specijalne `this` varijable
		exitButton.addActionListener(this);

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
	 * ActionListener za sve akcije koje se mogu desiti u trenutnom prozoru.
	 * 
	 * Ovaj pristup je nezgodan jer moramo ispitati koji objekat je izvor događaja.
	 * Kako mijenjamo sadržaj prozora, tako moramo mijenjati i ovu metodu što
	 * može dovesti do grešaka u budućnosti uslijed zaboravljenih ili loše napisanih
	 * uslova.
	 * 
	 * Po pravilu, u objektno-orijentisonom programiranju, trebamo izbjegavati
	 * kondicionale i preferirati polimorfizam kad god možemo. Ovo je primjer
	 * kondicionala koji možemo jednostavno zamijeniti polimorfizmom koristeći
	 * inner ili anonimne klase.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newNameButton) {
			nameLabel.setText(students.getRandomStudent());
		} else {
			System.exit(0);
		}
	}
}
