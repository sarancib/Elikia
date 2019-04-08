package entities;

import java.io.Serializable;
import java.util.List;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Sub
 *
 */
@Entity

public class Sub implements Serializable {

	
	private static final long serialVersionUID = 3152690779535828408L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String prenom;
	
	private String nom;	
	
	@ManyToMany(fetch = FetchType.EAGER , mappedBy="subs", cascade = CascadeType.ALL) 
	private List<Entreprise> entreprises;

	public Sub() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}
   
}
