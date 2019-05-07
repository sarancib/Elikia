package ManagedBeanss;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Entities.Candidate;
import Entities.Employe;
import Implimentation.UserImp;


@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {
private static final long serialVersionUID = 1L;

private String login; private int password; private Employe employe; 
private Boolean loggedIn; int Id;

@EJB
UserImp employeService; 

//Getters & Setters

public String doLogin( int id)
{
String navigateTo = "null"; 
employe = employeService.getEmploye(password);
if (employe != null && employe.getNom().equals(login))
/**if (login=="lul" && password=="lul")**/
{
	System.out.println("leeeeeeeeeee");
	Id=id;
	navigateTo = "/DisplayC?faces-redirect=true";
	loggedIn = true;
	//return navigateTo;
 
}
else 
{
FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
navigateTo = "/login1?faces-redirect=true";
}
return navigateTo;


}



public String doLoginCand( int id)
{
String navigateTo = "null"; 
Candidate w;
 w= employeService.AuthCand(password,login);
if (w != null && w.getNom().equals(login))
/**if (login=="lul" && password=="lul")**/
{
	System.out.println("leeeeeeeeeee");
	Id=id;
	navigateTo = "/IntrvCand?faces-redirect=true";
	loggedIn = true;
	//return navigateTo;
 
}
else 
{
FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
navigateTo = "/login1?faces-redirect=true";
}
return navigateTo;


}
















public int getId() {
	return Id;
}


public void setId(int id) {
	Id = id;
}


public String doLogout()
{FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
return "/login1?faces-redirect=true";}
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
public UserImp getEmployeService() {
	return employeService;
}
public void setEmployeService(UserImp employeService) {
	this.employeService = employeService;
}
public LoginBean(String login, int password, Employe employe, Boolean loggedIn, UserImp employeService) {
	super();
	this.login = login;
	this.password = password;
	this.employe = employe;
	this.loggedIn = loggedIn;
	this.employeService = employeService;
}
public LoginBean() {
	super();
}
}



