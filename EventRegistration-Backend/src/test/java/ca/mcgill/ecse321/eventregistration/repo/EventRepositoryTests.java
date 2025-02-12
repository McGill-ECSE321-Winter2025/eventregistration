package ca.mcgill.ecse321.eventregistration.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

@SpringBootTest
public class EventRepositoryTests {
    @Autowired
    private EventRepository repo;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        repo.deleteAll();
    }

    @Test
    public void testCreateAndReadInPersonEvent() {
        String name = "McGill Juggling Fest";
        Date date = Date.valueOf("2024-02-09");
        Time startTime = Time.valueOf("17:25:00");
        Time endTime = Time.valueOf("23:59:59");
        int limit = 4;
        String address = "McGill";
        InPersonEvent juggling = new InPersonEvent(name, date, startTime, endTime, limit, address);

        juggling = repo.save(juggling);

        Event jugglingFromDb = repo.findEventById(juggling.getId());

        assertNotNull(jugglingFromDb);
        assertEquals(name, jugglingFromDb.getName());
        // Similar to:
        // if (name != jugglingFromDb.getName) throw new AssertionFailedError();
        assertEquals(date, jugglingFromDb.getDate());
        assertEquals(startTime, jugglingFromDb.getStartTime());
        assertEquals(endTime, jugglingFromDb.getEndTime());
        assertEquals(limit, jugglingFromDb.getRegistrationLimit());
        assertTrue(jugglingFromDb instanceof InPersonEvent, "The event should be in-person.");
        assertEquals(address, ((InPersonEvent) jugglingFromDb).getAddress());
    }

    @Test
    public void testCreateAndReadOnlineEvent() {
        String name = "Zoom Meeting";
        Date date = Date.valueOf("2024-10-04");
        Time startTime = Time.valueOf("17:25:00");
        Time endTime = Time.valueOf("23:59:59");
        int limit = 4;
        String url = "https://mcgill.zoom.us/j/01234567890";
        OnlineEvent zoomMeeting = new OnlineEvent(name, date, startTime, endTime, limit, url);

        zoomMeeting = repo.save(zoomMeeting);

        Event zoomMeetingFromDb = repo.findEventById(zoomMeeting.getId());

        assertNotNull(zoomMeetingFromDb);
        assertEquals(name, zoomMeetingFromDb.getName());
        assertEquals(date, zoomMeetingFromDb.getDate());
        assertEquals(startTime, zoomMeetingFromDb.getStartTime());
        assertEquals(endTime, zoomMeetingFromDb.getEndTime());
        assertEquals(limit, zoomMeetingFromDb.getRegistrationLimit());
        assertTrue(zoomMeetingFromDb instanceof OnlineEvent, "The event should be online.");
        assertEquals(url, ((OnlineEvent) zoomMeetingFromDb).getUrl());
    }
}
