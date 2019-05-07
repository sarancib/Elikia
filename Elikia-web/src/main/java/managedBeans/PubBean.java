package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import entities.Publication;
import service.impl.EntrepriseService;

@ManagedBean(name="pubBean") 
@SessionScoped
public class PubBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private Publication pub;
	private String titre;
	private String desc;
	private int niv;
	@EJB
	EntrepriseService entrepriseService;
	@ManagedProperty(value="#{loginBean}")
	LoginBean loginBean ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	public Publication getPub() {
		return pub;
	}
	public void setPub(Publication pub) {
		this.pub = pub;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getNiv() {
		return niv;
	}
	public void setNiv(int niv) {
		this.niv = niv;
	}
	public EntrepriseService getEntrepriseService() {
		return entrepriseService;
	}
	public void setEntrepriseService(EntrepriseService entrepriseService) {
		this.entrepriseService = entrepriseService;
	}
	public PubBean() {
		super();
	}
	public List<Integer> niveaulist(){
		List<Integer> lista=new ArrayList<>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		return lista;
	}
	public String addpub(){
		Publication publi=new Publication();
		Date dat=new Date();
		publi.setContenu(desc);
		publi.setDatepub(dat);
		if(loginBean.getAdminetrhetpm()){
		publi.setImportance(niv);}
		else{
			publi.setImportance(3);	
		}
		publi.setTitre(titre);
		
		entrepriseService.affecterPublicationAEntreprise(entrepriseService.getEmploye(loginBean.getPassword()).getEntreprise().getId(), publi);;
		return "/pages/publications?faces-redirect=true" ; 
	}
	public void supppub(int id){
		entrepriseService.removePublication(id);
		
	}
	public String modifpub(Publication publi){
		pub=publi;
		return "/pages/modif?faces-redirect=true" ;
		
	}
	public String modifpublic(){
		entrepriseService.updatePublication(pub.getId(),desc , niv, titre);
		return "/pages/publications?faces-redirect=true" ;
		
	}
}
