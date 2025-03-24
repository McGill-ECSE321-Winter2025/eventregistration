package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

public class OnlineEventResponseDto extends EventResponseDto {
    private String url;

    public OnlineEventResponseDto(OnlineEvent event, int numRegistered) {
        super(event, numRegistered);
        this.url = event.getUrl();
    }

    public String getUrl() {
        return url;
    }
}
