package streams.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {

	public static void main(String[] args) {
		// objekat koji ćemo serijalizirati
		Person p = new Person("Mujo", "Mujić", 27);

		// lokacija za serijalizaciju
		File f = new File("/Users/damir/Desktop/person");

		// kreiramo FileOutputStream i ObjectOutputStream unutar try bloka kako
		// bi ovi resursi bili automatski zatvoreni nakon try bloka, bez obzira
		// da li se desio exception ili ne
		try (FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			// koristimo objekat tipa ObjectOutputStream da zapišemo objekat p
			// FileOutputStream
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// kreiramo FileOutputStream i ObjectOutputStream unutar try bloka kako
		// bi ovi resursi bili automatski zatvoreni nakon try bloka, bez obzira
		// da li se desio exception ili ne
		try (FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);) {

			// koristimo objekat tipa ObjectInputStream da pročitamo objekat
			// tipa Person is FileInputStream-a
			Person p2 = (Person) ois.readObject();

			// ispisujemo podatke o osobi koju smo deserijalizirali iz fajla.
			// Koristimo činjenicu da Person klasa override-a metodu toString().
			System.out.printf("Pročitao: %s\n", p2);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
