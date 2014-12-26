package animation.array;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Panel koji predstavlja srž animacije.
 * 
 * Ovaj panel predstavlja prostor na kojem iscrtavamo krugove koji se kreću
 * prema dole.
 * 
 * @author damir
 *
 */
public class AnimationPanel extends JPanel implements ActionListener {
	// veličina krugova (dijametar, tj. prečnik)
	int[] circleDiameter = new int[] { 20, 30 };

	// x i y koordinate na kojima treba iscrtati krugove u trenutnom frame-u
	int[] x = { 0, 0 };
	int[] y = { 0, 0 };

	// "brzina kretanja" krugova
	int[] speed = new int[] { 2, 3 };

	// timer koji određuje koliko često će se crtati frame animacije
	Timer animationTimer;

	/**
	 * Konstruktor našeg panela za animaciju u kojem inicijaliziramo timer.
	 */
	public AnimationPanel() {
		// inicijaliziramo timer tako da 60 puta u jednoj sekundi (svakih 17ms)
		// pozove actionPerformed metodu na trenutnom objektu (specijalna `this`
		// varijabla)
		animationTimer = new Timer(1000 / 60, this);
		animationTimer.start();
	}

	/**
	 * Crta frame animacije. Pozicija na kojoj se krugovi iscrtavaju je određena
	 * ponašanjem na okidanje timera — vidi actionPerformed.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < x.length; i++) {
			g.fillOval(x[i], y[i], circleDiameter[i], circleDiameter[i]);
		}
	}

	/**
	 * Handler za "okidanje" timer-a. Ova metoda se poziva otprilike 60 puta u
	 * sekundi (vidi konstruktor).
	 * 
	 * Pri okidanju timer-a, mijenjamo poziciju na kojoj se krugovi iscrtavaju
	 * za vrijednosti iz speed niza.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		x[0] = getWidth() / 2;
		x[1] = getWidth() / 2 + circleDiameter[1];
		
		for (int i = 0; i < x.length; i++) {
			y[i] += speed[i];
		}

		// zahtijevamo od sistema da pozove paintComponent metodu u skoroj
		// budućnosti
		repaint();
	}
}
