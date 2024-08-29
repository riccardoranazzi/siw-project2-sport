package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Player {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String name;
    
    private String surname;
    
    private LocalDate dataNascita;
    
    private String luogoNascita;
    
    private String ruolo;
    
    private LocalDate dataInizioTesseramento;
    
    private LocalDate dataFineTesseramento;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="image_id")
    private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
		return Objects.hash(surname, dataFineTesseramento, dataInizioTesseramento, dataNascita, id, luogoNascita, name,
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
		return Objects.equals(surname, other.surname)
				&& Objects.equals(dataFineTesseramento, other.dataFineTesseramento)
				&& Objects.equals(dataInizioTesseramento, other.dataInizioTesseramento)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(id, other.id)
				&& Objects.equals(luogoNascita, other.luogoNascita) && Objects.equals(name, other.name)
				&& Objects.equals(ruolo, other.ruolo) && Objects.equals(team, other.team);
	}

	public Player(Long id, String name, String surname, LocalDate dataNascita, String luogoNascita, String ruolo,
			LocalDate dataInizioTesseramento, LocalDate dataFineTesseramento, Team team, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.ruolo = ruolo;
		this.dataInizioTesseramento = dataInizioTesseramento;
		this.dataFineTesseramento = dataFineTesseramento;
		this.team = team;
		this.image = image;
	}

	public Player() {
		super();
	}
    

	
}
