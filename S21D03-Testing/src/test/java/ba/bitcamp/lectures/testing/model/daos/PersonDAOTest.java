package ba.bitcamp.lectures.testing.model.daos;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ba.bitcamp.lectures.testing.model.daos.inmem.InMemoryPersonDAO;

@RunWith(Parameterized.class)
public class PersonDAOTest {
	
	private PersonDAO personDao;

	public PersonDAOTest(PersonDAO personDao) {
		this.personDao = personDao;
	}
	
	@Parameters
	public static Collection<PersonDAO> getPersonDAOImplementations() {
		return Arrays.asList((PersonDAO)new InMemoryPersonDAO());
	}
	
	@Before
	public void clearPersonDAO() {
		personDao.deleteAll();
	}

	@Test
	public void testCreateAndGetName() {
		int id = personDao.create("test");
		assertTrue(id > 0);
		assertEquals("test", personDao.getName(id));
	}
	
	@Test
	public void testUpdate() {
		int id = personDao.create("test");
		String old = personDao.update(id, "new name");
		assertEquals("test", old);
		assertEquals("new name", personDao.getName(id));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetByIdNonexistingId() {
		personDao.getName(-1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateNullName() {
		personDao.create(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateNullName() {
		int id = personDao.create("test");
		personDao.update(id, null);
	}
	
	@Test
	public void testFind() {
		int id1 = personDao.create("test");
		int id2 = personDao.create("foo");
		int id3 = personDao.create("some TEST");
		
		Collection<Integer> all = personDao.findByName(null);
		assertEquals(3, all.size());
		assertTrue(all.contains(id1));
		assertTrue(all.contains(id2));
		assertTrue(all.contains(id3));
		
		assertEquals(3, personDao.findByName("").size());
		
		Collection<Integer> test = personDao.findByName(".*test.*");
		assertEquals(2, test.size());
		assertTrue(test.contains(id1));
		assertTrue(test.contains(id3));
		
		assertEquals(0, personDao.findByName("bla").size());
		
	}

}
