
public class StatCalculator {
	
	public static void main(String[] args) {
		int current, min, max, count = 0;
		float sum = 0;
		
		boolean minOnly = args.length > 0 && args[0].equals("-min");
		boolean maxOnly = args.length > 0 && args[0].equals("-max");
		boolean avgOnly = args.length > 0 && args[0].equals("-avg");
		
		System.out.print("Unesite broj: ");
		current = TextIO.getlnInt();
		min = current;
		max = current;
		count += 1;
		sum += current;
		
		do {
			System.out.print("Unesite broj: ");
			current = TextIO.getlnInt();
			if (current < min) {
				min = current;
			}
			if (current > max) {
				max = current;
			}
			count += 1;
			sum += current;
		} while (count <= 4);
		
		if (minOnly) {
			System.out.printf("Minimum je: %d\n", min);
		} else if (maxOnly) {
			System.out.printf("Maksimum je: %d\n", max);
		} else if (avgOnly) {
			System.out.printf("Prosjek je: %f\n", sum / count);
		} else {
			System.out.printf("Minimum je: %d\n", min);
			System.out.printf("Maksimum je: %d\n", max);
			System.out.printf("Prosjek je: %f\n", sum / count);
		}
	}
	
}
