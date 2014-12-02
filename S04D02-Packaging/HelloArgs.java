
public class HelloArgs {

	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("Hello, world!");
		} else {
			System.out.printf("Hello, %s!\n", args[0]);
		}

	}

}
