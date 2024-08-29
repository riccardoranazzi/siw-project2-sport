package it.uniroma3.siw.validator;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.repository.PlayerRepository;
import it.uniroma3.siw.service.PlayerService;

@Component
public class PlayerValidator implements Validator{
	
	@Autowired PlayerService playerService;

	@Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

	@Override
	public void validate(Object target, Errors errors) {
		Player player = (Player) target;
		
		if(playerService.existsByNameAndSurname(player.getName(), player.getSurname())) {
			errors.reject("player.duplicate", "esiste già un giocatore con questo stesso nome e cognome");
		}
		
		if(player.getDataNascita().isAfter(LocalDate.now()))
			errors.reject("player not born", "non é ancora nato?");
	}

}
