package predavanje.mouse;

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
	 * listener koji ??e reagovati na osnovne akcije mi??a (press,
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
	 * izvornog objekta na njemu crta kru??i?? na mjestu klika.
	 * 
	 * @author damir
	 *
	 */
	public static class StampListener implements MouseListener {
		/**
		 * Metoda dohva??a komponentu koja je primila klik, te njen Graphics
		 * objekat. Ovaj objekat koristimo da direktno na komponenti nacrtamo
		 * kru??i?? koriste??i fillOval() metodu.
		 * 
		 * NAPOMENA: Ovo nije najbolji na??in da se ovaj problem rije??i, ali
		 * ??emo bolje rje??enje ostaviti za budu??nost.
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
