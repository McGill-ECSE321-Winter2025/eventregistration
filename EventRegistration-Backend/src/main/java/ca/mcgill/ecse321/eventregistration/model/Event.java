package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

public abstract class Event {
	private int id;
	private String name;
	private Date date;
	private Time startTime;
	private Time endTime;
	private int registrationLimit;

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
