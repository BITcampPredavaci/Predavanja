package ba.bitcamp.s13d02.echo.threadpool;

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
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Multi-threaded ECHO server koji koristi threadpool
 * 
 * Ovaj server kreira blocking queue od max 10 elemenata u koje može smjestiti
 * otvorene konekcije, te niz od 4 thread-a koji uzimaju konekciju iz queue-a
 * kada su spremni. Ako dobijemo više hiljada konekcija momentalno, naš program
 * će još uvijek koristiti samo 4 thread-a i imati otvoreno maksimalno 14
 * konekcija u jednom momentu (10 čeka u queue-u, 4 obrađuju thread-ovi). Sve
 * ostale konekcije ostaju neotvorene dok se broj konekcija iz queue-a ne
 * smanji.
 * 
 * Broj thread-ova koji želimo koristiti u pool-u i broj konekcija koje queue
 * može čuvati zavise od potreba naše aplikacije, a prvenstveno od broja
 * očekivanih paralelnih konekcija i brzine kojom se zahtjevi izvršavaju.
 * 
 * @author damir
 *
 */
public class EchoServer {

	public static void main(String[] args) {
		System.out.println("Pokrenut je ECHO server.");
		System.out.println("Sve što server dobije će se vratiti klijentu.");
		System.out.println();

		// Queue svih konekcija koje držimo otvorenim dok čekam thread-ove da se
		// oslobode
		ArrayBlockingQueue<Socket> connections = new ArrayBlockingQueue<>(10);

		Thread[] pool = { new ConnectionHandler(connections, "First"),
				new ConnectionHandler(connections, "Second"),
				new ConnectionHandler(connections, "Third"),
				new ConnectionHandler(connections, "Fourth") };

		for (Thread t : pool) {
			t.start();
		}

		try (ServerSocket listener = new ServerSocket(8080)) {
			while (true) {
				System.out.println("Čekam konekciju...");
				Socket connection = listener.accept();

				// kada dobijemo konekciju stavljamo je u Queue i čekamo da je
				// prvi slobodan thread pokupi od tamo
				connections.put(connection);
				System.out.printf(
						"Ubacio konekciju u queue. Dužina queue-a: %d\n",
						connections.size());
			}
		} catch (IOException e) {
			System.out.println("Ne možemo otvoriti port 8080.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out
					.println("Glavni thread je bio prekinut dok je čekao mjesto u redu");
			e.printStackTrace();
		}

		System.out.println("Kraj main-a");
	}

	public static class ConnectionHandler extends Thread {
		private ArrayBlockingQueue<Socket> connections;
		private String name;

		public ConnectionHandler(ArrayBlockingQueue<Socket> connections,
				String name) {
			super();
			System.out.printf("[%s] Kreiran...\n", name);

			this.connections = connections;
			this.name = name;
		}

		@Override
		public void run() {
			System.out.printf("[%s] Pokrenut...\n", name);

			while (true) {
				try {
					// `connections.take()` blokira ako je queue prazan. čim se
					// u queue doda nova konekcija jedan od thread-ova će je
					// uzeti i nastaviti izvršavanje
					Socket connection = connections.take();
					System.out.printf("[%s] Dobio konekciju");

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

					// čitamo poruke koje šalje klijent sve dok klijent ne
					// zatvori
					// konekciju ili ne pošalje praznu liniju. Ako klijent ne
					// pošalje praznu liniju konekcija će ostati zauvijek
					// otvorena i
					// novi klijenti se neće moći spojiti
					while (true) {
						String line = br.readLine();
						if (line == null || line.isEmpty()) {
							// izlazimo iz beskonačne petlje ako je klijent
							// zatvorio
							// konekciju
							break;
						}

						// za bolje razumijevanje u konzolu ispisujemo sve što
						// nam
						// je klijent poslao
						System.out.printf("[%s] Klijent rekao: %s\n", name,
								line);

						// šaljemo klijentu poruku nazad (echo)
						pw.println(line);
					}

					pw.close();
				} catch (IOException e) {
					System.out
							.println("Ne možemo doći do input ili output stream-a.");
					e.printStackTrace();
				} catch (InterruptedException e) {
					System.out
							.println("Thread je bio prekinut dok je čekao konekciju iz queue-a.");
					e.printStackTrace();
				}
			}
		}
	}
}
