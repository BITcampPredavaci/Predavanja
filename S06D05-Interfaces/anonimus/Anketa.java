package anonimus;

import priprema.amonimac.Prolaznik;

public class Anketa {

	public static void main(String[] args) {
		Anketar nele = new Anketar();
		nele.pitaj();

		Prolaznik pilot = new Prolaznik() {
			public void odgovori() {
				System.out.println("Ja ne želim biti anominan. Ja želim biti pilot. Aaaaaaa!");
			}
		};
		
		pilot.odgovori();
	}

}
