package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Publication
 *
 */
@Entity

public class Publication implements Serializable {

	
	private static final long serialVersionUID = 3152690779535828408L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String contenu;
	@Temporal(TemporalType.DATE)
	private Date datepub;
	private int importance;
	@ManyToOne
	@JoinColumn(name="FK_ENTREPRISE") 
	private Entreprise entreprisepub;
	
	public Entreprise getEntreprisepub() {
		return entreprisepub;
	}

	public void setEntreprisepub(Entreprise entreprisepub) {
		this.entreprisepub = entreprisepub;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDatepub() {
		return datepub;
	}

	public void setDatepub(Date datepub) {
		this.datepub = datepub;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public Publication() {
		super();
	}
   
}
