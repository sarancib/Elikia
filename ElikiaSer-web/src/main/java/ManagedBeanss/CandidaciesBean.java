package ManagedBeanss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import Entities.Candidacy;
import Entities.Contact;
import Entities.Interview;
import Entities.Notification;
import Entities.Quiz;
import Entities.ResultatQuiz;
import Entities.Role;
import Implimentation.UserImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

@ManagedBean(name = "CandidaciesBean") 
@SessionScoped
public class CandidaciesBean implements Serializable {

private static final long serialVersionUID = 1L;

private String login; private String password;  private String email; 
private Boolean isActif;  private Candidacy t; Date varr; String typeintv;String nivv;int nbrr; String Que; String rep; String op1; String op2;String typQ;String LevelQuiz; String repChoise; String Quesq;List<Quiz> repQ;int note;
@ManagedProperty(value="#{loginBean}")
LoginBean loginBean ;
@EJB
UserImp employeService; 


public CandidaciesBean() {
	super();
}

public UserImp getEmployeService() {
	return employeService;
}

public void setEmployeService(UserImp employeService) {
	this.employeService = employeService;
}

public List<Candidacy> DisplayCandA() {

	List<Candidacy> l= new ArrayList();
	
	l=employeService.DisplayCandAccepted();
	return l;} 




public List<Interview> DisplayInterC() {

	List<Interview> l= new ArrayList();
	
	l=employeService.ListIntervC(loginBean.getPassword());
	return l;} 


public List<Interview> DisplayInterFixed() {

	List<Interview> l= new ArrayList();
	List<Interview> l1= new ArrayList();
	
	l=employeService.displayInterv();
	
	for( Interview ft: l)
		
	{
		if (!(ft.getDate_fixee()== null))
			
		{    l1.add(ft) ;  }
		
		
	}
	
	
	
	
	
	return l;} 










public void showMessage() {
RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity."));
}





 public String red( Candidacy c)
 {
	 
	 
	 String navigateToo;
	 t=c;
		navigateToo = "/SetupDa?faces-redirect=true";
		return navigateToo;
	 
	 
 }
 
 public String redR( )
 {
	 
	 
	 String navigateToo;
	
		navigateToo = "/Resultat?faces-redirect=true";
		
		return navigateToo;
	 
	 
 }
 

 public String redi( )
 {
	 
	 
	 String navigateToo;
	
		navigateToo = "/IntervRH?faces-redirect=true";
		return navigateToo;
	 
	 
 }
 
 public String redq( )
 {
	 
	 
	 String navigateToo;
	
		navigateToo = "/AddQuiz?faces-redirect=true";
		return navigateToo;
	 
	 
 }
 
 
 public void assignQuiz(int f,String level, int nbr)
 {
	 Interview d= new Interview();
	 d=employeService.getIntervByid(f);
	 ResultatQuiz rq= new ResultatQuiz();
	 
	 rq.setI(d);
	 rq.setLevel(nivv);
	 rq.setType(d.getType());
	 rq.setNbr(nbrr);
	 employeService.AddRes(rq);
	 
	 
	
	 
 }
 
 
 public String GetQuiz()
 {
	 
	 String navigateToo;
		
		navigateToo = "/DisplayQuiz?faces-redirect=true";
		return navigateToo; 
	 
 }
 
 public List<Quiz> DisplayQuiz()
 {
	 
	 List<Quiz> b= new ArrayList();
	 ResultatQuiz sa = new ResultatQuiz();
	 sa=employeService.getRQByid(16);
	 
	b= employeService.AvoirQuiz(sa);
	return b;
	 
	 
	 
	 
	
	 
 }
 
 
 
 
 
 /**
 public List<Contact> DispalyNotifCand()
 
 {
	 List<Contact> l;
	
	l= employeService.ListContactCad(1);
	 return l;
	 
 }
 **/
 
public List<Notification> DispalyNotifCandd()
 
 {
	 List<Notification> l;
	
	l= employeService.ListNotifCan(1);
	 return l;
	 
 }


public List<Notification> DispalyNotifRH()

{
	 List<Notification> l;
	
	l= employeService.ListNotifRH();
	 return l;
	 
}






public List<Contact> DispalyNotifCand()

{
	 List<Contact> l;
	
	l= employeService.ListContactCad(1);
	 return l;
	 
}
 
 
 
 
 
 
 
