package vic.parametrizirano;

public class Vic {
	public static void main(String[] args) {
		Turista turista = new Turista();
		
		System.out.println("Turista se pita koji bus vodi u grad i pita prvog prolaznika...");
		Prolaznik prolaznik1 = new Prolaznik(true, false, false);
		turista.daNePitanje("Da li je ovo bus za grad?", prolaznik1);
		
		System.out.println("Ne nadje rijec u rjecniku, pa upita drugog prolaznika");
		Prolaznik prolaznik2 = new Prolaznik(true, true, false);
		turista.daNePitanje("Da li je ovo bus za grad?", prolaznik2);
		
		System.out.println("Nadje rijec, ali upita i treceg prolaznika");
		Prolaznik prolaznik3 = new Prolaznik(true, true, true);
		turista.daNePitanje("Da li je ovo bus za grad?", prolaznik3);
		
		System.out.println("Zbunjen razlicitim odgovorima upita i policajca koji je prolazio...");
		Policajac policajac = new Policajac();
		turista.daNePitanje("Da li je ovo bus za grad?", policajac);
		turista.objasniPitanje("Kako to da neko kaze 'Aha', neko 'Da', a vi 'Jeste'", policajac);
		turista.daNePitanje("Da li to znaci da ste vi zavrsili fakultet?", policajac);
	}
}
