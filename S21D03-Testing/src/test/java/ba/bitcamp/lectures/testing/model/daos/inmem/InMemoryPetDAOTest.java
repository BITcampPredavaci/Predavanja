package ba.bitcamp.lectures.testing.model.daos.inmem;

import org.junit.Before;

import ba.bitcamp.lectures.testing.model.daos.PetDAOTest;
import ba.bitcamp.lectures.testing.model.daos.inmem.InMemoryPetDAO;

public class InMemoryPetDAOTest extends PetDAOTest {

		@Before
		public void initDao() {
			petDao = new InMemoryPetDAO();
		}
}
