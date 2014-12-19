package vic;

public class Turista {
	public void daNePitanje(String pitanje, Prolaznik prolaznik) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println(prolaznik + " odgovara: " + prolaznik.potvrdi());
	}
	
	public void objasniPitanje(String pitanje, Policajac policajac) {
		System.out.println("Turista pita: " + pitanje);
		System.out.println(policajac + " odgovara: " + policajac.objasni());
	}
}
