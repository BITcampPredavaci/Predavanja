
public class SumOfArguments {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Greška: Dodajte argumente koje program treba sabrati");
		} else {
			int suma = 0;
			
			for (String arg : args) {
				try {
					suma = suma + Integer.parseInt(arg);
				} catch (NumberFormatException e) {
					System.out.printf(
							"Upozorenje: Vrijednost %s nije ispravan broj. Preskačem.\n",
							arg);
				}
			}
			
			System.out.printf("Suma argumenata je: %d\n", suma);
		}

	}

}
