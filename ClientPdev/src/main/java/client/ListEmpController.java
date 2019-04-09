package client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Employe;
import entities.Entreprise;
import entities.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import static client.AffichEntController.ident; 
import static client.loginController.util;; 


public class ListEmpController implements Initializable{
	@FXML
	private AnchorPane anchor;
	@FXML
	private TableView<Employe> table;
	@FXML
	private TableColumn<Employe,String> fname;
	@FXML
	private TableColumn<Employe,String> lname;
	@FXML
	private TableColumn<Employe,Role> role;
	@FXML
	private Label title;
	@FXML
	private Button addempl;
	@FXML
	private Label missing;
	public static int idempl;
	
	
	public void initialize(URL location, ResourceBundle rb) {
		missing.setVisible(false);
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			addempl.setVisible(false);
			if((proxy.getEmploye(util).getRole().toString()=="ADMINISTRATEUR")&&(proxy.getEmploye(util).getEntreprise().getId()==ident)){
				addempl.setVisible(true);
				missing.setVisible(true);
			}
			Entreprise ent=proxy.getEmp(ident);
			anchor.setStyle(ent.getStyle());
			table.setStyle(ent.getStyle());
			title.setStyle(ent.getFont());
			missing.setStyle(ent.getFont());
			int nb=proxy.nombreEmploye(ident);
			fname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			lname.setCellValueFactory(new PropertyValueFactory<>("nom"));
			role.setCellValueFactory(new PropertyValueFactory<>("role"));
			List<Employe> list=proxy.findAllEmploye(ident);
	        String texto="Useful tips: \n";
	        int admin=0;
	        int ceo=0;
	        int pm=0;
	        int dev=0;
	        int emp=0;
	        int ing=0;
	        int rh=0;
	        int tech=0;
			for(Employe e : list){
			if(e.getRole()==Role.ADMINISTRATEUR){
				admin++;
			}
			if(e.getRole()==Role.CEO){
				ceo++;
			}
			if(e.getRole()==Role.CHEF_PROJET){
				pm++;
			}
			if(e.getRole()==Role.DEVELOPPEUR){
				dev++;
			}
			if(e.getRole()==Role.EMPLOYE){
				emp++;
			}
			if(e.getRole()==Role.INGENIEUR){
				ing++;
			}
			if(e.getRole()==Role.RH){
				rh++;
			}
			if(e.getRole()==Role.TECHNICIEN){
				tech++;
			}
			}
			if(admin==0){
				texto=texto+"you are missing an administrator \n";
			}
			else if (admin>1){
				texto=texto+"you can't have more than one administrator \n";
			}
			if(ceo==0){
				texto=texto+"you are missing a CEO \n";
			}
			else if(ceo>1){
				texto=texto+"you can't have more than one CEO \n";
			}
			if(pm==0){
				texto=texto+"you are missing a Project manager \n";
			}
			else if(((pm/nb)*10)>2){
				texto=texto+"you have many Project managers : you can try adding more employee \n";
			}
			if(dev==0){
				texto=texto+"you are missing a developper \n";
			}
			else if(((dev/nb)*10)<1){
				texto=texto+"you have few developpers \n";
			}
			if(emp==0){
				texto=texto+"you are missing a employee \n";
			}
			else if(((emp/nb)*10)<1){
				texto=texto+"you have few employees \n";
			}
			if(ing==0){
				texto=texto+"you are missing an engineer \n";
			}
			else if(((ing/nb)*10)>3){
				texto=texto+"you have too many Engineers \n";
			}
			if(rh==0){
				texto=texto+"you are missing a human resources \n";
			}
			else if (rh>1){
				texto=texto+"you can't have more than one HR \n";
			}
			if(tech==0){
				texto=texto+"you are missing a technician \n";
			}
			else if(((tech/nb)*10)>3){
				texto=texto+"you have too many technicians \n";
			}
			ObservableList<Employe> data = FXCollections.observableArrayList(list);
			table.setItems(data);
			missing.setText(texto);
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
    }
	@FXML
	public void doubleclick(MouseEvent mouseEvent) throws NamingException, IOException {
		 if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
	            if(mouseEvent.getClickCount() == 2){
	                System.out.println("Double clicked");
	                idempl=table.getSelectionModel().getSelectedItem().getId();
	        		Node source = (Node) mouseEvent.getSource();
	                Stage stage = (Stage) source.getScene().getWindow();
	                stage.close();
	                
	                Stage primaryStage= new Stage();
	                FXMLLoader FL = new FXMLLoader(getClass().getResource("UpdateEmpl.fxml"));
	                Parent root = FL.load();
	                Scene scene = new Scene(root);
	                primaryStage.setTitle("Update for "+table.getSelectionModel().getSelectedItem().getNom());
	                primaryStage.setScene(scene);
	                primaryStage.show();
	                
	            }
	        }
	}
	@FXML
	public void addempl(Event event) throws IOException{
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("AjoutEmpl.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("adding");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
