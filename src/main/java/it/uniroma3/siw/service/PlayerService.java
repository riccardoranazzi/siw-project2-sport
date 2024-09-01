package it.uniroma3.siw.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired PlayerRepository playerRepository;
	
	@Autowired ImageRepository imageRepository;

	public Player createNewPlayer(Player player, MultipartFile imageFile) throws IOException{
		player.setDataInizioTesseramento(LocalDate.now());
		player.setDataFineTesseramento(null);
		Image image = new Image();
		image.setImageData(imageFile.getBytes());
		image.setName(player.getName());
		image.setType(imageFile.getContentType());
		image = imageRepository.save(image);
		player.setImage(image);
		return playerRepository.save(player);
	}

	public Boolean existsByNameAndSurname(String name, String surname) {
		
		return playerRepository.existsByNameAndSurname(name, surname);
	}

	public Player findById(Long playerId) {
		return playerRepository.findById(playerId).get();
		
	}
	
	   @Transactional
	    public void addPlayerToTeam(Long playerId, Team team) {
	        Player player = playerRepository.findById(playerId)
	            .orElseThrow(() -> new IllegalArgumentException("ID giocatore non valido"));
	        player.setTeam(team);
	        team.addPlayer(player);
	        playerRepository.save(player);
	    }

	    @Transactional
	    public void removePlayerFromTeam(Long playerId, Team team) {
	        Player player = playerRepository.findById(playerId)
	            .orElseThrow(() -> new IllegalArgumentException("ID giocatore non valido"));
	        player.setTeam(null);
	        team.removePlayer(player);
	        playerRepository.save(player);
	    }

	    public List<Player> findPlayersWithoutTeam() {
	        return playerRepository.findByTeamIsNull();
	    }

}
