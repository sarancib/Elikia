package Interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.Id;

import Entities.Candidacy;
import Entities.Candidate;
import Entities.Contact;
import Entities.Entreprise;
import Entities.Langues;
import Entities.PreacquisCand;
import Entities.Requirements;
import Entities.Typec;


@Local
public interface UserintLocal {
	
	
	
	public List<Candidacy> displayCand();
	public void AcceptCand(int idcand);
	public void RefuseCand(int idcand);
	public List<Candidacy>  DisplayCandAccepted();
	public List<Candidacy> FilterOffer(String emplois) ;
	public List<Candidacy>  FilterType( Typec Type);
	public void contacter();
	public  int AddCand( Candidate c);
	 public void affecterCOffreCandAEntreprise(int IdCand,  Candidacy Can, int Ido);
	 public List<Contact> DisplayMail();

	 public int ajouterEntreprise(Entreprise entreprise);
	 public List<Candidacy> FilterLevel( String niveau);
	 public void affecterPC(int IdCand,  PreacquisCand p);
	 public void mailEC(int IdCand,  Contact c, int emp);
	 public List<PreacquisCand> displayPreCand( int Id);
	 public List<Requirements> displayPreOffre( int Id);
	 public int nombreCand(int id);
	 public void affecterlC(int IdCand,  Langues p);
	
	

}
