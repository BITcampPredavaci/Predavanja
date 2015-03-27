package ba.bitcamp.lectures.i18n;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Demonstrates use of DateFormat class by displaying values for all styles and all supported Locales.
 * 
 * @author emir
 *
 */
public class Dates {
	private static final String ROW_TEMPLATE = "%-40s%-30s\t%-40s\t%-60s%s";

	public static void main(String[] args) {
		Locale[] locales = DateFormat.getAvailableLocales();
		System.out.println("Total number locales: " + locales.length);
		Date now = new Date();
		
		System.out.println(String.format(ROW_TEMPLATE, "LOCALE", "SHORT", "MEDIUM", "LONG", "FULL"));
		for (Locale locale : locales) {
			DateFormat shortFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT, locale);
			DateFormat mediumFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.MEDIUM, SimpleDateFormat.MEDIUM, locale);
			DateFormat longFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.LONG, SimpleDateFormat.LONG, locale);
			DateFormat fullFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.FULL, SimpleDateFormat.FULL, locale);

			System.out.println(String.format(ROW_TEMPLATE, 
					locale.getDisplayName(),
					shortFormat.format(now),
					mediumFormat.format(now),
					longFormat.format(now),
					fullFormat.format(now)
					));
		}
	}
}
