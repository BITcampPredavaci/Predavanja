
public class HelloUser {

	public static void main(String[] args) {
		User u = getUser();
		printUserInfo(u);
	}

	private static User getUser() {
		User u = new User();
		
		System.out.print("Kako se zovete? ");
		u.firstName = TextIO.getln();
		
		System.out.print("Kako se prezivate? ");
		u.lastName = TextIO.getln();
		
		System.out.print("Koliko imate godina? ");
		u.age = TextIO.getlnInt();
		
		return u;
	}

	private static void printUserInfo(User u) {
		System.out.printf("%s %s ima %d godina\n",
				u.firstName, u.lastName, u.age);
	}

}
