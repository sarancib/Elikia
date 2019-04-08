package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	@Temporal(TemporalType.DATE)
	private Date date_pub;
	
	private String niveau;
	
	@ManyToOne
	@JoinColumn(name="FK_ENTREPRISE") 
	private Entreprise entreprisef;
	
	@OneToMany(mappedBy="offre", cascade = {CascadeType.PERSIST, CascadeType. REMOVE},  fetch=FetchType.EAGER)
	private List<Requirements> reqs = new ArrayList<>();
	

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
		return entreprisef;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprisef = entreprise;
	}
	public void addReq(Requirements req){
		req.setOffre(this);
		this.reqs.add(req);
	}
   
}
