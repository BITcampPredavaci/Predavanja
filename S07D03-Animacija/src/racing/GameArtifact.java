package racing;

import java.awt.Graphics;

/**
 * Game object that have visual representation.
 * Game will call draw on any object registered as GameArtifact
 * @author emir
 *
 */
public interface GameArtifact {
	/**
	 * Invoked whenever game requires repaint
	 * @param graphics to use to draw artifact
	 */
	void draw(Graphics graphics);
}
