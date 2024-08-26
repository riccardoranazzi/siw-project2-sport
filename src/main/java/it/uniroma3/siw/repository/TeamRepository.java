package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
