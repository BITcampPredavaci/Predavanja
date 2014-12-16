
public class DHLPackagePrompt {

	public static void main(String[] args) {
		Package dhlp = new DHLPackage();
		
		System.out.print("Unesite širinu: ");
		dhlp.setWidth(TextIO.getlnDouble());
		
		System.out.print("Unesite visinu: ");
		dhlp.setHeight(TextIO.getlnDouble());
		
		System.out.print("Unesite dužinu: ");
		dhlp.setLength(TextIO.getlnDouble());

		System.out.print("Unesite težinu: ");
		dhlp.setWeight(TextIO.getlnDouble());

		// Ako je dhlp varijabla tipa `Package` onda
		// ne možemo pristupiti metodi `getDimensionalWeight`.
		//
		// Ovo je lako ispraviti ako promijenimo tip varijable
		// (tj. tip reference) na DHLPackage.
//		System.out.printf("Dimenziona težina je: %f\n",
//				dhlp.getDimensionalWeight());
		
		// Obratite pažnju da objekat zna svoj tip bez obzira na tip
		// varijable/reference. U ovom slučaju će biti pozvana `getWeight()`
		// koja je definisana u DHLPackage klasi.
		System.out.printf("Težina je: %f\n", dhlp.getWeight());
		System.out.printf("Cijena dostave je: %f\n", dhlp.getPrice());
	}
}
