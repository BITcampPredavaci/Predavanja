package vic.polimorfizam;

import java.util.Arrays;

public class VicBrzi {
	public static void main(String[] args) {
		Turista turista = new Turista();
		
		Prolaznik[] prolaznici = new Prolaznik[] {
				new Prolaznik(),
				new Osnovna(),
				new Srednja(),
				new Fakultet(),
				new Policajac()
			};
		
		
		System.out.println("Na stanici cekaju bus: " + Arrays.toString(prolaznici));
		System.out.println("Tu je i " + prolaznici[prolaznici.length-1]);
			
		for (int i=0; i<prolaznici.length; i++) {
			System.out.println(String.format("\nTurista pita %s...", prolaznici[i]));
			turista.daNePitanje("Da li je ovo bus za grad?", prolaznici[i]);
		}
		
		turista.objasniPitanje("Kako to da neko kaze 'Aha', neko 'Da', a vi 'Jeste'", (Policajac) prolaznici[prolaznici.length-1]);
		turista.daNePitanje("Da li to znaci da ste vi zavrsili fakultet?", prolaznici[prolaznici.length-1]);
	}
}
