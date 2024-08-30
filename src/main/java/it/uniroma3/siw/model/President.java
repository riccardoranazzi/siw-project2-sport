package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class President {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	 private String name;
	 
	 private String surname;
	 
	 private String codiceFiscale;
	 
	 private LocalDate dataNascita;
	 
	 private String luogoNascita;

	 @OneToOne(mappedBy="president")
	 private Team team;
	 
	 @OneToOne
	 @JoinColumn(name = "user_id", nullable = false)
	 private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return user.getName();
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getSurname() {
		return user.getSurname();
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale, surname, dataNascita, id, luogoNascita, name, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		President other = (President) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(surname, other.surname)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(id, other.id)
				&& Objects.equals(luogoNascita, other.luogoNascita) && Objects.equals(name, other.name)
				&& Objects.equals(team, other.team);
	}

	public President(Long id, String codiceFiscale, LocalDate dataNascita,
			String luogoNascita, Team team, User user) {
		super();
		this.id = id;
		this.name = user.getName();
		this.surname = user.getSurname();
		this.codiceFiscale = codiceFiscale;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.team = team;
		this.user = user;
	}

	public President() {
		super();
		// TODO Auto-generated constructor stub
	}

}
