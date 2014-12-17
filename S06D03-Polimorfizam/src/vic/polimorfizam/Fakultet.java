package vic.polimorfizam;

public class Fakultet extends Srednja {
	public String potvrdi() {
		return "Jeste!";
	}
	
	@Override
	public String ime() {
		return "Fakultet";
	}
	
	@Override
	public boolean fakultet() {
		return true;
	}
}