 public Candidacy getT() {
	return t;
}

public void setT(Candidacy t) {
	this.t = t;
}

public void AcceptDateRH()
 {
	List<Interview> lm= new ArrayList();
	lm= employeService.displayInterv();

	 
	
	 Interview iv = new Interview();
	/** iv= employeService.getIntervByid(27);**/
	 int  emp;
	 int cand;
	 for ( Interview goint : lm)
	 {
	 if (goint.getCan().getId()==t.getId())
	 {
	iv=goint;
	
	
			 
			 Date dmsg = new Date();
	 dmsg=iv.getDate_volatile();
	 
	 Contact rhcon = new Contact();
	// rhcon.setMsg("ça vous convent le "+dmsg);
	 Notification rhconn = new Notification();
	 rhcon.setMsg("ça vous convent le "+dmsg);
	 rhconn.setMsg("ça vous convent le "+dmsg);
	 rhconn.setI(iv);
	 rhconn.setRec(1);
		
	/** emp= employeService.getRH(, Role.RH);
	 cand
	 **/
	 cand=iv.getCan().getCandidat().getId();
			 
			emp=loginBean.getPassword();
			
	 
	 employeService.mailEC(cand,  rhcon, emp);
	 employeService.AddNotif(rhconn);
	 varr=dmsg;
	 }
	
	 
	 }
	 
	 
 }
 
 public void RefuseDateRH()
 {
	 
	 
	varr= Setup();
	 
 }
 
 
 public void GetD()
 {
	 
	 
	varr= Setup();
	 
 }
 
 
 public void Addquiz()
 {
	 
	 Quiz w= new Quiz();
	 w.setLevel(LevelQuiz);
	 w.setQuestion(Que);
	 w.setReponse(rep);
	 w.setType(typQ);
	 w.setOption1(op1);
	 w.setOption2(op2);
	 employeService.AddQuesQuiz(w);
	
	 
 }
 
 
 
 
 
 
 
 public void annulerDteRH()
 
 
 {
	
	 employeService.AnnulerInterv(22);
	
	 
	 
	 
 }
 
 public void AcceptDateCand( Interview intv)
 {
	         
	 Interview iv = new Interview();
	 
	 iv= intv;
	 
	
	 
	// iv= employeService.getIntervByid(i);
	
	 //iv.setDate_fixee(iv.getDate_volatile());
	int  id=iv.getId();
	
	System.out.println("haw l id li tjareb alih ");
	 
	 employeService.FixeDateInterv(id,iv.getDate_volatile() );
	 
	 System.out.println("hay date tbadlet");
	
	 Notification h= new Notification();
	 h.setMsg("time accepted");
	 h.setRec(2);
	Interview i= new Interview();
	i=employeService.getIntervByid(id);
	 h.setI(i);
	 employeService.AddNotif(h);
	 System.out.println("hay otif tzedet ll rh");
	
	 
 }
 
 public void RefuseDateCand(Interview intv)
 {
	 
     
	 Interview iv = new Interview();
	 
	 iv= intv;
	 
	
	 
	// iv= employeService.getIntervByid(i);
	
	 //iv.setDate_fixee(iv.getDate_volatile());
	int  id=iv.getId();
	 String cause= "je sis pas dispo";
	/** Contact rhcon = new Contact();
	 rhcon.setMsg(cause);
	 employeService.mailEC(1,  rhcon, 1);
	  **/
	 
	 Notification h= new Notification();
	 h.setMsg("time not available");
	 h.setRec(2);
	Interview i= new Interview();
	i=employeService.getIntervByid(id);
	 h.setI(i);
	 employeService.AddNotif(h);
	 
	 
	 
 }
 
public void CandQuiz( String ques)

