package prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.model.Team;

public interface ProductRepository extends JpaRepository<Team, Long> {

}
