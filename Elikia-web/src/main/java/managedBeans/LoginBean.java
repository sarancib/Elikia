package managedBeans;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.Employe;
import entities.Role;
import io.gitbooks.abhirockzz.jwah.chat.WebSocketServerManager;
import service.impl.EntrepriseService;

@ManagedBean(name="loginBean") 
@SessionScoped
public class LoginBean implements Serializable {
private static final long serialVersionUID = 1L;

private String login; private int password; private Employe employe; 
private Boolean admin;
private Boolean adminetrhetpm;
private Boolean loggedIn;
@ManagedProperty(value="#{entrepriseBean}")
EntrepriseBean entrepriseBean ;

@EJB
EntrepriseService entrepriseService; 

//Getters & Setters

public String doLogin()
{
String navigateTo = "null"; 

employe = entrepriseService.getEmploye(password);
if(employe.getRole().equals(Role.ADMINISTRATEUR)){
	admin=true;
	adminetrhetpm=true;
}
else{
	admin=false;
	adminetrhetpm=false;
}
if((employe.getRole().equals(Role.CHEF_PROJET)) || employe.getRole().equals(Role.RH)){
	adminetrhetpm=true;
}else{
	adminetrhetpm=false ;
}
if (employe != null && employe.getNom().equals(login)) {
entrepriseBean.setEntreprise(employe.getEntreprise());
entrepriseBean.setNb(entrepriseService.nombreEmploye(employe.getEntreprise().getId()));
for(Employe e: entrepriseService.findAllEmploye(employe.getEntreprise().getId())){
	if(e.getRole()==Role.ADMINISTRATEUR){
		entrepriseBean.setAdmin(e);
	}
	if(e.getRole()==Role.CHEF_PROJET){
		entrepriseBean.setManager(e);;
	}
	if(e.getRole()==Role.RH){
		entrepriseBean.setRh(e);
	}
}
navigateTo = "/pages/welcome?faces-redirect=true";
loggedIn = true; 
}
else 
{
FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));}
return navigateTo; 
}
public String doLogout()
{FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
return "/login?faces-redirect=true";}
 //Gnrer un constructeur sans argument, les Getters et les Setters
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public int getPassword() {
	return password;
}
public void setPassword(int password) {
	this.password = password;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public Boolean getLoggedIn() {
	return loggedIn;
}
public void setLoggedIn(Boolean loggedIn) {
	this.loggedIn = loggedIn;
}
public LoginBean() {
	super();
}
public EntrepriseBean getEntrepriseBean() {
	return entrepriseBean;
}
public void setEntrepriseBean(EntrepriseBean entrepriseBean) {
	this.entrepriseBean = entrepriseBean;
}
public EntrepriseService getEntrepriseService() {
	return entrepriseService;
}
public void setEntrepriseService(EntrepriseService entrepriseService) {
	this.entrepriseService = entrepriseService;
}
public String nomEntree() {
	
	return entrepriseService.getEmploye(password).getNom()+" "+entrepriseService.getEmploye(password).getPrenom();
}
public void connect() throws Exception{
	new WebSocketServerManager().runServer(true);
    new CountDownLatch(1).await();
}
public Boolean getAdmin() {
	return admin;
}
public void setAdmin(Boolean admin) {
	this.admin = admin;
}
public Boolean getAdminetrhetpm() {
	return adminetrhetpm;
}
public void setAdminetrhetpm(Boolean adminetrhetpm) {
	this.adminetrhetpm = adminetrhetpm;
}


}


