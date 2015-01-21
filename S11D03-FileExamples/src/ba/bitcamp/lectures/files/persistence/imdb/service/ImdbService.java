package ba.bitcamp.lectures.files.persistence.imdb.service;

import ba.bitcamp.lectures.files.persistence.imdb.model.Actor;
import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;

/**
 * IMDB service with basic movie methods.
 * 
 * @author emir
 *
 */
public interface ImdbService extends Iterable<Movie> {
	/**
	 * Register movie.
	 * @param movie to register
	 */
	void addMovie(Movie movie);
	
	/**
	 * Iterate movies matching provided name.
	 * 
	 * @param name to filter movies
	 * @return movies matching provided name
	 */
	Iterable<Movie> getMoviesByName(String name);
	
	/**
	 * Iterate movies matching provided year.
	 * 
	 * @param year to filter movies
	 * @return movies filmed in provided year
	 */
	Iterable<Movie> getMoviesByYear(int year);
	
	/**
	 * Iterate movies matching provided year span. Boundaries included.
	 * 
	 * @param fromYear min year
	 * @param toYear max year
	 * @return movies filmed within provided year span.
	 */
	Iterable<Movie> getMoviesByYearSpan(int fromYear, int toYear);
	
	/**
	 * Iterate movies matching provided actor
	 * 
	 * @param actor to filter movies by
	 * @return movies where actor acted
	 */
	Iterable<Movie> getMoviesByActor(Actor actor);
}
