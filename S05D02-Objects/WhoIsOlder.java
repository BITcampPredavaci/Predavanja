
public class WhoIsOlder {

	public static void main(String[] args) {
		User u1 = getUser();
		User u2 = getUser();
		
		if (u1.age > u2.age) {
			printUserInfo(u1);
		} else {
			printUserInfo(u2);
		}
	}

	private static User getUser() {
		User u = new User();
		
		System.out.print("Unesite ime: ");
		u.firstName = TextIO.getln();
		
		System.out.print("Unesite prezime: ");
		u.lastName = TextIO.getln();
		
		System.out.print("Unesite broj godina: ");
		u.age = TextIO.getlnInt();
		
		return u;
	}

	private static void printUserInfo(User u) {
		System.out.printf("%s %s je starija/i. Ima %d godina\n",
				u.firstName, u.lastName, u.age);
	}

}
