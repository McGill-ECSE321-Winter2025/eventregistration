package ca.mcgill.ecse321.eventregistration.dto;

import java.time.LocalDate;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {
	private int id;
	private String name;
	private String email;
	private LocalDate creationDate;

	// Jackson needs a no-args constructor, but it doesn't need to be public
	@SuppressWarnings("unused")
	private PersonResponseDto() {
	}

	public PersonResponseDto(Person p) {
		this.id = p.getId();
		this.name = p.getName();
		this.email = p.getEmailAddress();
		this.creationDate = p.getCreationDate().toLocalDate();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}
}
