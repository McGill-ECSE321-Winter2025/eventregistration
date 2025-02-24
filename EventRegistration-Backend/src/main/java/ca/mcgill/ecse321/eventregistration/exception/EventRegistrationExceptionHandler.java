package ca.mcgill.ecse321.eventregistration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;

@ControllerAdvice
public class EventRegistrationExceptionHandler {
	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException e) {
		return new ResponseEntity<ErrorDto>(new ErrorDto(e.getMessage()), e.getStatus());
	}
}