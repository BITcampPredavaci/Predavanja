package ba.bitcamp.s12d04.xpath;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Demonstrira korištenje XPath-a u Javi na nekoliko jednostavnih predefinisanih
 * primjera
 * 
 * @author damir
 *
 */
public class XPathDemo {

	public static void main(String[] args) {
		// TODO: promijeniti putanju do fajla prije pokretanja programa
		File xmlFile = new File("/Users/damir/Desktop/plakari.xml");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		XPathFactory xpf = XPathFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			// čitamo XML dokument iz XML fajla
			Document xmlDocument = db.parse(xmlFile);

			// pravimo instancu XPath objekta koji ćemo koristiti za izvršavanje
			// XPath naredbi
			XPath xpath = xpf.newXPath();

			// PRIMJER 1:
			//
			// čitamo broj elemenata s imenom 'product' koja su direktna djeca
			// root elementa 'products' koristeći count() funkciju:
			String productCountString = xpath.evaluate("count(/products/product)",
					xmlDocument);
			System.out.printf("Broj proizvoda: %s\n", productCountString);

			// PRIMJER 2:
			//
			// Pristupamo prvom elementu 'product' koji se može nalaziti bilo
			// gdje u dokumentu koristeći operator // i filter [1], te čitamo
			// vrijednost njegovog atributa "url" koristeći znak "@"
			String firstProductUrl = xpath.evaluate("//product[1]/@url",
					xmlDocument);
			System.out.printf("URL prvog proizvoda: %s\n", firstProductUrl);

			// PRIMJER 3:
			//
			// Pristupamo **četvrtom** od kraja elementu 'product' koji se može
			// nalaziti bilo gdje u dokumentu koristeći funkciju last() i
			// čitamo njegov datum iz elementa 'date'.
			//
			// (Prvi element od kraja je zadnji element te bi njemu pristupili
			// sa [last()])
			String fourthFromLastProductsDate = xpath.evaluate("//product[last()-3]/date",
					xmlDocument);
			System.out.printf("Datum pretposljednjeg elementa: %s\n", fourthFromLastProductsDate);

			// PRIMJER 4a:
			//
			// Dohvaćamo listu svih cijena i prikazujemo njihove vrijednosti.
			// Kako bismo mogli dobiti više vrijednosti, moramo XPath objektu
			// reći da vrati NODESET kojem pristupamo kroz klasu `NodeList`.
			//
			// Zatim koristimo for petlju da prođemo kroz sve `Node` objekte iz
			// liste i ispišemo ih na ekran.
			NodeList priceNodes = (NodeList) xpath.evaluate("//price",
					xmlDocument, XPathConstants.NODESET);

			System.out.println("Ispis elemenata tipa 'price'...");
			for (int i = 0; i < priceNodes.getLength(); i++) {
				Node currentNode = priceNodes.item(i);
				System.out.println(currentNode);
			}

			// PRIMJER 4b:
			//
			// Prethodni primjer nije pokazao korisne informacije u konzoli.
			// Sada izvršavamo skoro isti upit ali na kraj XPath izraza dodajemo
			// funkciju `text()` koja pristupa tekstualnom sadržaju elementa 'price'.
			NodeList priceTextNodes = (NodeList) xpath.evaluate("//price/text()",
					xmlDocument, XPathConstants.NODESET);

			System.out.println("Ispis tekstualnih sadržaja svih elemenata tipa 'price'...");
			for (int i = 0; i < priceTextNodes.getLength(); i++) {
				Node currentNode = priceTextNodes.item(i);
				System.out.println(currentNode);
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
}
