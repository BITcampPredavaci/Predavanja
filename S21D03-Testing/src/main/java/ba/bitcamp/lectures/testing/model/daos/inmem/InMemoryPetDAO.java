package ba.bitcamp.lectures.testing.model.daos.inmem;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import ba.bitcamp.lectures.testing.model.Pet;
import ba.bitcamp.lectures.testing.model.daos.PetDAO;

public class InMemoryPetDAO implements PetDAO {
	private AtomicInteger idFactory = new AtomicInteger(1);
	private Map<Integer, Collection<Pet>> ownerPets = new HashMap<>();
	private Map<Integer, Pet> petsLookup = new ConcurrentHashMap<>();

	@Override
	public void save(Pet pet, int ownerId) {
		if (pet.getName() == null || pet.getType() == null) {
			throw new NullPointerException("Pet' name and/or type is null");
		}
		
		if (pet.getId() <= 0) {
			int id = idFactory.getAndIncrement();
			pet.setId(id);
			// add clone to avoid changing pet outside of DAO
			Pet clone = pet.clone();
			ensureOwnerPets(ownerId).add(clone);
			petsLookup.put(pet.getId(), clone);
		} else {
			Pet toChange = petsLookup.get(pet.getId());
			if (toChange != null) {
				toChange.setName(pet.getName());
				toChange.setType(pet.getType());
				for (Collection<Pet> pets : ownerPets.values()) {
					if (pets.remove(toChange)) {
						break;
					}
				}
				ensureOwnerPets(ownerId).add(toChange);
			} else {
				throw new NoSuchElementException("No pet with id " + pet.getId());
			}
		}
	}

	@Override
	public Pet getById(int id) {
		Pet pet = petsLookup.get(id);
		if (pet != null) {
			// clone is returned to avoid changing pet outside of DAO
			return pet.clone();
		}
		throw new NoSuchElementException("No pet with id " + id);
	}

	@Override
	public Collection<Pet> getByOwnerId(int ownerId) {
		Collection<Pet> pets = ownerPets.get(ownerId);
		if (pets != null) {
			// unmodifiable collection is returned to avoid changing outside of DAO
			return Collections.unmodifiableCollection(pets);
		}
		return Collections.emptyList();
	}
	
	@Override
	public Pet delete(int id) {
		Pet pet = getById(id);
		for (Collection<Pet> pets : ownerPets.values()) {
			if (pets.remove(pet)) {
				break;
			}
		}
		petsLookup.remove(pet.getId());
		return pet;
	}
	
	private synchronized Collection<Pet> ensureOwnerPets(int ownerId) {
		Collection<Pet> pets = ownerPets.get(ownerId);
		if (pets == null) {
			pets = new ConcurrentLinkedQueue<>();
			ownerPets.put(ownerId, pets);
		}
		return pets;
		
		
	}

}
