package ba.bitcamp.s13d02.echo.threadpool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Jednostavan HTTTP server koji vraća zahtjevani fajl iz direktorija "http"
 * koji se nalazi na desktopu.
 * 
 * @author damir
 *
 */
public class HttpServer {

	public static void main(String[] args) {
		System.out.println("BitCamp HTTP server.");
		System.out
				.println("Napravite direktorij 'http' na desktopu i u njega stavite index.html fajl.");
		System.out
				.println("Zatim otvorite browser i upišite adresu localhost:8080/index.html.");
		System.out.println();

		// Queue svih konekcija koje držimo otvorenim dok čekam thread-ove da se
		// oslobode
		ArrayBlockingQueue<Socket> connections = new ArrayBlockingQueue<>(10);

		Thread[] pool = { new HttpConnectionHandler(connections, "First"),
				new HttpConnectionHandler(connections, "Second"),
				new HttpConnectionHandler(connections, "Third"),
				new HttpConnectionHandler(connections, "Fourth") };

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

	/**
	 * Handler za HTTP konekcije
	 * 
	 * Tumači input stream ovog socketa kao Reader i čita request header-e. Ako
	 * nađemo liniju koja počinje sa GET čitamo prvu riječ nakon nje koju
	 * tumačimo kao naziv fajla koji klijent traži i ispisujemo sadržaj
	 * tekstualnog fajla na output stream socketa kroz Writer.
	 * 
	 * Fajlove koje HTTP klijent traži ćemo pokušati pronaći unutar direktorija
	 * "http" na Desktopu. Ako fajl ne postoji vratit ćemo status 404 Not Found
	 * i kratku poruku o grešci.
	 * 
	 * @author damir
	 *
	 */
	public static class HttpConnectionHandler extends Thread {
		private ArrayBlockingQueue<Socket> connections;
		private String name;

		public HttpConnectionHandler(ArrayBlockingQueue<Socket> connections,
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
					System.out.printf("[%s] Dobio konekciju", name);

					// Naziv fajla koji HTTP klijent zahtjeva
					String requestedFileName = null;

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

						// ako dosad nismo naišli na liniju koja počinje sa
						// GET...
						if (requestedFileName == null) {
							// kreiramo scanner i gledamo prvi token linije
							@SuppressWarnings("resource")
							Scanner s = new Scanner(line);
							String method = s.next();

							// ako je prvi token GET, onda nam drugi token
							// sadrži ime fajla
							if (method.equals("GET")) {
								requestedFileName = s.next();

								// ako korisnik nije upisao ime fajla
								// podrazumijevamo da želi fajl index.html
								if (requestedFileName.equals("/")) {
									requestedFileName = "/index.html";
								}
							}
						}
					}

					// Sastavljamo punu putanju do fajla
					String homePath = System.getProperty("user.home");
					String desktopPath = homePath + "/Desktop";
					String filePath = desktopPath + "/http" + requestedFileName;
					System.out.printf("[%s] Pokušava otvoriti fajl %s\n", name,
							filePath);

					File f = new File(filePath);

					// otvaramo reader za fajl, čitamo ga liniju po liniju i
					// ispisujemo klijentu
					try (Reader fileReader = new FileReader(f);
							BufferedReader bufferedFileReader = new BufferedReader(
									fileReader)) {

						// prvo ispisujemo response header-e koji kažu da je
						// zahtjev bio ispravan
						pw.println("HTTP/1.1 200 OK");
						pw.println("Content-Type: text/html; charset=utf-8");
						pw.println();

						// a zatim liniju po liniju fajla koji čitamo
						while (true) {
							String line = bufferedFileReader.readLine();
							if (line == null) {
								break;
							}
							pw.println(line);
						}

					} catch (FileNotFoundException e) {
						System.out.printf(
								"Klijent tražio fajl %s koji ne postoji.\n",
								filePath);

						// za ove slučajeve imamo grešku 404
						pw.println("HTTP/1.1 404 Not Found");
						pw.println("Content-Type: text/html; charset=utf-8");
						pw.println();
						pw.println("Nema te stranice... :(");
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
