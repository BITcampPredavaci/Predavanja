package ba.bitcamp.s18d02.app.presentation;

import java.util.ArrayList;

import ba.bitcamp.s18d02.app.business.Purchase;
import ba.bitcamp.s18d02.app.business.PurchaseLoadException;

/**
 * Ulazna tačka za našu CLI aplikaciju
 * 
 * Ovo je prezentacijski sloj naše aplikacije, te se u njemu ograničavamo
 * isključivo na interakciju sa business logic layerom (neki ga zovu i domain
 * layer, ili čak service layer).
 * 
 * Možete primjetiti da u ovaj program nije importovana nijedna klasa za rad sa
 * JDBC-em.
 * 
 * @author damir
 *
 */
public class LayeredApp {

	public static void main(String[] args) {
		ArrayList<Purchase> purchases;

		try {
			purchases = Purchase.findAll();
		} catch (PurchaseLoadException e) {
			e.printStackTrace();
			return;
		}

		for (Purchase p : purchases) {
			System.out.printf("Naziv proizvoda: %s\n", p.getProductName());
			System.out.printf("ID proizvoda:    %d\n", p.getProductId());
			System.out.printf("Ime kupca:       %s\n", p.getBuyerName());
			System.out.printf("ID kupca:        %d\n", p.getBuyerId());
			System.out.printf("Cijena:          %s\n", p.getPrice());
			System.out.println();
		}

	}

}
