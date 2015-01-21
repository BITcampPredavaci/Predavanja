package ba.bitcamp.lectures.files.persistence.imdb.model.storage.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.StorageException;

/**
 * File based storage that stores movies as single CSV line.
 * Format of CSV line is defined in {@link Movie} class. For better control can be moved in this class.
 * 
 * @author emir
 *
 */
public class CSVStorage extends FileBackedInMemoryStorage {
	private BufferedWriter writer;

	public CSVStorage(String path) throws IOException {
		super(path);
		writer = new BufferedWriter(new OutputStreamWriter(out));
	}

	@Override
	protected Iterable<Movie> loadMovies(FileInputStream in) throws IOException {
		LinkedList<Movie> movies = new LinkedList<Movie>();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			Movie movie = Movie.parseMovie(line);
			if (movie != null) {
				movies.add(movie);
			} else {
				throw new StorageException("Unable to parse movie: " + line);
			}
		}
		return movies;
	}
	
	@Override
	protected void persist(Movie movie) throws IOException {
		movie.writeCSVLine(writer);
		writer.flush();
	} 
	
	@Override
	public void close() throws IOException {
		writer.close();
		super.close();
	}

}
