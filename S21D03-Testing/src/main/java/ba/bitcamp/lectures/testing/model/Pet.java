package ba.bitcamp.lectures.testing.model;

public class Pet {
	private int id;
	private String type;
	private String name;

	public Pet(String type, String name) {
		this.type = type;
		this.name = name;
		this.id = -1;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	@Override
	public Pet clone() {
		Pet clone = new Pet(type, name);
		clone.id = id;
		return clone;
	}
}
