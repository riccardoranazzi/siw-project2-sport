package prova.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prova.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
