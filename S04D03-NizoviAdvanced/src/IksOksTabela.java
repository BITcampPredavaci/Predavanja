import java.util.Arrays;


public class IksOksTabela {
	public static void main(String[] args) {
		char[][] igra = novaIgra();
		
		ispisiStanjeIgrice(igra);
	}
	
	private static char[][] novaIgra() {
		char[][] igra = new char[3][3];
		for (int i=0; i<3; i++) {
			Arrays.fill(igra[i], ' ');
		}
		return igra;
	}
	
	private static void ispisiStanjeIgrice(char[][] poljanaZaIgru) {
		for (int i = 0; i< 3; i++) {
			System.out.printf("+---+---+---+\n| %s | %s | %s |\n", poljanaZaIgru[i][0], poljanaZaIgru[i][1], poljanaZaIgru[i][2]);
		}
		System.out.println("+---+---+---+");
	}
}
