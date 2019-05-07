package Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Quiz
 *
 */
@Entity

public class Quiz implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Quiz() {
		super();
	}
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="quiz_ID") int id;
	
	String question;
	String reponse;
	String option1;
	String option2;
	String type;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	String level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
   
}
