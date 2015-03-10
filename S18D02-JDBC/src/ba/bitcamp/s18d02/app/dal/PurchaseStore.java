package ba.bitcamp.s18d02.app.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Database Abstraction Layer za prodaje
 * 
 * Metode našeg DAL-a su statičke metode na Store klasama.
 * 
 * @author damir
 */
public class PurchaseStore {

	/**
	 * Vraća podatke za sve prodaje
	 * 
	 * @return niz mapa koje predstavljaju redove koje dolaze iz sistema za pohranu
	 * @throws StoreException
	 */
	public static ArrayList<Map<String, Object>> findAllPurchases()
			throws StoreException {
		ArrayList<Map<String, Object>> rows = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new StoreException(e);
		}

		try (Connection connection = DriverManager
				.getConnection("jdbc:sqlite:shop.sqlite3");
				PreparedStatement statement = connection
						.prepareStatement("SELECT p.name AS product_name, p.id AS product_id, b.name AS buyer_name, b.id AS buyer_id, price "
								+ "FROM buyer_products bp "
								+ "INNER JOIN buyers b ON  bp.buyer_id = b.id "
								+ "INNER JOIN products p ON bp.product_id = p.id ");) {

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				
				row.put("ProductId", resultSet.getInt("product_id"));
				row.put("BuyerId", resultSet.getInt("buyer_id"));
				row.put("ProductName", resultSet.getString("product_name"));
				row.put("BuyerName", resultSet.getString("buyer_name"));
				row.put("Price", resultSet.getBigDecimal("price"));
				
				rows.add(row);
			}

			return rows;
		} catch (SQLException e) {
			throw new StoreException(e);
		}
	}
}
