package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	// GET /hello
	@GetMapping("hello/{name}")
	public String hello(@PathVariable String name, @RequestParam String myName) {
		return String.format("Hi, %s! My name is %s.", name, myName);
	}
}
