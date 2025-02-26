package ca.mcgill.ecse321.eventregistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PersonCreationDto {
	@NotBlank(message = "name is required")
	private String name;
	@NotBlank(message = "email address is required")
	@Email
	private String email;
	@Size(min = 8, message = "password must be at least eight characters long")
	@NotBlank(message = "password is required")
	private String password;

	public PersonCreationDto(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
