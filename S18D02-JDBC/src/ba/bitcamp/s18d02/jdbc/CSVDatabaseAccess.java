package ba.bitcamp.s18d02.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Primjer čitanja CSV fajlova korištenjem CSV JDBC drivera
 * 
 * http://csvjdbc.sourceforge.net/
 * 
 * Obratite pažnju da je izuzev učitavanja driver-a i spajanja na bazu kôd jako
 * sličan onom u SQLiteDatabaseAccess i PostgreSQLDatabaseAccess klasama, s
 * velikom razlikom u tome da ne možemo raditi JOIN korištenjem CSV JDBC
 * drivera.
 * 
 * @author damir
 *
 */
public class CSVDatabaseAccess {

	public static void main(String[] args) throws ClassNotFoundException {
		// čitamo klasu koja definiše JDBC driver za SQLite. Naziv klase zavisi
		// od samog driver-a i treba ga pronaći u dokumentaciji driver-a.
		Class.forName("org.relique.jdbc.csv.CsvDriver");

		// konekciju na bazu dobivamo od DriverManager-a koristeći JDBC
		// connection string. Sintaksa connection string-a zavisi od driver-a,
		// pa i za ovo treba konsultovati dokumentaciju
		try (Connection connection = DriverManager
				.getConnection("jdbc:relique:csv:shop")) {

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM products");

			while (rs.next()) {
				System.out.printf("ID:    %d\n", rs.getInt("id"));
				System.out.printf("Name:  %s\n", rs.getString("name"));
				System.out.printf("Price: %s\n", rs.getBigDecimal("price"));
				System.out.println();
			}

			ResultSet rsBuyers = statement.executeQuery("SELECT * FROM buyers");

			while (rsBuyers.next()) {
				System.out.printf("ID:     %d\n", rsBuyers.getInt("id"));
				System.out.printf("Name:   %s\n", rsBuyers.getString("name"));
				System.out.printf("Credit: %s\n", rsBuyers.getBigDecimal("credit"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
