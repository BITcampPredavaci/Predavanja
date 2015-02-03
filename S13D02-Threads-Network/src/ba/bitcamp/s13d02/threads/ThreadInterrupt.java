package ba.bitcamp.s13d02.threads;

/**
 * Primjer 02: Jedan Thread "budi" drugi thread
 * 
 * Ponovo treba obratiti pažnju na ispis u konzoli. Ne samo na redoslijed kojim
 * se naredbe ispisuju, već i šta se ispisuje. U ovom primjeru su thread-ovi
 * kreirani kroz anonimne klase.
 * 
 * @author damir
 *
 */
public class ThreadInterrupt {

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

		System.out.println("`main()` je gotov!");
	}

}
