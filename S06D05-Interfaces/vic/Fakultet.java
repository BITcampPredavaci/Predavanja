package vic;

public class Fakultet extends Srednja {
	@Override
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
