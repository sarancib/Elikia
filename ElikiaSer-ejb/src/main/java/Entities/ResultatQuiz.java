package Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ResultatQuiz
 *
 */
@Entity

public class ResultatQuiz implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ResultatQuiz() {
		super();
	}
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="res_ID") int id;
	
	
	
	
	
	
	String type;
	
	
	int nbr;
	
	String Level;
	
	
	
	
	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public int getNbr() {
		return nbr;
	}





	public void setNbr(int nbr) {
		this.nbr = nbr;
	}





	public String getLevel() {
		return Level;
	}





	public void setLevel(String level) {
		Level = level;
	}





	public Interview getI() {
		return i;
	}





	public void setI(Interview i) {
		this.i = i;
	}

	@ManyToOne @JoinColumn(name="FK_interview_res") Interview i;
   
}
