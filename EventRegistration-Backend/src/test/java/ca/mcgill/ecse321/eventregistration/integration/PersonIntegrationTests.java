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

	private static final String VALID_NAME = "Louis";
	private static final String VALID_EMAIL = "louis@gmail.com";
	private static final String VALID_PASSWORD = "1234";
	private static final LocalDate TODAY = LocalDate.now();

	private int createdPersonId;

	@Test
	@Order(0)
	public void createValidPerson() {
		// Arrange
		PersonCreationDto body = new PersonCreationDto(VALID_NAME, VALID_EMAIL, VALID_PASSWORD);

		// Act
		ResponseEntity<PersonResponseDto> response = client.postForEntity(
				"/people", body, PersonResponseDto.class);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		PersonResponseDto responseBody = response.getBody();
		assertNotNull(responseBody);
		assertEquals(VALID_NAME, responseBody.getName());
		assertEquals(VALID_EMAIL, responseBody.getEmail());
		assertEquals(TODAY, responseBody.getCreationDate());
		assertTrue(responseBody.getId() > 0, "the ID should be greater than zero");
		this.createdPersonId = responseBody.getId();
	}

	@Test
	@Order(1)
	public void findPersonByValidId() {
		// Arrange
		String url = String.format("/people/%d", this.createdPersonId);

		// Act
		ResponseEntity<PersonResponseDto> response = client.getForEntity(url, PersonResponseDto.class);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		PersonResponseDto responseBody = response.getBody();
		assertNotNull(responseBody);
		assertEquals(VALID_NAME, responseBody.getName());
		assertEquals(VALID_EMAIL, responseBody.getEmail());
		assertEquals(TODAY, responseBody.getCreationDate());
		assertEquals(this.createdPersonId, responseBody.getId());
	}
}
