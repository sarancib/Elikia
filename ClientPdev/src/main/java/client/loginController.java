package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Employe;
import javafx.event.ActionEvent;

public class loginController implements Initializable{
	@FXML
	private Label erreur;
	@FXML
	private TextField name;
	@FXML
	private TextField id;
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button start;
	@FXML
	private Button login;
	@FXML
	private Button exit;
	public static int ent;
	public static int util;

	public void initialize(URL location, ResourceBundle rb) {
		Random r = new Random();
		int i=r.nextInt((5 - 1) + 1) + 1;
		anchor.setStyle("-fx-background-image: url('file:C:/images/"+i+".JPG') ; -fx-background-repeat: stretch ; -fx-background-size: 440 440; -fx-background-position: center center ; -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
	}
	// Event Listener on Button[#start].onAction
	@FXML
	public void start(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("NewAdmin.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("First Step");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on Button[#login].onAction
	@FXML
	public void login(ActionEvent event) throws IOException, NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		util=Integer.parseInt(id.getText());
		Employe emp=proxy.getEmploye(util);
		if(name.getText().equals(emp.getNom())){
		ent=emp.getEntreprise().getId();
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("ProfilEmpl.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Profil");
        primaryStage.setScene(scene);
        primaryStage.show();
		}
		else{
			erreur.setText("wrong name or id ");
		}
	}
	// Event Listener on Button[#exit].onAction
	@FXML
	public void exit(ActionEvent event) {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
	}
}
