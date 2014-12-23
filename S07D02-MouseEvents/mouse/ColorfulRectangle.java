package predavanja;

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
	 * Ovom container-u dajemo listener koji će reagovati na osnovne
	 * akcije miša (press, release, click, enter i exit).
	 * 
	 * @param args Argumenti komandne linije
	 */
	public static void main(String[] args) {
		RepaintListener listener = new RepaintListener(); 
		
		Canvas canvasPanel = new Canvas();
		canvasPanel.addMouseListener(listener);
		
		JFrame mainWindow = new JFrame("Šareni pravougaonik!");
		mainWindow.setContentPane(canvasPanel);
		mainWindow.setSize(300, 200);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	/**
	 * Implementacija MouseListener eventa koja na klik dugmeta miša
	 * izvršava repaint izvorne komponente.
	 * 
	 * Ova metoda se izvršava automatski pri pritisku dugmeta.
	 * 
	 * @author damir
	 *
	 */
	public static class RepaintListener implements MouseListener {
		/**
		 * Dohvaća komponentu koja je izazavla event ("izvor" događaja)
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
	 * Vlastita klasa koja nasljeđuje JPanel. U ovoj klasi
	 * redefinišemo paintComponent() metodu tako da svaki put
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
		 * Ova metoda se izvršava automatski svaki put kada se panel
		 * treba ponovo nacrtati: npr. pri promjeni veličine prozora ili
		 * nakon poziva metode repaint().
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);	
			
			// Boju možemo postaviti dajući RGB komponente kao float vrijednosti
			// u intervalu 0.0 do 1.0
			//
			// g.setColor(new Color(0.3f, 0.2f, 0.9f));
			
			// Boju također možemo odrediti dajući RGB komponente kao int vrijednosti
			// u intervalu 0 do 255
			//
			// g.setColor(new Color(255, 95, 32));

			// Boju možemo odrediti zadavanjem HSB komponenti kao float vrijednosti
			// (u intervalu 0.0 do 1.0)
			// H — hue (boja), S — saturation (zasićenost), B — brightness (svjetlina)
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
