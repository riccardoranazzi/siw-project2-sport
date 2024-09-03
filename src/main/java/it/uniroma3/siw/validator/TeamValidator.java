package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.TeamRepository;
import it.uniroma3.siw.service.TeamService; // Assicurati di utilizzare il pacchetto corretto

@Component
public class TeamValidator implements Validator {

    private static final int MIN_YEAR = 1800;  
    private static final int MAX_YEAR = 2024;  
    
    @Autowired TeamService teamService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Team team = (Team) target;

        
        if (team.getAnnoFondazione() < MIN_YEAR || team.getAnnoFondazione() > MAX_YEAR) {
            errors.rejectValue("annoFondazione", "value.teamForm.annoFondazione", "Anno di fondazione non valido.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzo", "required", "L'indirizzo è obbligatorio.");
        if (team.getIndirizzo() != null && team.getIndirizzo().length() < 5) {
            errors.rejectValue("indirizzo", "size.teamForm.indirizzo", "L'indirizzo deve contenere almeno 5 caratteri.");
        }
        
        if(teamService.existsByName(team.getName())) {
        	errors.reject("team.duplicate", "Esiste già un team con questo nome, prego scegliere un altro nome per il tuo team");
        }
    }
}
