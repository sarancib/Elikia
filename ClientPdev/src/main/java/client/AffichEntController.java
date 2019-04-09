package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

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
import entities.Role;
import entities.Sub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import static client.loginController.util;
import static client.ProfilEmplController.entre;

public class AffichEntController implements Initializable{
	@FXML
	private AnchorPane anchor;
	@FXML
	private Label name;
	@FXML
	private Label nb;
	@FXML
	private Label labelfilter;
	@FXML
	private Label labeltype;
	@FXML
	private Label labellevel;
	@FXML
	private TextField positiontext;
	@FXML
	private Label position;
	@FXML
	private Button positionfilter;
	@FXML
	private Button back;
	@FXML
	private ChoiceBox<String> levelfilter;
	@FXML
	private ChoiceBox<String> typefilter;
	@FXML
	private Label offerslabel;
	@FXML
	private Label slogan;
	@FXML
	private Label desc;
	@FXML
	private ImageView logo;
	@FXML
	private Label state;
	@FXML
	private Label rh;
	@FXML
	private Label pm;
	@FXML
	private Label admin;
	@FXML
	private Button subs;
	@FXML
	private Button addoffer;
	@FXML
	private Button update;
	@FXML
	private Button employe;
	@FXML
	private Button seeoffer;
	@FXML
	private Button subscribe;
	@FXML
	private TableView<Offre> table;
	@FXML
	private TableColumn<Offre,String> titre;
	@FXML
	private TableColumn<Offre,String> type;
	@FXML
	private TableColumn<Offre,String> niveau;
	
	public static int ident;
	public static int idutil;
	public static int tipe;
	public static int idoffre;
	
