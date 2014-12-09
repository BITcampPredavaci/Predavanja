
public class VolumetricWeightVariables {

	public static void main(String[] args) {
		float w, h, l, weight;
		
		System.out.print("Unesite širinu (cm): ");
		w = TextIO.getlnFloat();

		System.out.print("Unesite visinu (cm): ");
		h = TextIO.getlnFloat();

		System.out.print("Unesite dužinu (cm): ");
		l = TextIO.getlnFloat();

		System.out.print("Unesite težinu (kg): ");
		weight = TextIO.getlnFloat();
		
		float sp = shippingPrice(w, h, l, weight);
		
		System.out.printf("Cijena dostave: %f", sp);
	}

	public static float shippingPrice(float w, float h, float l, float weight) {
		float dw = dimensionalWeight(w, h, l);
		float shippingWeight = Math.max(dw, weight);
		
		return shippingWeight * 3;
	}
	
	public static float dimensionalWeight(float w, float h, float l) {
		return w * h * l / 5000;
	}
	
}