 {
	 List<Quiz> a = new ArrayList();
	 
	 Quiz h= new Quiz();
	h.setQuestion(ques);
	
	h.setReponse(repChoise);
	a.add(h);
	
	repQ=a;
	//return a;
	
	
	 
 }
 
public String CompareQuiz()


{
	

	List<Quiz>quizk= new ArrayList();
	quizk=employeService.GetAllQuiz();
	
	
	int per=1;
	
	
	
	for ( Quiz uy : repQ)
		
	{   System.out.println(uy.getReponse());}
	
	
	for ( Quiz e : quizk )
	{
		for ( Quiz s : repQ)
			
		{
			
			if ( s.getQuestion().equals(e.getQuestion()) && s.getReponse().equals(e.getReponse()))
			{
				per++;
				System.out.println(per);
			}
			
			
			
		}
		
		
		
	}
	
	
	
	int longE=repQ.size();
	System.out.println("taille"+longE);
	System.out.println("per"+per);
	
	//note = (per*100)/longE;
	System.out.println((per*100)/longE);


return ("percent :"+(per*100)/longE);



}
 

public  Date Setup()

{
	
	
	
Date d = new Date();
Date r = new Date();
int g =0;

//Candidacy h= employeService.getCandByid(1);

Candidacy h=t;

Interview sd= new Interview();

List<Interview> lm= new ArrayList();
lm= employeService.displayInterv();
int idd;

for ( Interview goint : lm)
{
if (goint.getCan().getId()==h.getId())
{
g=1;
sd=goint;
r= goint.getDate_volatile();

idd=goint.getId();


d=employeService.getEmptyDate(r);
/**
System.out.println(idd+"hedha l id");
System.out.println(d);
sd = employeService.getIntervByid(idd);
sd.setDate_volatile(d);
System.out.println("c bnnn set fl intrv");
System.out.println(sd.getDate_volatile()+"date l sd vol");
goint.setDate_volatile(d);
System.out.println(goint.getDate_volatile()+"date l goint vol");**/
employeService.UpdateInterv(idd, d);
System.out.println("sate set");

	
}


}
	

 if (g==0)
{
	

	 
	 Interview x = new Interview ();
	 x.setCan(h);
	 x.setDate_fixee(null);
	 x.setDate_volatile(d);
	 x.setResultat("NOTYET");
	 x.setType(typeintv);
	 employeService.AddInterv(x);
	 System.out.println("added");
	 
	
}


return d;



}








 // getters & setters

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Boolean getIsActif() {
	return isActif;
}

public void setIsActif(Boolean isActif) {
	this.isActif = isActif;
}

public LoginBean getLoginBean() {
	return loginBean;
}

public void setLoginBean(LoginBean login) {
	loginBean = login;
}

public Date getVarr() {
	return varr;
}

public void setVarr(Date varr) {
	this.varr = varr;
}

public String getTypeintv() {
	return typeintv;
}

public void setTypeintv(String typeintv) {
	this.typeintv = typeintv;
}

public String getNivv() {
	return nivv;
}

public void setNivv(String nivv) {
	this.nivv = nivv;
}

public int getNbrr() {
	return nbrr;
}

public void setNbrr(int nbrr) {
	this.nbrr = nbrr;
}

public String getQue() {
	return Que;
}

public void setQue(String que) {
	Que = que;
}

public String getRep() {
	return rep;
}

public void setRep(String rep) {
	this.rep = rep;
}

public String getOp1() {
	return op1;
}

public void setOp1(String op1) {
	this.op1 = op1;
}

public String getOp2() {
	return op2;
}

public void setOp2(String op2) {
	this.op2 = op2;
}

public String getTypQ() {
	return typQ;
}

public void setTypQ(String typQ) {
	this.typQ = typQ;
}

public String getLevelQuiz() {
	return LevelQuiz;
}

public void setLevelQuiz(String levelQuiz) {
	LevelQuiz = levelQuiz;
}

public String getRepChoise() {
	return repChoise;
}

public void setRepChoise(String repChoise) {
	this.repChoise = repChoise;
}

public String getQuesq() {
	return Quesq;
}

public void setQuesq(String quesq) {
	Quesq = quesq;
}

public List<Quiz> getRepQ() {
	return repQ;
}

public void setRepQ(List<Quiz> repQ) {
	this.repQ = repQ;
}

public int getNote() {
	return note;
}

public void setNote(int note) {
	this.note = note;
}




}
