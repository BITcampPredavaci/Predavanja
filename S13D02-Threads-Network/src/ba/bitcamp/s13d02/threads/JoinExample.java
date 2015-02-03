package ba.bitcamp.s13d02.threads;

/**
 * Primjer 04: Dva Thred-a se spajaju s glavnim threadom
 * 
 * Ovaj put čekamo da se oba threda u potpunosti završe prije nego što završimo
 * `main()` funkciju pozivajući metodu `join()` na thread-ovima.
 * 
 * Metoda `join()` blokira trenutni thread (thread koji je pozvao metodu) sve
 * dok se thread nad kojim je metoda pozvana ne završi. U konkretnom primjeru,
 * kada pozovemo `join()` na oba thread-a, glavi thread se pauzira dok se i prvi
 * i drugi thread ne završe.
 * 
 * @author damir
 *
 */
public class JoinExample {

	public static void main(String[] args) {
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

		try {
			firstThread.join();
			System.out.println("firstThread se spojio u glavni thread.");

			secondThread.join();
			System.out.println("secondThread se spojio u glavni thread.");
		} catch (InterruptedException e) {
			// Ovo se ne dešava u našem programu (pročitajte knjigu da vidite
			// kad se može desiti)
			System.out.println("Thread je bio prekinut dok smo čekali da se spoji.");
		}

		System.out.println("`main()` je gotov!");
	}

}
