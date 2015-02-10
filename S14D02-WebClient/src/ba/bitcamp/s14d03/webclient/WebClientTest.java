package ba.bitcamp.s14d03.webclient;

import java.io.IOException;
import java.net.UnknownHostException;

public class WebClientTest {

	public static void main(String[] args) {
		// Početni URL
		String url = "http://pik.ba/";

		try {
			Response response;

			// izvršavamo upite sve dok status kôd nije 3xx koristeći URL koji
			// smo dobili unutar response-header-field-a Location
			do {
				// dobijamo odgovor upita na traženi URL
				response = WebClient.get(url);

				// ispisujemo tijelo odgovora
				System.out.println(response.getBody());

				// pokušavamo dobiti novi URL koji će nam možda trebati ako je
				// HTTP status neki u 3xx porodici
				url = response.getHeaders().get("Location");
			} while (response.getStatusCode() >= 300
					&& response.getStatusCode() <= 399);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
