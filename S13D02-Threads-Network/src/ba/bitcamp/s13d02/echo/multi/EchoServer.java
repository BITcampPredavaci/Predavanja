package ba.bitcamp.s13d02.echo.multi;

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
 * Multi-threaded ECHO server
 * 
 * Ovaj server pokreće poseban thread za svakog novog klijenta. Ako istovremeno
 * dobijemo više hiljada konekcija, program će pokušati napraviti više hiljada
 * thread-ova što može dovesti do problema s performansama našeg programa.
 * 
 * @author damir
 *
 */
public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Pokrenut je ECHO server.");
		System.out.println("Sve što server dobije će se vratiti klijentu.");
		System.out.println();
		int count = 1;

		try (ServerSocket listener = new ServerSocket(8080)) {
			while (true) {
				System.out.println("Čekam konekciju...");
				Socket connection = listener.accept();

				// kreiramo novo ime za novi thread
				System.out.println("Stigla konekcija, kreiramo novi thread");
				String threadName = String.format("Thread %d", count);
				count++;

				// kreiramo thread, dajemo mu konekciju i ime
				Thread t = new ConnectionHandler(connection, threadName);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("Ne možemo otvoriti port 8080.");
			e.printStackTrace();
		}

		System.out.println("Kraj main-a");
	}

	public static class ConnectionHandler extends Thread {
		private Socket connection;
		private String name;

		public ConnectionHandler(Socket connection, String name) {
			super();
			System.out.printf("[%s] Kreiran...\n", name);

			this.connection = connection;
			this.name = name;
		}

		@Override
		public void run() {
			System.out.printf("[%s] Pokrenut...\n", name);

			try {
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
					System.out.printf("[%s] Klijent rekao: %s\n", name, line);

					// šaljemo klijentu poruku nazad (echo)
					pw.println(line);
				}

				pw.close();
			} catch (IOException e) {
				System.out.println("Ne možemo doći do input ili output stream-a.");
				e.printStackTrace();
			}
		}
	}
}
