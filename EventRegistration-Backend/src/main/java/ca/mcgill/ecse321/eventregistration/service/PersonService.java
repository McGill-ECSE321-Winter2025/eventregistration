package ca.mcgill.ecse321.eventregistration.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repo.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepo;

	@Transactional
	public Person createPerson(String name, String email, String password) {
		Date today = Date.valueOf(LocalDate.now());
		Person p = new Person(name, email, password, today);
		return personRepo.save(p);
	}

	public Person findPersonById(int id) {
		return personRepo.findPersonById(id);
	}
}
