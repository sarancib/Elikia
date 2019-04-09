package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Employe;
import entities.Entreprise;
import entities.Offre;
import entities.Requirements;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;

import static client.AffichEntController.idoffre;
import static client.loginController.util;
import static client.AffichEntController.ident;;

public class OffreController implements Initializable{
	@FXML
	private AnchorPane anchor;
	@FXML
	private Label titre;
	@FXML
	private Button apply;
	@FXML
	private Button deleteoff;
	@FXML
	private Button updateoff;
	@FXML
	private TextFlow text;
	@FXML
	private Button addreq;
	@FXML
	private Button retour;

	
	public void initialize(URL location, ResourceBundle rb) {
		deleteoff.setVisible(false);
		updateoff.setVisible(false);
		addreq.setVisible(false);
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			Offre off=proxy.getOffre(idoffre);
			Employe emp=proxy.getEmploye(util);
			if(emp.getEntreprise().getId()==ident){
				apply.setVisible(false);
			}
			if((emp.getRole().toString()=="RH")&&(emp.getEntreprise().getId()==ident)){
				deleteoff.setVisible(true);
				updateoff.setVisible(true);
				addreq.setVisible(true);
			}
			System.out.println(off.getEmplois());
			Entreprise ent=off.getEntreprise();
			System.out.println(off.getEmplois()+" position for "+ent.getName());
			titre.setStyle(ent.getFont());
			anchor.setStyle(ent.getStyle());
			titre.setText(off.getEmplois()+" pour "+ent.getName());
			text.getChildren().add(new Text(" Type: "+off.getType()));
			text.getChildren().add(new Text("\n Level: "+off.getNiveau()));
			text.getChildren().add(new Text("\n Publishing date: "+off.getDate_pub()));
			text.getChildren().add(new Text("\n Description: "+off.getDescription()));
			text.getChildren().add(new Text("\n Requirements: "));
			List<Requirements> list=off.getReqs();
			for(int i=0; i<list.size();i++){
				for(int j=i+1 ;j<list.size();j++){
					if(list.get(i).getId()==list.get(j).getId()){
						list.remove(list.get(j));
						j--;
					}
					
				}
			}
			for(Requirements r: list){
				text.getChildren().add(new Text("\n  - "+r.getDescription()));
			}
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#apply].onAction
	@FXML
	public void apply(ActionEvent event) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Application");
		alert.setHeaderText("Application");
		alert.setContentText("Your application has been sent to the HR of the Company");
		alert.show();
	}
	// Event Listener on Button[#deleteoff].onAction
	@FXML
	public void deleteoff(ActionEvent event) throws NamingException, IOException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		proxy.removeOffre(idoffre);
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
	// Event Listener on Button[#updateoff].onAction
	@FXML
	public void updateoff(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
		Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("UpdateOffre.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Update");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on Button[#addreq].onAction
	@FXML
	public void addreq(ActionEvent event) throws IOException {
		Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("Addreq.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Requirement");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on Button[#retour].onAction
	@FXML
	public void retour(ActionEvent event) throws IOException {
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
