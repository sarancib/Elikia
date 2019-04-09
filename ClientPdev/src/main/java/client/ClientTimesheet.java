package client;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.hibernate.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Employe;
import entities.Entreprise;
import entities.Offre;
import entities.Requirements;
import entities.Role;
import entities.Sub;
import service.intrf.EntrepriseServiceRemote;


public class ClientTimesheet {
	 public static void main(String[] args) throws InterruptedException, NamingException, IOException {
		 
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		/*Entreprise ent=new Entreprise();
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
		emp1.setRole(Role.ADMINISTRATEUR);
		Employe emp2=new Employe();
		emp2.setNom("employelul");
		emp2.setPrenom("prenomlul");
		emp2.setRole(Role.EMPLOYE);
		ent.addEmploye(emp1);
		ent.addEmploye(emp2);
		proxy.ajouterEntreprise(ent);*/
		//proxy.deleteEntrepriseById(3);
		//proxy.affecterEmployeAEntreprise(2, emp1);
		//proxy.affecterEmployeAEntreprise(2, emp2);
		/*Entreprise ent=proxy.getEmp(4);
		System.out.println(ent.getName());
		for(Employe e : ent.getEmployes()){
			if(e.getRole().toString()=="RH"){
			System.out.println(e.getNom());
			}
		}
		Offre off=new Offre();
		off.setDescription("description2");
		off.setEmplois("emplois2");
		off.setNiveau("niveau2");
		off.setType("type2");
		off.setDate_pub(null);
		
		
		proxy.affecterOffreAEntreprise(2,off);
		
		Requirements req=new Requirements();
		req.setDescription("reqq1");
		Requirements req2=new Requirements();
		req2.setDescription("reqq2");
		proxy.affecterReqAOffre(5, req);
		proxy.affecterReqAOffre(5, req2);
		//proxy.removeOffre(4);
		/*Date date=new Date();
		proxy.updateDateOffre(3, date);*/
		//proxy.updateOffre(3, "updated desc", "updatedtitre");
		//proxy.updateEmploye(6, Role.RH);
		//proxy.updateEntreprise(2,"logoup","sloganup", "descup", "fontup", "styleup", "emailup");*/
		
		/*List<Offre> list=proxy.findAllOffre(4);
		for(int i=0 ;i < list.size();i++){
			list.remove(list.get(i+1));
			list.remove(list.get(i+1));
				
		}
		List<Employe> list1=proxy.findAllEmploye(4);
		/*for(int i=0 ;i < list1.size();i++){
			list1.remove(list1.get(i+1));
			
				
		}
		for (Employe o : list1){
			System.out.println(o.getNom());
		}
		for (Offre o : list){
			System.out.println(o.getEmplois());
		}
		/*Sub sub=new Sub();
		sub.setNom("1subpour4");
		sub.setPrenom("1subbpour4");
		proxy.ajouterSub(sub);
		Sub sub1=new Sub();
		sub1.setNom("2subpour4");
		sub1.setPrenom("2subbpour4");
		proxy.ajouterSub(sub1);
		Sub sub2=new Sub();
		sub2.setNom("3subpour4");
		sub2.setPrenom("3subbpour4");
		proxy.ajouterSub(sub2);
		proxy.affecterSubAEntreprise(4, 1);
		proxy.affecterSubAEntreprise(4, 2);
		proxy.affecterSubAEntreprise(4, 3);*/
		/*List<Sub> list=proxy.getEmp(4).getSubs();
		for(int i=0; i<list.size();i++){
			
			list.remove(list.get(i+1));
			list.remove(list.get(i+1));
		}
		for(Sub s : list ){
			System.out.println(s.getNom());
		}
		//System.out.println(proxy.nombreEmploye(4));
		Employe emp1=new Employe();
		emp1.setNom("employeeeee");
		emp1.setPrenom("prenomeeae");
		emp1.setRole(Role.ADMINISTRATEUR);
		proxy.ajouterEmploye(emp1);
		Employe emp =proxy.getEmployeParTitre("employeeeee");
		System.out.println(emp.getId());*/
		//proxy.affecterEmployeAEntreprise(4,emp);
		PDDocument document = new PDDocument();
		PDPage blankPage = new PDPage();
		document.addPage( blankPage );
		PDFont font = PDType1Font.HELVETICA_BOLD;
		PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
		contentStream.beginText();
		contentStream.setFont( font, 26 );
		contentStream.setLeading(14.5f);
		contentStream.moveTextPositionByAmount( 25, 700 );
		contentStream.showText("                                 Certificate of Employement");
		contentStream.setFont( font, 12 );
		contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("Entreprise adress");
	    contentStream.newLine();
	    contentStream.showText("");
	    Date date=new Date();
	    contentStream.newLine();
	    contentStream.showText("date: "+date);
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("Re: employee name");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("To whom it may concern");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("this letter is to verify that employeename has been employed by company ");
	    contentStream.newLine();
	    contentStream.showText("in the position of role");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("During that the main duties of name is :");
	    contentStream.newLine();
	    contentStream.showText("role");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("For any refenrence contact us on email ");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("Sincerely");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("admin name");
	    contentStream.newLine();
	    contentStream.showText("admin role");
	    contentStream.newLine();
	    contentStream.showText("company");
	    contentStream.endText();
		contentStream.close();
		document.save("C:/images/BlankPage.pdf");
		document.close();
		File file=new File("C:/images/BlankPage.pdf");
        Desktop desktop=Desktop.getDesktop();
        desktop.open(file);
				
	 }

}
