package vic;

public class IspisVeceg {
	
	public static void ispišiVećeg(Uporedivi prvi, Uporedivi drugi) {
		Uporedivi max;
		if (prvi.uporedi(drugi) < 0) {
			max = drugi;
		} else {
			max = prvi;
		}
		
		System.out.printf("Veći je: %s\n", max);
	}
	
}
