package ba.bitcamp.s13d02.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/**
 * Jednostavan klijent koji se konektuje na lokalni server na adresi 8080 i kaže
 * Zdravo. Klijent čita odgovor i završava.
 * 
 * @author damir
 *
 */
public class HelloClient {

	public static void main(String[] args) {
		System.out.println("Pokrećem  klijent...");

		// kreiramo konekciju u `try` bloku tako da će biti automotski zatvorena
		// kada izađemo iz `try` bloka
		try (Socket connection = new Socket("localhost", 8080)) {
			// u OutputStream socketa pišemo sadržaj koji će se poslati serveru.
			// Pošto želimo poslati tekstualne vrijednosti pristupit ćemo
			// stream-u kao `Writer`-u
			OutputStream os = connection.getOutputStream();
			Writer w = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(w);

			// iz InputStream-a čitamo odgovor servera. Pošto serever odgovara
			// tekstualno, koristit ćemo `Reader` da pristupimo ovom Stream-u
			InputStream is = connection.getInputStream();
			Reader r = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(r);

			// pravimo pauzu od 1s pa pišemo serveru...
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pw.println("Zdravo");
			pw.println();
			pw.flush();

			// čitamo odgovor servera sve dok server ne zatvori konekciju
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				System.out.printf("Server odgovorio: %s\n", line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Klijent završio");
	}
}
