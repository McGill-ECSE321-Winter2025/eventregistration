package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Registration;

public class RegistrationResponseDto {
    private int registrantId;
    private int eventId;

    public RegistrationResponseDto(Registration registration) {
        this.registrantId = registration.getKey().getRegistrant().getId();
        this.eventId = registration.getKey().getEvent().getId();
    }

    public int getRegistrantId() {
        return registrantId;
    }

    public int getEventId() {
        return eventId;
    }
}
