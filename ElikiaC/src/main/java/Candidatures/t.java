package Candidatures;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Candidacy;
import Entities.Typec;
import Interfaces.UserintRemote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuBar;

import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class t implements  Initializable {
	@FXML
	private AnchorPane AId;
	@FXML
	private TableView tableId;
	@FXML
	private TableColumn ComnName;
	@FXML
	private TableColumn columnType;
	@FXML
	private Button ButunMore;
	@FXML
	private ImageView ImgId;
	@FXML
	private MenuBar meniId;
	@FXML
	private ChoiceBox mtype;
	@FXML
	private ChoiceBox typeO;
	@FXML
	private ChoiceBox typeL;
	 List<Candidacy>  candidatures = new ArrayList<>();
	 
	 @FXML
	    private ObservableList<Candidacy> data_table;

	// Event Listener on Button[#ButunMore].onAction
	 
	 	 
	 
	 @FXML
	public void afficherDetail(ActionEvent event) throws NamingException {
		Candidacy ep = new  Candidacy();

	     ep = (Candidacy) tableId.getSelectionModel().getSelectedItem();
	     

	     String nom = ep.getNomc();
	     
	   /**  System.out.println(nom);**/
	     //Filtrer ();
	   
	     
		 
		  
	}
	// Event Listener on ChoiceBox[#mtype].onAction
	@FXML
	public void filtrerT(ActionEvent event) throws NamingException {
		ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        
        data_table = FXCollections.observableArrayList();
  	
  	String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
  	Context context = new InitialContext();
  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	
	

 	if (mtype.getValue().equals(null)==false)
	
 	{	if (mtype.getValue()=="spontannéé")
  		
	{
  	 candidatures = proxy.FilterType(Typec.spontannéé);
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
  		

  
  
  /** for (int i = 0; i < candidatures.size(); i++) {
   	
       data_table.add(candidatures.get(i));
   }
   tableId.setItems(data_table);
	
	   		**/	 
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
        list2.add("1");
        list2.add("2");
        
        List<String> list3 = new ArrayList();
        list3.add("ingenieur");
        list3.add("manager");
        list3.add("sec");
        list3.add("emplois");
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
       

       
       
       data_table = FXCollections.observableArrayList();

       //List<Candidacy>  candidatures = new ArrayList<>();
       
      
    
       
       candidatures = proxy.displayCand();

       for (int i = 0; i < candidatures.size(); i++) {
       	
       	
       	
         
           data_table.add(candidatures.get(i));
       }
       tableId.setItems(data_table);
       

   }


   
   
void Filtrer () throws NamingException
   
   
   
   {
   	/**
   	
   	  ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
         columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
         

         
         data_table = FXCollections.observableArrayList();
   	
   	String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
   	Context context = new InitialContext();
   	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	
	

  	if (mtype.getValue().equals(null)==false)
	
  	{	if (mtype.getValue()=="Spontannee")
   		
	{
   	 candidatures = proxy.FilterType(Typec.spontannéé);
   	 String s;
   		if (!(typeL.getSelectionModel().isEmpty() )){ 
   		
   			s=typeL.getValue().toString();
   		System.out.println(s);
       		
       	
   	for (int i = 0; i < candidatures.size(); i++) {
   	candidatures.get(i).getNomc();
     	if (candidatures.get(i).getOffre().getNiveau().equals(s)==false)
     	{
     		
     		candidatures.remove(candidatures.get(i));
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
 	}
	}  
		}
   	
 
	
for (int i = 0; i < candidatures.size(); i++) {
    	
        data_table.add(candidatures.get(i));
    }
    tableId.setItems(data_table);	
	}
   		

   
   else if 
   
   (mtype.getValue()=="non spontannee")
		
	{
	 candidatures = proxy.FilterType(Typec.non_spontannée);
	 String t=typeL.getValue().toString();
		if (t.equals(null)==false)
		{ 
		
		System.out.println(t);
    		
    	
	for (int i = 0; i < candidatures.size(); i++) {
	candidatures.get(i).getNomc();
  	if (candidatures.get(i).getOffre().getNiveau().equals(t)==false)
  	{
  		
  		candidatures.remove(candidatures.get(i));
  	}
	}  
      
  }
		
	
	    String p=typeO.getValue().toString();
			if (p.equals(null)==false)
			{ 
			
			System.out.println(p);
	   		
	   	
		for (int i = 0; i < candidatures.size(); i++) {
		candidatures.get(i).getNomc();
	 	if (candidatures.get(i).getOffre().getEmplois().equals(p)==false)
	 	{
	 		
	 		candidatures.remove(candidatures.get(i));
	 	}
		}  
    for (int i = 0; i < candidatures.size(); i++) {
    	
        data_table.add(candidatures.get(i));
    }
    tableId.setItems(data_table);
	}
	   			 
	       	}} **/
	 
	   	}
	
	
@Override
public void initialize(URL location, ResourceBundle resources) {
    try {
			LoadData();
		} catch ( Exception e) {
			
			e.getMessage();
		}
    
    ButunMore.setVisible(false);
}
   


}
