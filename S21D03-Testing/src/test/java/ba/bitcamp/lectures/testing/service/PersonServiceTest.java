package ba.bitcamp.lectures.testing.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ba.bitcamp.lectures.testing.model.Person;
import ba.bitcamp.lectures.testing.model.Pet;
import ba.bitcamp.lectures.testing.model.daos.PersonDAO;
import ba.bitcamp.lectures.testing.model.daos.PetDAO;

public class PersonServiceTest {
	
	private PersonDAO personDao;
	private PetDAO petDao;
	
	private PersonService personService;
	
	@Before
	public void initService() {
		personDao = mock(PersonDAO.class);
		petDao = mock(PetDAO.class);
		personService = new PersonService(personDao, petDao);
	}

	@Test
	public void testSaveNew() {
		when(personDao.create("test")).thenReturn(100);
		Person person = new Person(-1, "test", new Pet("dog", "Rex"), new Pet("cat", "Flyffy"));
		personService.save(person);
		verify(personDao, times(1)).create("test");
		verify(petDao, times(2)).save(any(Pet.class), eq(100));
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected=NullPointerException.class)
	public void testSaveWithNulls() {
		when(personDao.create(null)).thenThrow(NullPointerException.class);
		Person person = new Person(-1, null);
		personService.save(person);
	}

	@Test
	public void testGet() {
		when(personDao.getName(100)).thenReturn("test");
		when(petDao.getByOwnerId(100)).thenReturn(Arrays.asList(new Pet("dog", "Rex")));
		
		Person person = personService.get(100);
		assertNotNull(person);
		assertEquals(100, person.getId());
		assertFalse(person.getPets().isEmpty());
		assertEquals("dog", person.getPets().iterator().next().getType());
	}
}
