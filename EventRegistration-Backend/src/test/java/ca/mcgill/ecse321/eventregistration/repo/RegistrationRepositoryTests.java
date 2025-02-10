package ca.mcgill.ecse321.eventregistration.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;

@SpringBootTest
public class RegistrationRepositoryTests {
	@Autowired
	private RegistrationRepository registrationRepo;
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private EventRepository eventRepo;

	@AfterEach
	private void clearDatabase() {
		registrationRepo.deleteAll();
		personRepo.deleteAll();
		eventRepo.deleteAll();
	}

	@Test
	public void testCreateAndReadRegistration() {
		// Arrange
		Date today = Date.valueOf("2025-02-03");
		Person peteMikeJoe = new Person(
				"Pete Mike Joe",
				"pete.mike.joe@mail.mcgill.ca",
				"petemjdabest",
				today);
		peteMikeJoe = personRepo.save(peteMikeJoe);

		String name = "McGill Juggling Fest";
		Date date = Date.valueOf("2024-02-09");
		Time startTime = Time.valueOf("17:25:00");
		Time endTime = Time.valueOf("23:59:59");
		int limit = 4;
		String address = "McGill";
		InPersonEvent juggling = new InPersonEvent(name, date, startTime, endTime, limit, address);
		juggling = eventRepo.save(juggling);

		Registration registration = new Registration(new Registration.Key(peteMikeJoe, juggling));
		registrationRepo.save(registration);

		// Act
		Registration registrationFromDb = registrationRepo.findRegistrationByKey(registration.getKey());

		// Assert
		assertNotNull(registrationFromDb);
		assertNotNull(registrationFromDb.getKey());
		assertNotNull(registrationFromDb.getKey().getRegistrant());
		assertEquals(registrationFromDb.getKey().getRegistrant().getId(), peteMikeJoe.getId());
		assertNotNull(registrationFromDb.getKey().getEvent());
		assertEquals(registrationFromDb.getKey().getEvent().getId(), juggling.getId());
	}
}
