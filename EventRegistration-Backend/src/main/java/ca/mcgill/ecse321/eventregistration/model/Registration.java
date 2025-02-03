package ca.mcgill.ecse321.eventregistration.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {
	@EmbeddedId
	private Key key;

	public Registration(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}

	@Embeddable
	public static class Key implements Serializable {
		@ManyToOne
		private Person registrant;
		@ManyToOne
		private Event event;

		// Hibernate needs a public no-arg constructor
		public Key() {
		}

		public Key(Person registrant, Event event) {
			this.registrant = registrant;
			this.event = event;
		}

		public Person getRegistrant() {
			return registrant;
		}

		public Event getEvent() {
			return event;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Key)) {
				return false;
			}
			Key that = (Key) obj;
			return this.registrant.getId() == that.registrant.getId()
					&& this.event.getId() == that.event.getId();
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.registrant.getId(), this.event.getId());
		}
	}
}
