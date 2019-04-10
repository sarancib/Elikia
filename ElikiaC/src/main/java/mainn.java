

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Candidacy;
import Entities.Candidate;
import Entities.Employe;
import Entities.Entreprise;

import Entities.Offre;
import Entities.Requirements;
import Entities.Role;
import Entities.Typec;
import Interfaces.UserintRemote;
import org.jboss.ejb.client.*;

public class mainn {
	
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException, NamingException {
		

		String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
		Context context = new InitialContext();
		UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
		
		
		
		
		/**
		
		Entreprise ent=new Entreprise();
		ent.setName("tt");
		ent.setDescription("lul");
		ent.setFont("font");
		ent.setLogo("logo");
		ent.setSlogan("slogan");
		ent.setStyle("style");
		ent.setEmail("mail");
		
		Employe emp1=new Employe();
		emp1.setNom("employe");
		emp1.setPrenom("prenom");
		emp1.setRole(Role.RH);
		Employe emp2=new Employe();
		emp2.setNom("employelul");
		emp2.setPrenom("prenomlul");
		emp2.setRole(Role.EMPLOYE);
		ent.addEmploye(emp1);
		ent.addEmploye(emp2);
	
		
		
		Offre off=new Offre();
		off.setDescription("ing");
		off.setEmplois("ingenieur");
		off.setNiveau("2");
		off.setType("type");
		off.setDate_pub(null);
		ent.addOffre(off);
		
		
		Offre offf=new Offre();
		offf.setDescription("sec");
		offf.setEmplois("sec");
		offf.setNiveau("2");
		offf.setType("type");
		offf.setDate_pub(null);
		ent.addOffre(offf);
		**/
		
		
		/**
		Requirements req=new Requirements();
		req.setDescription("python");
		off.addReq(req);
	 int i =	proxy.ajouterEntreprise(ent);
		
		
		**/
		
		
		

	
		Candidacy can = new Candidacy();
		can.setLettre_candidature("j");
		can.setLettre_motivation("je suis forttt");
		can.setDate_soumission(null);
		can.setMotif("Accepted");
		can.setNomc("cand_spont_lev1");
		can.setDecision("yes");
		can.setType(Typec.non_spontannée);
		
		
		
		
	Candidate c= new Candidate();
		c.setNom("sara");
		c.setAdresseMail("a");
		c.setPrenom("d");



		//c.addCandd(can);
		
		//off.addCanddd(can);
		/**proxy.AddCand(c);	**/	
	
		
	
//	proxy.affecterDepartementAEntreprise(2, can,6);
		
	/**	
		List<Candidacy>a= new ArrayList();
		
		a=proxy.displayCand();**/
	/**	for(Candidacy model : a) {
            System.out.println(model.getNomc());
        }
		
		//proxy.RefuseCand(1);
		**/
		
		
		/** List<Candidacy>b= new ArrayList();
				
				b=proxy.FilterType(Typec.spontannéé);
				for(Candidacy modell : b) {
		            System.out.println(modell.getNomc());
		        }
		
	
	**/
		
		
		/**List<Candidacy>ab= new ArrayList();	
		
		ab=proxy.FilterOffer("ingenieur");
		
		for(Candidacy modelll : ab) {
            System.out.println(modelll.getNomc());
        }
		
		**/
		
/**	List<Candidacy>abb= new ArrayList();	
		
		abb=proxy.FilterLevel("2");
	
		for(Candidacy modelll : abb) {
            System.out.println(modelll.getNomc());
        }**/
		
		
		
		
List<Candidacy>abbbb= new ArrayList();	
		
		abbbb=proxy.DisplayCandAccepted();
	
		for(Candidacy modelllll : abbbb) {
            System.out.println(modelllll.getNomc());
        }
		
		
		
		
		
		System.out.println("nekhdem ok?");	
	
	}
	
	
	

}
