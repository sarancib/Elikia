package Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Candidate
 *
 */
@Entity

 @Table(name="T_CANDIDATE") 
public class Candidate implements Serializable 
{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="C_ID") int id;
@Column(name="UT_NOM") String nom; @Column(name="UT_PRENOM") String prenom; 
@Column(name="UT_ADRESS_MAIL") String adresseMail; 
public Candidate() {} 
public String getNom() {return nom;} 
public void setNom(String nom) {this.nom = nom;} 
public String getPrenom() {return prenom;}
public void setPrenom(String prenom) {this.prenom = prenom;}
public String getAdresseMail() {return adresseMail;} 
public void setAdresseMail(String adresseMail) {this.adresseMail = adresseMail;} 

@OneToMany(mappedBy="candidat",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
fetch=FetchType.EAGER)


private Set<Candidacy> candidatures;



@OneToMany(mappedBy="ce",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
fetch=FetchType.EAGER)


private Set<PreacquisCand> preacq;



@OneToMany(mappedBy="cee",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
fetch=FetchType.EAGER)


private Set<Langues> langues;
/**

private int experience;**/




/**

public int getExperience() {
	return experience;
}
public void setExperience(int experience) {
	this.experience = experience;
}**/
public Set<PreacquisCand> getPreacq() {
	return preacq;
}
public void setPreacq(Set<PreacquisCand> preacq) {
	this.preacq = preacq;
}
public Set<Contact> getMailsR() {
	return mailsR;
}
public void setMailsR(Set<Contact> mailsR) {
	this.mailsR = mailsR;
}

@OneToMany(mappedBy="c",  cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
fetch=FetchType.EAGER)


private Set<Contact> mailsR;










public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Set<Candidacy> getCandidatures() {
	return candidatures;
}
public void setCandidatures(Set<Candidacy> candidatures) {
	this.candidatures = candidatures;
}

public void addCandd(Candidacy d)
{ 
	
d.setCandidat(this);
this.candidatures.add(d);



}




public void addPre(PreacquisCand p)
{ 
	
p.setCe(this);
this.preacq.add(p);



}


public Set<Langues> getLangues() {
	return langues;
}
public void setLangues(Set<Langues> langues) {
	this.langues = langues;
}
public void addLang(Langues p)
{ 
	
p.setCe(this);
this.langues.add(p);



}


public void addMail(Contact c)
{ 
	
c.setC(this);
this.mailsR.add(c);



}













}
