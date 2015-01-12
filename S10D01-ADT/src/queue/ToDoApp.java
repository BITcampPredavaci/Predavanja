package queue;

import rpn.TextIO;

/**
 * Program koji pita korisnika da unese listu zadataka, a zatim dodaje zadatke u
 * Queue i ispisuje ih iz queue-a na ekran
 * 
 * @author damir
 *
 */
public class ToDoApp {

	/**
	 * Pravimo objekat q tipa QueueOfString i u njega dodajemo zadatke
	 * 
	 * Nakon što su zadaci dodati, praznimo queue i ispisujemo zadatak po
	 * zadatak.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		QueueOfString q = new QueueOfString();

		System.out.println("Unesite zadatke, prazan red da završite");
		
		// beskonačna petlja
		while (true) {
			String task = TextIO.getlnString();
			
			// prekidamo petlju ako korisnik pritisne Enter
			if (task.isEmpty()) {
				break;
			}

			// dodajemo zadatak u listu
			q.enqueue(task);
		}

		// ispisujemo zadatke jedan po jedan sve dok lista nije prazna
		while (!q.isEmpty()) {
			System.out.printf("Vrijeme je za: %s\n", q.dequeue());
			TextIO.getlnString();
		}
	}

}
