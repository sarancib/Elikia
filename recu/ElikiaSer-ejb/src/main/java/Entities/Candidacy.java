package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Candidacy
 *
 */
@Entity

@Table(name="T_CANDIDACY") 
public class Candidacy implements Serializable 

{ 
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="CANDIDACY_ID") int id;
@Column(name="CANDIDACY_NOM") String nomc; 
@Enumerated(EnumType.STRING)
@Column(name="CANDIDACY_TYPE") Typec type; 

@Column(name="CANDIDACY_lettremotivation") String lettre_motivation; 
@Column(name="CANDIDACY_lettrecandidature") String lettre_candidature;
@Column(name="CANDIDACY_decision") String decision;
@Column(name="CANDIDACY_motif") String motif;
@Temporal(TemporalType.DATE)
@Column(name="CANDIDACY_dayee") Date date_soumission;

@ManyToOne
@JoinColumn(name="FK_candidacy_candidate") Candidate candidat; 

@ManyToOne
@JoinColumn(name="FK_candidacy_offet") Offre offre; 


public Offre getOffre() {
	return offre;
}

public void setOffre(Offre offre) {
	this.offre = offre;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNomc() {
	return nomc;
}

public void setNomc(String nomc) {
	this.nomc = nomc;
}

public Typec getType() {
	return type;
}

public void setType(Typec type) {
	this.type = type;
}

public String getLettre_motivation() {
	return lettre_motivation;
}

public void setLettre_motivation(String lettre_motivation) {
	this.lettre_motivation = lettre_motivation;
}

public String getLettre_candidature() {
	return lettre_candidature;
}

public void setLettre_candidature(String lettre_candidature) {
	this.lettre_candidature = lettre_candidature;
}

public Candidate getCandidat() {
	return candidat;
}

public void setCandidat(Candidate candidat) {
	this.candidat = candidat;
}



public Candidacy() {
	super();
}

public String getDecision() {
	return decision;
}

public void setDecision(String decision) {
	this.decision = decision;
}

public String getMotif() {
	return motif;
}

public void setMotif(String motif) {
	this.motif = motif;
}

public Date getDate_soumission() {
	return date_soumission;
}

public void setDate_soumission(Date date_soumission) {
	this.date_soumission = date_soumission;
}




 }

