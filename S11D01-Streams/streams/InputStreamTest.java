package streams;

import java.io.IOException;

/**
 * Demontsrira direktni rad sa InputStream-om (System.in)
 * 
 * Pošto InputStream radi s binarnim podacima on nije dovoljno inteligentan da
 * razlikuje tekst od drugih podataka, te ne zna prepoznati višebajtne unicode
 * karaktere kao što su Č, ć i ☃.
 * 
 * @author damir
 *
 */
public class InputStreamTest {

	public static void main(String[] args) {
		System.out.println("Početak programa");

		try {
			// počinjemo beskonačnu petlju iz koje ćemo izaći koristeći break
			// naredbu
			while (true) {
				// čitamo bajt iz InputStream-a
				int readByte = System.in.read();

				// ako se stream zatvori (npr. pritiskanjem Ctrl+D), pročitani
				// byte će biti -1 što znači da nema više vrijednosti za čitanje
				if (readByte == -1) {
					break;
				}

				// cast-amo pročitani bajt u char i prikazujemo ga
				char readChar = (char) readByte;
				System.out.printf("Pročitani karakter: %c\n", readChar);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Prgoram gotov");

	}

}
