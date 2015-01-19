package streams;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Demonstrira rad s OutputStream-om kroz OutputStreamWriter i PrintWriter
 * dekoratore
 * 
 * OutputStreamWriter dekorator "obavija" obični OutputStream (binarni output
 * stream), tako da možemo koristiti metodu write() kao što bismo je koristili s
 * output character-stream-om (klasom Writer). To znači da će višebajtni unicode
 * karakteri kao što su Č, ć i ☃ biti ispravno prikazani u konzoli.
 * 
 * Pošto je OutputStreamWriter također Writer, njega možemo dodatno obaviti
 * dekoratorom PrintWriter koji daje ljepši interfejs za ispis teksta.
 * 
 * @author damir
 *
 */
public class OutputStreamWriterTest {

	public static void main(String[] args) {
		// pristupamo System.out stream-u kao binarnom
		OutputStream os = System.out;

		// dekorišemo binarni output stream klasom OutputStreamWriter koja ga
		// čini "pametnijim" u pogledu na višebajtne karaktere
		Writer w = new OutputStreamWriter(os);

		// PrintWriter dekorator obavija OutputStreamWriter, te nam daje jasne
		// metode koje je jednostavno koristiti za ispis: print, println, printf
		// isl.
		PrintWriter pw = new PrintWriter(w);

		try {
			// radi
			w.write((int) 'Z');
			w.write((int) 'd');
			w.write((int) 'r');
			w.write((int) 'a');
			w.write((int) 'v');
			w.write((int) 'o');
			w.write((int) '\n');

			// Sada radi i ovo :)
			w.write((int) 'Ć');
			w.write((int) 'a');
			w.write((int) 'o');
			w.write((int) '!');

			// možemo koristiti i PrintWriter dekorator sa puno smislenijim
			// interface-om
			pw.printf("\nZdravo, ovo je 42: %d\n", 6 * 7);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
