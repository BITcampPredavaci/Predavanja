package ba.bitcamp.s12d04.xpath.database;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Omogućava korisniku unos XPath upita koji vraćaju elemente tipa 'product'.
 * 
 * Ovi elementi se pretvaraju u objekte Product klase i ispisuju na ekranu
 * koristeći metodu `toString` Product objekata.
 * 
 * @author damir
 *
 */
public class XPathDatabase {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Ovaj program izvršava XPath, parsira Node-ove u Product objekte i ispisuje ih na ekran.");
		System.out.println("Probjate unijeti izraz: //product[price < 200 and state = \"NOVO\"]");

		// TODO: promijeniti putanju do fajla prije pokretanja programa
		File xmlFile = new File("/Users/damir/Desktop/plakari.xml");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		XPathFactory xpf = XPathFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document xmlDocument = db.parse(xmlFile);
			XPath xpath = xpf.newXPath();

			while (true) {
				System.out.println("Unesite XPath koji vraća elemente tipa 'product':");
				String xPathExpression = s.nextLine();
				if (xPathExpression.isEmpty()) {
					break;
				}

				// čitamo XPath izraz sa standardnog ulaza. Očekujemo da ovaj
				// XPath vrati elemente tipa 'product'
				NodeList productNodes = (NodeList) xpath.evaluate(
						xPathExpression, xmlDocument, XPathConstants.NODESET);

				// prolazimo kroz sve node-ove koje XPath vraća i pokušavamo ih
				// cast-ati u Element. Zatim taj element dajemo statičkoj metodi
				// Product.parse koja treba da vrati kreiran objekat tipa
				// Product
				for (int i = 0; i < productNodes.getLength(); i++) {
					Element productElement = (Element) productNodes.item(i);

					Product p = Product.parse(productElement);
					
					System.out.println("+---------------------------------------------------------------");
					System.out.println(p);
					System.out.println("+---------------------------------------------------------------");
					System.out.println();
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
