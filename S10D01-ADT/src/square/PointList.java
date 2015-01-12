package square;

/**
 * Lista tačaka koja implementira stack operacije za rukovanje tačkama u listi
 * 
 * @author damir
 *
 */
public class PointList {
	PointNode head;
	int size = 0;

	/**
	 * Dodaje tačku u listu
	 * 
	 * Tačku dodajemo na početak linkane liste, odnosno vrh zamišljenog stack-a.
	 * 
	 * @param point
	 *            tačka koja se dodaje
	 */
	public void push(Point point) {
		PointNode newNode = new PointNode(point);

		newNode.setNext(head);
		head = newNode;
		size += 1;
	}

	/**
	 * Briše posljednju dodatu tačku iz liste i vraća je
	 * 
	 * Tačku brišemo s početka linkane liste i stavljamo sljedeću tačku da bude
	 * početak.
	 * 
	 * @return posljednja tačka koja je bila dodana
	 */
	public Point pop() {
		if (head == null) {
			throw new IllegalStateException("List is empty");
		}

		Point resultPoint = head.getValue();
		head = head.getNext();
		size -= 1;

		return resultPoint;
	}

	/**
	 * Pretvara trenutnu listu tačaka u niz tačaka
	 * 
	 * Ovaj niz možemo koristiti nezavisno od liste (npr. za iscrtavanje
	 * tačaka).
	 * 
	 * @return reprezentacija linkane liste kroz niz tačaka
	 */
	public Point[] toArray() {
		Point[] array = new Point[size];
		int i = 0;

		for (PointNode currentNode = head; currentNode != null; currentNode = currentNode
				.getNext()) {
			array[i] = currentNode.getValue();
			i++;
		}

		return array;
	}
}
