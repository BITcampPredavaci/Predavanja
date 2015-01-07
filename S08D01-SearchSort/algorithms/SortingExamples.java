package algorithms;

/**
 * Klasa implementira nekoliko algoritama za sortiranje u statičkim metodama
 * 
 * Sadrži main metodu koja definiše niz koji treba sortirati.
 * 
 * @author damir
 *
 */
public class SortingExamples {

	public static void main(String[] args) {
		int[] numbers = new int[] { 871, 12, 8, 12, 98, 12, 19, 18, 82, 11, 65 };

		System.out.println("Insertion sort");
		insertionSort(numbers);
		printArray(numbers);

		System.out.println("Selection sort");
		selectionSort(numbers);
		printArray(numbers);
	}

	/**
	 * Implementacija algoritma za insertion sort
	 * 
	 * Insertion sort funkcioniše po principu sličnom redanju karata u ruci. Sa
	 * jedne strane imamo gomilu poredanih karata (već sortiran niz), a sa druge
	 * neporedane karte, tj. nesortiran niz.
	 * 
	 * U svakom koraku uzmemo jednu kartu iz nesortirane gomile i dodajemo je u
	 * poredani niz tako što poredimo uzetu kartu s najvećom kartom u sortiranom
	 * nizu. Ako je uzeta karta manja, stavljamo je ispred veće karte. Operaciju
	 * ponavljamo sve dok ne dođemo do karte koja je manja ili jednaka uzetoj
	 * karti ili početka sortiranog niza.
	 * 
	 * Implementacija algoritma podrazumijeva da jedan niz podijelimo na dva
	 * dijela: sortirani i nesortirani. Ovu "podjelu" vršimo pomjeranjem indeksa
	 * koji predstavlja početak nesortiranog dijela niza (varijabla `i`).
	 * 
	 * Zatim koristimo drugu varijablu (`newPos`) koja će pokazivati na broj
	 * koji se umeće dok ga pomjeramo mjesto po mjesto u sortiranom nizu.
	 * 
	 * Kompleksnost insertion sorta je O(n^2).
	 * 
	 * Vidjeti poglavlje 7.4.3 u knjizi.
	 * 
	 * @param array
	 *            Niz koji treba sortirati
	 */
	public static void insertionSort(int[] array) {
		// i je indeks koji predstavlja prvi element nesortiranog dijela niza
		for (int i = 1; i < array.length; i++) {
			// newPos je indeks koji predstavlja element koji "guramo" na pravo
			// mjesto unutar sortiranog niza
			int newPos = i;

			// novi element guramo "lijevo" sve dok ne dođemo do manjeg ili
			// jednakog elementa ili početka niza
			while (newPos > 0 && array[newPos - 1] > array[newPos]) {
				int t = array[newPos - 1];
				array[newPos - 1] = array[newPos];
				array[newPos] = t;

				newPos--;
			}
		}
	}

	/**
	 * Implementacija algoritma za selection sort
	 * 
	 * Selection sort funkcioniše na sljedeći način: podijelimo niz na sortirani
	 * i nesortirani dio (sortirani dio je na početku prazan).
	 * 
	 * Iz nesortiranog niza uzmemo najveći element i prebacimo ga na početak
	 * sortiranog niza. Zatim ovaj proces ponavljamo sve dok nesortirani niz ne
	 * ostane prazan.
	 * 
	 * Kompleksnost selection sorta je O(n^2)
	 * 
	 * Vidjeti poglavlje 7.4.4 u knjizi
	 * 
	 * @param array
	 *            Niz koji treba sortirati
	 */
	public static void selectionSort(int[] array) {
		// lastUnsorted - indeks koji predstavlja poziciju posljednjeg
		// nesortiranog broja
		for (int lastUnsorted = array.length - 1; lastUnsorted >= 0; lastUnsorted--) {
			// maxIndex - indeks koji predstavlja poziciju najvećeg elementa
			// nesortiranog dijela niza
			int maxIndex = 0;

			for (int j = 1; j <= lastUnsorted; j++) {
				if (array[j] > array[maxIndex]) {
					maxIndex = j;
				}
			}

			// ubacujemo najveći element na početak sortiranog niza, a element
			// koji je tamo ranije stajao ubacujemo na mjesto ranije najvećeg
			// elementa
			int t = array[lastUnsorted];
			array[lastUnsorted] = array[maxIndex];
			array[maxIndex] = t;
		}
	}

	/**
	 * Pomoćna metoda za ispis niza
	 * 
	 * @param array
	 *            Niz koji treba ispisati
	 */
	private static void printArray(int[] array) {
		for (int n : array) {
			System.out.printf("%d ", n);
		}

		System.out.println("\n");
	}

}
