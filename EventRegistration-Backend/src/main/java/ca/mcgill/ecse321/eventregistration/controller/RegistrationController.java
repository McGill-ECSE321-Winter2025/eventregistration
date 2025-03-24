package ca.mcgill.ecse321.eventregistration.controller;

import ca.mcgill.ecse321.eventregistration.dto.RegistrationResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.service.EventService;
import ca.mcgill.ecse321.eventregistration.service.PersonService;
import ca.mcgill.ecse321.eventregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private PersonService personService;
    @Autowired
    private EventService eventService;
    @Autowired
    private RegistrationService service;

    @PutMapping("/registrations/{pid}/{eid}")
    public RegistrationResponseDto register(@PathVariable int pid, @PathVariable int eid) {
        Person registrant = personService.findPersonById(pid);
        Event event = eventService.findEventById(eid);
        Registration reg = service.register(registrant, event);
        return new RegistrationResponseDto(reg);
    }

    @DeleteMapping("/registrations/{pid}/{eid}")
    public void unregister(@PathVariable int pid, @PathVariable int eid) {
        Person registrant = personService.findPersonById(pid);
        Event event = eventService.findEventById(eid);
        service.unregister(registrant, event);
    }

    @GetMapping("/registrations/{pid}/{eid}")
    public RegistrationResponseDto findRegistration(@PathVariable int pid, @PathVariable int eid) {
        Person registrant = personService.findPersonById(pid);
        Event event = eventService.findEventById(eid);
        Registration reg = service.findRegistration(registrant, event);
        return new RegistrationResponseDto(reg);
    }
}
