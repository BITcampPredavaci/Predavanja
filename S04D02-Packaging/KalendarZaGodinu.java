
public class KalendarZaGodinu {

	public static void main(String[] args) {
		int godina, prviDanUGodini;
	
		System.out.print("Unesite godinu: ");
		godina = TextIO.getInt();
		System.out.print("Koji je prvi dan godine? ");
		prviDanUGodini = TextIO.getInt();
		
		String[] mjeseci = new String[] {"Januar", "Februar", "Mart", "April", "Maj", "Juni", "Juli", "Avgust", "Septembar", "Oktobar", "Novaembar", "Decembar"};
		int[] brDanaPoMjesecima = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if (godinaJePrestupna(godina)) {
			brDanaPoMjesecima[1] = 29;
		}
		for (int i = 0; i < 12; i++) {
			System.out.println(
					ispisiMjesec(
							mjeseci[i],
							brDanaPoMjesecima[i],
							vratiPrviDanUMjesecu(
									i,
									prviDanUGodini,
									brDanaPoMjesecima
							), // vratiPrviDanUMjesecu(
							1
					) // ispisiMjesec(
			); // System.out.println(
			System.out.println();
		}
	}

	public static boolean godinaJePrestupna(int godina) {
		if (godina % 400 == 0) {
			return true;
		}
		if (godina % 100 == 0) {
			return false;
		}
		if (godina % 4 == 0) {
			return true;
		}
		return false;
	}
	
	public static String ispisiMjesec(String nazivMjeseca, int brDanaUMjesecu, int prviDanUMjesecu, int prviDan, String...dani) {
		if (dani.length != 7) {
			dani = new String[] {"Pon", "Uto", "Sri", "Cet", "Pet", "Sub", "Ned"};
		}

		String str = String.format("%28s", nazivMjeseca) + "\n";
		int idx = prviDan - 1;
		do {
			str += String.format("%4s", dani[idx]);
			idx++;
			idx %= 7;
		} while (idx != prviDan - 1);
		str += "\n";
		int slobodnihMjesta = (prviDanUMjesecu - prviDan + 7) % 7;
		for (idx = 0; idx < slobodnihMjesta; idx++) {
			str += String.format("%4s", "");
		}
		while(idx < brDanaUMjesecu + slobodnihMjesta) {
			str += String.format("%4d", (idx - slobodnihMjesta + 1));
			idx++;
			if (idx % 7 == 0) {
				str += "\n";
			}
		}
		return str;
	}
	
	public static int vratiPrviDanUMjesecu(int mjesec, int prviDanUGodini, int[] brojDanaPoMjesecima) {
		int prviDanUMjesecu = prviDanUGodini;
		for (int i = 0; i < mjesec; i++) {
			prviDanUMjesecu += brojDanaPoMjesecima[i];
		}
		prviDanUMjesecu %= 7;
		if (prviDanUMjesecu == 0)
			prviDanUMjesecu = 7;
		return prviDanUMjesecu;
	}
}
