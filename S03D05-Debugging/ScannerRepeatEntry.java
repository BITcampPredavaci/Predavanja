import java.util.InputMismatchException;
import java.util.Scanner;


public class ScannerRepeatEntry {

	public static void main(String[] args) {
		int broj = 0;
		boolean success = false;
		Scanner s = null;
		
		do {
			try {
				s = new Scanner(System.in);
				System.out.print("Unesite broj: ");
				broj = s.nextInt();
				success = true;
			} catch (InputMismatchException e) {
				System.out.println("Molimo vas, unesite broj.");
			} finally {
				// s.close(); ← Zašto???
			}
		} while (!success);

		System.out.println("Unijeli ste broj: " + broj);
	}

}
