
public class BiloKojaTabela {
	public static void main(String[] args) {
		char[][] igra = novaIgra(5, 8);
		postaviPolje(igra, 'X', "A0");
		postaviPolje(igra, 'Y', "F3");
		ispisiStanjeIgrice(igra);
		
		System.out.println("Igra X-0");
		char[][] iksOks = novaIgra(3, 3);
		odigrajPotez(iksOks, 'X', "B1");
		odigrajPotez(iksOks, '0', "A0");
		odigrajPotez(iksOks, 'X', "C0");
		odigrajPotez(iksOks, '0', "B0");
		odigrajPotez(iksOks, 'X', "A2");
	}

	private static char[][] novaIgra(int redova, int kolona) {
		char[][] table = new char[redova][kolona];
		return table;
	}
	
	private static void postaviPolje(char[][] poljanaZaIgru, char vrijednost, String polje) {
		int kolona = polje.toUpperCase().charAt(0) - 'A';
		int red = Integer.parseInt(polje.substring(1));
		poljanaZaIgru[red][kolona] = vrijednost;
		
	}
	
	private static void odigrajPotez(char[][] poljanaZaIgru, char vrijednost, String polje) {
		postaviPolje(poljanaZaIgru, vrijednost, polje);
		ispisiStanjeIgrice(poljanaZaIgru);
	}
	
	private static void ispisiStanjeIgrice(char[][] poljanaZaIgru) {
		int redova = poljanaZaIgru.length;
		int kolona = poljanaZaIgru[0].length;
		ispisiZaglavlje(kolona);
		
		for (int i = 0; i< redova; i++) {
			ispisiRed(poljanaZaIgru[i], i);
		}
	}

	private static void ispisiZaglavlje(int kolona) {
		ispisiImeReda(" ");
		for (int i=0; i<kolona; i++) {
			char ime = (char) ('A' + i);
			System.out.printf("  %c ", ime);
		}
		ispisiHorizontalnuLiniju(kolona);
		
	}
	
	private static void ispisiRed(char[] poljaReda, int indexReda) {
		ispisiImeReda(String.valueOf(indexReda));
		System.out.print('|');
		for (int i=0; i<poljaReda.length; i++) {
			char polje = poljaReda[i];
			if (polje == 0) {
				polje = ' ';
			}
			System.out.printf(" %c |", polje);
		}
		ispisiHorizontalnuLiniju(poljaReda.length);
	}
	
	private static void ispisiImeReda(String imeReda) {
		System.out.printf("%3s", imeReda);
	}

	private static void ispisiHorizontalnuLiniju(int brojKolona) {
		System.out.println();
		ispisiImeReda(" ");
		System.out.print('+');
		for (int i=0; i<brojKolona; i++) {
			System.out.printf("---+");
		}
		System.out.println();
	}
}
