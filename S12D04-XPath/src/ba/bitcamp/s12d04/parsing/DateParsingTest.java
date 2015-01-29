package ba.bitcamp.s12d04.parsing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Demonstrira parsiranje i formatiranje datuma
 * 
 * Za parsiranje i formatiranje datuma koristimo klasu DateFormat, odnosno klasu
 * SimpleDateFormat kako bismo mogli ručno odrediti format datuma s kojim
 * radimo.
 * 
 * @author damir
 *
 */
public class DateParsingTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy. HH:mm");

		while (true) {
			System.out.print("Unesite datum: ");
			String dateString = s.nextLine();
			if (dateString.isEmpty()) {
				break;
			}

			try {
				Date d = df.parse(dateString);

				// Koristi toString metodu Date objekta koja koristi default-ni
				// lokal za formatiranje
				System.out.printf("Unijeli ste datum: %s\n", d);

				// Koristi formatiranje zadato kroz SimpleDateFormat objekat df
				// za formatiranje po specifikaciji
				System.out.printf("Formatirani datum je: %s\n", df.format(d));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
