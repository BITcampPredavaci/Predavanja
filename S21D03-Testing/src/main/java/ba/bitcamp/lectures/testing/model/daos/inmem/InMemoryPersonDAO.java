package ba.bitcamp.lectures.testing.model.daos.inmem;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import ba.bitcamp.lectures.testing.model.daos.PersonDAO;

public class InMemoryPersonDAO implements PersonDAO {
	private AtomicInteger idFactory = new AtomicInteger(1);
	private Map<Integer, String> personNames = new ConcurrentHashMap<>();
	
	@Override
	public int create(String name) {
		if (name != null) {
			int id = idFactory.getAndIncrement();
			personNames.put(id, name);
			return id;
		}
		throw new NullPointerException("Name cannot be null");
	}

	@Override
	public String getName(int id) {
		String name = personNames.get(id);
		if (name != null) {
			return name;
		}
		throw new NoSuchElementException("No person with id " + id);
	}

	@Override
	public String update(int id, String name) {
		if (name != null) {
			String old = personNames.get(id);
			if (old != null) {
				personNames.put(id, name);
				return old;
			}
			throw new NoSuchElementException("No person with id " + id);
		}
		throw new NullPointerException("Name cannot be null");
	}

	@Override
	public String delete(int id) {
		String deleted = personNames.remove(id);
		if (deleted != null) {
			return deleted;
		}
		throw new NoSuchElementException("No person with id " + id);
	}

	@Override
	public void deleteAll() {
		idFactory.set(1);
		personNames.clear();
	}
	
	@Override
	public Collection<Integer> findByName(String nameRegex) {
		LinkedList<Integer> matches = new LinkedList<Integer>();
		if (nameRegex == null || nameRegex.isEmpty()) {
			matches.addAll(personNames.keySet());
		} else {
			Pattern namePattern = Pattern.compile(nameRegex, Pattern.CASE_INSENSITIVE); 
			for (Entry<Integer, String> idName : personNames.entrySet()) {
				if (namePattern.matcher(idName.getValue()).matches()) {
					matches.add(idName.getKey());
				}
			}
		}
		return matches;
	}
}
