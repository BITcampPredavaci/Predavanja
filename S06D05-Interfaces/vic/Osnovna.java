package vic;

public class Osnovna implements Prolaznik, Uporedivi {
	@Override
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

	@Override
	public boolean srednja() {
		return false;
	}

	@Override
	public boolean fakultet() {
		return false;
	}
	
	@Override
	public int uporedi(Uporedivi drugi) {
		Prolaznik drugiProlaznik = (Prolaznik)drugi;
		
		if (ime().compareTo(drugiProlaznik.ime()) < 0 ) {
			return -1;
		} else if (ime().equals(drugiProlaznik.ime())) {
			return 0;
		} else {
			return 1;
		}
		
		//return ime().compareTo(drugiProlaznik.ime());
	}
	
	@Override
	public String toString() {
		return String.format("%s (o:%s; s:%s; f:%s)", ime(), osnovna(), srednja(), fakultet());
	}
}
