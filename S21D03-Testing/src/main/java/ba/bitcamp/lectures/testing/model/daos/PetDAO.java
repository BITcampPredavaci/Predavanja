package ba.bitcamp.lectures.testing.model.daos;

import java.util.Collection;
import java.util.NoSuchElementException;

import ba.bitcamp.lectures.testing.model.Pet;

/**
 * Data Access Object for Pet object. Unlike {@link PersonDAO} it uses Pet in method signatures.
 * This flavor is more common.
 * 
 * @author emir
 *
 */
public interface PetDAO {
	/**
	 * Saves or updates pet with owner id info. 
	 * In case pet's id is negative, new pet will be created.
	 * In case pet's id is positive, but there is no pet with such id, {@link NoSuchElementException} should be thrown.
	 * In case pet's name or type is null, {@link NullPointerException} should be thrown.
	 * It does not check owner id in any way.
	 * 
	 * @param pet to save or update
	 * @param ownerId to register pet for
	 */
	void save(Pet pet, int ownerId);
	
	/**
	 * Return pet with provided id.
	 * If there is no pet with such id, {@link NoSuchElementException} should be thrown.
	 * 
	 * @param id of requested pet
	 * @return pet with provided id
	 */
	Pet getById(int id);
	
	/**
	 * Return pets for provided owner id. 
	 * In case there are no registered pets, empty collection should be returned.
	 *  
	 * @param ownerId to return pets for
	 * @return unmodifiable collection of pets
	 */
	Collection<Pet> getByOwnerId(int ownerId);
	
	
	/**
	 * Deletes pet with provided id.
	 * If there is no pet with such id, {@link NoSuchElementException} should be thrown.
	 * 
	 * @param id of pet to be deleted
	 * @return deleted pet
	 */
	Pet delete(int id);
}
