package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;

@Entity
public class InPersonEvent extends Event {
	private String address;

	// Hibernate needs a no-args constructor, but it doesn't need to be public
	// https://docs.jboss.org/hibernate/orm/6.5/userguide/html_single/Hibernate_User_Guide.html#entity-pojo-constructor
	protected InPersonEvent() {
	}

	public InPersonEvent(String name, Date date, Time start, Time end, int registrationLimit, String address) {
		super(name, date, start, end, registrationLimit);
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
}
