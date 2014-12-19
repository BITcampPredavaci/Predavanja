package paketi;

import vic.IspisVeceg;

public class UnifiedPrompt {

	public static void main(String[] args) {
		Package p1 = readPackage();
		
		// Javnoj inner klasi možemo pristupiti kroz objekat(!)
//		Package.FormatPackage fp = p1.new FormatPackage();
//		System.out.println(fp.format());
		
		Package p2 = readPackage();
		IspisVeceg.ispišiVećeg(p1, p2);
		
		System.out.printf("Cijena dostave: %f\n", p1.getPrice());
	}
	
	private static Package readPackage() {
		System.out.print("Unesite naziv kurira (npr. DHL, pošta): ");
		String type = TextIO.getln();
		
		Package p;
		
		if (type.toLowerCase().equals("dhl")) {
			p = new DHLPackage();
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
