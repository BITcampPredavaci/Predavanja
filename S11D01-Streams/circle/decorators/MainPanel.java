package circle.decorators;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	/**
	 * Crta krugove koristeći dekoratore
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// jednostavan, crni krug
		Circle c = new Circle(g);
		c.drawAt(40, 40);
		
		// crnom krugu dodajemo crvenu boju kroz dekorator
		Circle rc = new RedCircleDecorator(c);
		rc.drawAt(290, 30);
		
		// crni krug crtamo prekrižen kroz dekorator
		Circle cc = new CrossedCircleDecorator(c);
		cc.drawAt(150, 120);
		
		// krug dekorisan crvenom bojom dekorišemo tako da postane prekrižen
		Circle crc = new CrossedCircleDecorator(rc);
		crc.drawAt(290, 190);

		// a možemo i prekriženi krug dekorisati crvenom bojom za isti efekat
		Circle rcc = new RedCircleDecorator(cc);
		rcc.drawAt(90, 250);
	}
}
