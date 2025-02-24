package ca.mcgill.ecse321.eventregistration.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.eventregistration.dto.PersonCreationDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class PersonIntegrationTests {
	@Autowired
	private TestRestTemplate client;
	private int createdPersonId;

	private static final String VALID_NAME = "Jim LeBron";
	private static final String VALID_EMAIL = "jim.lebron@mail.mcgill.ca";
	private static final String VALID_PASSWORD = "NotTheGoat";

	@Test
	@Order(0)
	public void testCreateValidPerson() {
		// Arrange
		PersonCreationDto body = new PersonCreationDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);

		// Act
		ResponseEntity<PersonResponseDto> response = client.postForEntity("/people", body, PersonResponseDto.class);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().getId() > 0, "the ID should be a positive int");
		this.createdPersonId = response.getBody().getId();
		assertEquals(body.getName(), response.getBody().getName());
		assertEquals(body.getEmail(), response.getBody().getEmail());
		assertEquals(LocalDate.now(), response.getBody().getCreationDate());
	}

	@Test
	@Order(1)
	public void testFindPersonByValidId() {
		// Arrange
		String url = String.format("/people/%d", this.createdPersonId);

		// Act
		ResponseEntity<PersonResponseDto> response = client.getForEntity(url, PersonResponseDto.class);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(this.createdPersonId, response.getBody().getId());
		assertEquals(VALID_NAME, response.getBody().getName());
		assertEquals(VALID_EMAIL, response.getBody().getEmail());
		assertEquals(LocalDate.now(), response.getBody().getCreationDate());
	}
}
