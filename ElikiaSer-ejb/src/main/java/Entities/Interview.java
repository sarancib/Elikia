package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Interview
 *
 */
@Entity

public class Interview implements  Serializable 

{ 
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="interview_ID") int id;
	@Temporal(TemporalType.DATE)
	 Date date_volatile;

	@Temporal(TemporalType.DATE)
	Date date_fixee;
	
	String resultat;
	
	String type;
	
	
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@OneToOne
	@JoinColumn(name="FK_CANDIDACY_ID") private Candidacy can; 
	
	 
/**
	@OneToMany(mappedBy="i") 
	private Set<ResultatQuiz> quizs;

	/**public void addQuiz(ResultatQuiz c)
	{ 
		
	c.setI(this);
	this.quizs.add(c);



	}
	

	public Set<ResultatQuiz> getQuizs() {
		return quizs;
	} 


	public void setQuizs(Set<ResultatQuiz> quizs) {
		this.quizs = quizs;
	}
**/

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate_volatile() {
		return date_volatile;
	}


	public void setDate_volatile(Date date_volatile) {
		this.date_volatile = date_volatile;
	}


	public Date getDate_fixee() {
		return date_fixee;
	}


	public void setDate_fixee(Date date_fixee) {
		this.date_fixee = date_fixee;
	}


	public String getResultat() {
		return resultat;
	}


	public void setResultat(String resultat) {
		this.resultat = resultat;
	}


	public Candidacy getCan() {
		return can;
	}


	public void setCan(Candidacy can) {
		this.can = can;
	}


	public Interview() {
		super();
	}
   
}
