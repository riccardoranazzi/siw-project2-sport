package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Player;
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
	
	@Autowired PlayerService playerService;
	

	public Iterable<Team> findAll() {
		return teamRepository.findAll();
	}

	public Team findById(Long id) {	
		return this.teamRepository.findById(id).get();
	}

	public void save(Team team) {
		teamRepository.save(team);		
	}

	public Team createTeam(Team team, Long presidenteId, MultipartFile imageFile) throws IOException{
		Image image = new Image();
		image.setImageData(imageFile.getBytes());
		image.setName(team.getName());
		image.setType(imageFile.getContentType());
		image = imageRepository.save(image);
		team.setImage(image);
		President president = new President();
		president = presidentService.findById(presidenteId);
		team.setPresident(president);
		return teamRepository.save(team);
	}

	public Team findByPresident(President president) {
		return teamRepository.findByPresident(president);
	}

	public void updateTeam(Team team) {
		teamRepository.save(team);
		
	}

	public boolean existsByName(String name) {
		return teamRepository.existsByName(name);
	}

}