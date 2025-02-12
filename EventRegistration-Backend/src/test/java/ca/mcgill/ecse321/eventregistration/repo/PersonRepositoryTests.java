package ca.mcgill.ecse321.eventregistration.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Person;

@SpringBootTest
public class PersonRepositoryTests {
	@Autowired
	private PersonRepository repo;

	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testCreateAndReadPerson() {
		// Arrange
		Date creationDate = Date.valueOf("2025-02-12");
		Person louis = new Person("Louis", "louis@mail.mcgill.ca", "1234", creationDate);
		louis = repo.save(louis);

		// Act
		Person louisFromDb = repo.findPersonById(louis.getId());

		// Assert
		assertNotNull(louisFromDb);
		assertEquals(louis.getId(), louisFromDb.getId());
		assertEquals(louis.getName(), louisFromDb.getName());
		assertEquals(louis.getEmailAddress(), louisFromDb.getEmailAddress());
		assertEquals(louis.getPassword(), louisFromDb.getPassword());
		assertEquals(louis.getCreationDate(), louisFromDb.getCreationDate());
	}
}
