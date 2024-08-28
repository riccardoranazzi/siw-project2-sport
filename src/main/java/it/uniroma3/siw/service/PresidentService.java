package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.PresidentRepository;

@Service
public class PresidentService {

	@Autowired PresidentRepository presidentRepository;
	
	public President findById(Long id) {
		return presidentRepository.findById(id).get();
	}
	
	public void save(President president) {
        presidentRepository.save(president);
    }

	public Iterable <President>  findAll() {	
		return presidentRepository.findAll();
	}

	public void setTeam(Long presidenteId, Team team) {
		President president = presidentRepository.findById(presidenteId).get();
		president.getTeam();
		presidentRepository.save(president);
	}
}
