package ba.bitcamp.s12d04.xpath.database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;

public class Product {
	private int id;
	private String title;
	private String description;
	private Money price;
	private String state;
	private String url;
	private String imageUrl;
	private String location;
	private Date postDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		String priceString;
		if (price == null) {
			priceString = "-No price-";
		} else {
			priceString = price.toString();
		}

		String dateString;
		if (postDate == null) {
			dateString = "";
		} else {
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,
					DateFormat.SHORT, Locale.getDefault());
			dateString = df.format(postDate);
		}

		return String.format("%s<%d>: %s [%s]\n%s\n%s\n%s\n%s", title, id, priceString,
				dateString, description, location, url, imageUrl);
	}

	private static XPathFactory xpf = XPathFactory.newInstance();
	private static XPath xpath = xpf.newXPath();
	private static DateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dddd'T'HH:mm");

	public static Product parse(Element element)
			throws XPathExpressionException, ParseException {
		if (!element.getTagName().equals("product")) {
			throw new IllegalArgumentException(
					"Cannot parse XML elements other than <product>.");
		}

		// pravimo objekat tipa Product koji ćemo popuniti elementa
		Product p = new Product();

		// ID čitamo iz atributa "id" elementa <product>.
		// Koristimo ./ da kažemo da želimo pristupiti @id atributu trenutnog elementa.
		String idString = xpath.evaluate("./@id", element);
		p.setId(Integer.parseInt(idString));

		// Naslov čitamo iz teksta elementa <title> koji je direktno dijete trenutnog elementa
		p.setTitle(xpath.evaluate("./title", element));

		// Opis čitamo iz teksta elementa <description> koji je direktno dijete trenutnog elementa
		p.setDescription(xpath.evaluate("./description", element));

		// Cijenu čitamo iz elementa <price> koji je direktno dijete trenutnog elementa
		// Ako je cijena po dogovoru p.getPrice() će vraćati null
		String priceString = xpath.evaluate("./price", element);
		if (!priceString.equals("PO DOGOVORU")) {
			String currencyString = xpath
					.evaluate("./price/@currency", element);
			String formattedPrice = String.format("%s %s", priceString,
					currencyString);

			Money price = Money.parse(formattedPrice);
			p.setPrice(price);
		}

		// Stanje proizvoda čitamo iz teksta elementa <state> koji je direktno dijete trenutnog elementa
		p.setState(xpath.evaluate("./state", element));

		// URL čitamo iz atributa "url" elementa "product"
		p.setUrl(xpath.evaluate("./@url", element));

		// URL slike proizvoda čitamo iz atributa "url" elementa <image> koji je direktno dijete trenutnog elementa
		p.setImageUrl(xpath.evaluate("./image/@url", element));

		// Lokaciju prodavača čitamo iz teksta elementa <location>
		p.setLocation(xpath.evaluate("./location", element));

		// Datum objave čitamo iz teksta elementa <date> ali ga moramo prvo parsirati!
		String dateString = xpath.evaluate("./date", element);
		p.setPostDate(xmlDateFormat.parse(dateString));
		
		return p;
	}

}
