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
	    
	    private President president;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name="image_id")
	    private Image image;

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

		public Team(Long id, String name, Image image) {
			super();
			this.id = id;
			this.name = name;
			this.image = image;
		}
	    
	    
}
