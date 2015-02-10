package ba.bitcamp.s14d03.webclient;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Predstavlja HTTP odgovor servera
 * 
 * Sadrži atribute koji predstavljaju: - statusnu liniju (sadrži verziju
 * protokola, statusni kôd i opis statusa) - response header-e - tijelo odgovora
 * (HTML kôd stranice)
 * 
 * @author damir
 *
 */
public class Response {
	/**
	 * Mapu inicijaliziramo odmah i nemamo setter za nju. Korisnici ove klase
	 * mogu promijeniti vrijednosti u mapi, ali ne i samu mapu.
	 */
	private Map<String, String> headers = new HashMap<String, String>();
	private String statusLine;
	private String body;
	private int statusCode;

	public String getStatusLine() {
		return statusLine;
	}

	/**
	 * Prilikom dodjele statusne linije čitamo statusni kôd kao integer.
	 * 
	 * Statusni kôd dodjelujemo u poseban atribut `statusCode` za koji imamo
	 * getter `getStatusCode()`
	 * 
	 * @param statusLine
	 *            string koji sadrži verziju protokola, status odgovora i
	 *            tekstualni opis odgovora (npr. "HTTP/1.1 404 Not Found")
	 */
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;

		@SuppressWarnings("resource")
		Scanner s = new Scanner(statusLine);
		s.next();
		this.statusCode = s.nextInt();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}
}
