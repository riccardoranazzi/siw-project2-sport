package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.President;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.PresidentService;
import it.uniroma3.siw.service.TeamService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.validator.PresidentValidator;

@Controller
public class AdminController {
	
	@Autowired TeamService teamService;
	
	@Autowired PresidentService presidentService;

	@Autowired PresidentValidator presidentValidator;
	
	@Autowired UserService userService;
	
	@GetMapping("/manageTeam/{teamId}")
	public String manageTeam(@PathVariable("teamId")Long teamId, Model model) {
		Team team = teamService.findById(teamId);
		model.addAttribute("team", team);
		model.addAttribute("presidents", presidentService.findAll());
		return "admin/manageTeam";
	}
	
	@GetMapping("/manageTeams")
	public String manageTeams(Model model) {
		model.addAttribute("teams", teamService.findAll());
		return "admin/manageTeams";
	}
	
	@PostMapping("/updateTeam")
	public String updateTeam(@ModelAttribute("team")Team team, Model model) {
		teamService.updateTeam(team);
		model.addAttribute("message", "Le informazioni del team sono state aggiornate con successo.");
		return "redirect:/admin/manageTeam?teamId=" + team.getId();
	}
	
	@PostMapping("/changePresident")
	public String changePresident(Model model, @RequestParam("presidentId")Long presidentId, @RequestParam("teamId")Long teamId) {
	     Team team = teamService.findById(teamId);
	        President newPresident = presidentService.findById(presidentId);
	        
	        if (team != null && newPresident != null) {
	            team.setPresident(newPresident);
	            teamService.save(team);
	            model.addAttribute("message", "Il presidente del team è stato cambiato con successo.");
	        } else {
	            model.addAttribute("errorMessage", "Errore nel cambiare il presidente. Verificare i dati inseriti.");
	        }

	        return "redirect:/admin/manageTeam?teamId=" + teamId;
	}
	
	@GetMapping("/formNewPresident")
	public String formNewPresident(Model model) {
		model.addAttribute("president", new President());
		model.addAttribute("users", userService.findAll());
		return "/admin/formNewPresident";
	}
	
	@PostMapping("/addNewPresident")
	public String AddNewPresident(@ModelAttribute("president")President president, BindingResult bindingResult, @RequestParam("userId") Long userId) {
		presidentValidator.validate(president, bindingResult);
		presidentService.createPresident(president, userId);
		return "redirect:/admin/formNewTeam";
	}
}

