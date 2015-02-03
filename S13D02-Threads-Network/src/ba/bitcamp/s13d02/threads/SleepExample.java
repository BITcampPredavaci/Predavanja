package ba.bitcamp.s13d02.threads;

/**
 * Primjer 01: Threadovi koji spavaju i prirodno se bude
 * 
 * Obratiti pažnju na ispis poruka u konzoli nakon pokretanja programa. Vidjet
 * ćemo da je `main()` funkcija završena puno prije nego što su ispisane poruke
 * iz thread-ova.
 * 
 * Uz primjer slušati ovo kao soundtrack:
 * https://www.youtube.com/watch?v=GB2yiIoEtXw
 * 
 * @author damir
 *
 */
public class SleepExample {

	public static void main(String[] args) {
		System.out.println("`main()` počinje...");

		Thread firstThread = new LongSleepThread();
		Thread secondThread = new ShortSleepThread();

		firstThread.start();
		secondThread.start();

		System.out.println("`main()` je gotov!");
	}

	/**
	 * Thread koji spava 3 sekunde nakon pokretanja, te onda ispiše poruku
	 * 
	 * @author damir
	 *
	 */
	public static class LongSleepThread extends Thread {
		@Override
		public void run() {
			try {
				sleep(3000);
				System.out
						.println("Good morning, we've talked the whole night through!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Thread koji spava 1.5 sekundu nakon pokretanja, te onda ispiše poruku
	 * 
	 * @author damir
	 *
	 */
	public static class ShortSleepThread extends Thread {
		@Override
		public void run() {
			try {
				sleep(1500);
				System.out.println("Early bird gets the worm!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
