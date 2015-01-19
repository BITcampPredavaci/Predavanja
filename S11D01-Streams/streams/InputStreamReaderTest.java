package streams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Demontsrira rad InputStreamReader dekoratora za čitanje podataka iz
 * InputStrem-a (npr. System.in)
 * 
 * U poređenju s programom InputStreamTest, ova klasa ispravno učitava i
 * prikazuje višebajtne unicode karaktere kao što su Č, ć i ☃.
 * 
 * @author damir
 *
 */
public class InputStreamReaderTest {

	public static void main(String[] args) {
		System.out.println("Početak programa");
		Reader r = new InputStreamReader(System.in);

		try {
			// počinjemo beskonačnu petlju iz koje ćemo izaći koristeći break
			// naredbu
			while (true) {
				// čitamo iz InputStreamReader-a što je dekroator za InputStream
				int readByte = r.read();

				// ako se stream zatvori (npr. pritiskanjem Ctrl+D), pročitani
				// byte će biti -1 što znači da nema više vrijednosti za čitanje
				if (readByte == -1) {
					break;
				}

				// cast-amo pročitanu vrijednost (tehnički može biti više od
				// jednog byte-a) u char i prikazujemo je
				char readChar = (char) readByte;
				System.out.printf("Pročitani karakter: %c\n", readChar);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Program gotov");
	}

}
