package streams;

import java.util.Scanner;

/**
 * Demonstrira rad Scanner-a
 * 
 * @author damir
 *
 */
public class ScannerTest {

	public static void main(String[] args) {
		// Kada zatvorimo Scanner objekat, on će automatski zatvoriti i stream
		// iz kojeg čita. U vom slučaju će to biti standardni input stream, što
		// može biti pogubno za naš program. Ako bismo iz nekog drugog dijela
		// programa pokušali koristiti input stream (npr. ako bismo napravili
		// drugi Scanner objekat i pokušali čitati iz njega) dobili bismo
		// exception jer je stream već zatvoren.
		//
		// Umjesto da rizikujemo grešku, warning koji dobijemo je bolje
		// "utišati" korištenjem @SuppressWarnings anotacije.
		//
		// Ovo važi SAMO za Scanner koji je napravljen za System.in stream, te
		// NE VAŽI za Scanner objekte koji čitaju iz drugih stream-ova. Te
		// Scanner-e treba OBAVEZNO zatvoriti nakon upotrebe.
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		// petlja koja čita tokene iz Scanner-a sve dok ima tokena u stream-u.
		// Podataka neće biti u stream-u ako stream zatvorimo (kao korisnici
		// pritiskanjem dugmadi Ctrl+D (^D) ili kao programeri pozivom close()
		// metode na InputStream ili Scanner objektu
		while (s.hasNext()) {
			// jedan "token" je niz karaktera koji su odvojeni blanko
			// karakterima (space, tab ili newline) od drugih tokena
			String token = s.next();
			System.out.printf("Učitani token je: %s\n", token);
		}

	}
}
