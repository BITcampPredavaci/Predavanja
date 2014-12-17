package vic.polimorfizam;

public class Osnovna extends Prolaznik{
	public String potvrdi() {
		return "Aha!";
	}
	
	@Override
	public String ime() {
		return "Osnovna";
	}
	
	@Override
	public boolean osnovna() {
		return true;
	}
}
