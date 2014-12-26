package callout.separate;

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
 * U ovoj implomentaciji, koristimo odvojene klase kao event listenere
 * (NextSTudentListener i ExitListener). Ovo je primjer koji pokazuje zašto
 * **nije** uobičajeno definisati listenere u posebnim klasama, te ujedno
 * demonstrira da su listeneri klase kao i bilo koje druge.
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

		// kako bi NextStudentListener objekat mogao promijeniti tekst labele,
		// on mora imati pristup labeli, kao i students objektu iz kojeg će
		// dobiti ime sljedećeg studenta kojeg treba prozvati
		NextStudentListener nsl = new NextStudentListener(nameLabel, s);

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

}
