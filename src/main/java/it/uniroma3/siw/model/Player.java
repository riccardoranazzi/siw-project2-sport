package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String nome;
    
    private String cognome;
    
    private LocalDate dataNascita;
    
    private String luogoNascita;
    
    private String ruolo;
    
    private LocalDate dataInizioTesseramento;
    
    private LocalDate dataFineTesseramento;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public LocalDate getDataInizioTesseramento() {
		return dataInizioTesseramento;
	}

	public void setDataInizioTesseramento(LocalDate dataInizioTesseramento) {
		this.dataInizioTesseramento = dataInizioTesseramento;
	}

	public LocalDate getDataFineTesseramento() {
		return dataFineTesseramento;
	}

	public void setDataFineTesseramento(LocalDate dataFineTesseramento) {
		this.dataFineTesseramento = dataFineTesseramento;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, dataFineTesseramento, dataInizioTesseramento, dataNascita, id, luogoNascita, nome,
				ruolo, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(cognome, other.cognome)
				&& Objects.equals(dataFineTesseramento, other.dataFineTesseramento)
				&& Objects.equals(dataInizioTesseramento, other.dataInizioTesseramento)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(id, other.id)
				&& Objects.equals(luogoNascita, other.luogoNascita) && Objects.equals(nome, other.nome)
				&& Objects.equals(ruolo, other.ruolo) && Objects.equals(team, other.team);
	}

	public Player(Long id, String nome, String cognome, LocalDate dataNascita, String luogoNascita, String ruolo,
			LocalDate dataInizioTesseramento, LocalDate dataFineTesseramento, Team team) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.ruolo = ruolo;
		this.dataInizioTesseramento = dataInizioTesseramento;
		this.dataFineTesseramento = dataFineTesseramento;
		this.team = team;
	}

	public Player() {
		super();
	}
    

	
}
