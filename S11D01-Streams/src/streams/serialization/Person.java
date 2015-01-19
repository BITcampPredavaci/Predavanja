package streams.serialization;

import java.io.Serializable;

/**
 * Serializable klasa Person
 * 
 * Klasa implementira Serializable interfejs čime daje do znanja drugim
 * komponentama sistema da ju je moguće serijalizirati u byte stream i
 * deserijalizirati iz byte streama.
 * 
 * @author damir
 *
 */
public class Person implements Serializable {
	/**
	 * Ovaj atribut omogućava objektima koji vrše deserijalizaciju da pronađu
	 * klasu u koju objekat treba deserijalizirati
	 */
	private static final long serialVersionUID = -2489738561072190302L;

	private String firstName;
	private String lastName;
	private int age;

	public Person(String firstName, String lastName, int age) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("Osoba: %s, %s, %d", firstName, lastName, age);
	}
}
