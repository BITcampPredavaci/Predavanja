package predavanje.mouse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painter {
	
	/**
	 * Main metoda kreira prozor (JFrame) u koji stavlja objekat
	 * klase Canvas kao container.
	 * 
	 * Ovom container-u postavljamo boju pozadine na bijelu i dajemo
	 * listenere koji ??e reagovati na osnovne akcije mi??a (press,
	 * release, click, enter i exit), kao i na kretanje mi??a (pomjeranje
	 * i povla??enje ??? tj. pomjeranje dok dugme mi??a dr??imo stisnutim).
	 * 
	 * @param args Argumenti komandne linije
	 */
	public static void main(String[] args) {
		PaintListener listener = new PaintListener(); 
		
		Canvas canvasPanel = new Canvas();
		canvasPanel.setBackground(Color.WHITE);
		canvasPanel.addMouseListener(listener);
		canvasPanel.addMouseMotionListener(listener);
		
		JFrame mainWindow = new JFrame("Na????mali paint");
		mainWindow.setContentPane(canvasPanel);
		mainWindow.setSize(300, 200);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
	
	/**
	 * Klasa koja implementira dva interface-a: MouseListener (za osnovne
	 * akcije mi??a) i MouseMotionListener (za kretanje i povla??enje kursora mi??a).
	 * 
	 * Stisak dugmeta mi??a koristimo da odaberemo boju za crtanje, a povla??enje
	 * koristimo za crtanje. 
	 * 
	 * @author damir
	 *
	 */
	public static class PaintListener implements MouseListener, MouseMotionListener {
		/**
		 * Boja koja se koristi za crtanje. Ovdje ??emo spasiti boju koju
		 * korisnik odabere klikom.
		 */
		private Color selectedColor = Color.RED;
		
		/**
		 * Metoda koja se automatski poziva pri kliku dugmeta mi??a.
		 * 
		 * Provjerava koordinate na kojima je dugme bilo kliknuto i
		 * ako koordinate odgovaraju prostoru na kojem je nacrtana jedna od boja
		 * spa??avamo boju u atribut selectedColor.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			Component source = (Component)e.getSource();
			int colorPickerSize = 30;
			
			if (e.getY() >= source.getHeight() - colorPickerSize) {
				if (e.getX() < colorPickerSize) {
					selectedColor = Color.RED;
				} else if (e.getX() < colorPickerSize * 2) {
					selectedColor = Color.GREEN;
				} else if (e.getX() < colorPickerSize * 3) {
					selectedColor = Color.BLUE;
				}				
			}
		}

		/**
		 * Metoda koja se automatski poziva svaki put kad se mi?? pomjeri
		 * dok je dugme stisnuto.
		 * 
		 * U ovoj metodi koristimo boju koju smo spasili pri kliku (atribut
		 * selectedColor) da obojimo krug.
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			Component source = (Component)e.getSource();
			Graphics g = source.getGraphics();
			
			g.setColor(selectedColor);
			
			int ovalDiameter = 10;
			g.fillOval(e.getX() - ovalDiameter/2, e.getY() - ovalDiameter/2, ovalDiameter, ovalDiameter);			
		}

		@Override
		public void mouseReleased(MouseEvent e) { }

		@Override
		public void mouseClicked(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }

		@Override
		public void mouseMoved(MouseEvent e) { }
	}

	/**
	 * Vlastita klasa koja naslje??uje JPanel. U ovoj klasi redefini??emo
	 * paintComponent() tako da iscrtamo obojene pravougaonike u donjem dijelu
	 * ekrana. 
	 * 
	 * @author damir
	 *
	 */
	public static class Canvas extends JPanel {
		/**
		 * Crta tri pravougaonika razli??itih boja jedan pored drugog
		 * koje ??emo koristiti kao paletu na??eg programa.
		 */
		@Override
		public void paintComponent(Graphics g) {
			int colorPickerSize = 30;
			
			g.setColor(Color.RED);
			g.fillRect(colorPickerSize * 0, getHeight() - colorPickerSize, colorPickerSize, colorPickerSize);
			
			g.setColor(Color.GREEN);
			g.fillRect(colorPickerSize * 1, getHeight() - colorPickerSize, colorPickerSize, colorPickerSize);
			
			g.setColor(Color.BLUE);
			g.fillRect(colorPickerSize * 2, getHeight() - colorPickerSize, colorPickerSize, colorPickerSize);
		}
	}

}
