package ba.bitcamp.lectures.i18n;

import java.io.IOException;
import java.util.Locale;

/**
 * List default country, language and Locale and all available Locales using default Locale for display name.
 * 
 * @author emir
 *
 */
public class Locales {
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperties());
		
		System.out.println("Country: " + System.getProperty("user.country"));
		System.out.println("Language: " + System.getProperty("user.language"));
		System.out.println("Default locale: " + Locale.getDefault().getDisplayName());
		
		System.out.println("Press enter to list available locales");
		System.in.read();
		
		// list locales
		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			System.out.println(String.format("%-20s%s", locale.toString(), locale.getDisplayName()));
		}
		
		System.out.println("Total: " + locales.length);
	}
}
