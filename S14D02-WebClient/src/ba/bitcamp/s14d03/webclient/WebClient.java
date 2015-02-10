package ba.bitcamp.s14d03.webclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import ba.bitcamp.s14d03.url.URL;

/**
 * HTTP klijent koji može izvršiti GET zahtjev na proizvoljnom web serveru
 * 
 * Ovaj klijent NIJE compliant s HTTP specifikacijom.
 * 
 * @author damir
 *
 */
public class WebClient {

	/**
	 * Izvršava GET zahtjev na zadatu adresu.
	 * 
	 * Svaki put kad se pozove ova metoda otvara se novi Socket i preko njega se
	 * šalje HTTP request.
	 * 
	 * @param urlString
	 *            URL na koji treba izvršiti GET zahtjev
	 * @return odgovor servera u vidu Response objekta
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static Response get(String urlString) throws UnknownHostException,
			IOException {
		// parsiramo URL u URL objekat
		URL url = URL.parse(urlString);

		// otvaramo socket u try bloku kako bi se konekcija na server zatvorila
		// u slučaju greške
		try (Socket webSocket = new Socket(url.getHost(), 80)) {

			// kreiramo writer koji će "pisati poruke" serveru kroz socket
			// Writer obogaćujemo PrintWriter dekoratorom kako bismo imali
			// jednostavan API za pisanje upita.
			PrintWriter requestWriter = new PrintWriter(new OutputStreamWriter(
					webSocket.getOutputStream()));

			// kreiramo reader koji će čitati poruku koje nam je server poslao
			// kroz socket.
			// Reader obogaćujemo BufferedReader dekoratorom kako bismo imali
			// API za čitanje odgovora liniju po liniju.
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(webSocket.getInputStream()));

			// kreiramo request koji se sastoji od request-line i request
			// headera
			//
			// Request-Line: http://tools.ietf.org/html/rfc2616#section-5.1
			requestWriter.printf("GET %s HTTP/1.1\n", url.getPath());

			// Header-fields
			// Host: http://tools.ietf.org/html/rfc2616#section-14.23
			requestWriter.printf("Host: %s\n", url.getHost());
			// User-Agent: http://tools.ietf.org/html/rfc2616#section-14.43
			requestWriter.println("User-Agent: bitCamp(WebServer)");
			// prazna linija znači kraj request-header-a
			requestWriter.println();

			// definitivno šaljemo zahtjev
			requestWriter.flush();

			// pripremamo prazan response u koji ćemo smjestiti odgovor koji nam
			// server šalje kroz socket
			Response response = new Response();

			// čitamo status-line
			response.setStatusLine(responseReader.readLine());

			// čitamo response header-fields i smještamo ih u mapu
			while (true) {
				String line = responseReader.readLine();
				if (line == null || line.isEmpty()) {
					break;
				}

				// vrijednosti header-a mogu sadržavati dvotačku, pa je dobra
				// ideja
				// ograničiti se na dva elementa
				String[] headerField = line.split(": ", 2);
				response.getHeaders().put(headerField[0], headerField[1]);
			}

			// čitamo tijelo odgovora i spremamo ga u novi string builder
			StringBuilder bodyBuilder = new StringBuilder();

			while (true) {
				String line = responseReader.readLine();
				if (line == null) {
					break;
				}

				bodyBuilder.append(line);
				bodyBuilder.append('\n');
			}

			response.setBody(bodyBuilder.toString());

			return response;
		}
	}

}
