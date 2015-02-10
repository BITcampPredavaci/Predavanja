package ba.bitcamp.s14d03.url;

/**
 * Reprezentacija komponenti URL-a
 * 
 * Klasa nudi mogućnost parsiranja URL reprezentovanog kao string u objekat koji
 * sadrži bitne komponente URL-a (schema-u, host i path). Treba napomenuti da je
 * query string dio URL-a uključen u path.
 * 
 * @author damir
 *
 */
public class URL {
	private String schema;
	private String host;
	private String path;

	public String getSchema() {
		return schema;
	}

	public String getHost() {
		return host;
	}

	public String getPath() {
		return path;
	}

	/**
	 * Pretvara URL zapisan kao string u objekat tipa URL
	 * 
	 * @param urlString
	 *            URL koji treba parsirati
	 * @return URL objekat koji sadrži schema-u, host i path izvučene iz URL-a
	 *         datog kao parametar
	 */
	public static URL parse(String urlString) {
		// konstruišemo "prazan" URL objekat
		URL url = new URL();

		// razdvajamo URL string po sekvenci "://"
		// S lijeve strane sekvence se nalazi šema URL-a, a s desne sve ostalo.
		String[] schemaAndOthers = urlString.split("://");
		url.schema = schemaAndOthers[0];

		// razdvajamo ostatak URL-a (bez šeme) po prvom znaku /
		// S lijeve strane prvog slash-a imamo host, a s desne strane putanju
		String[] hostAndOthers = schemaAndOthers[1].split("/", 2);
		url.host = hostAndOthers[0];

		// ako je putanja prazna, onda nećemo pokušati pristupiti
		// "desnoj strani" kako ne bismo rizikovali exception
		if (hostAndOthers.length > 1) {
			url.path = "/" + hostAndOthers[1];
		} else {
			url.path = "/";
		}

		return url;
	}

	/**
	 * Formatira komponente URL-a u ispravan URL prezentovan kao String
	 */
	@Override
	public String toString() {
		return String.format("%s://%s%s", schema, host, path);
	}

}
