package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.service.PlayerService;
import it.uniroma3.siw.service.TeamService;
import it.uniroma3.siw.validator.PlayerValidator;

@Controller
public class PlayerController {
	
	@Autowired PlayerService playerService;
	
	@Autowired PlayerValidator playerValidator;
	
	@Autowired TeamService teamService;

	@GetMapping("/president/formNewPlayer")
	public String showForm(Model model) {
		model.addAttribute("player", new Player());
		return "president/formNewPlayer";
	}
	
	@GetMapping("/player/{playerId}")
	public String showPlayer(Model model, @PathVariable("playerId")Long playerId) {
		Player player = playerService.findById(playerId);
		model.addAttribute("player", player);
		return "playerDetails";
	}
	
	@PostMapping("/president/newPlayer")
	public String createNewPLayer(@ModelAttribute("player")Player player, BindingResult bindingResult, Model model, @RequestParam("image") MultipartFile image) throws IOException{
		playerValidator.validate(player, bindingResult);
		playerService.createNewPlayer(player, image);
		return "redirect:president/manageTeam";
	}
	
	

}
