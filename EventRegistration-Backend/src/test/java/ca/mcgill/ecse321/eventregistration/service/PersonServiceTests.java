package ca.mcgill.ecse321.eventregistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repo.PersonRepository;

@SpringBootTest
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PersonServiceTests {
	@Mock
	private PersonRepository repo;
	@InjectMocks
	private PersonService service;

	private static final String VALID_NAME = "Jim LeBron";
	private static final String VALID_EMAIL = "jim.lebron@mail.mcgill.ca";
	private static final String VALID_PASSWORD = "NotTheGoat";

	@Test
	public void testCreateValidPerson() {
		// Arrange
		PersonCreationDto dto = new PersonCreationDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);
		when(repo.save(any(Person.class)))
				.thenAnswer((InvocationOnMock iom) -> iom.getArgument(0));

		// Act
		Person createdPerson = service.createPerson(dto);

		// Assert
		assertNotNull(createdPerson);
		assertEquals(VALID_NAME, createdPerson.getName());
		assertEquals(VALID_EMAIL, createdPerson.getEmailAddress());
		assertEquals(VALID_PASSWORD, createdPerson.getPassword());
		Date today = Date.valueOf(LocalDate.now());
		assertEquals(today, createdPerson.getCreationDate());

		verify(repo, times(1)).save(any(Person.class));
	}

	@Test
	public void testFindPersonByValidId() {
		// Arrange
		Date date = Date.valueOf("2025-02-24");
		when(repo.findPersonById(42)).thenReturn(new Person(VALID_NAME, VALID_EMAIL, VALID_PASSWORD, date));

		// Act
		Person p = service.findPersonById(42);

		// Assert
		assertNotNull(p);
		assertEquals(VALID_NAME, p.getName());
		assertEquals(VALID_EMAIL, p.getEmailAddress());
		assertEquals(VALID_PASSWORD, p.getPassword());
		assertEquals(date, p.getCreationDate());
	}

	@Test
	public void testFindPersonThatDoesntExist() {
		// Arrange
		// The repo returns null by default
		// when(repo.findPersonById(42)).thenReturn(null);

		// Act + assert
		EventRegistrationException e = assertThrows(
				EventRegistrationException.class,
				() -> service.findPersonById(42));
		assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		assertEquals("no person has ID 42", e.getMessage());
		// try {
		// service.findPersonById(42);
		// fail("No exception thrown");
		// } catch (EventRegistrationException e) {
		// assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		// }
	}
}
