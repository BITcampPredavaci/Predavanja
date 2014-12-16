
public class TernaryOperator {

	public static void main(String[] args) {
		int a = TextIO.getlnInt();
		int b = TextIO.getlnInt();
		
		int c;
		
		// Conditional:
		if (a > b) {
			c = a;
		} else {
			c = b;
		}
		
		System.out.printf("Veći broj je: %d\n", c);
		
		// Ternary operator:
		c = (a > b) ? a : b; 

		// Cheating:
		c = Math.max(a, b);
	}

}
