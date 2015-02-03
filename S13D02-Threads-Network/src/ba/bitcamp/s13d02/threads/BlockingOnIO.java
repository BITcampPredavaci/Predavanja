package ba.bitcamp.s13d02.threads;

import java.util.Scanner;

/**
 * Primjer 05: Blokiranje na standardnom izlazu
 * 
 * Dva thread-a se izvršavaju dok glavni thread čeka da korisnik unese poruku s
 * tastature. Tek kada se oba threada završe i kad korisnik unese poruku program
 * može završiti.
 * 
 * Iz ovog možemo vidjeti da thread-ovi mogu da nastave izvršavanje čak i ako
 * neki drugi thread (pa i glavni) čekaju na neki eksterni resurs (IO).
 * 
 * @author damir
 *
 */
public class BlockingOnIO {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		System.out.println("`main()` počinje...");

		// kreiramo prvi thread kao anonimnu klasu koja nasljeđuju klasu Thread.
		// Ovdje moramo koristiti ključnu riječ `final` kako bi imali pristup
		// varijabli `firstThread` iz drugog threada
		final Thread firstThread = new Thread() {
			@Override
			public void run() {
				// pet puta uspavljujemo thread. Nakon svakog buđenja thread
				// ispisuje poruku "Dobro jutro". Međutim ako thread bude
				// prekinut tokom spavanja ispisat će se druga poruka
				for (int i = 0; i < 5; i++) {
					try {
						sleep(3000);
						System.out.println("Dobro jutro, svijet je divan!");
					} catch (InterruptedException e) {
						System.out.println("Jutro nije dobro :(");
					}
				}
			}
		};

		// kreiramo drugi thread, također kao anonimnu klasu. Nakon buđenja
		// ispisuje poruku "Ko rano rani..." i poziva `interrupt()` metodu na
		// prvom thread-u kako bi ga probudio
		Thread secondThread = new Thread() {
			@Override
			public void run() {
				try {
					sleep(1500);
					System.out.println("Ko rano rani, il' je pekar il' trči");
					firstThread.interrupt();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		// pokrećemo oba thread-a jedan za drugim
		firstThread.start();
		secondThread.start();

		System.out.print("Unesite neku lijepu poruku: ");
		
		// metoda `nextLine()` će blokirati glavni thread sve dok korisnik ne
		// pritisne Enter u konzoli
		s.nextLine();

		System.out.println("`main()` je gotov!");
	}
}
