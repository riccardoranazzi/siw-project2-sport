package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.PresidentRepository;

@Service
public class PresidentService {

	@Autowired PresidentRepository presidentRepository;
	
	@Autowired UserService userService;
	
	@Autowired CredentialsService credentialsService;
	
	public President findById(Long id) {
		return presidentRepository.findById(id).get();
	}
	
	public void save(President president) {
        presidentRepository.save(president);
    }

	public Iterable <President>  findAll() {	
		return presidentRepository.findAll();
	}

	
	public President findByUser(User user) {
		return presidentRepository.findByUser(user);
	}
	
	public void setTeam(Long presidenteId, Team team) {
		President president = presidentRepository.findById(presidenteId).get();
		president.setTeam(team);
		presidentRepository.save(president);
		
	}

	public President createPresident(President president, Long userId) {
		User user = userService.findById(userId);
		
		if (user == null) {
	        throw new IllegalArgumentException("User not found with ID: " + userId);
	    }
		
		Credentials credentials = credentialsService.findByUser(user);
		
		if (credentials == null) {
	        throw new IllegalArgumentException("Credentials not found for user with ID: " + userId);
	    }
		president.setName(user.getName());
		president.setSurname(user.getSurname());
		credentials.setRole("PRESIDENT_ROLE");
		credentialsService.saveCredentials(credentials);
		user.setPresident(president);
		president.setUser(user);
		presidentRepository.save(president);
		userService.saveUser(user);
		return president;
	}
}
