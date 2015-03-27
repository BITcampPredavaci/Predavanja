package ba.bitcamp.lectures.i18n;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * List available Locale-s in default language and local language. Used to demo importance of encoding.
 * 
 * @author emir
 *
 */
public class LocalesInLocalLanguage {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("Default encoding: " + System.getProperty("file.encoding"));
		System.out.println("Or through Charset: " + Charset.defaultCharset().name());
		// make sure it outputs in UTF-8
		if (!Charset.defaultCharset().equals(StandardCharsets.UTF_8)) {
			System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
		}
		
		// still need to make sure console is actually expecting UTF-8 
		// in eclipse set in preferences General->Workspace->TextFileEncoding to UTF-8 if you are seeing ?
		
		
		// list Locales
		for (Locale locale : Locale.getAvailableLocales()) {
			System.out.print(locale.getDisplayName());
			System.out.print(" -> ");
			System.out.println(locale.getDisplayName(locale));
		}
	}
}
