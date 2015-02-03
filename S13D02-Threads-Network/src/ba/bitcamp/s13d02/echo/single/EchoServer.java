package ba.bitcamp.s13d02.echo.single;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Jednostavan ECHO server koji napiše nazad klijentu sve što je dobio
 * 
 * Ovaj server je implementiran bez thread-ova. Ako se više klijenata spoji
 * istovremeno, morat ćemo procesirati jednu po jednu konekciju. Ovo se očituje
 * time što se u konzoli ispisuje tekst "Čekam konekciju..." tek nakon što
 * ispišemo poruku koju je klijent poslao.
 * 
 * @author damir
 *
 */
public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Pokrenut je ECHO server.");
		System.out.println("Sve što server dobije će se vratiti klijentu.");
		System.out.println();

		try (ServerSocket listener = new ServerSocket(8080)) {
			while (true) {
				System.out.println("Čekam konekciju...");
				Socket connection = listener.accept();

				System.out.println("Stigla konekcija");

				// iz InputStream-a čitamo poruke koje klijent šalje. Pošto
				// klijent šalje tekstualne poruke, koristit ćemo Reader da
				// pristupimo ovom Stream-u
				InputStream is = connection.getInputStream();
				Reader r = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(r);

				// u OutputStream socketa pišemo sadržaj koji će se poslati
				// klijentu. Pošto želimo poslati tekstualne vrijednosti
				// pristupit ćemo stream-u kao Writer-u
				OutputStream os = connection.getOutputStream();
				Writer w = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(w);
				PrintWriter pw = new PrintWriter(bw);

				// čitamo poruke koje šalje klijent sve dok klijent ne zatvori
				// konekciju ili ne pošalje praznu liniju. Ako klijent ne
				// pošalje praznu liniju konekcija će ostati zauvijek otvorena i
				// novi klijenti se neće moći spojiti
				while (true) {
					String line = br.readLine();
					if (line == null || line.isEmpty()) {
						// izlazimo iz beskonačne petlje ako je klijent zatvorio
						// konekciju
						break;
					}

					// za bolje razumijevanje u konzolu ispisujemo sve što nam
					// je klijent poslao
					System.out.printf("Klijent rekao: %s\n", line);

					// šaljemo klijentu poruku nazad (echo)
					pw.println(line);
				}

				pw.close();
			}
		} catch (IOException e) {
			System.out.println("Ne možemo otvoriti port 8080.");
			e.printStackTrace();
		}

		System.out.println("Kraj main-a");
	}
}
