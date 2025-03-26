package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.EventListDto;
import ca.mcgill.ecse321.eventregistration.dto.EventRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.EventResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.EventSummaryDto;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.service.EventService;
import ca.mcgill.ecse321.eventregistration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8090")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/events")
    public EventResponseDto createEvent(@Valid @RequestBody EventRequestDto request) {
        Event createdEvent;
        switch (request.getType()) {
            case ONLINE -> {
                createdEvent = eventService.createOnlineEvent(request.getName(), request.getDate(),
                        request.getStartTime(), request.getEndTime(), request.getRegistrationLimit(), request.getUrl());
            }
            case IN_PERSON -> {
                createdEvent = eventService.createInPersonEvent(request.getName(), request.getDate(),
                        request.getStartTime(), request.getEndTime(), request.getRegistrationLimit(),
                        request.getAddress());
            }
            default -> throw new IllegalArgumentException("Invalid event type.");
        }
        return EventResponseDto.create(createdEvent, 0);
    }

    @GetMapping("/events/{eid}")
    public EventResponseDto findEventById(@PathVariable int eid) {
        Event event = eventService.findEventById(eid);
        int numRegistered = registrationService.countRegistrantsForEvent(event);
        return EventResponseDto.create(event, numRegistered);
    }

    @GetMapping("/events")
    public EventListDto findAllEvents() {
        List<EventSummaryDto> dtos = new ArrayList<EventSummaryDto>();
        for (Event e : eventService.findAllEvents()) {
            dtos.add(new EventSummaryDto(e));
        }
        return new EventListDto(dtos);
    }
}
