package vic.polimorfizam;

public class Prolaznik {

	public String potvrdi() {
		return "Mrs!";
	}
	
	public String ime() {
		return "Bezobrazan";
	}
	
	public boolean osnovna() {
		return false;
	}
	
	public boolean srednja() {
		return false;
	}
	
	public boolean fakultet() {
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%s (o:%s; s:%s; f:%s)", ime(), osnovna(), srednja(), fakultet());
	}
}
