package ba.bitcamp.lectures.i18n;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Demonstrates use of NumberFormat class for numbers, percents and currencies by displaying values for all supported Locales.
 * @author emir
 *
 */
public class Numbers {
	private static final String ROW_TEMPLATE = "%-40s\t%s\t%s\t%s\t%s";

	public static void main(String[] args) {
		Locale[] locales = NumberFormat.getAvailableLocales();
		System.out.println("Total number locales: " + locales.length);
		long largeLong = 123456789;
		double largeDouble = 123456789.9876;
		double perc = 0.756;
		
		System.out.println(String.format(ROW_TEMPLATE, "LOCALE", "LONG", "\tDOUBLE", "\tPERCENT", "CURRENCY"));
		for (Locale locale : locales) {
			NumberFormat numberFormat = NumberFormat.getInstance(locale);
			NumberFormat percFormat = NumberFormat.getPercentInstance(locale);
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
			System.out.println(String.format(ROW_TEMPLATE, 
					locale.getDisplayName(), 
					numberFormat.format(largeLong), 
					numberFormat.format(largeDouble),
					percFormat.format(perc),
					currencyFormat.format(largeDouble)));
		}
	}
}
