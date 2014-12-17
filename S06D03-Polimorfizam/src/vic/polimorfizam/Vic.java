package vic.polimorfizam;

public class Vic {
	public static void main(String[] args) {
		Turista turista = new Turista();
		
		System.out.println("Turista se pita koji bus vodi u krad i pita prvog prolaznika...");
		Osnovna prolaznik1 = new Osnovna();
		turista.daNePitanje("Da li je ovo bus za grad?", prolaznik1);
		
		System.out.println("Ne nadje rijec u rjecniku, pa upita drugog prolaznika");
		Srednja prolaznik2 = new Srednja();
		turista.daNePitanje("Da li je ovo bus za grad?", prolaznik2);
		
		System.out.println("Nadje rijec, ali upita i treceg prolaznika");
		Fakultet prolaznik3 = new Fakultet();
		turista.daNePitanje("Da li je ovo bus za grad?", prolaznik3);
		
		System.out.println("Zbunjen razlicitim odgovorima upita i policajca koji je prolazio...");
		Policajac policajac = new Policajac();
		turista.daNePitanje("Da li je ovo bus za grad?", policajac);
		turista.objasniPitanje("Kako to da neko kaze 'Aha', neko 'Da', a neko 'Jeste'", policajac);
		turista.daNePitanje("Da li to znaci da ste vi zavrsili fakultet?", policajac);
	}
}
