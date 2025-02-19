package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	/**
	 * Creates a new person.
	 *
	 * @return The created person
	 */
	@PostMapping("people")
	public PersonResponseDto createPerson(@RequestBody PersonCreationDto personToCreate) {
		Person createdPerson = personService.createPerson(
				personToCreate.getName(),
				personToCreate.getEmail(),
				personToCreate.getPassword());
		return new PersonResponseDto(createdPerson);
	}

	/**
	 * Find a specific user.
	 *
	 * @param id The primary key of the user.
	 * @return The user with that ID.
	 */
	@GetMapping("people/{id}")
	public PersonResponseDto findPersonById(@PathVariable int id) {
		Person p = personService.findPersonById(id);
		return new PersonResponseDto(p);
	}
}
