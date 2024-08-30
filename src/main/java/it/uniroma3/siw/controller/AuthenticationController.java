package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.UserRegistrationDto;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;



@Controller
public class AuthenticationController {

	@Autowired CredentialsService credentialsService;

	@Autowired UserService userService;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	@ModelAttribute("username")
    public String addUserToModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && 
            !(authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser"))) {
            
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
            return credentials.getUsername();
        }
        return null; // Nessun utente autenticato
    }
	

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    	model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "formRegister";
    }
    
    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(value="error", required = false) String error) {   	     	 
        model.addAttribute("credentials", new Credentials());   
        if(error!=null) {
        	model.addAttribute("errorMessage", "Username o password errati, perfavore riprova.");
        }
        return "formLogin";
    }

    @GetMapping("/success")
    public String defaultSuccessAfterLogin(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
        model.addAttribute("username", credentials.getUsername());
        
        if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "admin/indexAdmin";
		}
        
        if(credentials.getRole().equals(Credentials.PRESIDENT_ROLE)) {
			return "president/indexPresident";
		}
		return "index";
    }
    
    @GetMapping("/logout")
    public String logout() {
    	return "index";
    }
    
    @Transactional
    @GetMapping("/index")
    public String index(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Credentials credentials = credentialsService.findByUsername(userDetails.getUsername());
            model.addAttribute("username", credentials.getUsername());
        }
    	if(authentication instanceof AnonymousAuthenticationToken) {
    		return "index";
    	} else {
    		UserDetails userdetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		Credentials credentials = credentialsService.findByUsername(userdetails.getUsername());
    		if(credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    			return "admin/indexAdmin";
    		} else if(credentials.getRole().equals(Credentials.PRESIDENT_ROLE)) {
    			return "president/indexPresident";
    		}
    	} 	
    		return "index";
    }
    
    @GetMapping("/registrationSuccessfull")
    public String registrationSuccessfull() {
    	return "registrationSuccessfull";
    	
    }
    
    @PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult, Model model) {
        User user = userRegistrationDto.getUser();
        Credentials credentials = userRegistrationDto.getCredentials();
        
        if (bindingResult.hasErrors()) { 
        	model.addAttribute("errorMessage", "Errore nella registrazione. Per favore controlla i dettagli inseriti.");      
            return "formRegister";
        }
        
        if(!credentialsService.isUsernameUnique(userRegistrationDto.getCredentials().getUsername())){
        	 model.addAttribute("errorMessage", "Username già esistente. Scegli un altro username.");
             return "formRegister";
        }
        try {
            
            credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
            credentials.setUser(user);
            credentials.setRole(Credentials.DEFAULT_ROLE);

            userService.saveUser(user);
            credentialsService.saveCredentials(credentials);

            return "redirect:/registrationSuccessfull";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Errore durante la registrazione, per favore riprova");
            return "formRegister";
        }
        
       
    }
    
}
