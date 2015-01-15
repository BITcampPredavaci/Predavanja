package ba.bitcamp.lectures.generics.intro.unsafe.domain;

public class Address {
	private String address;
	
	public Address(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return address;
	}
}
