package Candidatures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.swabunga.spell.event.*;
import com.swabunga.spell.engine.*;

import Entities.Candidacy;
import Entities.Candidate;
import Entities.Contact;
import Entities.Employe;
import Entities.Entreprise;
import Entities.Langues;
import Entities.LanguesOff;
import Entities.Offre;
import Entities.PreacquisCand;
import Entities.Requirements;
import Entities.Role;
import Entities.Typec;
import Interfaces.UserintRemote;




public class mainn {
	
	
public static void main(String[] args) throws InterruptedException, NamingException, IOException {
		

		String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
		Context context = new InitialContext();
		UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
		
		
		
		
		
		
		Entreprise ent=new Entreprise();
		ent.setName("Vermegg");
		ent.setDescription("societe de services");
		ent.setFont("font");
		ent.setLogo("logo");
		ent.setSlogan("slogan");
		ent.setStyle("style");
		ent.setEmail("mail");
		
		Employe emp1=new Employe();
		emp1.setNom("ncib");
		emp1.setPrenom("sara");
		emp1.setRole(Role.RH);
		emp1.setEmail("sugarsara@email.com");
		Employe emp2=new Employe();
		emp2.setNom("dridi");
		emp2.setPrenom("dorsaf");
		emp2.setRole(Role.EMPLOYE);
		emp2.setEmail("drididorsaf@email.com");
		ent.addEmploye(emp1);
		ent.addEmploye(emp2);
	
		
		
		Offre off=new Offre();
		off.setDescription("The Industrial Engineer is an entry level position in the Industrial ");
		off.setEmplois("engineer");
		off.setNiveau("bac+5");
		off.setType("3");
		off.setDate_pub(null);
		ent.addOffre(off);
		
		
		Offre offf=new Offre();
		offf.setDescription("This is an opportunity for those wh global clientele.");
		offf.setEmplois("sales");
		offf.setNiveau("bac+3");
		offf.setType("5");
		offf.setDate_pub(null);
		ent.addOffre(offf);
		
		
		Offre offff=new Offre();
		offff.setDescription("Permanent Staff (SHRA)");
		offff.setEmplois("accountant");
		offff.setNiveau("bac+3");
		offff.setType("4");
		offff.setDate_pub(null);
		ent.addOffre(offff);
		
		Offre offff1=new Offre();
		offff1.setDescription("We are pleased that you are exploring Hyatt Hotels Corporation. ");
		offff1.setEmplois("agent");
		offff1.setNiveau("bac+3");
		offff1.setType("10");
		offff1.setDate_pub(null);
		ent.addOffre(offff1);
		
		
		
		
		
		
		
		LanguesOff lf= new LanguesOff();
		LanguesOff le= new LanguesOff();
		LanguesOff li= new LanguesOff();
		lf.setLangue("frensh");
		le.setLangue("english");
		li.setLangue("italien");
		off.addLang(lf);
		off.addLang(le);
		offf.addLang(lf);
		offf.addLang(le);
		offf.addLang(li);
		offff.addLang(lf);
		offff.addLang(le);
		offff1.addLang(lf);
		
		
		
		
		
		Requirements req=new Requirements();
		Requirements req1=new Requirements();
		Requirements req2=new Requirements();
		Requirements req3=new Requirements();
		Requirements req4=new Requirements();
		Requirements req5=new Requirements();
		
		
		
		
		
		
		req1.setDescription("python");
		req.setDescription("database");
		req2.setDescription("r");
		req3.setDescription("marcketing");
		req4.setDescription("communication");
		req5.setDescription("sage");
		
		
		
		
		
		off.addReq(req);
		off.addReq(req1);
		off.addReq(req2);
		off.addReq(req4);
		offf.addReq(req3);
		offf.addReq(req4);
		offff.addReq(req5);
		offff.addReq(req4);
		offff1.addReq(req4);
		
		
		
		
	/**	
		try 
	{int x =	proxy.ajouterEntreprise(ent);}
		catch(Exception e1)
		{
			System.out.println(e1.getMessage());
			
			
		}**/
		
		
		
		Candidacy can = new Candidacy();
		can.setLettre_candidature("I am excited to be applying for this  position at Vermeg. This specific role perfectly captures what I hoped to achieve as an engineer when starting my career journey..");
		can.setLettre_motivation("I was able to implement a new testing procedure that cut our beta testing phase by up to 12%, meaning the clients were able to see a finished prototype weeks before our competitors.");
		can.setDate_soumission(null);
		can.setMotif("NOTSEEN");
		can.setNomc("sales");
		can.setDecision("NOTSEEN");
		can.setType(Typec.spontannéé);
		
		PreacquisCand pre = new PreacquisCand();
		pre.setAcquis("sage");
	
		//pre.setAcquis("sociable");
		
		

		Langues lfc= new Langues();
		Langues lec= new Langues();
		//LanguesOff li= new LanguesOff();
		lfc.setLangue("frensh");
		//lec.setLangue("english");
		
		
		
		
	Candidate c= new Candidate();
		c.setNom("ncib ");
		c.setAdresseMail("saraz@gmail.com");
		c.setPrenom("salah");
		/**c.addLang(lfc);
		c.addLang(lec);**/
		

//proxy.affecterPC(IdCand, p);

		//c.addCandd(can);
		
		//off.addCanddd(can);
		//proxy.AddCand(c);	
	
		/**proxy.affecterPC(1, pre);
	/**	try { proxy.affecterlC(1, lfc);}
		catch (Exception h)
		{System.out.println(h.getMessage());}**/
		
	/**
	try 
	{proxy.affecterCOffreCandAEntreprise(6, can,1);}
	catch( Exception n)
	{
		System.out.println(n.getMessage());
		
		
	}**/
	//c.addCandd(can);
	
	List<Candidacy>a= new ArrayList();
		
	/**	a=proxy.displayCand();
		for(Candidacy model : a) {
            System.out.println(model.getNomc());
        }
		**/
		//proxy.RefuseCand(1);
		
		Contact msg = new Contact ();
		
		msg.setMsg("vous etes null");
		
		
		
		//proxy.mailEC(1, msg, 1);
		
		
	
		
		/**
		List<Contact>bc= new ArrayList();
		
		bc=proxy.DisplayMail();
		for(Contact modell : bc) {
            System.out.println(modell.getMsg());
        }**/
		
		
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
	
	/**	for(Candidacy modelllll : abbbb) {
            System.out.println(modelllll.getNomc());
        }**/
		
		 List<String> chaineP =new ArrayList();
		 List<String> chainePO =new ArrayList();
		
List<Requirements>r= new ArrayList();	
		
		r=proxy.displayPreOffre(1);
	
		for(Requirements mol : r) {
            System.out.println(mol.getDescription());
            chainePO.add(mol.getDescription());
            
            
        }
		
	
		
List<PreacquisCand>ro = new ArrayList();	
		
		ro=proxy.displayPreCand(1);
	
		for(PreacquisCand mol : ro) {
            System.out.println(mol.getAcquis());
            chaineP.add(mol.getAcquis());
        }
		
	
		  List<String> similar =new ArrayList(chainePO);
          List<String> different = new ArrayList();
          different.addAll(chainePO);
          different.addAll(chaineP);
          
          similar.retainAll( chaineP );
          different.removeAll( similar );
          
          
          System.out.printf("One:%s%nTwo:%s%nSimilar:%s%nDifferent:%s%n", chainePO, chaineP, similar, different);
          
         
		int i = proxy.nombreCand(1);
		 
		System.out.printf("number is "+i);
		
		
		
		
		
	/**	
		JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
		// comment in to use statistical ngram data:
		//langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
		List<RuleMatch> matches = langTool.check("A sentence with a error in the Hitchhiker's Guide tot he Galaxy");
		for (RuleMatch match : matches) {
		  System.out.println("Potential error at characters " +
		      match.getFromPos() + "-" + match.getToPos() + ": " +
		      match.getMessage());
		  System.out.println("Suggested correction(s): " +
		      match.getSuggestedReplacements());**/
	//	}
		
		
		
		
		List<String> SpecialCode = new ArrayList();
		SpecialCode.add("this");
		SpecialCode.add("is");
		List<String> lettre = new ArrayList();
		
		String myStringOfWords = "This is my string of words as an example";
		String[] words = myStringOfWords.split(" ");
		
		for(String eachWord : words) {
			//+
			System.out.println(eachWord);
			lettre.add(eachWord);
		
		}
		
		
		 List<String> found =new ArrayList(SpecialCode);
        
         
         found.retainAll( lettre );
        
		System.out.println(found+"kifkif");
		
		
		
		
	
	}
	
	
	
	
	
	
	
	
	

}
