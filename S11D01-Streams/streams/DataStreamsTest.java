package streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Klasa demonstira korištenje Data*Stream klasa za pisanje i čitanje prostih
 * Java tipova u binarne fajlove. Ove fajlove možemo pregledati korištenjem hex
 * editora ili naredbe xxd u terminalu
 * 
 * @author damir
 *
 */
public class DataStreamsTest {

	public static void main(String[] args) {
		// fajl u koji će podaci biti serijalizirani
		File f = new File("/Users/damir/Desktop/double");

		// ovdje deklarišemo varijablu kako bi je, po potrebi, mogli zatvoriti u
		// finally bloku
		DataOutputStream dos = null;

		try {
			// otvaramo fajl
			OutputStream fs = new FileOutputStream(f);

			// dekorišemo OutputStream s DataOutputStream klasom, kako bismo
			// dobili mogućnost pisanja binarnih podataka u fajl
			dos = new DataOutputStream(fs);

			// zapisujemo binarne vrijednosti u fajl
			dos.writeDouble(2.67);
			dos.writeInt(15);
			dos.writeUTF("Foo");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// u finally trebamo zatvoriti stream-ove koje smo otvorili, pod
			// uslovom da su uopšte kreirani
			if (dos != null) {
				try {
					// close() metoda može baciti exception, tako da mora biti
					// unutar try/catch bloka
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// puno jednostavniji način da se osiguramo da je stream zatvoren jeste
		// da otvorimo stream-ove unutar try() bloka. Svi objekti kreirani na
		// ovaj način će biti automatski zatvoreni bez obzira na to da li će
		// exception biti bačen ili ne. Ovo je alternativa ručnom pozivanju
		// metode close() u finally bloku
		try (InputStream is = new FileInputStream(f);
				DataInputStream dis = new DataInputStream(is)) {

			// čitamo binarne vrijednosti iz fajla i zapisujemo ih u varijable
			// odgovarajućeg tipa. Jako je bitno da vrijednosti čitamo istim
			// redoslijedom kojim smo ih i zapisali. Probajte zamijeniti
			// redoslijed naredbi ispod i pokrenuti program da vidite zašto je
			// to bitno
			double readDouble = dis.readDouble();
			int readInt = dis.readInt();
			String readString = dis.readUTF();

			// ispisujemo učitane vrijednosti
			System.out.printf("Pročitane vrijednosti: %f, %d, %s\n",
					readDouble, readInt, readString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
