package vic.parametrizirano;

public class Turista {
	public void daNePitanje(String pitanje, Prolaznik prolaznik) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println("Prolaznik odgovara: " + prolaznik.potvrdi());
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
