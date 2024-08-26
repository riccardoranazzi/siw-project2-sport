package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.repository.TeamRepository;

@Service
public class TeamService {
	
 @Autowired
 private TeamRepository teamRepository;
 @Autowired
 private ImageRepository imageRepository;
 
 public Team createProduct(String name, MultipartFile imageFile) throws IOException {
     Image image = new Image();
     image.setImageData(imageFile.getBytes());
     image.setType(imageFile.getContentType());
     image = imageRepository.save(image);
     Team team = new Team();
     team.setName(name);
     team.setImage(image);
     return teamRepository.save(team);
 }
 
 public List<Team> getAllTeams() {
     return teamRepository.findAll();
 }

}