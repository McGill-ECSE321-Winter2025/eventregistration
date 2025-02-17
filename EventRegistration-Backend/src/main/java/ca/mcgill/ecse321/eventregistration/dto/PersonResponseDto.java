package ca.mcgill.ecse321.eventregistration.dto;

import java.time.LocalDate;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {
	private int id;
	private String name;
	private String emailAddress;
	private LocalDate creationDate;

	public PersonResponseDto(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.emailAddress = person.getEmailAddress();
		this.creationDate = person.getCreationDate().toLocalDate();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}
}
