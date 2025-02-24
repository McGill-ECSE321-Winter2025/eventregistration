package ca.mcgill.ecse321.eventregistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

// Extend RuntimeException to make this an unchecked exception.
// This is more practical in this case because almost any service method could
// throw one of these and the controller should almost never catch it.
public class EventRegistrationException extends RuntimeException {
	private HttpStatus status;

	public EventRegistrationException(@NonNull HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
