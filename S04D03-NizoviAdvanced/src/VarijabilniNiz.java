
public class VarijabilniNiz {
	public static void main(String[] args) {
		System.out.println(max(new int[] {1, 3, 4, 2}));
		System.out.println(maxVarijabilno(1, 3, 4, 2));
		System.out.println(maxVarijabilno2(1, 3, 4, 2));
		maxVarijabilno();
	}
	
	private static int max(int[] brojevi) {
		if (brojevi != null && brojevi.length == 0) {
			throw new RuntimeException("Neinicijaliziran ili prazan niz brojeva");
		}
		int max = brojevi[0];
		for (int i=1; i<brojevi.length; i++) {
			if (brojevi[i] > max) {
				max = brojevi[i];
			}
		}
		return max;
	}
	
	private static int maxVarijabilno(int...brojevi) {
		// ne mora se provjeriti da li je null jer je uvijek inizijaliziran
		if (brojevi.length == 0) {
			throw new RuntimeException("Prazan niz brojeva");
		}
		int max = brojevi[0];
		for (int i=1; i<brojevi.length; i++) {
			if (brojevi[i] > max) {
				max = brojevi[i];
			}
		}
		return max;
	}
	
	private static int maxVarijabilno2(int prvi, int...ostali) {
		// ne mora se provjeriti da li je null jer je uvijek inizijaliziran
		
		int max = prvi;
		for (int i=0; i<ostali.length; i++) {
			if (ostali[i] > max) {
				max = ostali[i];
			}
		}
		return max;
	}
}
