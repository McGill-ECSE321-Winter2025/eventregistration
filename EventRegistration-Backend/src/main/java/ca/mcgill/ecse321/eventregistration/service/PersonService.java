package ca.mcgill.ecse321.eventregistration.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repo.PersonRepository;
import jakarta.validation.Valid;

@Service
@Validated
public class PersonService {
	@Autowired
	private PersonRepository personRepo;

	@Transactional
	public Person createPerson(@Valid PersonCreationDto personToCreate) {
		Date today = Date.valueOf(LocalDate.now());
		Person p = new Person(
				personToCreate.getName(),
				personToCreate.getEmail(),
				personToCreate.getPassword(),
				today);
		return personRepo.save(p);
	}

	public Person findPersonById(int id) {
		Person p = personRepo.findPersonById(id);
		if (p == null) {
			throw new EventRegistrationException(
					HttpStatus.NOT_FOUND,
					String.format("no person has ID %d", id));
		}
		return p;
	}
}
