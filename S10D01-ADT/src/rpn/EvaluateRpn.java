package rpn;

/**
 * Program koji traži od korisnika unos izraza u obrnutoj poljskoj notaciji
 * (Reverse Polish Notation) ili tzv. postfix notaciji, te prikazuje njegov
 * rezultat na ekranu.
 * 
 * @author damir
 *
 */
public class EvaluateRpn {

	/**
	 * U main metodi pravimo stack integera u kojem čuvamo listu brojeva nad kojima
	 * treba izvršiti operacije. Koristimo TextIO da učitava izraz od korisnika,
	 * karakter po karakter. Ako sljedeći simbol predstavlja operator (+, -, /, *) onda
	 * izvršavamo operaciju na zadnja dva elementa stacka, inače pokušavamo učitati broj
	 * i staviti ga na stack.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out
				.println("Unesite izraz u obrnutoj poljskoj notaciji (RPN): ");
		StackOfInt stack = new StackOfInt();

		while (TextIO.peek() != '\n') {
			
			// da li je sljedeća operacija sabiranje?
			if (TextIO.peek() == '+') {
				// pop-amo dva operanda za operaciju sabiranja
				int b = stack.pop();
				int a = stack.pop();
				
				// rezultat sabiranja vraćamo u stack
				stack.push(a + b);
				
				// preskačemo simbol za operator (+)
				TextIO.getChar();
			} else if (TextIO.peek() == '-') {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(a - b);
				TextIO.getChar();
			} else if (TextIO.peek() == '*') {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(a * b);
				TextIO.getChar();
			} else if (TextIO.peek() == '/') {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(a / b);
				TextIO.getChar();
			} else {
				// pokušavamo učitati broj i staviti ga u stack
				stack.push(TextIO.getInt());
			}

			// preskačemo sva "prazna" polja (spaces, tabs...)
			TextIO.skipBlanks();
		}

		// rezultat je zadnji broj koji je ostao na stacku
		System.out.printf("Rezultat je: %d\n", stack.pop());
	}

}
