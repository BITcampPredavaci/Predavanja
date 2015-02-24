package ba.bitcamp.lectures.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;

import ba.bitcamp.lectures.json.Person;
import ba.bitcamp.lectures.json.PersonSerializer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * HTTP server that implements RESTful API for persons
 * @author emir
 *
 */
public class RestServer {
	private static final Charset UTF8 = Charset.forName("UTF8");
	private static final int SERVER_PORT = 8080;
	private static final String ROOT_CONTEXT = "/persons";

	private ArrayList<Person> elements = new ArrayList<Person>();
	
	public static void main(String[] args) throws Exception {
		new RestServer().start();
	}
	
	public void start() throws IOException {
		System.out.println("Starting REST server on http:/" + ROOT_CONTEXT + ":" + SERVER_PORT);
		HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
		server.createContext(ROOT_CONTEXT, new CollectionsHandler());
		server.setExecutor(null);
		server.start();
		System.out.println("Server started");
		System.out.println("POST {\"name\":\"test\" \"friends\":[]} to add new element to collection");
	}

	private class CollectionsHandler implements HttpHandler {
		public void handle(HttpExchange http) throws IOException {
			String method = http.getRequestMethod();
			System.out.println("Method: " + method);
			// parse url
			String path = http.getRequestURI().getPath().toLowerCase();
			System.out.println("Path: " + path);
			String id = null;
			// check if starts in case of
			if (path.length() > ROOT_CONTEXT.length() + 1) {
				id = path.substring(ROOT_CONTEXT.length()+1);
			}
			
			if (id != null) {
				System.out.println("Resource: element " + path);
				Person el = getPerson(id);
				if (el != null) {
					if (method.equalsIgnoreCase("GET")) {
						sendResponse(http, HttpURLConnection.HTTP_OK, PersonSerializer.serialize(el));
					} else {
						// TODO Add support for other methods
						sendResponse(http, HttpURLConnection.HTTP_BAD_METHOD, "");
					}
				} else {
					sendResponse(http, HttpURLConnection.HTTP_NOT_FOUND, "");
				}
			} else {
				System.out.println("Resource: collection");
				if (method.equalsIgnoreCase("GET")) {
					sendResponse(http, HttpURLConnection.HTTP_OK, PersonSerializer.serialize(elements));
				} else if (method.equalsIgnoreCase("POST")) {
					Person el = PersonSerializer.deserialize(http.getRequestBody());
					synchronized (elements) {
						// TODO use atomic int to track IDs
						el.setId(elements.size());
						elements.add(el);
					}
					http.getResponseHeaders().add("Created-Element", ROOT_CONTEXT + "/" + el.getId());
					sendResponse(http, HttpURLConnection.HTTP_CREATED, PersonSerializer.serialize(el));
				} else {
					// TODO Add support for other methods
					sendResponse(http, HttpURLConnection.HTTP_BAD_METHOD, "");
				}
			}
			
			
			sendResponse(http, HttpURLConnection.HTTP_INTERNAL_ERROR, null);
		}
		
		private Person getPerson(String id) {
			try {
				// TODO do proper id search - can use binary search
				return elements.get(Integer.parseInt(id));
			} catch (RuntimeException e) {
				return null;
			}
		}
		
		private void sendResponse(HttpExchange http, int code, String response) throws IOException {
			http.sendResponseHeaders(code, response.getBytes(UTF8).length+1);
			http.getResponseHeaders().add("Content-type", "application/json");
			OutputStream os = http.getResponseBody();
			os.write(response.getBytes(UTF8));
			os.write('\n');
			os.close();
		}
	}
}
