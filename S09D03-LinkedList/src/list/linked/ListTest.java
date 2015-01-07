package list.linked;

public class ListTest {
	public static void main(String[] args) {
		List test = new List();
		System.out.println("Empty list: " + test);
		
		test.add("B");
		test.add("D");
		System.out.println("After add: " + test);
		
		test.push("A");
		test.push("X");
		System.out.println("After push: " + test);
		
		test.pop();
		System.out.println("After pop: " + test);
		
		test.insertAt("C", 2);
		System.out.println("After insert: " + test);
		
		test.insertAt("X", 4);
		System.out.println("After insert at end: " + test);
		
		test.removeAt(4);
		test.add("E");
		System.out.println("After remove from end: " + test);
		
		for (int i=0; i<5; i++) {
			test.pop();
		}
		System.out.println("After popping all: " + test);
		
		test.add("Z");
		System.out.println("After add: " + test);
		
	}
}
