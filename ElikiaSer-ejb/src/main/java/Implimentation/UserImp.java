package Implimentation;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import Entities.Notification;
import Entities.Candidacy;
import Entities.Candidate;
import Entities.Contact;
import Entities.Employe;
import Entities.Entreprise;
import Entities.Interview;
import Entities.Langues;
import Entities.LanguesOff;
import Entities.Offre;
import Entities.PreacquisCand;
import Entities.Quiz;
import Entities.Requirements;
import Entities.ResultatQuiz;
import Entities.Role;
import Entities.Typec;
import Interfaces.UserintLocal;
import Interfaces.UserintRemote;


@Stateless
@LocalBean
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




		Query query = em.createQuery("Select e from  Requirements e  where e.offre.id =:Id", Requirements.class);


		query.setParameter("Id", Id);
		return query.getResultList();	



	}










	public int nombreCand(int id){
		return ((Number)em.createQuery("SELECT COUNT(e) FROM Candidacy e where e.offre.id=:id").setParameter("id", id).getSingleResult()).intValue();
	}





	public Employe getEmploye(int id){
		Employe employe=em.find(Employe.class, id);
		return employe;
	}

	/**public List<Langues> displayLangues( int Id) {




	Query query = em.createQuery("Select e from Langues e  where e.cee.id =:Id", Langues.class);


	query.setParameter("Id", Id);
return query.getResultList();	



}
public List<LanguesOff> displayLanguesOff( int Id) {

	Query query = em.createQuery("Select e from LanguesOff  e  where e.off.id =:Id", LanguesOff.class);


	query.setParameter("Id", Id);

return query.getResultList();	



}


	 **/
	
	
	public int AddInterv( Interview c)
	{
		em.persist(c);
		return 1;

	}
	
	
	public void PassInterv( int IdIntr)
	{
		Interview e = em.find(Interview.class, IdIntr);

		String s="Accepted";

		e.setResultat(s);;
		System.out.println("Out of updateUser : ");


	}
	
	
	public  Candidacy getCandByid ( int id)
	{
		Candidacy c=em.find(Candidacy.class, id);
		return c;


	}
	
	
	
	public List<Interview> displayInterv() {


		


		
		Query query = em.createQuery("Select e from Interview e ", Interview.class);


		
		return query.getResultList();	




	}
	
	
	
	public Employe getRH(int Id , Role role){
		
		
		Query query = em.createQuery("Select e from  Employe e  where e.entreprise.id =:Id and e.role=:role    ", Employe.class);


		query.setParameter("Id", Id);
		query.setParameter("role", role);
		
		return (Employe) query.getSingleResult();
	}
	
	
	
	

	public void AnnulerInterv(int interv) {

		
		
		em.remove(em.find(Interview.class, interv)); 


	}

	
	 public  Date   getEmptyDate(Date d) 
		{
		
		
		
		List<Interview>listInterview= new ArrayList();
		
		
		
		listInterview= displayInterv();
		
		
		

		

		Date dt=d;
		Calendar cc = Calendar.getInstance(); 
		cc.setTime(dt); 
		cc.add(Calendar.DATE, 1);
		System.out.println(dt+"date fel ejb");
		
		//cc.add(Calendar.DATE, 1);
		// = cc.getTime();
		
		//System.out.println(dt+"date ghodwa");
		
		
		
		
		int i = 0;

		//Employe emm = proxy.getRH(1,Role.RH);
		//System.out.println(emm.getPrenom());
		
		
		
		
		
		while (!(i==1))
			{i=1;
			int j=0;
		for(Interview inter : listInterview) {
	       // System.out.println(inter.getResultat());
			
			
			
			if (!(inter.getDate_fixee()== null))
			{
				//if(inter.getCan().getOffre().getEntreprise().getEmployes())
				
				//if (inter.getCan().getCandidat().getId()==4)
					
					
			//	LocalDateTime.from(dt.toInstant()).plusDays(1);
				
				
			if((dt.getDay()==inter.getDate_fixee().getDay())
			 &&  (dt.getMonth()==inter.getDate_fixee().getMonth() ) &&  (dt.getYear()==inter.getDate_fixee().getYear()))
			{	
				
				j++;
				
				i=0;
				System.out.println(j);
		
				
				//System.out.println(dt+"date jeya");
				}
			
			
				
			}
			
			
			
			
	/**		else {
				
				
				System.out.println(dt);
				
				
				
			}
			
		**/
			
		}
		
		
		
		if (j==4)
		{
		cc.add(Calendar.DATE, 1);
		
		 cc.set(Calendar.HOUR_OF_DAY,9);
		  cc.set(Calendar.MINUTE,30);
		  cc.set(Calendar.SECOND,0);
		  cc.set(Calendar.MILLISECOND,0);
		dt = cc.getTime();
		//inter.setDate_volatile(dt);
		
		
		}
		
		
		
		else if (j==3)
		{
		
			i=1;
			
		 // cc.add(Calendar.HOUR_OF_DAY, 2);
		  
		  
		  cc.set(Calendar.HOUR_OF_DAY,15);
		  cc.set(Calendar.MINUTE,30);
		  cc.set(Calendar.SECOND,0);
		  cc.set(Calendar.MILLISECOND,0);
		  dt = cc.getTime();
		  
		  
		  // adds one hour
		 // inter.setDate_volatile(dt);
		}
		
		
		else if (j==2)
		{
		
			i=1;
			
		 // cc.add(Calendar.HOUR_OF_DAY, 2);
		  
		  
		  cc.set(Calendar.HOUR_OF_DAY,14);
		  cc.set(Calendar.MINUTE,30);
		  cc.set(Calendar.SECOND,0);
		  cc.set(Calendar.MILLISECOND,0);
		  dt = cc.getTime();
		  
		  
		  
		  // adds one hour
		//  inter.setDate_volatile(dt);
		}
		
		
		
		
		
		
		
		if (j==0)
		{
		
			i=1;
			
		 // cc.add(Calendar.HOUR_OF_DAY, 2);
		  
		  
		  cc.set(Calendar.HOUR_OF_DAY,9);
		  cc.set(Calendar.MINUTE,30);
		  cc.set(Calendar.SECOND,0);
		  cc.set(Calendar.MILLISECOND,0);
		  
		  
		  dt = cc.getTime();
		  // adds one hour
		  //inter.setDate_volatile(dt);
		}
		
		else if (j==1)
		{
		
			i=1;
			
		 // cc.add(Calendar.HOUR_OF_DAY, 2); // adds one hour
			
			
			 cc.set(Calendar.HOUR_OF_DAY,10);
			  cc.set(Calendar.MINUTE,30);
			  cc.set(Calendar.SECOND,0);
			  cc.set(Calendar.MILLISECOND,0);
			  dt = cc.getTime();
		 // inter.setDate_volatile(dt);
		}
		
		//System.out.println(dt+"date jeya");
		}


			
			
	    
		
		return dt;}
	 
	 
	 
	 public  Interview getIntervByid ( int id)
		{
			Interview c=em.find(Interview.class, id);
			return c;


		}
		
	
	 public void UpdateInterv(int idinterv , Date d) {
			Interview e = em.find(Interview.class, idinterv);

			//String s="Accepted";

			e.setDate_volatile(d);
			
			System.out.println("Out of updateUser : ");


		}
	 
	 
	 public void FixeDateInterv(int idinterv , Date d) {
			Interview e = em.find(Interview.class, idinterv);

			//String s="Accepted";

			e.setDate_fixee(d);
			
			System.out.println("Out of updateUser : ");


		}
	 
	 
	 

		public Candidate AuthCand(int id, String nom){
			
			
			Query query = em.createQuery("Select e from Candidate  e  where e.id =:id and e.nom=:nom   ", Candidate.class);


			query.setParameter("id", id);
			query.setParameter("nom", nom);
			
			return (Candidate) query.getSingleResult();
		}
		
	 
	 
		public List<Interview>  ListIntervC(int id) {


			//String s="Accepted";

			Query query = em.createQuery("Select e from Interview e  where e.can.candidat.id =:id", Interview.class);


			query.setParameter("id", id);
			return query.getResultList();	




		}
		
		public List<Contact>  ListContactRH(int id) {


			//String s="Accepted";

			Query query = em.createQuery("Select t from Contact t  where t.e.id =:id", Contact.class);


			query.setParameter("id", id);
			return query.getResultList();	

		}
		
		public List<Contact>  ListContactCad(int id) {


			//String s="Accepted";

			Query query = em.createQuery("Select e from Contact e  where e.c.id =:id", Contact.class);


			query.setParameter("id", id);
			return query.getResultList();	




		}
		
		
		public int AddNotif( Notification c)
		{
			em.persist(c);
			return 1;

		}
		
		 public void UpNotif(int id ,  int rec, String msg) {
				Notification e = em.find(Notification.class, id);

				//String s="Accepted";

				e.setMsg(msg);
				e.setRec(rec);
				
				
				System.out.println("Out of updateUser : ");


			}
		 
		 public List<Notification>  ListNotifCan(int id) {


				//String s="Accepted";

				Query query = em.createQuery("Select e from Notification e  where e.i.can.candidat.id =:id and e.rec=1", Notification.class);


				query.setParameter("id", id);
				return query.getResultList();	

			}
		 
		 public List<Notification>  ListNotifRH() {


				//String s="Accepted";

				Query query = em.createQuery("Select e from Notification e  where e.rec=2", Notification.class);


				
				return query.getResultList();	

			}


		 public int AddQuesQuiz( Quiz c)
			{
				em.persist(c);
				return 1;

			}


		@Override
		public void affecterresInt(int IdInterv, ResultatQuiz p) {
			// TODO Auto-generated method stub
			
		}
	
		/**	public void affecterresInt(int IdInterv,  ResultatQuiz p)

			{

				Interview e = em.find(Interview.class, IdInterv);	

				e.addQuiz(p);




	
}**/
		





		public int AddRes( ResultatQuiz c)
		{
			em.persist(c);
			return 1;

		}


		public List<Quiz>  AvoirQuiz( ResultatQuiz rq ) {


			//String s="Accepted";
			String level = rq.getLevel();
			String type=rq.getType();
			int nbr=rq.getNbr();
			

			Query query = em.createQuery("Select  e from Quiz e  where e.level =:level and e.type=:type  ORDER BY RAND() ", Quiz.class);

			
			
			query.setParameter("level", level);
			query.setParameter("type", type);
			//query.setParameter("nbr", nbr);
			
			//query.setParameter("nbr",nbr);
			return query.getResultList();	




		}

		
		

		 public  ResultatQuiz getRQByid ( int id)
			{
				ResultatQuiz c=em.find(ResultatQuiz.class, id);
				return c;


			}
			
		
		
			public List<Quiz>  GetAllQuiz( ) {



				Query query = em.createQuery("Select  e from Quiz e   ", Quiz.class);

				return query.getResultList();	

			}
			


}
