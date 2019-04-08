package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Entreprise implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3152690779535828408L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String logo;
	
	private String slogan;
	
	private String description;
	
	private String font;
	
	private String style;
	
	private String Email;
	
	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.PERSIST, CascadeType. REMOVE},  fetch=FetchType.EAGER)
	private List<Employe> employes = new ArrayList<>();
	
	@OneToMany(mappedBy="entreprisef", cascade = {CascadeType.PERSIST, CascadeType. REMOVE},  fetch=FetchType.EAGER)
	private List<Offre> offres = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL) 
	private List<Sub> subs;
	
	
	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public Entreprise() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}
	
	public void addEmploye(Employe emp){
		emp.setEntreprise(this);
		this.employes.add(emp);
	}
	public void addOffre(Offre off){
		off.setEntreprise(this);
		this.offres.add(off);
	}

	public List<Sub> getSubs() {
		return subs;
	}

	public void setSubs(List<Sub> subs) {
		this.subs = subs;
	}
	
}
