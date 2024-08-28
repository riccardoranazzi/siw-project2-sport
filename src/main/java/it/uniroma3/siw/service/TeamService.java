package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.repository.PresidentRepository;
import it.uniroma3.siw.repository.TeamRepository;

@Service
public class TeamService {
	
	@Autowired TeamRepository teamRepository;
	
	@Autowired ImageRepository imageRepository;
	
	@Autowired PresidentService presidentService;
	
	@Autowired PresidentRepository presidentRepository;

	public Iterable<Team> findAll() {
		return teamRepository.findAll();
	}

	public Team findById(Long id) {	
		return this.teamRepository.findById(id).get();
	}

	public void save(Team team) {
		teamRepository.save(team);		
	}

	public Team createTeam(String name, int annoFondazione, String indirizzo, Long presidenteId,
			MultipartFile imageFile) throws IOException{
		Image image = new Image();
		image.setImageData(imageFile.getBytes());
		image.setName(name);
		image.setType(imageFile.getContentType());
		image = imageRepository.save(image);
		Team team = new Team();
		team.setAnnoFondazione(annoFondazione);
		team.setIndirizzo(indirizzo);
		team.setImage(image);
		team.setName(name);
		President president = new President();
		president = presidentService.findById(presidenteId);
		team.setPresident(president);
		return teamRepository.save(team);
		
	}

}