package ca.mcgill.ecse321.eventregistration.repo;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, Registration.Key> {
	Registration findRegistrationByKey(Registration.Key key);

	Iterable<Registration> findRegistrationsByRegistrantId(int id);
}
