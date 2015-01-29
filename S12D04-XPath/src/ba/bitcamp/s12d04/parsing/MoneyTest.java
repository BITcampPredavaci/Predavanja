package ba.bitcamp.s12d04.parsing;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Scanner;

/**
 * Demonstrira korištenje klase Money
 * 
 * @author damir
 *
 */
public class MoneyTest {

	public static void main(String[] args) {
		printMoney();
		parseAndPrintMoney();
	}

	/**
	 * Demonstrira formatiranje podataka o novcu
	 */
	private static void printMoney() {
		Money m = new Money(
				new BigDecimal("10.15"),
				Currency.getInstance("BAM"));
		
		System.out.println(m);
	}

	/**
	 * Demonstrira parsiranje podataka o novcu iz Stringa
	 */
	private static void parseAndPrintMoney() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		while (true) {
			System.out.print("Show me the money: ");
			String str = s.nextLine();
			if (str.isEmpty()) { break; }
			
			Money m = Money.parse(str);
			System.out.println(m);
		}
	}

}
