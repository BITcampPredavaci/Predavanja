package rpn;

/**
 * Klasa koja implementira stack integera
 * 
 * @author damir
 *
 */
public class StackOfInt {

	IntNode head;

	/**
	 * Stavljanje elementa na vrh stacka
	 * 
	 * Vrh stacka je početak linkane list. Svaki novi element postavljamo kao
	 * head, a prethodni head linkamo is novog elementa.
	 * 
	 * @param n
	 *            vrijednost novog člana liste
	 */
	public void push(int n) {
		IntNode nextNode = new IntNode(n);
		nextNode.setNext(head);
		head = nextNode;
	}

	/**
	 * Skidanje elementa s vrha stacka
	 * 
	 * Vrh stacka je početak linkane liste, tako da pomjeramo pokazivač head da
	 * pokazuje na element na koji je pokazivao dosadašnji prvi element.
	 * 
	 * @return vrijednost ranije prvog člana liste
	 */
	public int pop() {
		if (head == null) {
			throw new IllegalStateException("Stack is empty");
		}

		int result = head.getValue();
		head = head.getNext();
		return result;
	}

	/**
	 * Implementacija člana linkane liste koju koristimo za implementaciju samog
	 * stacka
	 * 
	 * @author damir
	 *
	 */
	private static class IntNode {
		int value;
		IntNode next;

		public IntNode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public IntNode getNext() {
			return next;
		}

		public void setNext(IntNode next) {
			this.next = next;
		}
	}
}
