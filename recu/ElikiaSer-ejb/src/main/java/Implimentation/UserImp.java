package Implimentation;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import Entities.Candidacy;
import Entities.Candidate;
import Entities.Contact;
import Entities.Employe;
import Entities.Entreprise;
import Entities.Langues;
import Entities.Offre;
import Entities.PreacquisCand;
import Entities.Requirements;
import Entities.Typec;
import Interfaces.UserintLocal;
import Interfaces.UserintRemote;


@Stateless
public class UserImp implements UserintLocal,UserintRemote  {

	@PersistenceContext
	EntityManager em; 
	
	public List<Candidacy> displayCand() {
		
		
	/**	List<Candidacy> emp = em.createQuery("Select c from Candidacy c ", Candidacy.class).getResultList();
				return emp;**/
		
				
				
				
				String n="NOTSEEN";
				
				Query query = em.createQuery("Select e from Candidacy e  where e.decision =:n", Candidacy.class);
				
				
				query.setParameter("n", n);
			return query.getResultList();	
		
		
		
		
	}

	
	public void AcceptCand(int idcand) {
		 Candidacy e = em.find(Candidacy.class, idcand);
		 
		 String s="Accepted";
		 
		 e.setDecision(s);
		 System.out.println("Out of updateUser : ");
		 
		
	}


	public void RefuseCand(int idcand) {
		
		em.find(Candidacy.class, idcand).getCandidat().getCandidatures().remove(em.find(Candidacy.class, idcand));
		em.find(Candidacy.class, idcand).getOffre().getCandidaturess().remove(em.find(Candidacy.class, idcand));
		em.remove(em.find(Candidacy.class, idcand)); 
		
		
}
		
		
	
	

	
	public List<Candidacy> FilterOffer(String emplois) {
		
		
		
		Query query = em.createQuery("Select e from Candidacy e  where e.offre.emplois =:emplois", Candidacy.class);
		
		
		query.setParameter("emplois", emplois);
	return query.getResultList();
		
	}

	
	
	public List<Candidacy> FilterLevel( String niveau) {
		
Query query = em.createQuery("Select e from Candidacy e  where e.offre.niveau =:niveau", Candidacy.class);
		
		
		query.setParameter("niveau", niveau);
	return query.getResultList();
		
		
	}
	
	
	
	
	
	
	public List<Candidacy>  FilterType( Typec type) {
		
		
		/**String jpql = "from PriorityAreaKeyword as pak where pak.key = :keyName";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("keyName", key); **/
		
		
		
		Query query = em.createQuery("Select e from Candidacy e  where e.type =:type", Candidacy.class);
		
		
		query.setParameter("type", type);
	return query.getResultList();	
		
	}

public void contacter() {
	
		
	}
public int AddCand( Candidate c)
{
	em.persist(c);
	return 1;
	
}

 public void affecterCOffreCandAEntreprise(int IdCand,  Candidacy Can, int Ido)

{
	 
	 Candidate e = em.find(Candidate.class, IdCand);	
	 
	 Offre o= em.find(Offre.class, Ido);
	 
	 
e.addCandd(Can);

o.addCanddd(Can);	 
	 
	
}

 
 public void mailEC(int IdCand,  Contact c, int emp)

{
	 
	 Candidate e = em.find(Candidate.class, IdCand);	
	 
	 Employe Em= em.find(Employe.class, emp);
	 
	 e.addMail(c);
	 Em.addMaill(c);
	
}
 
 
 public void affecterPC(int IdCand,  PreacquisCand p)

{
	 
	 Candidate e = em.find(Candidate.class, IdCand);	
	 
e.addPre(p);

	 
	 
	
}
 
 public void affecterlC(int IdCand,  Langues p)

 {
 	 
 	 Candidate e = em.find(Candidate.class, IdCand);	
 	 
 e.addLang(p);

 	 
 	 
 	
 }
 
 
 


	
public List<Candidacy>  DisplayCandAccepted() {
	
	
	
	
	
	
	String s="Accepted";
	
	Query query = em.createQuery("Select e from Candidacy e  where e.decision =:s", Candidacy.class);
	
	
	query.setParameter("s", s);
return query.getResultList();	
	
	

	
}



public List<Contact> DisplayMail() {
	
	
	List<Contact> emp = em.createQuery("Select c from Contact c ", Contact.class).getResultList();
	return emp;
	
}


public int ajouterEntreprise(Entreprise entreprise){
	em.persist(entreprise);
	return 0;
}





public List<PreacquisCand> displayPreCand( int Id) {
	
	
	;
				
				Query query = em.createQuery("Select e from PreacquisCand e  where e.ce.id =:Id", PreacquisCand.class);
				
				
				query.setParameter("Id", Id);
			return query.getResultList();	
		
	
		
	}


public List<Requirements> displayPreOffre( int Id) {
	
	
	;
				
				Query query = em.createQuery("Select e from  Requirements e  where e.offre.id =:Id", Requirements.class);
				
				
				query.setParameter("Id", Id);
			return query.getResultList();	
		
	
		
	}




public int nombreCand(int id){
	return ((Number)em.createQuery("SELECT COUNT(e) FROM Candidacy e where e.offre.id=:id").setParameter("id", id).getSingleResult()).intValue();
}







	

}
