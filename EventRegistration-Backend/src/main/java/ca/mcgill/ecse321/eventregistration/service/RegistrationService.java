package ca.mcgill.ecse321.eventregistration.service;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.repo.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepo;

    @Transactional
    public Registration register(Person person, Event event) {
        Registration existingRegistration = findRegistration(person, event);
        if (existingRegistration != null) {
            // Already registered
            return existingRegistration;
        } else {
            Registration newRegistration = new Registration(new Registration.Key(person, event));
            return registrationRepo.save(newRegistration);
        }
    }

    @Transactional
    public void unregister(Person person, Event event) {
        Registration existingRegistration = findRegistration(person, event);
        registrationRepo.delete(existingRegistration);
    }

    public Registration findRegistration(Person person, Event event) {
        Registration reg = registrationRepo.findRegistrationByKey(new Registration.Key(person, event));
        if (reg == null) {
            throw new EventRegistrationException(HttpStatus.NOT_FOUND,
                    String.format("Person %d is not registered for event %d.", person.getId(), event.getId()));
        }
        return reg;
    }

    public int countRegistrantsForEvent(Event event) {
        return registrationRepo.countRegistrationsByKeyEvent(event);
    }
}
