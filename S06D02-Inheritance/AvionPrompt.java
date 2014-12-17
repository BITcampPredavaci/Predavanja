
public class AvionPrompt {

	public static void main(String[] args) {
		Avion a = new Avion();
		Package[] packages = a.getPackages();
		
		for (int i = 0; i < packages.length; i++) {
			packages[i] = getPackage();
		}
		
		System.out.printf("Ukupna težina aviona: %f\n",
				a.getTotalWeight());
	}
	
	public static Package getPackage() {
		Package p = new Package();
		
		System.out.print("Unesite širinu: ");
		p.setWidth(TextIO.getlnDouble());
		
		System.out.print("Unesite visinu: ");
		p.setHeight(TextIO.getlnDouble());
		
		System.out.print("Unesite dužinu: ");
		p.setLength(TextIO.getlnDouble());

		System.out.print("Unesite težinu: ");
		p.setWeight(TextIO.getlnDouble());
		
		return p;
	}

}
