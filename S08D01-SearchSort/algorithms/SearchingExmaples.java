package algorithms;

import warmup.TextIO;

/**
 * Klasa koja implementira nekoliko algoritama za pretragu niza
 * 
 * Sadrži main metodu koja definiše niz koji treba pretražiti.
 * 
 * @author damir
 *
 */
public class SearchingExmaples {

	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 10, 12, 32, 34, 52, 68, 72, 80, 93 };

		System.out.print("Unesite broj: ");
		int n = TextIO.getlnInt();
		int index;

		System.out.println("Linearna pretraga niza");
		index = search(numbers, n);
		if (index == -1) {
			System.out.println("Broj se ne nalazi u nizu");
		} else {
			System.out.printf("Broj se nalazi na indeksu %d\n", index);
		}

		// Linearna pretraga je već uključena u Java biblioteku:
		// int index = Arrays.asList(numbers).indexOf(n);

		System.out.println("Binarna pretraga niza");
		index = binarySearch(numbers, n);
		if (index == -1) {
			System.out.println("Broj se ne nalazi u nizu");
		} else {
			System.out.printf("Broj se nalazi na indeksu %d\n", index);
		}

		// Binarna pretraga također:
		// index = Arrays.binarySearch(numbers, n);
	}

	/**
	 * Implementacija linerne pretrage niza
	 * 
	 * Prolazimo kroz svaki element niza koristeći petlju. Ako na bilo kom
	 * indeksu nađemo element koji se traži (`el`), vraćamo njegov indeks.
	 * 
	 * Ako nismo izašli iz funkcije u toku petlje, onda vraćamo -1 da
	 * signaliziramo da element nije pronađen.
	 * 
	 * @param array
	 *            Niz koji treba pretražiti
	 * @param el
	 *            Element koji tražimo u nizu
	 * @return -1 ako element nije nađen ili indeks elementa u nizu.
	 */
	public static int search(int[] array, int el) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == el) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Implementacija binarne pretrage niza
	 * 
	 * U svakom koraku petlje pokušavamo naći element u intervalu ograničenom
	 * indeksima `startIndex` i `endIndex`. Prvo odredimo indeks elementa u
	 * sredini intervala (varijabla `m`). Ako je traženi element `el` manji od
	 * elementa na indeksu `m` onda posmatramo novi interval
	 * `startIndex`...`m-1`. Ako je traženi element veći od onog na indeksu `m`
	 * onda posmatramo novi interval `m+1`...`endIndex`. Ako je element s
	 * indeksom `m` jednak elementu `el` vraćamo `m`.
	 * 
	 * Proces se ponavlja sve dok `startIndex` ne postane veće od `endIndex`. U
	 * tom slučaju izvršavanje algoritma se zaustavlja i vraćamo -1.
	 * 
	 * Pogledati poglavlje 7.4.1 iz knjige.
	 * 
	 * @param array
	 * @param el
	 * @return
	 */
	public static int binarySearch(int[] array, int el) {
		int startIndex = 0;
		int endIndex = array.length - 1;

		while (startIndex <= endIndex) {
			int m = (startIndex + endIndex) / 2;

			if (array[m] == el) {
				// bingo!
				return m;
			} else if (el < array[m]) {
				// posmatramo "lijevi" podniz
				endIndex = m - 1;
			} else {
				// posmatramo "desni" podniz
				startIndex = m + 1;
			}
		}

		return -1;
	}

}
