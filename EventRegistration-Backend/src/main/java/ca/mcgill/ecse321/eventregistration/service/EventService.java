package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
import ca.mcgill.ecse321.eventregistration.repo.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepo;

    @Transactional
    public OnlineEvent createOnlineEvent(String name, LocalDate date, LocalTime startTime, LocalTime endTime,
            int registrationLimit, String url) {
        OnlineEvent event = new OnlineEvent(name, Date.valueOf(date), Time.valueOf(startTime), Time.valueOf(endTime),
                registrationLimit, url);
        return eventRepo.save(event);
    }

    @Transactional
    public InPersonEvent createInPersonEvent(String name, LocalDate date, LocalTime startTime, LocalTime endTime,
            int registrationLimit, String address) {
        InPersonEvent event = new InPersonEvent(name, Date.valueOf(date), Time.valueOf(startTime),
                Time.valueOf(endTime), registrationLimit, address);
        return eventRepo.save(event);
    }

    @Transactional
    public Event findEventById(int id) {
        Event event = eventRepo.findEventById(id);
        if (event == null) {
            throw new EventRegistrationException(HttpStatus.NOT_FOUND,
                    String.format("There is no event with ID %d.", id));
        }
        return event;
    }

    @Transactional
    public Iterable<Event> findAllEvents() {
        return eventRepo.findAll();
    }
}
