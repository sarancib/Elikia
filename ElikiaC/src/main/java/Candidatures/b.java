package Candidatures;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

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
import javafx.scene.control.TableColumn;

public class b implements Initializable {
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
    private ObservableList<Candidacy> data_table;
	
	  @FXML
	    private ChoiceBox mtype;
	  
	  
	  
	  
	  @FXML
	    private ChoiceBox typeO;
	  @FXML
	    private ChoiceBox typeL;
	  
	  
	  List<Candidacy>  candidatures = new ArrayList<>();

	// Event Listener on Button[#ButunMore].onAction
	  
	  
	  
	  
	  
	  @FXML
		public void filtrerT(ActionEvent event) throws NamingException {
			
		     Filtrer ();
		     
		     
		     System.out.println("nemchi");
		}
	  
	  
	  @FXML
			public void filtrerL(ActionEvent event) throws NamingException {
				
			     Filtrer ();
			     
			     
			     System.out.println("nemchi");
			}
		  
	  
	  @FXML
			public void filtrerO(ActionEvent event) throws NamingException {
				
			     Filtrer ();
			     
			     
			     System.out.println("nemchi");
			}
		  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	@FXML
	public void afficherDetail(ActionEvent event) throws NamingException {
		/**Candidacy ep = new  Candidacy();

	     ep = (Candidacy) tableId.getSelectionModel().getSelectedItem();

	     String nom = ep.getNomc();
	     
	     System.out.println(nom);**/
	     Filtrer ();
	}

	

    void LoadData() throws InterruptedException, NamingException {
    	
    	 List<String> list = new ArrayList();
         list.add("Spontannee");
         list.add("non spontannee");
         
         List<String> list2 = new ArrayList();
         list.add("1");
         list.add("2");
         
     

         ObservableList<String> ob = FXCollections.observableArrayList();
         
         ObservableList<String> obb = FXCollections.observableArrayList();
         ob.addAll(list);
         mtype.setItems(ob);
         obb.addAll(list2);
         
         typeL.setItems(obb);
    	
    	
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
    	
    	
    	  ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
          columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
          

          
          
          data_table = FXCollections.observableArrayList();
    	

    	
    	String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
    	Context context = new InitialContext();
    	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	
	
    

    
    	
    	
    	if (mtype.getValue()=="Spontannee")
    		
    	{
    		
    		 String s=typeL.getValue().toString();
    		
    		if ( s.equals(null)== false)
    			
        		
        	{
    			
    			 candidatures = proxy.FilterLevel(s);
    			
        	}
    		
    		
        	 candidatures = proxy.FilterType(Typec.spontannéé);
             for (int i = 0; i < candidatures.size(); i++) {
             	
             	
             	
                 
                 data_table.add(candidatures.get(i));
             }
    		
    		
    		
    		
    		
    	 candidatures = proxy.FilterType(Typec.spontannéé);
         for (int i = 0; i < candidatures.size(); i++) {
         	
         	
         	
             
             data_table.add(candidatures.get(i));
         }
         tableId.setItems(data_table);
         

     
    
    	}
    
    
    else if 
    
    (mtype.getValue()=="non spontannee")
		
	{
	 candidatures = proxy.FilterType(Typec.non_spontannée);
     for (int i = 0; i < candidatures.size(); i++) {
     	
     	
     	
         
         data_table.add(candidatures.get(i));
     }
     tableId.setItems(data_table);
     

 }
	 
	 

	}
    	

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		   
        try {
			LoadData();
		} catch ( Exception e) {
			
			e.getMessage();
		}
        
		
	}
	
	
	
	 
	
	
}
