package square;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * Klasa koja predstavlja panel unutar kojeg se crta.
 * 
 * @author damir
 *
 */
public class CanvasPanel extends JPanel {

	private PointList pointList;

	/**
	 * Konstruktor kreira novu instancu PointList klase i dodjeljuje sebi
	 * mouseListener koji će na press dugmeta miša dodati tačku u listu (push)
	 * ili, na stisak desnog dugmeta, izbrisati posljednju dodatu tačku (pop).
	 */
	public CanvasPanel() {
		pointList = new PointList();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == e.BUTTON1) {
					pointList.push(new Point(e.getX(), e.getY()));
				} else {
					pointList.pop();
				}

				// izvršavamo repaint koji će ponovo iscrtati panel, tj. i
				// posljednju dodatu tačku
				repaint();
			}
		});
	}

	/**
	 * Override metode koja treba da crta komponentu (JPanel)
	 * 
	 * Prilikom crtanja komponente prikazujemo sve tačke koje smo ranije dodali
	 * u listu.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Point[] points = pointList.toArray();
		for (Point p : points) {
			g.fillRect(p.getX() - 15, p.getY() - 15, 30, 30);
		}
	}

}
