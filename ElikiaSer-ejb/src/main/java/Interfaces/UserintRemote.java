package Interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Id;

import Entities.Candidacy;
import Entities.Candidate;
import Entities.Contact;
import Entities.Employe;
import Entities.Entreprise;
import Entities.Interview;
import Entities.Langues;
import Entities.LanguesOff;
import Entities.Notification;
import Entities.PreacquisCand;
import Entities.Quiz;
import Entities.Requirements;
import Entities.ResultatQuiz;
import Entities.Role;
import Entities.Typec;

@Remote
public interface UserintRemote {
	
	
	public List<Candidacy> displayCand();
	public void AcceptCand(int idcand);
	public void RefuseCand(int idcand);
	public List<Candidacy>  DisplayCandAccepted();
	public List<Candidacy> FilterOffer(String emplois);
	public List<Candidacy>  FilterType( Typec Type);
	public void contacter();
	public int AddCand( Candidate c);
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
	 public Employe getEmploye(int id);
	/** public List<Langues> displayLangues( int Id);
	 public List<LanguesOff> displayLanguesOff( int Id);**/
	 public int AddInterv( Interview c);
		
	 public void PassInterv( int IdIntr);
	 public  Candidacy getCandByid ( int id);
	 public List<Interview> displayInterv();
	 public Employe getRH(int Id ,Role role);
	 public void AnnulerInterv(int interv);
	 Date   getEmptyDate(Date d) ;
	 public  Interview getIntervByid ( int id);
	 public void UpdateInterv(int idinterv , Date d);
	 public void FixeDateInterv(int idinterv , Date d);
	 public Candidate AuthCand(int id, String nom);
	 public List<Interview>  ListIntervC(int id);
	 public List<Contact>  ListContactRH(int id);
	 public List<Contact>  ListContactCad(int id);
	 public int AddNotif( Notification c);
	 public void UpNotif(int id ,  int rec, String msg);
	 public List<Notification>  ListNotifCan(int id) ;
	 public List<Notification>  ListNotifRH() ;
	 public int AddQuesQuiz( Quiz c);
	 public void affecterresInt(int IdInterv,  ResultatQuiz p);
		public int AddRes( ResultatQuiz c);
		public List<Quiz>  AvoirQuiz( ResultatQuiz rq );
		 public  ResultatQuiz getRQByid ( int id);
		 public List<Quiz>  GetAllQuiz( );
	
}
