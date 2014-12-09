
public class VolumetricWeightWithClasses {

	public static void main(String[] args) {
		Dimensions dim = getDimensions();
		
		float sp = shippingPrice(dim);
		
		System.out.printf("Cijena dostave: %f", sp);
	}

	private static Dimensions getDimensions() {
		Dimensions dim = new Dimensions();
		
		System.out.print("Unesite širinu (cm): ");
		dim.width = TextIO.getlnFloat();

		System.out.print("Unesite visinu (cm): ");
		dim.height = TextIO.getlnFloat();

		System.out.print("Unesite dužinu (cm): ");
		dim.length = TextIO.getlnFloat();

		System.out.print("Unesite težinu (kg): ");
		dim.weight = TextIO.getlnFloat();
		
		return dim;
	}

	public static float shippingPrice(Dimensions dim) {
		float dw = dimensionalWeight(dim);
		float shippingWeight = Math.max(dw, dim.weight);
		return shippingWeight * 3;
	}
	
	public static float dimensionalWeight(Dimensions dim) {
		return dim.width * dim.height * dim.length / 5000;
	}
	
}
