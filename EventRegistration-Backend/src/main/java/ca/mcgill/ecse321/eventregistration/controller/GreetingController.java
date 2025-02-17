package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	@GetMapping("/hello/{yourName}")
	public String hello(@PathVariable String yourName, @RequestParam String myName) {
		return String.format("Hi, %s! I'm %s.", yourName, myName);
	}
}
