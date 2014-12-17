

public class PackagePrompt {

	public static void main(String[] args) {
		Package p = new Package();
		
		System.out.print("Unesite širinu: ");
		p.setWidth(TextIO.getlnDouble());
		
		System.out.print("Unesite visinu: ");
		p.setHeight(TextIO.getlnDouble());
		
		System.out.print("Unesite dužinu: ");
		p.setLength(TextIO.getlnDouble());

		System.out.print("Unesite težinu: ");
		p.setWeight(TextIO.getlnDouble());
		
		System.out.printf("Težina paketa je: %f\n", p.getWeight());
		
		System.out.printf("Cijena dostave je: %f\n", p.getPrice());
	}

}
