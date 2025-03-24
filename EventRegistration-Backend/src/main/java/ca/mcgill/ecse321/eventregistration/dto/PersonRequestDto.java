package ca.mcgill.ecse321.eventregistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PersonRequestDto {
	@NotBlank(message = "Person name is required.")
	private String name;
	@NotBlank(message = "Email address is required.")
	@Email(message = "Invalid email address.")
	private String emailAddress;
	@NotBlank(message = "Password is required.")
	private String password;

	public PersonRequestDto(String name, String emailAddress, String password) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}
}
