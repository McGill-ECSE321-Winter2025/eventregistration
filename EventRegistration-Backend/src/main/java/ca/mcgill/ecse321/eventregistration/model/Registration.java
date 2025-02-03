package ca.mcgill.ecse321.eventregistration.model;

public class Registration {
	private Person registrant;
	private Event event;

	public Registration(Person registrant, Event event) {
		this.registrant = registrant;
		this.event = event;
	}

	public Person getRegistrant() {
		return registrant;
	}

	public Event getEvent() {
		return event;
	}
}
