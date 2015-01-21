package ba.bitcamp.lectures.files.persistence.imdb.service.view.console;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.impl.BinaryStorage;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.impl.CSVStorage;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.impl.FileBackedInMemoryStorage;
import ba.bitcamp.lectures.files.persistence.imdb.service.ImdbService;
import ba.bitcamp.lectures.files.persistence.imdb.service.PlainStorageImdbService;
import ba.bitcamp.lectures.files.persistence.imdb.service.ServiceException;

/**
 * Simple console application that supports both CSV and binary format.
 * Main method takes single argument - storage file.
 * 
 * @author emir
 *
 */
public class ConsoleRunner {
	public static void main(String[] args) throws IOException {
		ImdbService imdb = getImdbService(args);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean running = true;
		printHelp();
		// command loop
		while (running) {
			try {
			String commandLine = null;
			while ((commandLine = in.readLine().trim()).isEmpty());
			int space = commandLine.indexOf(" ");
			String command = space > 0 ? commandLine.substring(0, space) : commandLine;
			switch (command) {
			case "list":
				for (Movie m : imdb) {
					System.out.println(m);
				}
				break;
			case "add":
				if (space > 0) {
					Movie movie = Movie.parseMovie(commandLine.substring(space).trim());
					System.out.println("Parsed movie: " + movie);
					if (movie != null) {
						imdb.addMovie(movie);
					} else {
						System.err.println("Unable to parse movie");
					}
				} else {
					System.err.println("Missing movie line");
				}
				break;
			case "name":
				System.err.println("Not yet implemented");
				break;
			case "year":
				System.err.println("Not yet implemented");
				break;
			case "exit":
				running = false;
				break;
			case "help":
				printHelp();
				break;
			default:
				System.err.println("Unknown command: " + command);
				printHelp();
				break;
			}
			} catch (ServiceException e) {
				System.err.println("IMDB Service error: " + e.getMessage());
			}
		}
		// close imdb if closeable
		if (imdb instanceof Closeable) {
			((Closeable)imdb).close();
		}
	}
	
	private static void printHelp() {
		System.out.println("\nEnter command: ");
		System.out.println("\tlist to list all movies");
		System.out.println("\tname <NAME> to search movies by name");
		System.out.println("\tyear <YEAR> to search movies b year");
		System.out.println("\tadd <NAME,YEAR,FIRST_NAME|LAST_NAME> to enter new movie");
		System.out.println("\texit to exit");
	}
	
	private static ImdbService getImdbService(String[] args) throws IOException {
		String path = "imdb.csv";
		if (args.length == 0) {
			System.out.println("Starting application using default file");
		} else {
			path = args[0];
		}
		System.out.println("Loading IMDB from: " + path);
		FileBackedInMemoryStorage storage = null;
		if (path.endsWith(".csv")) {
			System.out.println("Input format: CSV");
			storage = new CSVStorage(path);
		} else {
			System.out.println("Input format: Binary");
			storage = new BinaryStorage(path);
		}
		return new PlainStorageImdbService(storage);
	}
}
