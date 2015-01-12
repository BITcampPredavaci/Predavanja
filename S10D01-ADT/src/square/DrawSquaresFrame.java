package square;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Klasa koja predstavlja glavni prozor naše aplikacije
 * 
 * @author damir
 *
 */
public class DrawSquaresFrame extends JFrame {
	private JPanel canvasPanel;

	/**
	 * U konstruktoru u prozor dodajemo panel na kojem ćemo crtati (CanvasPanel)
	 * te postavljamo standardne postavke prozora
	 */
	public DrawSquaresFrame() {
		super("Squares");

		canvasPanel = new CanvasPanel();
		setContentPane(canvasPanel);

		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
