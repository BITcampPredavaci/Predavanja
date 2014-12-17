package vic.parametrizirano;

import java.util.Arrays;

public class VicBrzi {
	public static void main(String[] args) {
		Turista turista = new Turista();
		
		Prolaznik[] prolaznici = new Prolaznik[] {
				new Prolaznik(false, false, false),
				new Prolaznik(true, false, false),
				new Prolaznik(true, true, false),
				new Prolaznik(true, true, true)
			};
		Policajac policajac = new Policajac();
		
		System.out.println("Na stanici cekaju bus: " + Arrays.toString(prolaznici));
		System.out.println("Tu je i " + policajac);
			
		for (int i=0; i<prolaznici.length; i++) {
			System.out.println(String.format("Turista pita %d. prolaznika...", i+1));
			turista.daNePitanje("Da li je ovo bus za grad?", prolaznici[i]);
		}
		
		System.out.println("Zbunjen razlicitim odgovorima upita i policajca koji je prolazio...");
		turista.daNePitanje("Da li je ovo bus za grad?", policajac);
		turista.objasniPitanje("Kako to da neko kaze 'Aha', neko 'Da', a vi 'Jeste'", policajac);
		turista.daNePitanje("Da li to znaci da ste vi zavrsili fakultet?", policajac);
	}
}
