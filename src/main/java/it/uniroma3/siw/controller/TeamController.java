package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PlayerService;
import it.uniroma3.siw.service.PresidentService;
import it.uniroma3.siw.service.TeamService;
import it.uniroma3.siw.validator.TeamValidator;

@Controller
public class TeamController {
	
 @Autowired
 private TeamService productService;

 @Autowired TeamService teamService;
 
 @Autowired PresidentService presidentService;
 
 @Autowired CredentialsService credentialsService;
 
 @Autowired PlayerService playerService;
 
 @Autowired TeamValidator teamValidator;
 
 private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

 
 //rendo disponibile team a tutti i metodi del controller
 @Transactional
 @ModelAttribute("team")
 public Team getPresidentTeam() {
	 
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     
     if((auth == null) || !auth.isAuthenticated() || (auth instanceof AnonymousAuthenticationToken)) {
    	 return null;
     }
     
     String username = auth.getName();
     Credentials credentials = credentialsService.findByUsername(username);
	 
	 if(!credentials.getRole().equals(Credentials.PRESIDENT_ROLE) || credentials==null) {
    	 return null;
     }
     User user = credentials.getUser();
     President president = user.getPresident();
     if (president == null) {
         throw new IllegalStateException("Questo utente non é un presidente!");
     }
     Team team = president.getTeam();
     if (team == null) {
         throw new IllegalStateException("Il presidente non è associato a nessuna squadra.");
     }
     return team;
 }
 
 
 @GetMapping("/teams")
 public String showTeams(Model model) {
	model.addAttribute("teams", teamService.findAll());
	return "teams";
 }
 
 @GetMapping("/team/{teamId}")
 public String showTeam(Model model, @PathVariable("teamId")Long teamId) {
	 Team team = teamService.findById(teamId);
	 model.addAttribute("team", team);
	 model.addAttribute("players", team.getPlayers());
	 return "team";
 }
 
 @GetMapping("/formNewTeam")
 public String formNewTeam(Model model) {
	   model.addAttribute("newTeam", new Team());
	   model.addAttribute("presidents", presidentService.findAll());
 	return "admin/formNewTeam";
 }
 
 @PostMapping("/admin/newTeam")
 public String createProduct(@ModelAttribute("newTeam")Team newTeam, BindingResult bindingResult, @RequestParam("presidentId") Long presidentId, @RequestParam("image") MultipartFile imageFile, Model model) throws IOException {
	 teamValidator.validate(newTeam, bindingResult);
     teamService.createTeam(newTeam, presidentId,  imageFile);
     presidentService.setTeam(presidentId, newTeam);
     return "redirect:/teams";
     
    
 }
 
 @GetMapping("/admin/manageTeams")
 public String manageTeams(Model model) {
	  model.addAttribute("teams", teamService.findAll());
	  return "/admin/manageTeams";
 }
 
 @Transactional
 @GetMapping("/president/manageYourTeam")
 public String manageTeam(@ModelAttribute("team")Team team, Model model) {
     model.addAttribute("team", team);
     model.addAttribute("teamPlayers", team.getPlayers());
     model.addAttribute("playersWithoutTeam", playerService.findPlayersWithoutTeam());
     return "president/manageYourTeam"; 
 }
 
 @PostMapping("/removePlayerFromTeam")
 public String removePlayer(@RequestParam Long playerId, @ModelAttribute("team")Team team) {
     playerService.removePlayerFromTeam(playerId, team);
     return "redirect:/president/manageYourTeam";
 }

 @PostMapping("/addPlayerToTeam")
 public String addPlayerToTeam(@RequestParam Long playerId, @ModelAttribute("team")Team team) {
     playerService.addPlayerToTeam(playerId, team);
     return "redirect:/president/manageYourTeam";
 }

}
