package streams;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Demonstrira direktni rad s OutputStream-om
 * 
 * OutputStream radi s binarnim podacima, te nije dovoljno inteligentan da
 * razlikuje tekst od drugih podataka. To znači da višebajtni unicode karakteri
 * kao što su Č, ć i ☃ neće biti ispravno prikazani.
 * 
 * @author damir
 *
 */
public class OutputStreamTest {

	public static void main(String[] args) {
		OutputStream os = System.out;

		try {
			// radi
			os.write((int) 'Z');
			os.write((int) 'd');
			os.write((int) 'r');
			os.write((int) 'a');
			os.write((int) 'v');
			os.write((int) 'o');
			os.write((int) '\n');

			// ne radi ispravno :(
			os.write((int) 'Ć');
			os.write((int) 'a');
			os.write((int) 'o');
			os.write((int) '!');

			// flush je potreban da pošalje sve dosad zapisane vrijednosti iz
			// strem-a dalje. Drugim riječima, karakteri definisani iznad će se
			// pojaviti na ekranu tek nakon što flush-amo stream. Ako ispišemo
			// karakter \n, stream će biti automatski flush-an
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
