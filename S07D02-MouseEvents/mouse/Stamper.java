package predavanja;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Stamper {
	
	/**
	 * Main metoda kreira prozor (JFrame) u koji stavlja objekat
	 * klase JPanel kao container.
	 * 
	 * Ovom container-u postavljamo boju pozadine na bijelu i dajemo
	 * listener koji će reagovati na osnovne akcije miša (press,
	 * release, click, enter i exit).
	 * 
	 * @param args Argumenti komandne linije
	 */
	public static void main(String[] args) {
		StampListener listener = new StampListener(); 
		
		JPanel canvasPanel = new JPanel();
		canvasPanel.setBackground(Color.WHITE);
		canvasPanel.addMouseListener(listener);
		
		JFrame mainWindow = new JFrame("San svakog birokrate!");
		mainWindow.setContentPane(canvasPanel);
		mainWindow.setSize(300, 200);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	/**
	 * Klasa koja implementira MouseListener tako da na klikanje
	 * izvornog objekta na njemu crta kružić na mjestu klika.
	 * 
	 * @author damir
	 *
	 */
	public static class StampListener implements MouseListener {
		/**
		 * Metoda dohvaća komponentu koja je primila klik, te njen Graphics
		 * objekat. Ovaj objekat koristimo da direktno na komponenti nacrtamo
		 * kružić koristeći fillOval() metodu.
		 * 
		 * NAPOMENA: Ovo nije najbolji način da se ovaj problem riješi, ali
		 * ćemo bolje rješenje ostaviti za budućnost.
		 * 
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			Component source = (Component)e.getSource();
			Graphics g = source.getGraphics();
			
			g.setColor(Color.ORANGE);
			
			int ovalDiameter = 90;
			g.fillOval(e.getX() - ovalDiameter/2, e.getY() - ovalDiameter/2, ovalDiameter, ovalDiameter);
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

}
