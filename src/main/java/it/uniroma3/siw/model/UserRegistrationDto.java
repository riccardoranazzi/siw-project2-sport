package it.uniroma3.siw.model;

import jakarta.validation.Valid;

public class UserRegistrationDto {
	
    @Valid
    private User user;

    @Valid
    private Credentials credentials;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

}
