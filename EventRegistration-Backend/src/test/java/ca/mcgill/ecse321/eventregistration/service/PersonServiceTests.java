package ca.mcgill.ecse321.eventregistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
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

	private static final String VALID_NAME = "Louis";
	private static final String VALID_EMAIL = "louis@gmail.com";
	private static final String VALID_PASSWORD = "1234";
	private static final Date TODAY = Date.valueOf(LocalDate.now());

	@Test
	public void findPersonByValidId() {
		// Arrange
		Person louis = new Person(VALID_NAME, VALID_EMAIL, VALID_PASSWORD, TODAY);
		when(repo.findPersonById(42)).thenReturn(louis);

		// Act
		Person p = service.findPersonById(42);

		// Assert
		assertNotNull(p);
		assertEquals(louis.getName(), p.getName());
		assertEquals(louis.getEmailAddress(), p.getEmailAddress());
		assertEquals(louis.getPassword(), p.getPassword());
		assertEquals(louis.getCreationDate(), p.getCreationDate());
	}

	@Test
	public void findPersonThatDoesntExist() {
		// Arrange
		// when(repo.findPersonById(42)).thenReturn(null);

		// Act + assert
		EventRegistrationException e = assertThrows(
				EventRegistrationException.class,
				() -> service.findPersonById(42));
		assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		assertEquals("There is no person with ID 42", e.getMessage());
		// assertThrows basically works like this:
		// try {
		// service.findPersonById(42);
		// fail("Nothing was thrown");
		// } catch (EventRegistrationException e) {
		// assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		// assertEquals("There is no person with ID 42", e.getMessage());
		// }
	}

	@Test
	public void createValidPerson() {
		// Arrange
		when(repo.save(any(Person.class)))
				.thenAnswer((InvocationOnMock iom) -> iom.getArgument(0));

		// Act
		PersonCreationDto louis = new PersonCreationDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);
		Person p = service.createPerson(louis);

		// Assert
		assertNotNull(p);
		assertEquals(VALID_NAME, p.getName());
		assertEquals(VALID_EMAIL, p.getEmailAddress());
		assertEquals(VALID_PASSWORD, p.getPassword());
		assertEquals(TODAY, p.getCreationDate());
		// Check that the save() method was called with a correct argument
		verify(repo, times(1))
				.save(argThat((Person pp) -> VALID_NAME.equals(pp.getName())
						&& VALID_EMAIL.equals(pp.getEmailAddress())));
	}
}
