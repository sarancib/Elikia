package service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Employe;
import entities.Entreprise;
import entities.Offre;
import entities.Requirements;
import entities.Role;
import entities.Sub;
import service.intrf.EntrepriseServiceRemote;
import service.intrf.EntrepriseServiceLocal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class EntrepriseService implements EntrepriseServiceLocal,EntrepriseServiceRemote {
	@PersistenceContext(unitName = "Elikia-ejb")
	EntityManager em;
	public int ajouterEntreprise(Entreprise entreprise){
		em.persist(entreprise);
		return 0;
	}
	public int ajouterSub(Sub sub){
		em.persist(sub);
		return 0;
	}
	public int ajouterEmploye(Employe emp){
		em.persist(emp);
		return 0;
	}
	public void deleteEntrepriseById(int entrepriseId) {
		em.remove(em.find(Entreprise.class,entrepriseId)); 
	}
	public Entreprise getEmp(int id){
		Entreprise entreprise=em.find(Entreprise.class, id);
		return entreprise;
	}
	public Employe getEmploye(int id){
		Employe employe=em.find(Employe.class, id);
		return employe;
	}
	public Offre getOffre(int id){
		Offre off=em.find(Offre.class, id);
		return off;
	}
	public void affecterEmployeAEntreprise(int entId,Employe emp) {
		Entreprise entreprise=em.find(Entreprise.class, entId);
		entreprise.addEmploye(emp);
	}
	public void affecterOffreAEntreprise(int entId,Offre off) {
		Entreprise entreprise=em.find(Entreprise.class, entId);
		entreprise.addOffre(off);
	}
	public void affecterSubAEntreprise(int entId,int SubId) {
		Sub sub = em.find(Sub.class, SubId); 
		Entreprise ent = em.find(Entreprise.class, entId);
		if(ent.getSubs() == null){ 
			List<Sub> subs = new ArrayList<>(); 
			subs.add(sub); 
			ent.setSubs(subs); 
			}
		else{ 
			ent.getSubs().add(sub); 
			}
	}
	public List<Employe> getAllEmployeByEntreprise(int entrepriseId) {
		List<Employe> user=em.find(Entreprise.class, entrepriseId).getEmployes();
		return user;
	}
	public void removeEmploye(int idcand) {
		em.find(Employe.class, idcand).getEntreprise().getEmployes().remove(em.find(Employe.class, idcand));
		em.remove(em.find(Employe.class, idcand)); 
	}
	public void removeOffre(int idcand) {
		em.find(Offre.class, idcand).getEntreprise().getOffres().remove(em.find(Offre.class, idcand));
		em.remove(em.find(Offre.class, idcand)); 
	}
	public void updateDateOffre(int id, Date date) {  
		Offre user = em.find(Offre.class, id);
		user.setDate_pub(date);
	}
	public void updateOffre(int id, String desc,String titre ,String niveau) {  
		Offre user = em.find(Offre.class, id);
		user.setDescription(desc);
		user.setEmplois(titre);
		user.setNiveau(niveau);
	}
	public void updateEmploye(int id ,Role role){
		Employe user = em.find(Employe.class, id);
		user.setRole(role);
	}
	public void updateEntreprise(int id ,String logo,String slogan,String desc,String font,String style,String email){
		Entreprise user = em.find(Entreprise.class, id);
		user.setDescription(desc);
		user.setFont(font);
		user.setLogo(logo);
		user.setSlogan(slogan);
		user.setStyle(style);
		user.setEmail(email);
	}
	public void affecterReqAOffre(int Id,Requirements req) {
		Offre off=em.find(Offre.class, Id);
		off.addReq(req);
	}
	public Entreprise getEmpParTitre(String name){
		Query query =em.createQuery("Select e from Entreprise e where e.name=:name",Entreprise.class);
		query.setParameter("name", name);
		return (Entreprise)query.getSingleResult();
	}
	public Employe getEmployeParTitre(String name){
		Query query =em.createQuery("Select e from Employe e where e.nom=:name",Employe.class);
		query.setParameter("name", name);
		return (Employe) query.getSingleResult();
	}
	public int nombreEmploye(int id){
		return ((Number)em.createQuery("SELECT COUNT(e) FROM Employe e where e.entreprise.id=:id").setParameter("id", id).getSingleResult()).intValue();
	}
	public int removeReq(String name){
		Query query =em.createQuery("delete from Requirements e where e.description=:name");
		query.setParameter("name", name);
		return query.executeUpdate();
	}
	public List<Employe> findAllEmploye(int id) {
		List<Employe> users = em.createQuery("select e from Employe e where e.entreprise.id=:id", Employe.class).setParameter("id", id).getResultList(); 
		return users;
	} 
	public List<Offre> findAllOffre(int id) {
		List<Offre> users = em.createQuery("select e from Offre e where e.entreprisef.id=:id", Offre.class).setParameter("id", id).getResultList(); 
		return users;
	}
	
}
