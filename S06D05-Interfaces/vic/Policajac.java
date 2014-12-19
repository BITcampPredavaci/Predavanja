package vic;

public class Policajac extends Osnovna {
	int pitanje = 0;
	
	@Override
	public String potvrdi() {
		if (pitanje++ == 0) {
			return "Jeste!";
		}
		return "Aha!";
	}
	
	@Override
	public String ime() {
		return "Policajac";
	}
	
	public String objasni() {
		return "Onaj ko potvrdi sa 'Aha' ima osnovnu, ko potvrdi sa 'Da' ima srednju, a ko potvrdi sa 'Jeste' fakultet";
	}
}
