package patke;

public class PatkaTest {

	public static void main(String[] args) {
		Patka[] patke = {
				new Patka(),
				new SarajevskaPatka(),
				new GumenaPatka(),
				new DrvenaPatka()
		};
		
		for (Patka p : patke) {
			p.leti();
			p.kvači();
		}
	}

}
