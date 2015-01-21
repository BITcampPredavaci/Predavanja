package ba.bitcamp.lectures.files.persistence.imdb.model.storage.impl;

import java.util.LinkedList;

import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.PlainStorage;

/**
 * {@link PlainStorage} implementation that uses {@link LinkedList} for storing movies.
 * Does not provide persistence. Can be used for testing purposes.
 * 
 * @author emir
 *
 */
public class InMemoryStorage implements PlainStorage {
	
	LinkedList<Movie> movies = new LinkedList<Movie>();

	@Override
	public void store(Movie movie) {
		movies.add(movie);
	}

	@Override
	public Iterable<Movie> getAll() {
		return movies;
	}

	@Override
	public Iterable<Movie> getForYear(int year) {
		LinkedList<Movie> yearMovies = new LinkedList<Movie>();
		for (Movie m : movies) {
			if (m.getYear() == year) {
				yearMovies.add(m);
			}
		}
		return yearMovies;
	}

	@Override
	public Iterable<Movie> getByName(String namePrefix) {
		LinkedList<Movie> yearMovies = new LinkedList<Movie>();
		for (Movie m : movies) {
			if (m.getName().toUpperCase().startsWith(namePrefix.toUpperCase())) {
				yearMovies.add(m);
			}
		}
		return yearMovies;
	}

}
