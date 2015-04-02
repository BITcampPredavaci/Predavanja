package ba.bitcamp.lectures.testing.model.daos;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Test;

import ba.bitcamp.lectures.testing.model.Pet;

public abstract class PetDAOTest {
	protected PetDAO petDao;
	
	@Test
	public void testCreateNew() {
		Pet dog = new Pet("dog", "Rex");
		petDao.save(dog, 1);
		assertTrue(dog.getId() > 0);
		
		Pet cat = new Pet("cat", "Fluffy");
		petDao.save(cat, 1);
		assertTrue(cat.getId() > 0);
		assertNotEquals(cat.getId(), dog.getId());
	}
	
	@Test
	public void testGetById() {
		Pet dog = new Pet("dog", "Rex");
		petDao.save(dog, 1);
		
		Pet res = petDao.getById(dog.getId());
		assertEquals(dog.getId(), res.getId());
		assertEquals("dog", res.getType());
		assertEquals("Rex", res.getName());
	}
	
	@Test
	public void testSaveUpdate() {
		Pet dog = new Pet("dog", "Rex");
		petDao.save(dog, 1);
		
		// change
		dog.setName("TRex");
		
		// assert just setting will not change stored
		assertEquals("Rex", petDao.getById(dog.getId()).getName());
		
		petDao.save(dog, 2);
		assertEquals("TRex", petDao.getById(dog.getId()).getName());
		assertTrue(petDao.getByOwnerId(1).isEmpty());
		assertFalse(petDao.getByOwnerId(2).isEmpty());
	}
	
	@Test
	public void testGetByOwnerIdEmptyOwnerId() {
		assertTrue(petDao.getByOwnerId(-1).isEmpty());
	}
	
	@Test
	public void testGetByOwnerId() {
		petDao.save(new Pet("dog", "Rex"), 1);
		Collection<Pet> pets = petDao.getByOwnerId(1);
		assertFalse(pets.isEmpty());
		Pet pet = pets.iterator().next();
		assertTrue(pet.getId() > 0);
		assertEquals("dog", pet.getType());
		assertEquals("Rex", pet.getName());
	}
	
	@Test
	public void testDelete() {
		Pet pet = new Pet("dog", "Rex");
		petDao.save(pet, 1);
		
		Pet deleted = petDao.delete(1);
		
		assertEquals(pet.getId(), deleted.getId());
		assertEquals(pet.getType(), deleted.getType());
		assertEquals(pet.getName(), deleted.getName());
	}
	

	@Test(expected=NullPointerException.class)
	public void testSaveNullPetType() {
		petDao.save(new Pet(null, "My pet"), 1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testSaveNullPetName() {
		petDao.save(new Pet("dog", null), 1);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetByIdNonexistinId() {
		petDao.getById(-1);
	}
}
