package Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PreacquisCand
 *
 */
@Entity

public class PreacquisCand implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="Con_ID") int id;
	
	@Column	String acquis;
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAcquis() {
		return acquis;
	}



	public void setAcquis(String acquis) {
		this.acquis = acquis;
	}



	public Candidate getCe() {
		return ce;
	}



	public void setCe(Candidate ce) {
		this.ce = ce;
	}



	@ManyToOne
	@JoinColumn(name="FK_candidate_p") Candidate ce;
	
	

	public PreacquisCand() {
		super();
	}
   
}
