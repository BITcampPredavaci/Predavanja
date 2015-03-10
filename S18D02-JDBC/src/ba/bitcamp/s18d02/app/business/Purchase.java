package ba.bitcamp.s18d02.app.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import ba.bitcamp.s18d02.app.dal.PurchaseStore;
import ba.bitcamp.s18d02.app.dal.StoreException;

/**
 * Model koji predstavlja jednu prodaju
 * 
 * @author damir
 *
 */
public class Purchase {

	/**
	 * Vraća niz svih prodaja kao listu objekata tipa Purchase
	 * 
	 * @return niz objekata koji predstavljaju prodaja
	 * @throws PurchaseLoadException
	 *             u slučaju da dođe do greške prilikom čitanja prodaja
	 */
	public static ArrayList<Purchase> findAll() throws PurchaseLoadException {
		ArrayList<Purchase> result = new ArrayList<>();

		try {
			// iz repozitorija dobavljamo podatke koje smo reprezentirali kao
			// niz mapa koje mapiraju String (naziv atributa) u Object
			// (vrijednost)
			ArrayList<Map<String, Object>> rows = PurchaseStore
					.findAllPurchases();

			for (Map<String, Object> row : rows) {
				Purchase p = new Purchase();

				p.setProductId((int) row.get("ProductId"));
				p.setBuyerId((int) row.get("BuyerId"));
				p.setProductName((String) row.get("ProductName"));
				p.setBuyerName((String) row.get("BuyerName"));
				p.setPrice((BigDecimal) row.get("Price"));

				result.add(p);
			}
		} catch (StoreException e) {
			e.printStackTrace();
			throw new PurchaseLoadException();
		}

		return result;
	}

	private String buyerName;
	private String productName;
	private int buyerId;
	private int productId;
	private BigDecimal price;

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
