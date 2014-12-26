package animation.game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Panel koji predstavlja srž igre.
 * 
 * Ovaj panel predstavlja prostor na kojem iscrtavamo krug koji pada uz ubrzanje
 * (kako bi animacija "padanja" bila uvjerljiva).
 * 
 * @author damir
 *
 */
public class AnimationPanel extends JPanel implements ActionListener {
	// veličina kruga (dijametar, tj. prečnik)
	int circleDiameter = 30;

	// x i y koordinate na kojima treba iscrtati krug u trenutnom frame-u
	double x = 0;
	double y = 0;

	// "brzina kretanja" kruga
	double speed = 0;

	// ubrzanje, tj. faktor za koji se u svakom frame-u mijenja brzina kretanja
	final double ACCELERATION = 0.2;

	// timer koji određuje koliko često će se crtati frame animacije
	Timer animationTimer;

	/**
	 * Konstruktor našeg panela za igru.
	 * 
	 * Ovdje inicijaliziramo sve objekte i listenere za timer i miš.
	 */
	public AnimationPanel() {
		// inicijaliziramo timer tako da 60 puta u jednoj sekundi (svakih 17ms)
		// pozove actionPerformed metodu na trenutnom objektu (specijalna `this`
		// varijabla)
		animationTimer = new Timer(1000 / 60, this);
		animationTimer.start();

		// dodajemo anonimnu klasu koja proširuje apstraktnu klasu MouseAdapter
		// i redefiniše metodu mousePressed, tako da na klik miša lopti damo
		// početnu brzinu kretanja prema gore. Ova brzina će se postepeno
		// povećavati (tj. približavati nuli, radi toga što speed varijablu u
		// svakom frame-u povećavamo za ACCELERATION — vidi actionPerformed)
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				speed = -7;
			}
		});
	}

	/**
	 * Crta frame animacije. Pozicija na kojoj se krug iscrtava je određena
	 * ponašanjem na okidanje timera — vidi actionPerformed.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.fillOval((int) x, (int) y, circleDiameter, circleDiameter);
	}

	/**
	 * Handler za "okidanje" timer-a. Ova metoda se poziva otprilike 60 puta u
	 * sekundi (vidi konstruktor).
	 * 
	 * Pri okidanju timer-a, mijenjamo poziciju na kojoj se krug iscrtava za
	 * vrijednost speed varijable. Međutim, i vrijednost "speed" varijable
	 * mijenjamo za vrijednost ACCELERATION konstante, tako da stvaramo iluziju
	 * ubrzavanja (ubrzavanje je, po definiciji, promjena brzine kretanja) kruga
	 * prema dole, odonsno iluziju padanja.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		x = getWidth() / 2 - circleDiameter / 2;

		y += speed;
		speed += ACCELERATION;

		// zahtijevamo od sistema da pozove paintComponent metodu u skoroj
		// budućnosti
		repaint();
	}
}
