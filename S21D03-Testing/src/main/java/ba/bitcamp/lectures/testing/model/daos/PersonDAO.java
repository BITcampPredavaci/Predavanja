package ba.bitcamp.lectures.testing.model.daos;

import java.util.Collection;
import java.util.NoSuchElementException;


/**
 * Data Access Object for person - it stores/retrieves only values that are part of person - id and name.
 * Usually it uses Person class in method signatures, but to make sure it is expected to be combined with PetDAO,
 * we used such interface.
 * 
 * @author emir
 *
 */
public interface PersonDAO {
	/**
	 * Creates new user for provided name and returns its id.
	 * In case null is provided as name, {@link NullPointerException} should be thrown.
	 * 
	 * @param name to create user for
	 * @return id of newly created user
	 */
	int create(String name);
	
	/**
	 * Returns name of user with provided id.
	 * In case there is no user with provided id, {@link NoSuchElementException} should be thrown.
	 * 
	 * @param id of user
	 * @return name of user
	 */
	String getName(int id);
	
	/**
	 * Updates name for user with provided id.
	 * In case null is provided as name, {@link NullPointerException} should be thrown.
	 * In case there is no user with provided id, {@link NoSuchElementException} should be thrown.
	 * 
	 * @param id of user to be updated 
	 * @param name new name
	 * @return old name
	 */
	String update(int id, String name);
	
	/**
	 * Deletes user with provided id.
	 * In case there is no user with provided id, {@link NoSuchElementException} should be thrown.
	 * 
	 * @param id of user to delete
	 * @return name of deleted user
	 */
	String delete(int id);

	/**
	 * Deletes all records.
	 */
	void deleteAll();
	
	/**
	 * Returns all person ids whose name matches provided regex.
	 * In case of null or empty regex, all person ids should be returned.
	 * 
	 * @param nameRegex to filter result
	 * @return ids of mersons matching regex
	 */
	Collection<Integer> findByName(String nameRegex); 
}
