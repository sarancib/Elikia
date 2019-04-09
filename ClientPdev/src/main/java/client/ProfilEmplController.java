package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import static client.loginController.ent;
import static client.loginController.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import entities.Employe;
import entities.Entreprise;
import entities.Offre;
import entities.Role;
public class ProfilEmplController implements Initializable{
	@FXML
	private TextField company;
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button access;
	@FXML
	private Button my;
	@FXML
	private Button pdf;
	@FXML
	private Button logout;
	@FXML
	private Text name;
	@FXML
	private Text error;
	public static int entre ;
	
	public void initialize(URL location, ResourceBundle rb) {
		Random r = new Random();
		int i=r.nextInt((5 - 1) + 1) + 1;
		anchor.setStyle("-fx-background-image: url('file:C:/images/"+i+".JPG') ; -fx-background-repeat: stretch ; -fx-background-size: 350 240; -fx-background-position: center center ; -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
	
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			Employe emp=proxy.getEmploye(util);
			name.setText(emp.getNom()+" "+emp.getPrenom());
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
    }
	// Event Listener on Button[#access].onAction
	@FXML
	public void access(ActionEvent event) throws IOException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context= new InitialContext();
		
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		if(proxy.getEmpParTitre(company.getText())!=null){
			
		
		
		entre=proxy.getEmpParTitre(company.getText()).getId();
		System.out.println(entre);
		
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichEnt.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Company");
        primaryStage.setScene(scene);
        primaryStage.show();
		}
		else {
			error.setText("Entreprise introuvable");
		}
		}
		catch (NamingException e){
			error.setText("Entreprise introuvable");
			e.printStackTrace();
			
		}
	}
	// Event Listener on Button[#my].onAction
	@FXML
	public void my(ActionEvent event) throws IOException {
		entre=ent;
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichEnt.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Company");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on Button[#logout].onAction
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	@FXML
	public void pdf(ActionEvent event) throws IOException, NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context= new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		Entreprise entr=proxy.getEmp(ent);
		Employe empl=proxy.getEmploye(util);
		Employe admin= new Employe();
		List<Employe> list=entr.getEmployes();
		for(Employe e : list){
			if(e.getRole()==Role.ADMINISTRATEUR){
				admin=e;
			}
		}
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
	    Date date=new Date();
	    contentStream.newLine();
	    contentStream.showText("date: "+date);
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("Re: "+empl.getNom()+" "+empl.getPrenom());
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("To whom it may concern");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("this letter is to verify "+empl.getNom()+" "+empl.getPrenom()+ "has been employed by "+entr.getName());
	    contentStream.newLine();
	    contentStream.showText("in the position of "+empl.getRole().toString());
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("During that the main duties of "+empl.getNom()+" "+empl.getPrenom()+" is :");
	    contentStream.newLine();
	    contentStream.showText(empl.getRole().toString());
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("For any refenrence contact us on email : "+entr.getEmail());
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText("Sincerely");
	    contentStream.newLine();
	    contentStream.showText("");
	    contentStream.newLine();
	    contentStream.showText(admin.getNom()+" "+admin.getPrenom());
	    contentStream.newLine();
	    contentStream.showText("Administrator");
	    contentStream.newLine();
	    contentStream.showText(entr.getName());
	    contentStream.endText();
		contentStream.close();
		document.save("C:/images/"+empl.getId()+".pdf");
		document.close();
		File file=new File("C:/images/"+empl.getId()+".pdf");
        Desktop desktop=Desktop.getDesktop();
        desktop.open(file);
	}
}
