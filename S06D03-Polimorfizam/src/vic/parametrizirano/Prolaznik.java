package vic.parametrizirano;

public class Prolaznik {
	
	private boolean osnovna;
	private boolean srednja;
	private boolean fakultet;

	public Prolaznik(boolean osnovna, boolean srednja, boolean fakultet) {
		this.osnovna = osnovna;
		this.srednja = srednja;
		this.fakultet = fakultet;
	}
	
	public String potvrdi() {
		if (fakultet) {
			return "Jeste!";
		} else if (srednja) {
			return "Da!";
		} else if (osnovna) {
			return "Aha!";
		}
		return "Mrs!";
	}
	
	@Override
	public String toString() {
		return String.format("Prolaznik (o:%s; s:%s; f:%s)", osnovna, srednja, fakultet);
	}
}
