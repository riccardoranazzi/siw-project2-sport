package it.uniroma3.siw.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Team {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    
	    private int annoFondazione;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "president_id", referencedColumnName = "id", nullable = true)
	    private President president;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name="image_id")
	    private Image image;
	    
	    private String indirizzo;
	    
	    

		public int getAnnoFondazione() {
			return annoFondazione;
		}

		public void setAnnoFondazione(int annoFondazione) {
			this.annoFondazione = annoFondazione;
		}

		public President getPresident() {
			return president;
		}

		public void setPresident(President president) {
			this.president = president;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

		public void setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
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

		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
		}

		public Team() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Team(Long id, String name, Image image, int annoFondazione, String indirizzo, President president) {
			super();
			this.id = id;
			this.name = name;
			this.image = image;
			this.annoFondazione = annoFondazione;
			this.indirizzo = indirizzo;
			this.president = president;

		}
	    
	    
}
