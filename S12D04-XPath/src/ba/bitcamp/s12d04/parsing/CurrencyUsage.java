package ba.bitcamp.s12d04.parsing;

import java.util.Currency;
import java.util.Scanner;

/**
 * Demonstrira korištenje Currency klase za pohranu informacije o valuti
 * 
 * Napomena: JRE 1.6 i niže verzije ne implementiraju metodu getDisplayName za
 * klasu Currency.
 * 
 * @author damir
 *
 */
public class CurrencyUsage {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.print("Unesite valutu: ");
			String currencyCode = s.nextLine();

			if (currencyCode.isEmpty()) {
				break;
			}

			Currency c = Currency.getInstance(currencyCode);

			System.out.printf("Name: %s\n", c.getDisplayName());
			System.out.printf("Symbol: %s\n", c.getSymbol());
		}
	}

}
