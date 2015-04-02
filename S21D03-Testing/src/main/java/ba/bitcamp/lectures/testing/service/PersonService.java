package ba.bitcamp.lectures.testing.service;

import java.util.Collection;
import java.util.LinkedList;

import ba.bitcamp.lectures.testing.model.Person;
import ba.bitcamp.lectures.testing.model.Pet;
import ba.bitcamp.lectures.testing.model.daos.PersonDAO;
import ba.bitcamp.lectures.testing.model.daos.PetDAO;

public class PersonService {
	private PersonDAO personDao;
	private PetDAO petDao;

	public PersonService(PersonDAO personDao, PetDAO petDao) {
		this.personDao = personDao;
		this.petDao = petDao;
	}
	
	public Collection<Person> all() {
		LinkedList<Person> persons = new LinkedList<>();
		Collection<Integer> ids = personDao.findByName(null);
		for (int id : ids) {
			persons.add(get(id));
		}
		return persons;
	}
	
	public void save(Person person) {
		if (person.getId() > 0) {
			// update
			personDao.update(person.getId(), person.getName());
			Collection<Pet> currentPets = petDao.getByOwnerId(person.getId());
			// TODO add collection compare to avoid unnecessary reinserts
			// simplest logic - delete all and reinsert
			for (Pet pet : currentPets) {
				petDao.delete(pet.getId());
			}
			LinkedList<Pet> newPets = person.getPets();
			for (Pet pet : newPets) {
				petDao.save(pet, person.getId());
			}
		} else {
			// create
			int id = personDao.create(person.getName());
			LinkedList<Pet> pets = person.getPets();
			for (Pet pet : pets) {
				petDao.save(pet, id);
			}
		}
	}
	
	public Person get(int personId) {
		String name = personDao.getName(personId);
		Pet[] pets = petDao.getByOwnerId(personId).toArray(new Pet[0]);
		return new Person(personId, name, pets);
	}
}
