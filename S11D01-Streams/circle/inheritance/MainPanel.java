package circle.inheritance;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	/**
	 * Crta krugove koristeći hijerarhiju klasa krugova
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Circle c = new Circle(g);
		c.drawAt(40, 40);
		
		Circle rc = new RedCircle(g);
		rc.drawAt(290, 30);
		
		Circle cc = new CrossedCircle(g);
		cc.drawAt(150, 120);
		
		// Kako nacrtati crveni prekriženi krug?
		// HINT: Pogledajte circle.decorators.MainPanel
	}
}
