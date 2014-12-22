package shipping;


public class UnifiedPrompt {

	public static void main(String[] args) {
		Package p = readPackage();
		
		System.out.printf("Cijena dostave: %f\n", p.getPrice());
	}
	
	private static Package readPackage() {
		System.out.print("Unesite naziv kurira (npr. DHL, FedEx, pošta): ");
		String type = TextIO.getln();
		
		Package p;
		
		if (type.toLowerCase().equals("dhl")) {
			p = new DHLPackage();
		} else if (type.toLowerCase().equals("fedex")) {
			p = new FedExPackage();
		} else {
			p = new Package();
		}
		
		System.out.print("Unesite širinu (cm): ");
		p.setWidth(TextIO.getlnFloat());

		System.out.print("Unesite visinu (cm): ");
		p.setHeight(TextIO.getlnFloat());

		System.out.print("Unesite dužinu (cm): ");
		p.setLength(TextIO.getlnFloat());

		System.out.print("Unesite težinu (kg): ");
		p.setWeight(TextIO.getlnFloat());
		
		return p;
	}

}
