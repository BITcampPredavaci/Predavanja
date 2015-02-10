package ba.bitcamp.s14d03.url;

/**
 * Testira funkcionalnost URL klase
 * 
 * Poredi originalni URL s onim koji je dobiven parsiranjem URL-a iz stringa, te
 * serijazacijom URL objekta nazad u string.
 * 
 * @author damir
 *
 */
public class URLTest {

	/**
	 * Izvršava testne metode jednu po jednu
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testUrl("http://pik.ba/");
		testUrl("http://www.pik.ba/artikal/15843731/2015-novi-modeli/");
	}

	/**
	 * Testira tačnost parsiranja + serijalizacije URL-a
	 * 
	 * @param urlString URL koji testiramo
	 */
	private static void testUrl(String urlString) {
		// parsiramo URL
		URL parsedUrl = URL.parse(urlString);
		
		// poredimo originalni URL s parsiranim+formatiranim
		if (urlString.equals(parsedUrl.toString())) {
			System.out.println("OK");
		} else {
			System.out.printf("FAIL\nExpected: %s\nGot:      %s\n",
					urlString, parsedUrl);
		}
	}

}
