package service.intrf;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Employe;
import entities.Entreprise;
import entities.Offre;
import entities.Publication;
import entities.Requirements;
import entities.Role;
import entities.Sub;


@Remote

public interface EntrepriseServiceRemote {
	
	public void affecterEmployeAEntreprise(int entId,Employe emp) ;
	
	public List<Employe> getAllEmployeByEntreprise(int entrepriseId);

public int ajouterEntreprise(Entreprise entreprise);

public void deleteEntrepriseById(int entrepriseId);

public Entreprise getEmp(int id);

public void affecterOffreAEntreprise(int entId,Offre off);

public void removeEmploye(int idcand) ;

public void removeOffre(int idcand) ;

public void updateDateOffre(int id, Date date) ;

public void updateOffre(int id, String desc,String titre , String niveau) ;

public void updateEmploye(int id ,Role role);

public void updateEntreprise(int id ,String logo,String slogan,String desc,String font,String style,String email);
	
public void affecterReqAOffre(int Id,Requirements req);

public Entreprise getEmpParTitre(String titre);

public int ajouterSub(Sub sub);

public void affecterSubAEntreprise(int entId,int SubId);

public Employe getEmploye(int id);

public Offre getOffre(int id);

public int nombreEmploye(int id);

public int removeReq(String name);

public List<Employe> findAllEmploye(int id);

public List<Offre> findAllOffre(int id);

public int ajouterEmploye(Employe emp);

public Employe getEmployeParTitre(String name);
public void affecterPublicationAEntreprise(int entId,Publication pub);
public List<Publication> findAllPub(int id);
public void removePublication(int idcand);
public List<Publication> findAllPubImportant(int id);
public List<Publication> findAllPubMost(int id);

public int ajouterPub(Publication pub);
public void deletePublicationById(int PubId);
public void updatePublication(int id ,String contenu,int importance , String titre);
public void updatePublicationImpo(int id ,int importance);
}
