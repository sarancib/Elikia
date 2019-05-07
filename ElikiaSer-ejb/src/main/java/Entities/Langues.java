package Entities;




import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Contact
 *
 */




@Entity
public class Langues implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String langue;
	
	@ManyToOne
	@JoinColumn(name="languesCand") Candidate cee;
	/**@ManyToOne
	@JoinColumn(name="F_OFFREe") 
	private Offre off;
	
	

	public Offre getOff() {
		return off;
	}

	public void setOffre(Offre offre) {
		this.off = offre;
	}**/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public Candidate getCe() {
		return cee;
	}

	public void setCe(Candidate ce) {
		this.cee = ce;
	}
	
	
	
	

}
