package ba.bitcamp.s12d04.xpath.database;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Scanner;

/**
 * Predstavlja količinu novca određenu vrijednošću i valutom
 * 
 * Objekti ove klase su immutable, što znači da se vrijednosti atributa klase ne
 * mogu mijenjati nakon što je klasa napravljena. Immutability nam kaže da može
 * koristiti objekte ove klase bez straha da neki dio sistema tiho ili
 * neočekivano mijenja vrijednosti.
 * 
 * @author damir
 *
 */
public class Money {
	private BigDecimal value;
	private Currency currency;

	public Money(BigDecimal value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}

	/**
	 * Pretvara vrijednosti iz stringa u objekat tipa Money
	 * 
	 * Ulazni String treba sadržavati vrijednost i simbol valute razdvojen
	 * razmacima. Naprimjer: "10.15 BAM".
	 * 
	 * @param moneyString
	 *            String iz kojeg čitamo vrijednost i valutu
	 * @return objekat klase Money čiji su atributi inijalizirani vrijednostima
	 *         iz Stringa
	 */
	public static Money parse(String moneyString) {
		Scanner s = new Scanner(moneyString);
		String valueString = s.next();
		String currencyString = s.next();
		s.close();

		BigDecimal value = new BigDecimal(valueString);
		Currency currency = Currency.getInstance(currencyString);

		return new Money(value, currency);
	}

	public BigDecimal getValue() {
		return value;
	}

	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Formatiramo novac kao string tako da ispišemo vrijednost i simbol valute
	 * razdvojene razmakom.
	 */
	@Override
	public String toString() {
		return String.format("%s %s", value, currency.getSymbol());
	}
}
