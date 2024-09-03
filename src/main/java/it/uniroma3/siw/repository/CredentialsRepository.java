package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;

@Repository
public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

	Credentials findByUsername(String username);

	boolean existsByUsername(String username);

	Credentials findByUser(User user);

}
