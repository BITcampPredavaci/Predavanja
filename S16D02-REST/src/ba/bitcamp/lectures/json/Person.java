package ba.bitcamp.lectures.json;

public class Person {
	private int id;
	private String name;
	private int[] related;
	
	public Person() {
		// empty
	}
	
	public Person(int id, String name, int[] related) {
		super();
		this.id = id;
		this.name = name;
		this.related = related;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int[] getRelated() {
		return related;
	}
	
	public void setRelated(int[] friends) {
		this.related = friends;
	}
}
