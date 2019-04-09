package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

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

import static client.AffichEntController.ident;
import static client.ListEmpController.idempl;
import static client.loginController.util;

public class UpdateEmplController implements Initializable{
	@FXML
	private Label id;
	@FXML
	private Label title;
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button cancel;
	@FXML
	private Button apply;
	@FXML
	private Button remove;
	@FXML
	private Label role;
	@FXML
	private ChoiceBox<String> rolechoice;
	
	public void initialize(URL location, ResourceBundle rb) {
		id.setVisible(false);
		title.setVisible(false);
		apply.setVisible(false);
		remove.setVisible(false);
		rolechoice.getItems().addAll(
				"HR","Project manager","CEO","Admin","Developper","Employee","Engineer","technician" );
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			if((proxy.getEmploye(util).getRole().toString()=="ADMINISTRATEUR")&&(proxy.getEmploye(util).getEntreprise().getId()==ident)){
				apply.setVisible(true);
				remove.setVisible(true);
				title.setVisible(true);
				id.setVisible(true);
			}
			Entreprise ent=proxy.getEmp(ident);
			role.setStyle(ent.getFont());
			title.setStyle(ent.getFont());
			anchor.setStyle(ent.getStyle());
			Employe emp=proxy.getEmploye(idempl);
			id.setText("id: "+emp.getId());
			if(emp.getRole()==Role.ADMINISTRATEUR){
				rolechoice.setValue("Admin");
			}
			if(emp.getRole()==Role.CEO){
				rolechoice.setValue("CEO");
			}
			if(emp.getRole()==Role.CHEF_PROJET){
				rolechoice.setValue("Project manager");
			}
			if(emp.getRole()==Role.DEVELOPPEUR){
				rolechoice.setValue("Developper");
			}
			if(emp.getRole()==Role.EMPLOYE){
				rolechoice.setValue("Employee");
			}
			if(emp.getRole()==Role.INGENIEUR){
				rolechoice.setValue("Engineer");
			}
			if(emp.getRole()==Role.RH){
				rolechoice.setValue("HR");
			}
			if(emp.getRole()==Role.TECHNICIEN){
				rolechoice.setValue("technician");
			}
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
    
	}

	// Event Listener on Button[#apply].onAction
	@FXML
	public void apply(ActionEvent event) throws NamingException, IOException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		if(rolechoice.getValue().equals("Admin")){
			proxy.updateEmploye(idempl, Role.ADMINISTRATEUR);
		}
		if(rolechoice.getValue().equals("CEO")){
			proxy.updateEmploye(idempl, Role.CEO);
		}
		if(rolechoice.getValue().equals("Project manager")){
			proxy.updateEmploye(idempl, Role.CHEF_PROJET);
		}
		if(rolechoice.getValue().equals("Developper")){
			proxy.updateEmploye(idempl, Role.DEVELOPPEUR);
		}
		if(rolechoice.getValue().equals("Employee")){
			proxy.updateEmploye(idempl, Role.EMPLOYE);
		}
		if(rolechoice.getValue().equals("Engineer")){
			proxy.updateEmploye(idempl, Role.INGENIEUR);
		}
		if(rolechoice.getValue().equals("HR")){
			proxy.updateEmploye(idempl, Role.RH);
		}
		if(rolechoice.getValue().equals("technician")){
			proxy.updateEmploye(idempl, Role.TECHNICIEN);
		}
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
	// Event Listener on Button[#remove].onAction
	@FXML
	public void remove(ActionEvent event) throws IOException, NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		proxy.removeEmploye(idempl);
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
