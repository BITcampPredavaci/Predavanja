package streams;

/**
 * Klasa koja demonstrira kako su naši znakovi pohranjeni u Unicode tabeli
 * 
 * @author damir
 *
 */
public class AsciiUnicodeConversion {

	public static void main(String[] args) {
		System.out.printf("A: %d\n", (int) 'A');
		System.out.printf("Z: %d\n", (int) 'Z');
		System.out.printf("a: %d\n", (int) 'a');
		System.out.printf("z: %d\n", (int) 'z');

		// obratite pažnju da svi znakovi niže imaju vrijednost preko 255, tj.
		// koriste više od jednog bajta za pohranu
		System.out.printf("Č: %d\n", (int) 'Č');
		System.out.printf("č: %d\n", (int) 'č');

		System.out.printf("Ć: %d\n", (int) 'Ć');
		System.out.printf("ć: %d\n", (int) 'ć');

		System.out.printf("Đ: %d\n", (int) 'Đ');
		System.out.printf("đ: %d\n", (int) 'đ');

		System.out.printf("Š: %d\n", (int) 'Š');
		System.out.printf("š: %d\n", (int) 'š');

		System.out.printf("Ž: %d\n", (int) 'Ž');
		System.out.printf("ž: %d\n", (int) 'ž');
	}

}
