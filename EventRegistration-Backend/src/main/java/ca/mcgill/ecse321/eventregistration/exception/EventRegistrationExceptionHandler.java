package ca.mcgill.ecse321.eventregistration.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class EventRegistrationExceptionHandler {
	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException e) {
		return new ResponseEntity<ErrorDto>(new ErrorDto(e.getMessage()), e.getStatus());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
			errors.add(cv.getMessage());
		}
		return new ResponseEntity<ErrorDto>(new ErrorDto(errors), HttpStatus.BAD_REQUEST);
	}
}
