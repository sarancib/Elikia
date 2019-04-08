package service.intrf;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entities.Employe;
import entities.Entreprise;
import entities.Offre;
import entities.Requirements;
import entities.Role;
import entities.Sub;



@Local

public interface EntrepriseServiceLocal {
	
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

public int ajouterEmploye(Employe emp);

public Employe getEmployeParTitre(String name);

public void affecterSubAEntreprise(int entId,int SubId);

public Employe getEmploye(int id);

public Offre getOffre(int id);

public int nombreEmploye(int id);

public int removeReq(String name);

public List<Employe> findAllEmploye(int id);

public List<Offre> findAllOffre(int id);
}
