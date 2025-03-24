package ca.mcgill.ecse321.eventregistration.dto;

import java.util.List;

public class EventListDto {
    private List<EventSummaryDto> events;

    public EventListDto(List<EventSummaryDto> events) {
        this.events = events;
    }

    public List<EventSummaryDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventSummaryDto> events) {
        this.events = events;
    }
}
