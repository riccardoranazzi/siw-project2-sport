package it.uniroma3.siw.service;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired PlayerRepository playerRepository;
	
	@Autowired ImageRepository imageRepository;

	public Player createNewPlayer(Player player, MultipartFile imageFile) throws IOException{
		Player newPlayer = new Player();
		newPlayer.setDataInizioTesseramento(LocalDate.now());
		newPlayer.setDataFineTesseramento(null);
		newPlayer.setName(player.getName());
		newPlayer.setSurname(player.getSurname());
		newPlayer.setRuolo(player.getRuolo());
		newPlayer.setTeam(null);
		newPlayer.setLuogoNascita(player.getLuogoNascita());
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

}
