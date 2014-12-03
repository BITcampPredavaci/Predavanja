import java.util.Arrays;


public class NizStringova {
	public static void main(String[] args) {
		String[] jednodimenzionalno = new String[3];
		jednodimenzionalno[0] = "Ovo";
		jednodimenzionalno[1] = "je";
		jednodimenzionalno[2] = "razlicito";
		
		char[][] dvodimenzionalno = new char[jednodimenzionalno.length][];
		System.out.printf("Ovo je niz duzine %d gdje je svaki element neinicijalizirani niz\n", dvodimenzionalno.length);
		for (int i=0; i<dvodimenzionalno.length; i++) {
			dvodimenzionalno[i] = jednodimenzionalno[i].toCharArray();
			System.out.println(Arrays.toString(dvodimenzionalno[i]));
		}
	}
}
