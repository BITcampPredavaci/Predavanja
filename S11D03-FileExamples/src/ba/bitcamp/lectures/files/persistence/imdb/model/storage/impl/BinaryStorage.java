package ba.bitcamp.lectures.files.persistence.imdb.model.storage.impl;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.StorageException;

/**
 * File based storage that stores movies in binary format using Java serialization.
 * 
 * @author emir
 *
 */
public class BinaryStorage extends FileBackedInMemoryStorage {
	ObjectOutputStream oos;
	public BinaryStorage(String path) throws IOException {
		super(path);
		oos = new ObjectOutputStream(out);
	}

	@Override
	protected Iterable<Movie> loadMovies(FileInputStream in) throws IOException {
		LinkedList<Movie> movies = new LinkedList<Movie>();
		ObjectInputStream ois = new ObjectInputStream(in);
		Object o = null;
		try {
			try {
				while ((o = ois.readObject()) != null) {
					movies.add((Movie)o);
				}
			} catch (EOFException e) {
				// ignore it
			}
			return movies;
		} catch (ClassNotFoundException e) {
			throw new StorageException("Unable to deserialize class: " + e.getMessage());
		}
	}

	@Override
	protected void persist(Movie movie) throws IOException {
		oos.writeObject(movie);
		oos.flush();
	}
	
	@Override
	public void close() throws IOException {
		oos.close();
		super.close();
	}

}