	public void initialize(URL location, ResourceBundle rb) {
		ident=entre;
		idutil=util;
		typefilter.getItems().addAll(
	            "Internship","Job");
		levelfilter.getItems().addAll(
	            "beginner","meduim","advanced","expert"); 
		seeoffer.setVisible(false);
		update.setVisible(false);
		addoffer.setVisible(false);
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			Entreprise ent=proxy.getEmp(ident);
			logo.setImage(new Image("file:///C:/images/"+ent.getLogo()));
			Employe util=proxy.getEmploye(idutil);
			if((util.getRole().toString()=="RH")&&(util.getEntreprise().getId()==ident)){
				addoffer.setVisible(true);
			}
			if((util.getRole().toString()=="ADMINISTRATEUR")&&(util.getEntreprise().getId()==ident)){
				update.setVisible(true);
			}
			if((util.getRole().toString()=="CHEF_PROJET")&&(util.getEntreprise().getId()==ident)){
				addoffer.setVisible(true);
			}
			if(util.getEntreprise().getId()==ident){
				subscribe.setVisible(false);
			}
			List<Sub> lista=ent.getSubs();
			for(Sub s: lista){
				if(s.getId()==idutil){
					if(util.getEntreprise().getId()!=ident){
					state.setVisible(true);
					}
					subscribe.setVisible(false);
				}
			}
			nb.setStyle(ent.getFont());
			nb.setText(proxy.nombreEmploye(ident)+" Employees");
			anchor.setStyle(ent.getStyle());
			name.setText(ent.getName());
			name.setStyle(ent.getFont());
			desc.setStyle(ent.getFont());
			desc.setText(ent.getDescription());
			admin.setStyle(ent.getFont());
			slogan.setStyle(ent.getFont());
			slogan.setText(ent.getSlogan());
			pm.setStyle(ent.getFont());
			rh.setStyle(ent.getFont());
			offerslabel.setStyle(ent.getFont());
			for(Employe e : ent.getEmployes()){
				if(e.getRole().equals(Role.ADMINISTRATEUR)){
					admin.setText(e.getNom());
				}
			}
			for(Employe e : ent.getEmployes()){
				if(e.getRole().equals(Role.RH)){
					rh.setText(e.getNom());
				}
			}
			for(Employe e : ent.getEmployes()){
				if(e.getRole().equals(Role.CHEF_PROJET)){
					pm.setText(e.getNom());
				}
			}
			titre.setCellValueFactory(new PropertyValueFactory<>("emplois"));
			type.setCellValueFactory(new PropertyValueFactory<>("type"));
			niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
			List<Offre> list=proxy.findAllOffre(ident);
			ObservableList<Offre> data = FXCollections.observableArrayList(list);
			table.setItems(data);
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
    }
	// Event Listener on Button[#subs].onAction
	@FXML
	public void voirsubs(ActionEvent event)  throws IOException{
		tipe=0;
		Stage primaryStage= new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("ListSub.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Subs List");
         primaryStage.setScene(scene);
         primaryStage.show();
	}
	// Event Listener on Button[#update].onAction
	@FXML
	public void update(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("UpdateEnt.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("update");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on Button[#employe].onAction
	@FXML
	public void voirempl(ActionEvent event) throws IOException {
		tipe=1;
		Stage primaryStage= new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("ListEmp.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Employees List");
         primaryStage.setScene(scene);
         primaryStage.show();
	}
	// Event Listener on Button[#seeoffer].onAction
	@FXML
	public void offer(ActionEvent event) throws NamingException, IOException {
		idoffre=table.getSelectionModel().getSelectedItem().getId();
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("offre.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle(table.getSelectionModel().getSelectedItem().getEmplois());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on TableView[#table].onMouseClicked
	@FXML
	public void showoption(MouseEvent event) {
		seeoffer.setVisible(true);
	}
	@FXML
	public void addsub(ActionEvent event) {
		// TODO Autogenerated
	}
	@FXML
	public void typefilter(ActionEvent event) throws NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		titre.setCellValueFactory(new PropertyValueFactory<>("emplois"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
		List<Offre> list=proxy.findAllOffre(ident);
		for(int i=0; i<list.size();i++){
			for(int j=i+1 ;j<list.size();j++){
				if(list.get(i).getId()==list.get(j).getId()){
					list.remove(list.get(j));
					j--;
				}
				
			}
		}
		for(int i=0 ; i<list.size();i++){
			if((i>=0) && !(list.get(i).getType().equals(typefilter.getValue()))){
				
				list.remove(list.get(i));
				
					i--;
			}
		
		if (!(levelfilter.getSelectionModel().isEmpty() )){ 
	  		if ((i>=0) && !(list.get(i).getNiveau().equals(levelfilter.getValue())))
	  		{
	  			
	  			list.remove(list.get(i));
	  			
					i--;
	  		}
		} 
		if (!(positiontext.getText().isEmpty() )){ 
	  		if ((i>=0) && !(list.get(i).getEmplois().toUpperCase().equals(positiontext.getText().toUpperCase())))
	  		{
	  			
	  			list.remove(list.get(i));
	  			
					i--;
	  		}
		}
		}
		ObservableList<Offre> data = FXCollections.observableArrayList(list);
		table.setItems(data);
	}
	@FXML
	public void levelfilter(ActionEvent event) throws NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		titre.setCellValueFactory(new PropertyValueFactory<>("emplois"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
		List<Offre> list=proxy.findAllOffre(ident);
		for(int i=0; i<list.size();i++){
			for(int j=i+1 ;j<list.size();j++){
				if(list.get(i).getId()==list.get(j).getId()){
					list.remove(list.get(j));
					j--;
				}
				
			}
		}
		for(int i=0 ; i<list.size();i++){
			if((i>=0) && !(list.get(i).getNiveau().equals(levelfilter.getValue()))){
				
				list.remove(list.get(i));
				
				i--;
			}
		
		if (!(typefilter.getSelectionModel().isEmpty() )){ 
	  		if ((i>=0) && !(list.get(i).getType().equals(typefilter.getValue())))
	  		{
	  			if(!(list.isEmpty())){
	  			list.remove(list.get(i));
	  			
					i--;}
	  		}
		} 
		if (!(positiontext.getText().isEmpty())){ 
	  		if ((i>=0) && !(list.get(i).getEmplois().toUpperCase().equals(positiontext.getText().toUpperCase())))
	  		{
	  			list.remove(list.get(i));
	  			
					i--;
	  		}
		}
		}
		ObservableList<Offre> data = FXCollections.observableArrayList(list);
		table.setItems(data);
	}
	@FXML
	public void positionfilter(ActionEvent event) throws NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		titre.setCellValueFactory(new PropertyValueFactory<>("emplois"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
		List<Offre> list=proxy.findAllOffre(ident);
		for(int i=0; i<list.size();i++){
			for(int j=i+1 ;j<list.size();j++){
				if(list.get(i).getId()==list.get(j).getId()){
					list.remove(list.get(j));
					j--;
				}
				
			}
		}
		for(int i=0 ; i<list.size();i++){
			if((i>=0) && !(list.get(i).getEmplois().toUpperCase().equals(positiontext.getText().toUpperCase()))){
				list.remove(list.get(i));
				
					i--;
			}
		
		if (!(levelfilter.getSelectionModel().isEmpty() )){ 
	  		if ((i>=0) && !(list.get(i).getNiveau().equals(levelfilter.getValue())))
	  		{
	  			list.remove(list.get(i));
	  			
					i--;
	  		}
		} 
		if (!(typefilter.getSelectionModel().isEmpty())){ 
	  		if ((i>=0) && !(list.get(i).getType().equals(typefilter.getValue())))
	  		{
	  			list.remove(list.get(i));
	  			
					i--;
	  		}
		}
		}
		ObservableList<Offre> data = FXCollections.observableArrayList(list);
		table.setItems(data);
	}
	@FXML
	public void addoffer(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("AjoutOffre.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add offer");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	@FXML
	public void back(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("ProfilEmpl.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
