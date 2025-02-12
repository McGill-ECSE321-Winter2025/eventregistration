package ca.mcgill.ecse321.eventregistration.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;

@SpringBootTest
public class InPersonEventRepositoryTests {
	@Autowired
	private InPersonEventRepository repo;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testCreateAndReadInPersonEventAsEvent() {
		String name = "McGill Juggling Fest";
		Date date = Date.valueOf("2024-02-09");
		Time startTime = Time.valueOf("17:25:00");
		Time endTime = Time.valueOf("23:59:59");
		int limit = 4;
		String address = "McGill";
		InPersonEvent juggling = new InPersonEvent(name, date, startTime, endTime, limit, address);

		juggling = repo.save(juggling);

		InPersonEvent jugglingFromDb = repo.findEventById(juggling.getId());

		assertNotNull(jugglingFromDb);
		assertEquals(name, jugglingFromDb.getName());
		assertEquals(date, jugglingFromDb.getDate());
		assertEquals(startTime, jugglingFromDb.getStartTime());
		assertEquals(endTime, jugglingFromDb.getEndTime());
		assertEquals(limit, jugglingFromDb.getRegistrationLimit());
		assertEquals(address, jugglingFromDb.getAddress());
	}
}
