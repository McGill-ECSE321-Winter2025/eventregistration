package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class EventResponseDto {
    private int id;
    private EventType type;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int registrationLimit;
    private int numRegistered;

    public EventResponseDto(Event event, int numRegistered) {
        this.id = event.getId();
        this.type = EventType.ONLINE;
        this.name = event.getName();
        this.date = event.getDate().toLocalDate();
        this.startTime = event.getStartTime().toLocalTime();
        this.endTime = event.getEndTime().toLocalTime();
        this.registrationLimit = event.getRegistrationLimit();
        this.numRegistered = numRegistered;
    }

    public static EventResponseDto create(Event event, int numRegistered) {
        // Normally you'd want to have a method like toDto() on the model class so you
        // don't need to do this instanceof nonsense. But in a layered architecture, we
        // don't want a layer (in this case, the models) to depend on a higher layer (in
        // this case, the DTOs).
        if (event instanceof OnlineEvent) {
            return new OnlineEventResponseDto((OnlineEvent) event, numRegistered);
        } else if (event instanceof InPersonEvent) {
            return new InPersonEventResponseDto((InPersonEvent) event, numRegistered);
        } else {
            throw new IllegalArgumentException("Unknown event type.");
        }
    }

    public int getId() {
        return id;
    }

    public EventType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Integer getRegistrationLimit() {
        return registrationLimit;
    }

    public int getNumRegistered() {
        return numRegistered;
    }
}
