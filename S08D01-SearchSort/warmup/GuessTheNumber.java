package warmup;

import java.util.Random;

/**
 * Program u kojem kompjuter "zamišlja" broj a korisnik ga treba pogoditi.
 * 
 * @author damir
 *
 */
public class GuessTheNumber {

	public static void main(String[] args) {
		Random imaginer = new Random();
		int min = 1;
		int max = 100;
		int x = imaginer.nextInt(max) + min;

		int guess;
		int tries = 0;

		do {
			System.out.printf("Pogodite broj između %d i %d: ", min, max);
			guess = TextIO.getlnInt();
			tries += 1;

			if (guess < x) {
				System.out.println("Zamišljeni broj je veći");
			} else if (guess > x) {
				System.out.println("Zamišljeni broj je manji");
			}
		} while (guess != x);

		System.out.println("Bra'o!");
		System.out.printf("Pogodili ste u %d pokušaja\n", tries);
	}

}
