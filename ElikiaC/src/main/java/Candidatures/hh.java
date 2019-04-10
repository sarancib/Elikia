package Candidatures;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Candidacy;
import Entities.PreacquisCand;
import Entities.Requirements;
import Interfaces.UserintRemote;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import static Candidatures.DisplayCFXMLController.ep;


public class hh  implements  Initializable {
	@FXML
	private AnchorPane EspaceL;
	@FXML
	private Button ViewMotiv;
	@FXML
	private Button ButCand;
	@FXML
	private AnchorPane name;
	@FXML
	private Button BtnPre;
	@FXML
	private Button btnChek;
	@FXML
	private Button btnAccept;
	@FXML
	private Button BtnRefuse;
	@FXML
	private Button profBut;
	private Candidacy c;
	
	
	
	@FXML
	private TextFlow te;

	
	@FXML
	private Text n;
	
	JazzyTest2 t = new JazzyTest2();

	// Event Listener on Button[#ViewMotiv].onAction
	@FXML
	public void afficherMotiv(ActionEvent event) {
		
		

		
		te.getChildren().clear();
		String g;
		g=c.getLettre_motivation();
		Text text1 = new Text(g);
		t.setString1(g);
		t.setString2(g);
		
		System.out.println(t.getListOfMisspelledWords()+"hedhom");
		
		System.out.println(g);
		//System.out.println(c.getCandidat().getNom());

	te.getChildren().add(new Text(c.getLettre_motivation()));
		
		
	}
	// Event Listener on Button[#ButCand].onAction
	@FXML
	public void afficherCand(ActionEvent event) {
		te.getChildren().clear();
		te.getChildren().add(new Text(c.getLettre_candidature()));
	}
	// Event Listener on Button[#BtnPre].onAction
	@FXML
	public void afficherPre(ActionEvent event) throws NamingException {
		
		String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
	  	Context context = new InitialContext();
	  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
		
	  	te.getChildren().clear();
		
		
List<PreacquisCand>r= new ArrayList();	
		
		r=proxy.displayPreCand(ep.getCandidat().getId());
		
		String Skills=" le candidat acquiere ces skils ";
		
		for(PreacquisCand mol : r) {
            System.out.println(mol.getAcquis());
            
            Skills= Skills+mol.getAcquis()+"\n";
        }
		
		
		te.getChildren().add(new Text(Skills));
		
		
	}
	// Event Listener on Button[#btnChek].onAction
	@FXML
	public void afficherComp(ActionEvent event) throws NamingException {
		String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
	  	Context context = new InitialContext();
	  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	  	te.getChildren().clear();
		
		 List<String> chaineP =new ArrayList();
		 List<String> chainePO =new ArrayList();
		
List<Requirements>r= new ArrayList();	
		
		r=proxy.displayPreOffre(ep.getOffre().getId());
	
		for(Requirements mol : r) {
            System.out.println(mol.getDescription());
            chainePO.add(mol.getDescription());
            
            
            
            
       
		
		
		
		
		
		
		
		
		
		
	
		
List<PreacquisCand>ro = new ArrayList();	
		
		ro=proxy.displayPreCand(ep.getCandidat().getId());
	
		for(PreacquisCand mol : ro) {
            System.out.println(mol.getAcquis());
            chaineP.add(mol.getAcquis());
        }
		
	
		  List<String> similar =new ArrayList(chainePO);
          List<String> different = new ArrayList();
          different.addAll(chainePO);
          different.addAll(chaineP);
          
          similar.retainAll( chaineP );
          different.removeAll( similar );
          
          
          String dif ="";
          String sim="";
          String req="";
          
          for(String v : similar) {
              //System.out.println(mol.getAcquis());
             // chaineP.add(mol.getAcquis());
              sim= sim+v.toString()+" ";
          }
          for(String x : different) {
             // System.out.println(mol.getAcquis());
              dif=dif+x.toString()+" ";
         
          }
          for(String f : chainePO) {
              //System.out.println(mol.getAcquis());
             // chaineP.add(mol.getAcquis());
        	  req=req+f.toString()+" ";
        	  
          }
          
          
          te.getChildren().addAll(new Text(" le candidat a ces skils :"+sim+"\n"),new Text("le candidat doit avoir ces skills :"+req+"\n"));
        /**  te.getChildren().add(new Text(" le candidat a ces skils"+sim+"/n"));
          te.getChildren().add(new Text("le candidat doit avoir ces skills"+req+"/n"));**/
          
          System.out.printf("One:%s%nTwo:%s%nSimilar:%s%nDifferent:%s%n", chainePO, chaineP, similar, different);
     
	}
	// Event Listener on Button[#btnAccept].onAction
	@FXML
	public void accepter(ActionEvent event) throws NamingException {
		
		
		
		
		String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
	  	Context context = new InitialContext();
	  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	  	
	  	
	  	
	  	

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("are you sure , you want to accept this candidacy and start the interviewing process ?");
        

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	
        	
        	
        	
        	
        	  
   
          	proxy.AcceptCand(ep.getId());
        	
    	  
    		
        } else {
            // ... user chose CANCEL or closed the dialog
        }
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	
	  	
		
		
	}
	// Event Listener on Button[#BtnRefuse].onAction
	@FXML
	public void refuser(ActionEvent event) throws NamingException, IOException {
		
		String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
	  	Context context = new InitialContext();
	  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	  	
	  	
	  

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("voulez vous ?");
        

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	
        	
        	
        	
        	
        	  
   	     Node source = (Node) event.getSource();
   	        Stage stage = (Stage) source.getScene().getWindow();
   	        stage.close();
   	        
   	        Stage primaryStage= new Stage();
   	        FXMLLoader FLl = new FXMLLoader(getClass().getResource("Msg.fxml"));
   	        
   	        Parent root = FLl.load();
   	        
   	      
   	        Scene scene = new Scene(root);
   	        primaryStage.setTitle("refuse");
   	        primaryStage.setScene(scene);
   	        
   	       
   	        primaryStage.show();
        	
        	
    	  
    		
        } else {
            // ... user chose CANCEL or closed the dialog
        }
	  
	}
	// Event Listener on Button[#profBut].onAction
	@FXML
	public void afficherDetail(ActionEvent event) {
		// TODO Autogenerated
	}
	@Override
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		Platform.runLater(() -> {

			
	        //do stuff

	    });
		
		//System.out.println(c.getCandidat().getNom());
		
		n.setText(ep.getCandidat().getNom()+" "+ep.getCandidat().getPrenom());
		System.out.println(ep.getCandidat().getNom());
	}
	
	public void setCa(Candidacy c){
	    this.c = c;
	}
	
	
}
