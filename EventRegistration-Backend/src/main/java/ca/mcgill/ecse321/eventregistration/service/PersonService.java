package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repo.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepo;

	public Person findPersonById(int id) {
		return personRepo.findPersonById(id);
	}

	// TODO: Which Transactional?
	@Transactional
	public Person createPerson(Person personToCreate) {
		return personRepo.save(personToCreate);
	}
}
