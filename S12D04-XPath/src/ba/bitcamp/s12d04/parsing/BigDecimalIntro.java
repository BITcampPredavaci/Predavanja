package ba.bitcamp.s12d04.parsing;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Demonstrira razliku između decimalnih brojava pohranjenih kao double i onih
 * pohranjenih kao BigDecimal
 * 
 * @author damir
 *
 */
public class BigDecimalIntro {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		System.out.print("Unesite proizvoljno veliki decimalni broj: ");
		String decimalString = s.nextLine();

		// BigDecimal možemo kreirati iz stringa
		BigDecimal dec = new BigDecimal(decimalString);

		// Java ne podržava operator overloading tako da za klase moramo praviti
		// metode za osnovne računske operacije i ne možemo koristiti operatore
		// kao što su +, -, / i *
		dec = dec.multiply(new BigDecimal(2));

		// double možemo množiti koristeći obični operator, ali kod jako velikih
		// ili jako malih brojeva gubimo preciznost. double nije dobar izbor
		// kada nam je preciznost bitna (npr. kada radimo s novcem)
		double decDouble = Double.parseDouble(decimalString);
		decDouble *= 2;

		System.out.printf("Unijeli ste (BigDecimal): %s\n", dec);
		System.out.printf("Unijeli ste (double)    : %f\n", decDouble);
	}

}
