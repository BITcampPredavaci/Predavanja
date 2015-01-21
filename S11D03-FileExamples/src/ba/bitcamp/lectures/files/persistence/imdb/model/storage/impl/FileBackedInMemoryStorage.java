package ba.bitcamp.lectures.files.persistence.imdb.model.storage.impl;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;
import ba.bitcamp.lectures.files.utils.FileUtils;

/**
 * Abstract base class that adds persistance to {@link InMemoryStorage}. 
 * It assumes single file and inits loads in case of file existence.
 * 
 * @author emir
 *
 */
public abstract class FileBackedInMemoryStorage extends InMemoryStorage implements Closeable {
	protected FileOutputStream out;

	public FileBackedInMemoryStorage(String path) throws IOException {
		File file = new File(path);
		// load movies if file not empty
		if (file.exists() && file.length() > 0) {
			FileInputStream in = new FileInputStream(file);
			try {
				Iterable<Movie> persistedMovies = loadMovies(in);
				for (Movie movie : persistedMovies) {
					super.store(movie);
				}
			} finally {
				FileUtils.closeQuietly(in);
			}
		}
		out = new FileOutputStream(file, true);
	}
	

	@Override
	public void store(Movie movie) {
		// add to memory and invokes persistence
		super.store(movie);
		try {
			persist(movie);
		} catch (IOException e) {
			// wrap in runtime exception
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void close() throws IOException {
		out.close();
	}

	/**
	 * Parse movies from provided {@link FileInputStream}.
	 * 
	 * @param in to load movies from
	 * @return iterable of loaded movies
	 * @throws IOException in case of underlying read exception
	 */
	protected abstract Iterable<Movie> loadMovies(FileInputStream in) throws IOException;

	/**
	 * Persists movie to file. Concrete classes hace {@link FileOutputStream} field to use to access file.
	 * 
	 * @param movie to persist
	 * @throws IOException in case of underlying write exception
	 */
	protected abstract void persist(Movie movie) throws IOException;
}
