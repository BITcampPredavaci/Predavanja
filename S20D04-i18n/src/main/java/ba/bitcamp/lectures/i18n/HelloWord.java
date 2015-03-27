package ba.bitcamp.lectures.i18n;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import ba.bitcamp.lectures.i18n.bundles.CountryBinaries;

/**
 * Demonstrates how to use ResourceBundle, ListResourceBundle and MessageFormat to create i18n application.
 * 
 * @author emir
 *
 */
public class HelloWord {
	public static void main(String[] args) {
		
		System.out.println("Default greetings:");
		greetings(Locale.getDefault());
		
		System.out.println("\n\nA sad na hrvatskom:");
		greetings(Locale.forLanguageTag("hr"));
	}
	
	private static void greetings(Locale locale) {
		ResourceBundle greetings = ResourceBundle.getBundle("greetings", locale);
		MessageFormat localFormat = new MessageFormat("", locale);
		
		System.out.println(greetings.getString("hello"));
		
		localFormat.applyPattern(greetings.getString("intro"));
		System.out.println(localFormat.format(new Object[] {"test"}));
		
		Date now = new Date();
		
		localFormat.applyPattern(greetings.getString("date"));
		System.out.println(localFormat.format(new Object[] {now}));
		
		System.out.println("Static format metod koristi defaultni Locale za datum");
		System.out.println(MessageFormat.format(greetings.getString("date"), now));
		
		System.out.println("Zamislite da je slika");
		ResourceBundle binaries = ResourceBundle.getBundle(CountryBinaries.class.getName(), locale);
		System.out.println(binaries.getObject("flag"));
	}
}
