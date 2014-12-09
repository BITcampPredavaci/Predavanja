
public class VolumetricWeightWithArrays {

	public static void main(String[] args) {
		float[] dim = getDimensions();
		float sp = shippingPrice(dim);
		
		System.out.printf("Cijena dostave: %f", sp);
	}

	private static float[] getDimensions() {
		float[] dim = new float[3];
		
		System.out.print("Unesite širinu (cm): ");
		dim[0] = TextIO.getlnFloat();

		System.out.print("Unesite visinu (cm): ");
		dim[1] = TextIO.getlnFloat();

		System.out.print("Unesite dužinu (cm): ");
		dim[2] = TextIO.getlnFloat();

		System.out.print("Unesite težinu (kg): ");
		dim[3] = TextIO.getlnFloat();
		
		return dim;
	}

	public static float shippingPrice(float[] dim) {
		float dw = dimensionalWeight(dim);
		float shippingWeight = Math.max(dw, dim[3]);
		
		return shippingWeight * 3;
	}
	
	public static float dimensionalWeight(float[] dim) {
		return dim[0] * dim[1] * dim[2] / 5000;
	}
	
}
