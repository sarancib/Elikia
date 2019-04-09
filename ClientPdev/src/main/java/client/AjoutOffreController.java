package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Entreprise;
import entities.Offre;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;
import javafx.scene.control.ChoiceBox;
import static client.AffichEntController.ident;

public class AjoutOffreController implements Initializable{
	@FXML
	private AnchorPane anchor;
	@FXML
	private Label adding;
	@FXML
	private Label pos;
	@FXML
	private Label description;
	@FXML
	private Label typee;
	@FXML
	private Label note;
	@FXML
	private TextField position;
	@FXML
	private TextField desc;
	@FXML
	private ChoiceBox type;
	@FXML
	private Label level;
	@FXML
	private ChoiceBox niveau;
	@FXML
	private Button addoff;
	@FXML
	private Button cancel;
	
	
	
	public void initialize(URL location, ResourceBundle rb) {
		type.getItems().addAll(
	            "Internship","Job");
		niveau.getItems().addAll(
	            "beginner","meduim","advanced","expert"); 
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			Entreprise ent=proxy.getEmp(ident);
			anchor.setStyle(ent.getStyle());
			adding.setStyle(ent.getFont());
			level.setStyle(ent.getFont());
			note.setStyle(ent.getFont());
			typee.setStyle(ent.getFont());
			description.setStyle(ent.getFont());
			pos.setStyle(ent.getFont());
		} catch (NamingException e) {
		
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#addoff].onAction
	@FXML
	public void addoff(ActionEvent event) throws NamingException, IOException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		Offre off=new Offre();
		Date date=new Date();
		off.setDate_pub(date);
		off.setDescription(desc.getText());
		off.setEmplois(position.getText());
		off.setNiveau(niveau.getValue().toString());
		off.setType(type.getValue().toString());
		proxy.affecterOffreAEntreprise(ident, off);
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
	@FXML
	public void cancel(ActionEvent event) throws NamingException, IOException {
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
}
