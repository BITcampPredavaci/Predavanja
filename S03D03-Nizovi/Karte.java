
public class Karte {
	public static void main(String[] args) {
		int[] spil = noviSpil();
		System.out.println("Novi spil:" + nizUString(spil));
		
		promijesaj(spil, 10);
		
		System.out.println("Malo Promijesan spil: " + nizUString(spil));
		
		promijesaj(spil, 90);
		
		System.out.println("Dobro Promijesan spil: " + nizUString(spil));
	}
	
	public static void promijesaj(int[] spil, int brojPromjena) {
		for (int i=0; i<brojPromjena; i++) {
			zamijeniDvijeKarte(spil);
		}
	}
	
	public static void zamijeniDvijeKarte(int[] spil) {
		int prva = randomKarta();
		int druga = randomKarta();
		zamijeni(spil, prva, druga);
	}

	public static int[] noviSpil() {
		int[] karte = new int[52];
		for (int i=0; i<karte.length; i++) {
			karte[i] = i+1;
		}
		return karte;
	}
	
	public static int randomKarta() {
		return (int)(Math.random() * 52);
	}
	
	public static void zamijeni(int[] niz, int idx1, int idx2) {
		// TODO provjeri indexe
		int tmp = niz[idx1];
		niz[idx1] = niz[idx2];
		niz[idx2] = tmp;
	}
	
	public static String nizUString(int[] niz) {
		if (niz.length == 0) {
			return "";
		} else {
			String strNiz = String.valueOf(niz[0]);
			for (int idx=1; idx<niz.length; idx++) {
				strNiz = strNiz + ", " + niz[idx];
			}
			return strNiz;
		}
	}
}
