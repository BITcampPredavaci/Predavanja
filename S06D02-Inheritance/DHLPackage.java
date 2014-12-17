
public class DHLPackage extends Package {
	public double getDimensionalWeight() {
		// Ako su atributi `private`, onda moramo koristiti
		// gettere:
//		return getWidth() *
//				getHeight() *
//				getLength() / 5000;
		
		// Ako su atributi `protected`, možemo ih koristiti
		// direktno:
		return width * height * length / 5000;
	}
	
	@Override
	public double getWeight() {
		return Math.max(weight, getDimensionalWeight());
	}
}
