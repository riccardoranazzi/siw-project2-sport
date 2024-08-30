package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.User;


@Repository
public interface PresidentRepository extends CrudRepository<President, Long> {

	President findByUser(User user);

}
