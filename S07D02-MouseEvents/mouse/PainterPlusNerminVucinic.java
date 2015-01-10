import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PainterPlusNerminVucinic {
	/**
	 * Lista boja koje korisnik može odabrati za crtanje.
	 */
	private static Color[] palette = new Color[] {
		Color.WHITE,
		Color.BLACK,
		Color.RED,
		Color.BLUE,
		Color.GREEN,
		Color.CYAN,
		Color.MAGENTA,
		new Color(133, 7, 42),
	};

	/**
	 * Veličina kvadratića u paleti boja.
	 */
	private static int colorPickerSize = 50;
	private static int lineWidthPickerSize = 40;
	
	private static int prevX; // The previous location of the mouse.
	private static int prevY; // The previous location of the mouse.

	public static void main(String[] args) {
		PaintListener listener = new PaintListener();
		
		Canvas canvasPanel = new Canvas();
		canvasPanel.setBackground(Color.WHITE);
		canvasPanel.addMouseListener(listener);
		canvasPanel.addMouseMotionListener(listener);
		
		JFrame mainWindow = new JFrame("Paint+");
		mainWindow.setContentPane(canvasPanel);
		
		// postavljamo širinu prozora tako da vidimo sve boje
		mainWindow.setSize(palette.length * colorPickerSize, 500);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

	public static class PaintListener implements MouseListener,
			MouseMotionListener {
		private Color selectedColor = Color.RED;
		private int selectedLineWidth = 3;

		/**
		 * Provjerava koordinate na kojima se nalazio kursor miša i poredi s
		 * indeksima niza palette u kojem se nalaze boje iscrtane na dnu ekrana.
		 * Ako se klik poklapa s nekom bojom, stavlja tu boju u privatni atribut
		 * selectedColor.
		 *
		 * Obratite pažnju da ovdje koristimo dva privatna statička atributa
		 * klase PainterPlus kojima imamo pristup jer je klasa PaintListener
		 * static nested klasa koja pripada klasi PaintPlus. Ako bismo klasu
		 * PaintListener izdvojili u poseban fajl, ovim atributima ne bismo
		 * imali pristup.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			Component source = (Component) e.getSource();
			if (e.getY() >= source.getHeight() - colorPickerSize) {
				for (int i = 0; i < palette.length; i++) {
					if (e.getX() < colorPickerSize * (i + 1)) {
						selectedColor = palette[i];
						// ako bismo ovdje zaboravili break, for petlja
						// bi se nastavila izvršavati za ostale boje
						// a pošto bi uslov bio ispunjen i za njih,
						// bila bi odabrana posljednja broja u listi, a ne
						// ona na koju je korisnik kliknuo
						break;
					}
				}
			}
			if (e.getY() < lineWidthPickerSize
					&& e.getX() > source.getWidth() - 3 * lineWidthPickerSize) {
				if (e.getX() > source.getWidth() - lineWidthPickerSize)
					selectedLineWidth = 3;
				else if (e.getX() > source.getWidth() - 2 * lineWidthPickerSize)
					selectedLineWidth = 6;
				else
					selectedLineWidth = 10;
			} else {
				prevX = e.getX();
				prevY = e.getY();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			Component source = (Component) e.getSource();
			Graphics g = source.getGraphics();
			int x = e.getX();
			int y = e.getY();
			g.setColor(selectedColor);
			
			Graphics2D g2 = (Graphics2D) g;
			if ((y > lineWidthPickerSize + 5 && y < source.getHeight() - (colorPickerSize + 5)) || (
				 y < lineWidthPickerSize && x < source.getWidth() - 3 * lineWidthPickerSize - 5)) {
				g2.setStroke(new BasicStroke(selectedLineWidth));
				g.drawLine(prevX, prevY, x, y);

				prevX = x;
				prevY = y;
			}
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

	public static class Canvas extends JPanel {
		/**
		 * Crta onoliko pravougaonika različitih boja koliko imamo elemenata u
		 * listi palette.
		 *
		 * Obratite pažnju da je palette privatni static atribut klase
		 * PainterPlus, međutim da mu još uvijek imamo pristup iz nested static
		 * klase Canvas. Ako bismo klasu Canvas izdvojili u poseban fajl, ne
		 * bismo više imali pristup privatnim atributima.
		 */
		@Override
		public void paintComponent(Graphics g) {
			for (int i = 0; i < palette.length; i++) {
				g.setColor(palette[i]);
				g.fillRect(colorPickerSize * i, getHeight() - colorPickerSize,
						colorPickerSize, colorPickerSize);
			}
			
			for (int i = 3; i >= 1; i--) {
				g.drawRect(getWidth() - lineWidthPickerSize * i, 0,
						lineWidthPickerSize, lineWidthPickerSize);

			}
			
			for (int i = 3; i >= 1; i--) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(3 * i));
				g.drawLine(getWidth() - lineWidthPickerSize * i + 5,
						lineWidthPickerSize / 2, getWidth()
								- lineWidthPickerSize * i + lineWidthPickerSize
								- 5, lineWidthPickerSize / 2);
			}
		}
	}
}