package vic.jednostavno;

public class Turista {
	public void daNePitanje(String pitanje, Osnovna osnovna) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println("Prolaznik odgovara: " + osnovna.potvrdi());
	}
	
	public void daNePitanje(String pitanje, Srednja srednja) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println("Prolaznik odgovara: " + srednja.potvrdi());
	}
	
	public void daNePitanje(String pitanje, Fakultet fakultet) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println("Prolaznik odgovara: " + fakultet.potvrdi());
	}
	
	public void daNePitanje(String pitanje, Policajac policajac) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println("Policajac odgovara: " + policajac.potvrdi());
	}
	
	public void objasniPitanje(String pitanje, Policajac policajac) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println("Policajac objasnjava: " + policajac.objasni());
	}
}
