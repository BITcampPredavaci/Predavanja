package queue;

/**
 * Implementacija apstraktnog tipa podataka Queue (red)
 * 
 * Red omogućava operaciju dodavanja (enqueue) i brisanja elementa (dequeue).
 * Koristimo equeue operaciju da dodamo novi element na kraj reda, a daqueue da
 * uklonimo prvi element reda.
 * 
 * Red je implementiran kao linkana lista.
 * 
 * @author damir
 *
 */
public class QueueOfString {
	StringNode head;
	StringNode tail;

	/**
	 * Dodajemo novi element na kraj linkane liste.
	 * 
	 * @param string
	 *            vrijednost koju teba dodati u queue
	 * 
	 */
	public void enqueue(String string) {
		StringNode nextNode = new StringNode(string);

		if (head == null && tail == null) {
			// kad je lista prazna i head i tail su null reference, te ih obje
			// moramo inicjalizirati da pokazuju na prvi element
			head = nextNode;
			tail = nextNode;
		} else {
			// dodajemo novi element kao element koji slijedi trenutno
			// posljednji.
			// Mijenjamo referencu tail tako da pokazuje na novi posljednji
			// element
			tail.setNext(nextNode);
			tail = nextNode;
		}
	}

	/**
	 * Brišemo element s početka linkane liste i vraćamo ga
	 * 
	 * @return element koji je dosad bio na kraju ili null, ako je lista prazno
	 */
	public String dequeue() {
		// vratit ćemo null ako je lista prazna (hed pokazuje na null)
		if (head == null) {
			return null;
		}

		String result = head.getValue();

		head = head.getNext();

		// ako brišemo zadnji element, update-ujemo i tail
		if (head == null) {
			tail = null;
		}

		return result;
	}

	/**
	 * Govori da li je lista prazna
	 * @return
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Privatna statička nested klasa koja implementira član liste koji koristimo
	 * u QueueOfString klasi.
	 * 
	 * @author damir
	 *
	 */
	private static class StringNode {
		String value;
		StringNode next;

		public StringNode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public StringNode getNext() {
			return next;
		}

		public void setNext(StringNode next) {
			this.next = next;
		}
	}
}
