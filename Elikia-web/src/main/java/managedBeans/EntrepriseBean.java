package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.Employe;
import entities.Entreprise;
import entities.Publication;
import entities.Role;
import service.impl.EntrepriseService;

@ManagedBean(name="entrepriseBean") 
@SessionScoped
public class EntrepriseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Entreprise entreprise;
	private Employe rh;
	private Employe manager;
	private Employe admin ;
	private int nb;
	
	@EJB
	EntrepriseService entrepriseService;
	public int getNb() {
		return nb;
	}
	public void setNb(int nb) {
		this.nb = nb;
	}
	public EntrepriseService getEntrepriseService() {
		return entrepriseService;
	}
	public void setEntrepriseService(EntrepriseService entrepriseService) {
		this.entrepriseService = entrepriseService;
	}
	public EntrepriseBean() {
		super();
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public Employe getRh() {
		return rh;
	}
	public void setRh(Employe rh) {
		this.rh = rh;
	}
	public Employe getManager() {
		return manager;
	}
	public void setManager(Employe manager) {
		this.manager = manager;
	}
	public Employe getAdmin() {
		return admin;
	}
	public void setAdmin(Employe admin) {
		this.admin = admin;
	}
	public List<Publication> pub1(){
		return entrepriseService.findAllPubMost(4);
	}
	public List<Publication> pub2(){
		return entrepriseService.findAllPubImportant(4);
	}
	public List<Publication> pub3(){
		return entrepriseService.findAllPub(4);
	}
	public String doPubs(){
	FacesContext.getCurrentInstance().getExternalContext() ;
	return "/pages/publications?faces-redirect=true" ; 
	}
	
}
