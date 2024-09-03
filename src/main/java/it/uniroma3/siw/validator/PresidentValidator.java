package it.uniroma3.siw.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Player;
import it.uniroma3.siw.model.President;

@Component
public class PresidentValidator implements Validator{

	@Override
    public boolean supports(Class<?> clazz) {
        return President.class.equals(clazz);
    }
	
	@Override
	public void validate(Object target, Errors errors) {
		President president = (President) target;
		
		if(president.getCodiceFiscale().length()!=16) {
			errors.rejectValue("codiceFiscale", "size.presidentForm.codiceFiscale", "Il codice fiscale deve essere composto da 16 caratteri");
		}
		
		
	}
	
	
}
