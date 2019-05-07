package managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entities.Offre;
import service.impl.EntrepriseService;

@ManagedBean(name="offreBean") 
@SessionScoped
public class OffreBean implements Serializable{
private static final long serialVersionUID = 1L;
private List<Offre> items;
@EJB
EntrepriseService entrepriseService;
public List<Offre> getItems() {
	return items;
}
public void setItems(List<Offre> items) {
	this.items = items;
}
public EntrepriseService getEntrepriseService() {
	return entrepriseService;
}
public void setEntrepriseService(EntrepriseService entrepriseService) {
	this.entrepriseService = entrepriseService;
}
public OffreBean() {
	super();
}

public List<Offre> lista(){
	return entrepriseService.findAllOffre(4);
}



}
