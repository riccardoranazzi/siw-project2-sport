package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.PresidentService;
import it.uniroma3.siw.service.TeamService;

//ProductController.java

@Controller
public class TeamController {
	
 @Autowired
 private TeamService productService;

 @Autowired TeamService teamService;
 
 @Autowired PresidentService presidentService;
 
 @GetMapping("/teams")
 public String showTeams(Model model) {
	model.addAttribute("teams", teamService.findAll());
	return "teams";
 }
 
 @GetMapping("/team/{teamId}")
 public String showTeam(Model model, @PathVariable("teamId")Long teamId) {
	 Team team = teamService.findById(teamId);
	 model.addAttribute("team", team);
	 return "team";
 }
 
 @GetMapping("/formNewTeam")
 public String formNewTeam(Model model) {
	   model.addAttribute("team", new Team());
	   model.addAttribute("presidents", presidentService.findAll());
 	return "admin/formNewTeam";
 }
 
 @PostMapping("/admin/newTeam")
 public String createProduct(@RequestParam("name") String name, @RequestParam("annoFondazione") int annoFondazione, @RequestParam("indirizzo") String indirizzo, @RequestParam("presidenteId") Long presidenteId, @RequestParam("image") MultipartFile imageFile) throws IOException {
     Team team = teamService.createTeam(name, annoFondazione, indirizzo, presidenteId,  imageFile);
     presidentService.setTeam(presidenteId, team);
     return "redirect:/teams";
 }
 
 @GetMapping("/admin/manageTeams")
 public String manageTeams(Model model) {
	  model.addAttribute("teams", teamService.findAll());
	  return "/admin/manageTeams";
 }
 
 

}
