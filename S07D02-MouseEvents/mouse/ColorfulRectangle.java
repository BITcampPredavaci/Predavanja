package predavanje.mouse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorfulRectangle {

	/**
	 * Main metoda kreira prozor (JFrame) u koji stavlja objekat
	 * klase Canvas kao container.
	 * 
	 * Ovom container-u dajemo listener koji ??e reagovati na osnovne
	 * akcije mi??a (press, release, click, enter i exit).
	 * 
	 * @param args Argumenti komandne linije
	 */
	public static void main(String[] args) {
		RepaintListener listener = new RepaintListener(); 
		
		Canvas canvasPanel = new Canvas();
		canvasPanel.addMouseListener(listener);
		
		JFrame mainWindow = new JFrame("??areni pravougaonik!");
		mainWindow.setContentPane(canvasPanel);
		mainWindow.setSize(300, 200);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	/**
	 * Implementacija MouseListener eventa koja na klik dugmeta mi??a
	 * izvr??ava repaint izvorne komponente.
	 * 
	 * Ova metoda se izvr??ava automatski pri pritisku dugmeta.
	 * 
	 * @author damir
	 *
	 */
	public static class RepaintListener implements MouseListener {
		/**
		 * Dohva??a komponentu koja je izazavla event ("izvor" doga??aja)
		 * i na njoj poziva repaint() metodu.
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			Component source = (Component)e.getSource();
			source.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) { }

		@Override
		public void mouseReleased(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }
	}
	
	/**
	 * Vlastita klasa koja naslje??uje JPanel. U ovoj klasi
	 * redefini??emo paintComponent() metodu tako da svaki put
	 * nacrta pravougaonik druge boje.
	 * 
	 * @author damir
	 *
	 */
	public static class Canvas extends JPanel {
		/**
		 * Crta pravougaonik random boje koji pokriva cijeli panel (izuzev
		 * margine od 10px sa svake strane).
		 * 
		 * Ova metoda se izvr??ava automatski svaki put kada se panel
		 * treba ponovo nacrtati: npr. pri promjeni veli??ine prozora ili
		 * nakon poziva metode repaint().
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);	
			
			// Boju mo??emo postaviti daju??i RGB komponente kao float vrijednosti
			// u intervalu 0.0 do 1.0
			//
			// g.setColor(new Color(0.3f, 0.2f, 0.9f));
			
			// Boju tako??er mo??emo odrediti daju??i RGB komponente kao int vrijednosti
			// u intervalu 0 do 255
			//
			// g.setColor(new Color(255, 95, 32));

			// Boju mo??emo odrediti zadavanjem HSB komponenti kao float vrijednosti
			// (u intervalu 0.0 do 1.0)
			// H ??? hue (boja), S ??? saturation (zasi??enost), B ??? brightnest (svjetlina)
			//
			// g.setColor(Color.getHSBColor(0.3f,
			//                              (float)Math.random(),
			//                              (float)Math.random()));
			
			// Random boja
			g.setColor(new Color((float)Math.random(),
								 (float)Math.random(),
								 (float)Math.random()));
			
			g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);
		}
	}

}
