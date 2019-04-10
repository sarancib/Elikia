package Candidatures;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Candidacy;
import Entities.Candidate;
import Entities.Contact;
import Entities.Typec;
import Interfaces.UserintRemote;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TabPane;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Tab;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class DisplayCFXMLController implements  Initializable {
	@FXML
	private AnchorPane AId;
	@FXML
	private Button ButunMore;
	@FXML
	private Button checkd;
	
	@FXML
	private ImageView ImgId;
	@FXML
	private ChoiceBox mtype;
	@FXML
	private ChoiceBox typeO;
	@FXML
	private ChoiceBox typeL;
	@FXML
	private TabPane tabId;
	@FXML
	private Tab menuC;
	@FXML
	private TableView tableId;
	@FXML
	private TableColumn ComnName;
	@FXML
	private TableColumn columnType;
	
	@FXML
	private TableColumn dec;
	@FXML
	private TableColumn<Candidacy, String> can;
	@FXML
	private Tab refMenu;
	@FXML
	private TableView tableId1;
	@FXML
	private TableColumn<Contact, String> ComnName1;
	@FXML
	private TableColumn columnType1;
	@FXML
	private TableColumn<Contact, String> pw;
	@FXML
	private TableColumn<Candidacy, String> na;
	@FXML
	private TableColumn da;
	@FXML
	private Tab accMenu;
	@FXML
	private TableView tableId11;
	@FXML
	private TableColumn ComnName11;
	
	public static Candidacy ep;
	
	
 List<Candidacy>  candidatures = new ArrayList<>();
 List<Candidacy>  candidaturesA = new ArrayList<>();
 List<Contact>  candidaturesR = new ArrayList<>();
	 
	 @FXML
	    private ObservableList<Candidacy> data_table;
	 @FXML
	    private ObservableList<Candidacy> data_tableA;
	 
	 
	 @FXML
	    private ObservableList<Contact> data_tableR;

	// Event Listener on Button[#ButunMore].onAction
	@FXML
	public void afficherDetail(ActionEvent event) throws IOException {
		 ep = new  Candidacy();

	     ep = (Candidacy) tableId.getSelectionModel().getSelectedItem();
	     

	     String nom = ep.getNomc();
	     
	     
	     
	     
	     Node source = (Node) event.getSource();
	        Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
	        
	        Stage primaryStage= new Stage();
	        FXMLLoader FL = new FXMLLoader(getClass().getResource("DetalCand.fxml"));
	        
	        Parent root = FL.load();
	        
	        DetalCandController controller = FL.<DetalCandController >getController();
	        controller.setCa(ep);
		       
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("update");
	        primaryStage.setScene(scene);
	        
	       
	        primaryStage.show();
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	}
	
	
	@FXML
	public void checkd(ActionEvent event) throws IOException {
		
		
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("Decisions.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Decisions");
        primaryStage.setScene(scene);
        primaryStage.show();
	 
	}
	@FXML
	public void mre(MouseEvent event) throws IOException {
		 ButunMore.setVisible(true);
	 
	}
	// Event Listener on ChoiceBox[#mtype].onAction
	@FXML
	public void filtrerT(ActionEvent event) throws NamingException {
		ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        can.setCellValueFactory( Candidacy -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Candidacy.getValue().getCandidat().getNom() +""+ Candidacy.getValue().getCandidat().getPrenom());
            return property;
        });
        
        
        
       
        
        
        dec.setCellValueFactory(new PropertyValueFactory<>("decision"));
        

        
        data_table = FXCollections.observableArrayList();
  	
  	String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
  	Context context = new InitialContext();
  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	
	

 	if (mtype.getValue().equals(null)==false)
	
 	{	if (mtype.getValue()=="spontannéé")
  		
	{
  	 candidatures = proxy.FilterType(Typec.spontannéé);
  	 for(int i = 0; i < candidatures.size(); i++){
  		 if(!(candidatures.get(i).getDecision().equals("NOTSEEN"))){
  			 candidatures.remove(candidatures.get(i));
  			 i--;
  		 }
  	 }
  	 String s;
  		if (!(typeL.getSelectionModel().isEmpty() )){ 
  		
  			s=typeL.getValue().toString();
  		System.out.println(s);
      		
      	
  	for (int i = 0; i < candidatures.size(); i++) {
  	candidatures.get(i).getNomc();
    	if (candidatures.get(i).getOffre().getNiveau().equals(s)==false)
    	{
    		
    		candidatures.remove(candidatures.get(i));
    		i--;
    	}
  	}  
  		}   
  
  	
   String t;
		if (!(typeO.getSelectionModel().isEmpty() ))
		{ 
			
			t=typeO.getValue().toString();
		
		System.out.println(t);
  		
  	
	for (int i = 0; i < candidatures.size(); i++) {
	candidatures.get(i).getNomc();
	if (candidatures.get(i).getOffre().getEmplois().equals(t)==false)
	{
		
		candidatures.remove(candidatures.get(i));
		i--;
	}
	}  
		}
  	

	
for (int i = 0; i < candidatures.size(); i++) {
   	
       data_table.add(candidatures.get(i));
   }
   tableId.setItems(data_table);	
	}
  		

  
  else if 
  
  (mtype.getValue()=="non_spontannée")
		
	{
	 candidatures = proxy.FilterType(Typec.non_spontannée);
	 for(int i = 0; i < candidatures.size(); i++){
  		 if(!(candidatures.get(i).getDecision().equals("NOTSEEN"))){
  			 candidatures.remove(candidatures.get(i));
  			 i--;
  		 }
  	 }
	
		if (!(typeL.getSelectionModel().isEmpty() ))
		{ 
			 String t=typeL.getValue().toString();
		System.out.println(t);
   		
   	
	for (int i = 0; i < candidatures.size(); i++) {
	candidatures.get(i).getNomc();
 	if (candidatures.get(i).getOffre().getNiveau().equals(t)==false)
 	{
 		
 		candidatures.remove(candidatures.get(i));
 		i--;
 	}
	}  
     
 }
	
			if (!(typeO.getSelectionModel().isEmpty() ))
			{ 
			
				 String p=typeO.getValue().toString();
			System.out.println(p);
	   		
	   	
		for (int i = 0; i < candidatures.size(); i++) {
		candidatures.get(i).getNomc();
	 	if (candidatures.get(i).getOffre().getEmplois().equals(p)==false)
	 	{
	 		
	 		candidatures.remove(candidatures.get(i));
	 		i--;
	 	}
		}   }
   for (int i = 0; i < candidatures.size(); i++) {
   	
       data_table.add(candidatures.get(i));
   }
   tableId.setItems(data_table);
	
	   			 
	       	}}
	     
	     System.out.println("nemchi");
	}
	// Event Listener on ChoiceBox[#typeO].onAction
	@FXML
	public void filterO(ActionEvent event) throws NamingException {
		ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        
        data_table = FXCollections.observableArrayList();
  	
  	String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
  	Context context = new InitialContext();
  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	
	
  	 String s;
  	 
  	 String k;
 	if (typeO.getValue().equals(null)==false)
	
 	
  		
	{
 		s=typeO.getValue().toString();
  	 candidatures = proxy.FilterOffer(s);
  	for(int i = 0; i < candidatures.size(); i++){
 		 if(!(candidatures.get(i).getDecision().equals("NOTSEEN"))){
 			 candidatures.remove(candidatures.get(i));
 			 i--;
 		 }
 	 }
  	 
  	if (!(typeL.getSelectionModel().isEmpty() )){ 
  		
			k=typeL.getValue().toString();
		System.out.println(s);
  		
  	
	for (int i = 0; i < candidatures.size(); i++) {
	System.out.println(candidatures.get(i).getNomc());
	if (candidatures.get(i).getOffre().getNiveau().equals(k)==false)
	{
		System.out.println(candidatures.get(i).getNomc());
		candidatures.remove(candidatures.get(i));
		i--;
	}
	}  
		}    
  
  	
   String t;
   if (!(mtype.getSelectionModel().isEmpty() ))
	{ 
		
		t=mtype.getValue().toString();
	
	System.out.println(t);
	

for (int i = 0; i < candidatures.size(); i++) {
System.out.println(candidatures.get(i).getNomc());
System.out.println(candidatures.get(i).getType());
if (candidatures.get(i).getType().toString().equals(t)==false)
{
	System.out.println(candidatures.get(i).getNomc());
	candidatures.remove(candidatures.get(i));
	i--;
}
}  
	}
  	

	
for (int i = 0; i < candidatures.size(); i++) {
   	
       data_table.add(candidatures.get(i));
   }
   tableId.setItems(data_table);	
	}
  		
	}
	// Event Listener on ChoiceBox[#typeL].onAction
	@FXML
	public void filterL(ActionEvent event) throws NamingException {
		ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
	       columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
	       

	       
	       data_table = FXCollections.observableArrayList();
	 	
	 	String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
	 	Context context = new InitialContext();
	 	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
		
		
	 	 String s;
	 	 String m;
		if (typeL.getValue().equals(null)==false)
		
		
	 		
		{
			s=typeL.getValue().toString();
	 	 candidatures = proxy.FilterLevel(s);
	 	for(int i = 0; i < candidatures.size(); i++){
	  		 if(!(candidatures.get(i).getDecision().equals("NOTSEEN"))){
	  			 candidatures.remove(candidatures.get(i));
	  			 i--;
	  		 }
	  	 }
	 	 
	 		if (!(typeO.getSelectionModel().isEmpty() )){ 
	 		
	 			m=typeO.getValue().toString();
	 		System.out.println(m);
	     		
	     	
	 	for (int i = 0; i < candidatures.size(); i++) {
	 	candidatures.get(i).getNomc();
	   	if (candidatures.get(i).getOffre().getEmplois().equals(m)==false)
	   	{
	   		
	   		candidatures.remove(candidatures.get(i));
	   		i--;
	   	}
	 	}  
	 		}   
	 
	 	
	  String t;
			if (!(mtype.getSelectionModel().isEmpty() ))
			{ 
				
				t=mtype.getValue().toString();
			
			System.out.println(t);
	 		
	 	
		for (int i = 0; i < candidatures.size(); i++) {
		candidatures.get(i).getNomc();
		if (candidatures.get(i).getType().toString().equals(t)==false)
		{
			
			candidatures.remove(candidatures.get(i));
			i--;
		}
		}  
			}
	 	

		
	for (int i = 0; i < candidatures.size(); i++) {
	  	
	      data_table.add(candidatures.get(i));
	  }
	  tableId.setItems(data_table);	
		}
	 		
		     
		     System.out.println("nemchi");
	}
	
	
	
	 void LoadData() throws InterruptedException, NamingException {
	    	
	   	 List<String> list = new ArrayList();
	        list.add("spontannéé");
	        list.add("non_spontannée");
	        
	        List<String> list2 = new ArrayList();
	        list2.add("bac");
	        list2.add("bac+3");
	        list2.add("bac+5");
	        
	        List<String> list3 = new ArrayList();
	        list3.add("engineer");
	        list3.add("sales");
	        list3.add("accountant");
	        list3.add("agent");
	        list3.add("other");
	        
	 

	        ObservableList<String> ob = FXCollections.observableArrayList();
	        
	        ObservableList<String> obb = FXCollections.observableArrayList();
	        ObservableList<String> obbb = FXCollections.observableArrayList();
	        ob.addAll(list);
	        mtype.setItems(ob);
	        obb.addAll(list2);
	        
	        typeL.setItems(obb);
	        
	 obbb.addAll(list3);
	        
	        typeO.setItems(obbb);
	   	
	   	
	   	 String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
		    	Context context = new InitialContext();
		    	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	   	
	   	
	       ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
	       columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
	       
	     /**  ComnName11.setCellValueFactory(new PropertyValueFactory<>("nomc"));
	       columnType1.setCellValueFactory(new PropertyValueFactory<>("msg"));
	       
	       **/
	       
	    
	       
	       
	       
	       
	      /** ComnName1.setCellValueFactory(
	    	        Contact -> {
	    	            SimpleObjectProperty property = new SimpleObjectProperty();
	    	            property.setValue(Contact.getValue().getC().getNom() +""+ Contact.getValue().getC().getPrenom());
	    	            return property;
	    	        });**/
	    	        
	    	        
	       can.setCellValueFactory( Candidacy -> {
	            SimpleObjectProperty property = new SimpleObjectProperty();
	            property.setValue(Candidacy.getValue().getCandidat().getNom() +""+ Candidacy.getValue().getCandidat().getPrenom());
	            return property;
	        });
	        
	        
	        
	       
	        
	        
	        dec.setCellValueFactory(new PropertyValueFactory<>("decision"));  
	        
	      /**  pw.setCellValueFactory(contact -> {
	            SimpleObjectProperty property = new SimpleObjectProperty();
	            property.setValue(contact.getValue().getC().getAdresseMail());
	            return property;
	        });
	      na.setCellValueFactory(Candidacy -> {
	            SimpleObjectProperty property = new SimpleObjectProperty();
	            property.setValue(Candidacy.getValue().getCandidat().getNom() +""+ Candidacy.getValue().getCandidat().getPrenom());
	            return property;
	        });
	      da.setCellValueFactory(new PropertyValueFactory<>("decision"));
	       
	    	 **/      
	    
	     // data_tableA=FXCollections.observableArrayList();
	       data_table = FXCollections.observableArrayList();
	      // data_tableR=FXCollections.observableArrayList();

	       //List<Candidacy>  candidatures = new ArrayList<>();
	       
	    /**  candidaturesA= proxy.DisplayCandAccepted();
	      
	      
	      
	      
	      
	      for (int i = 0; i < candidaturesA.size(); i++) {
		       	
		       	
		       	
		         
	           data_tableA.add(candidaturesA.get(i));
	       	System.out.println(candidaturesA.get(i).getDecision());
	       }
	       tableId11.setItems(data_tableA);
	      
	       
	       
	       candidaturesR= proxy.DisplayMail();
		      
		      
		      
		      for (int i = 0; i < candidaturesR.size(); i++) {
			       	
		           data_tableR.add(candidaturesR.get(i));
		       	System.out.println(candidaturesR.get(i).getMsg());
		       }
		      tableId1.setItems(data_tableR);
		      **/
	      
	       candidatures = proxy.displayCand();

	       for (int i = 0; i < candidatures.size(); i++) {
	       	
	       	
	       	System.out.println(candidatures.get(i).getNomc());
	         
	           data_table.add(candidatures.get(i));
	       }
	       tableId.setItems(data_table);
	       

	   }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    try {
				LoadData();
			} catch ( Exception e) {
				
				e.getMessage();
			}
	    
	   // ComnName.
	 
	    /**tabId.setTabMinHeight(33);
	    tabId.setTabMaxWidth(69);
	    tabId.setTabMaxHeight(69);**/
	    
	    ButunMore.setVisible(false);
	}
	
	
	   	
	}

