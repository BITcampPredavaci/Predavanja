package ba.bitcamp.lectures.files.persistence.imdb.service;

import java.io.Closeable;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import ba.bitcamp.lectures.files.persistence.imdb.model.Actor;
import ba.bitcamp.lectures.files.persistence.imdb.model.Movie;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.PlainStorage;
import ba.bitcamp.lectures.files.persistence.imdb.model.storage.StorageException;

/**
 * {@link ImdbService} implementation that uses {@link PlainStorage} to implement service methods. 
 * It also includes validation logic before adding new movie.
 *  
 * @author emir
 *
 */
public class PlainStorageImdbService implements ImdbService, Closeable {
	
	private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	private PlainStorage storage;

	public PlainStorageImdbService(PlainStorage storage) {
		this.storage = storage;
	}

	@Override
	public Iterator<Movie> iterator() {
		return storage.getAll().iterator();
	}

	@Override
	public void addMovie(Movie movie) {
		validateMovie(movie);
		try {
			storage.store(movie);
		} catch (StorageException e) {
			throw new ServiceException("Failed adding movie", e);
		}
	}

	public void validateMovie(Movie movie) {
		validateName(movie);
		validateYear(movie);
	}
	
	public void validateName(Movie movie) {
		if (movie.getName() == null || movie.getName().trim().isEmpty()) {
			throw new ServiceException("Mandatory movie name", null);
		}
	}
	
	public void validateActors(Movie movie) {
		Collection<Actor> actors = movie.getActors();
		if (actors != null && !actors.isEmpty()) {
			for (Actor actor : actors) {
				validateActor(actor);
			}
		}
	}

	public void validateActor(Actor actor) {
		if (actor.getFirstName() == null || actor.getFirstName().trim().isEmpty()) {
			throw new ServiceException("Mandatory actor first name", null);
		}
	}

	public void validateYear(Movie movie) {
		if (movie.getYear() < 1900 || movie.getYear() > CURRENT_YEAR) {
			throw new ServiceException("Year must be between 1900 and " + CURRENT_YEAR, null);
		}
	}
	@Override
	public Iterable<Movie> getMoviesByName(String name) {
		return storage.getByName(name);
	}

	@Override
	public Iterable<Movie> getMoviesByYear(int year) {
		return storage.getForYear(year);
	}

	@Override
	public Iterable<Movie> getMoviesByYearSpan(int fromYear, int toYear) {
		LinkedList<Movie> movies = new LinkedList<Movie>();
		for (Movie m : storage.getAll()) {
			if (m.getYear() >= fromYear && m.getYear() <= toYear) {
				movies.add(m);
			}
		}
		return movies;
	}

	@Override
	public Iterable<Movie> getMoviesByActor(Actor actor) {
		LinkedList<Movie> movies = new LinkedList<Movie>();
		for (Movie m : storage.getAll()) {
			if (m.getActors() != null && m.getActors().contains(actor)) {
				movies.add(m);
			}
		}
		return movies;
	}

	@Override
	public void close() throws IOException {
		if (storage instanceof Closeable) {
			((Closeable)storage).close();
		}
	}
	
}
