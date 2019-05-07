package Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Contact
 *
 */
@Entity

public class Contact implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="Con_ID") int id;
	
	@Column	String msg;
	
	@ManyToOne
	@JoinColumn(name="FK_contact_e") Employe e;
	
	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getMsg() {
		return msg;
	}






	public void setMsg(String msg) {
		this.msg = msg;
	}






	public Candidate getC() {
		return c;
	}






	public void setC(Candidate c) {
		this.c = c;
	}






	@ManyToOne
	@JoinColumn(name="FK_contact_c") Candidate c;
	


	public Employe getE() {
		return e;
	}






	public void setE(Employe e) {
		this.e = e;
	}






	public Contact() {
		super();
	}
   
}
