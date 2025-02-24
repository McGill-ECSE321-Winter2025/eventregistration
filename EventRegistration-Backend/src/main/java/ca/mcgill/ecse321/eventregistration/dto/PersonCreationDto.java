package ca.mcgill.ecse321.eventregistration.dto;

public class PersonCreationDto {
	private String name;
	private String email;
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
