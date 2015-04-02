package ba.bitcamp.lectures.testing.model;

import java.util.Collections;
import java.util.LinkedList;

public class Person {
	private int id;
	private String name;
	private LinkedList<Pet> pets;
	
	public Person() {
		this.pets = new LinkedList<Pet>();
	}
	
	public Person(int id, String name, Pet...pets) {
		this();
		this.id = id;
		this.name = name;
		Collections.addAll(this.pets, pets);
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
	
	public LinkedList<Pet> getPets() {
		return pets;
	}
	
	public void setPets(LinkedList<Pet> pets) {
		this.pets = pets;
	}
}
