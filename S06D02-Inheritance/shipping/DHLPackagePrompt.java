package shipping;

public class DHLPackagePrompt {

	public static void main(String[] args) {
		Package dhlp = new DHLPackage();
		
		dhlp.setWidth(50);		
		dhlp.setHeight(20);
		dhlp.setLength(20);
		dhlp.setWeight(1);
		
		System.out.printf("Te??ina je: %f\n", dhlp.getWeight());
		System.out.printf("Cijena dostave je: %f\n", dhlp.getPrice());
	}
}
