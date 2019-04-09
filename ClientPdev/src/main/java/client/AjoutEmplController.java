package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import static client.AffichEntController.ident;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Employe;
import entities.Entreprise;
import entities.Role;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;
import javafx.scene.control.ChoiceBox;

public class AjoutEmplController implements Initializable{
	@FXML
	private AnchorPane anchor;
	@FXML
	private Label title;
	@FXML
	private Button apply;
	@FXML
	private Label role;
	@FXML
	private ChoiceBox<String> rolechoice;
	@FXML
	private Button cancel;
	@FXML
	private Label nom;
	@FXML
	private Label prenom;
	@FXML
	private TextField name;
	@FXML
	private TextField lastname;

	public void initialize(URL location, ResourceBundle rb) {
		rolechoice.getItems().addAll(
				"HR","Project manager","CEO","Admin","Developper","Employee","Engineer","technician" );
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			Entreprise ent=proxy.getEmp(ident);
			role.setStyle(ent.getFont());
			title.setStyle(ent.getFont());
			anchor.setStyle(ent.getStyle());
			nom.setStyle(ent.getFont());
			prenom.setStyle(ent.getFont());
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
    
	}
	// Event Listener on Button[#apply].onAction
	@FXML
	public void apply(ActionEvent event) throws IOException, NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		Employe emp=new Employe();
		if(rolechoice.getValue().equals("Admin")){
			emp.setRole(Role.ADMINISTRATEUR);
		}
		if(rolechoice.getValue().equals("CEO")){
			emp.setRole(Role.CEO);
		}
		if(rolechoice.getValue().equals("Project manager")){
			emp.setRole(Role.CHEF_PROJET);
		}
		if(rolechoice.getValue().equals("Developper")){
			emp.setRole(Role.DEVELOPPEUR);
		}
		if(rolechoice.getValue().equals("Employee")){
			emp.setRole(Role.EMPLOYE);
		}
		if(rolechoice.getValue().equals("Engineer")){
			emp.setRole(Role.EMPLOYE);
		}
		if(rolechoice.getValue().equals("HR")){
			emp.setRole(Role.RH);
		}
		if(rolechoice.getValue().equals("technician")){
			emp.setRole(Role.TECHNICIEN);
		}
		emp.setNom(name.getText());
		emp.setPrenom(lastname.getText());
		proxy.affecterEmployeAEntreprise(ident, emp);
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("ListEmp.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Employees List");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void cancel(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("ListEmp.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Employees List");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
