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

import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

@SpringBootTest
public class OnlineEventRepositoryTests {
	@Autowired
	private OnlineEventRepository repo;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testCreateAndReadOnlineEventAsEvent() {
		String name = "Zoom Meeting";
		Date date = Date.valueOf("2024-10-04");
		Time startTime = Time.valueOf("17:25:00");
		Time endTime = Time.valueOf("23:59:59");
		int limit = 4;
		String url = "https://mcgill.zoom.us/j/01234567890";
		OnlineEvent zoomMeeting = new OnlineEvent(name, date, startTime, endTime, limit, url);

		zoomMeeting = repo.save(zoomMeeting);

		OnlineEvent zoomMeetingFromDb = repo.findEventById(zoomMeeting.getId());

		assertNotNull(zoomMeetingFromDb);
		assertEquals(name, zoomMeetingFromDb.getName());
		assertEquals(date, zoomMeetingFromDb.getDate());
		assertEquals(startTime, zoomMeetingFromDb.getStartTime());
		assertEquals(endTime, zoomMeetingFromDb.getEndTime());
		assertEquals(limit, zoomMeetingFromDb.getRegistrationLimit());
		assertEquals(url, zoomMeetingFromDb.getUrl());
	}
}
