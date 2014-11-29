import java.util.Random;


public class LogicalError {

	public static void main(String[] args) {
		Random r = new Random();
		int x = r.nextInt(100) + 1;
		
		System.out.print("Pogodi broj koji sam zamislio: ");
		int guess = TextIO.getlnInt();
		
		while (guess == x) {
			System.out.println("Netačno :(");
			System.out.print("Probaj ponovo: ");
			guess = TextIO.getlnInt();
		}
		
		System.out.println("Bra'o!");
	}

}
