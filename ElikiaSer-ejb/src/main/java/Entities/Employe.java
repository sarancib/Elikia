package Entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employe implements Serializable {
	
	private static final long serialVersionUID = 3152690779535828408L;
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String prenom;
	
	private String nom;	
	
	private String email;	
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addMaill(Contact c)
	{ 
		
	c.setE(this);
	this.mails.add(c);



	}

	@ManyToOne
	@JoinColumn(name="FK_ENTREPRISE") 
	private Entreprise entreprise;
	

	
	@OneToMany(mappedBy="e",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)


			private Set<Contact> mails;
	
	
	
	
	public Set<Contact> getMails() {
		return mails;
	}

	public void setMails(Set<Contact> mails) {
		this.mails = mails;
	}

	public Employe() {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	
	
}
