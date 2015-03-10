package ba.bitcamp.s18d02.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Primjer pristupa SQLite bazi korištenjem SQLite JDBC drivera
 * 
 * https://bitbucket.org/xerial/sqlite-jdbc
 * 
 * @author damir
 *
 */
public class SQLiteDatabaseAccess {

	public static void main(String[] args) throws ClassNotFoundException {
		// čitamo klasu koja definiše JDBC driver za SQLite. Naziv klase zavisi
		// od samog driver-a i treba ga pronaći u dokumentaciji driver-a.
		Class.forName("org.sqlite.JDBC");

		// konekciju na bazu dobivamo od DriverManager-a koristeći JDBC
		// connection string. Sintaksa connection string-a zavisi od driver-a,
		// pa i za ovo treba konsultovati dokumentaciju
		try (Connection connection = DriverManager
				.getConnection("jdbc:sqlite:shop.sqlite3")) {

			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT b.id AS buyer_id, b.name AS buyer_name, p.id AS product_id, p.name AS product_name, price "
							+ "FROM products p "
							+ "INNER JOIN buyer_products bp ON p.id = bp.product_id "
							+ "INNER JOIN buyers b ON b.id = bp.buyer_id");

			while (rs.next()) {
				System.out.printf("Buyer ID:     %s\n", rs.getInt("buyer_id"));
				System.out.printf("Buyer Name:   %s\n", rs.getString("buyer_name"));
				System.out.printf("Product ID:   %d\n", rs.getInt("product_id"));
				System.out.printf("Product Name: %s\n", rs.getString("product_name"));
				System.out.printf("Price:        %s\n", rs.getBigDecimal("price"));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
