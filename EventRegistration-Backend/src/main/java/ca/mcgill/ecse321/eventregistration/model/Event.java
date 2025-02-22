package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Event {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Date date;
	private Time startTime;
	private Time endTime;
	private int registrationLimit;

	// Hibernate needs a no-args constructor, but it doesn't need to be public
	// https://docs.jboss.org/hibernate/orm/6.5/userguide/html_single/Hibernate_User_Guide.html#entity-pojo-constructor
	protected Event() {
	}

	public Event(String name, Date date, Time start, Time end, int registrationLimit) {
		this.name = name;
		this.date = date;
		this.startTime = start;
		this.endTime = end;
		this.registrationLimit = registrationLimit;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public int getRegistrationLimit() {
		return registrationLimit;
	}
}
