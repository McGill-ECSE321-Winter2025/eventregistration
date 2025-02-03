package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;

public class Person {
	private int id;
	private String name;
	private String emailAddress;
	private String password;
	private Date creationDate;

	public Person(String name, String emailAddress, String password, Date creationDate) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.creationDate = creationDate;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public Date getCreationDate() {
		return creationDate;
	}
}
