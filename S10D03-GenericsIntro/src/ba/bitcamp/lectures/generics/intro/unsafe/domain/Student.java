package ba.bitcamp.lectures.generics.intro.unsafe.domain;

import ba.bitcamp.lectures.generics.intro.unsafe.CSVBuilder;

public class Student {
	private int id;
	private String name;
	private Address address;

	public Student(int id, String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public String toCsv() {
		CSVBuilder csv = new CSVBuilder();
		csv.append(id).append(name).append(address);
		return csv.toString();
	}
}
