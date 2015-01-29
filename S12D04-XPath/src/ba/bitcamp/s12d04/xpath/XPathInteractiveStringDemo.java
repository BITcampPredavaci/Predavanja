package ba.bitcamp.s12d04.xpath;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Pokreće interaktivnu sesiju u kojoj možemo unijeti proizvoljni XPath izraz i
 * vidjeti rezultat njegovog izvršavanja kao string.
 * 
 * Prije gledanja ovog programa pogledati XPathDemo. 
 * 
 * @author damir
 *
 */
public class XPathInteractiveStringDemo {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Ovaj program izvršava XPath i vraća rezultat kao string.");
		System.out.println("Probajte unijeti izraz: count(//product[price > 200])");

		// TODO: promijeniti putanju do fajla prije pokretanja programa
		File xmlFile = new File("/Users/damir/Desktop/plakari.xml");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		XPathFactory xpf = XPathFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document xmlDocument = db.parse(xmlFile);
			XPath xpath = xpf.newXPath();

			while (true) {
				System.out.println("Unesite XPath izraz:");
				
				// čitamo XPath izraz sa standardnog ulaza
				String xpathExpression = s.nextLine();
				if (xpathExpression.isEmpty()) { break; }
				
				// izvršavamo pročitani XPath izraz na čitavom XML dokumentu
				String result = xpath.evaluate(xpathExpression, xmlDocument);
				System.out.println(result);
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
