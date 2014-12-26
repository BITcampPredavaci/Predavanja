package animation.array;

import javax.swing.JFrame;

/**
 * Glavni prozor aplikacije
 * 
 * @author damir
 *
 */
public class AnimationFrame extends JFrame {
	AnimationPanel animationPanel;

	/**
	 * Konstruktor glavnog prozora aplikacije.
	 * 
	 * U prozor stavljamo ponel tipa AnimationPanel koji prikazuje animaciju i
	 * dodaje neophodne event listenere.
	 */
	public AnimationFrame() {
		super("Animation");

		setSize(400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		animationPanel = new AnimationPanel();
		setContentPane(animationPanel);
	}
}
