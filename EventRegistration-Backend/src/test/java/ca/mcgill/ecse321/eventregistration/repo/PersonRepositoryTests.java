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
	private void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testCreateAndReadPerson() {
		// Arrange
		Date today = Date.valueOf("2025-02-03");
		Person peteMikeJoe = new Person(
				"Pete Mike Joe",
				"pete.mike.joe@mail.mcgill.ca",
				"petemjdabest",
				today);
		peteMikeJoe = repo.save(peteMikeJoe);

		// Act
		Person peteMikeJoeFromDb = repo.findPersonById(peteMikeJoe.getId());

		// Assert
		assertNotNull(peteMikeJoeFromDb);
		assertEquals(peteMikeJoe.getId(), peteMikeJoeFromDb.getId());
		assertEquals(peteMikeJoe.getName(), peteMikeJoeFromDb.getName());
		assertEquals(peteMikeJoe.getEmailAddress(), peteMikeJoeFromDb.getEmailAddress());
		assertEquals(peteMikeJoe.getPassword(), peteMikeJoeFromDb.getPassword());
		assertEquals(peteMikeJoe.getCreationDate(), peteMikeJoeFromDb.getCreationDate());
	}
}
