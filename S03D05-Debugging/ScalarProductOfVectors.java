
public class ScalarProductOfVectors {

	public static void main(String[] args) {
		System.out.print("Unesite prvi vektor (3 broja razdvojena razmakom): ");
		int[] vec1 = readVector();
		System.out.print("Unesite drugi vektor (3 broja razdvojena razmakom): ");
		int[] vec2 = readVector();

		int result = dotProduct(vec1, vec2);
		
		System.out.printf("Skalarni proizvod vektora: %s⋅%s = %d\n",
				vecToString(vec1),
				vecToString(vec2),
				result);
	}

	private static int[] readVector() {
		int[] vec = new int[3];
		
		for (int i = 0; i < vec.length; i++) {
			vec[i] = TextIO.getInt();
		}
		TextIO.getln();
		return vec;
	}

	private static String vecToString(int[] vec) {
		return String.format("(%d, %d, %d)", vec[0], vec[1], vec[2]);
	}

	private static int dotProduct(int[] vec1, int[] vec2) {
		int sum = 0;
		
		for (int i = 0; i < 3; i++) {
			sum += vec1[i]*vec2[i];
		};
		
		return sum;
	}

}
