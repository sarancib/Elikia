package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Offre
 *
 */
@Entity

public class Offre implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	private String emplois;	
	
	private String type;
	private int nbrplaces;
	
	@Temporal(TemporalType.DATE)
	private Date date_pub;
	@Temporal(TemporalType.DATE)
	private Date deadline;
	
	private String niveau;
	private int experience;
	
	public int getNbrplaces() {
		return nbrplaces;
	}



	public void setNbrplaces(int nbrplaces) {
		this.nbrplaces = nbrplaces;
	}



	public Date getDeadline() {
		return deadline;
	}



	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}



	public int getExperience() {
		return experience;
	}



	public void setExperience(int experience) {
		this.experience = experience;
	}


/**
	public List<Langues> getLangues() {
		return langues;
	}



	public void setLangues(List<Langues> langues) {
		this.langues = langues;
	}
**/

	@ManyToOne
	@JoinColumn(name="FK_ENTREPRISE") 
	private Entreprise entreprise;
	
	@OneToMany(mappedBy="offre", cascade = {CascadeType.PERSIST, CascadeType. REMOVE},  fetch=FetchType.EAGER)
	private List<Requirements> reqs = new ArrayList<>();
	
	@OneToMany(mappedBy="off", cascade = {CascadeType.PERSIST, CascadeType. REMOVE},  fetch=FetchType.EAGER)
	private List<LanguesOff> languesOf = new ArrayList<>();
	
	/**@OneToMany(mappedBy="o", cascade = {CascadeType.PERSIST, CascadeType. REMOVE},  fetch=FetchType.EAGER)
	private List<SpecialCode> SpecialCode = new ArrayList<>();
	
	**/
	
	@OneToMany(mappedBy="offre",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
	
	private Set<Candidacy> candidaturess;
	
	
	


	public void addCanddd(Candidacy d)
	{ 
		
	d.setOffre(this);
	this.candidaturess.add(d);



	}
	
	

	public Set<Candidacy> getCandidaturess() {
		return candidaturess;
	}

	public void setCandidaturess(Set<Candidacy> candidaturess) {
		this.candidaturess = candidaturess;
	}

	public List<Requirements> getReqs() {
		return reqs;
	}

	public void setReqs(List<Requirements> reqs) {
		this.reqs = reqs;
	}

	public Offre() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmplois() {
		return emplois;
	}

	public void setEmplois(String emplois) {
		this.emplois = emplois;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate_pub() {
		return date_pub;
	}

	public void setDate_pub(Date date_pub) {
		this.date_pub = date_pub;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public void addReq(Requirements req){
		req.setOffre(this);
		this.reqs.add(req);
	}
	
	
public void addLang(LanguesOff l){
		l.setOffre(this);
		this.languesOf.add(l);
	}



public List<LanguesOff> getLanguesOf() {
	return languesOf;
}



public void setLanguesOf(List<LanguesOff> languesOf) {
	this.languesOf = languesOf;
}

   /**
    *  void addCode(SpecialCode k){
		k.setO(this);
		this.SpecialCode.add(k);
   
}**/
}