package Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Notification() {
		
		
		super();
	}
	
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
      private int id;
	
	private	String msg;
	
	@OneToOne
	@JoinColumn(name="interv_col") Interview i;
	
	private int rec;

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

	public Interview getI() {
		return i;
	}

	public void setI(Interview i) {
		this.i = i;
	}

	public int getRec() {
		return rec;
	}

	public void setRec(int rec) {
		this.rec = rec;
	}
   
}
