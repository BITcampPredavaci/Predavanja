package ba.bitcamp.lectures.files.persistence.imdb.model.storage;

import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;

/**
 * Simple movie storage interface. Defines only basic methods.
 * 
 * @author emir
 *
 */
public interface PlainStorage {
	/**
	 * Stores provided movie with its actors.
	 * 
	 * @param movie to store
	 */
	void store(Movie movie);
	
	/**
	 * Returns all movies in storage.
	 * @return all movies
	 */
	Iterable<Movie> getAll();
	
	/**
	 * Returns all movies with provided year 
	 * @param year
	 * @return movies with provided year or empty iterable if no such movies.
	 */
	Iterable<Movie> getForYear(int year);
	
	/**
	 * Returns all movies whose name starts with provided prefix
	 * @param namePrefix to match movies
	 * @return movies matching provided prefix or empty iterable if no such movies.
	 */
	Iterable<Movie> getByName(String namePrefix);
}
