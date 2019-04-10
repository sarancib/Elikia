package Candidatures;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Candidacy;
import Interfaces.UserintRemote;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.MenuBar;

import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class f  implements Initializable {
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

	
	
	
	
	
	
	
	
	
	
	
    void LoadData() throws InterruptedException, NamingException {
    	

    	
    	
    	
    	 String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
	    	Context context = new InitialContext();
	    	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
    	
    	
        

        

      

        ComnName.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        

        
        
        data_table = FXCollections.observableArrayList();

        List<Candidacy>  candidatures = new ArrayList<>();
        candidatures = proxy.displayCand();

        for (int i = 0; i < candidatures.size(); i++) {
        	
        	
        	
          
            data_table.add(candidatures.get(i));
        }
        tableId.setItems(data_table);
       

    }









	public void initialize(URL location, ResourceBundle resources) {
		
		   
        try {
			LoadData();
		} catch ( Exception e) {
			
			e.getMessage();
		}
        
        
        
        
        
        
        
        
        
        
        
        
        
	}



	
	 @FXML
	    private void afficherDetail(ActionEvent even) {
	        /**
	         * detail.setVisible(true);*
	         */
	        Candidacy ep = new  Candidacy();

	        ep = (Candidacy) tableId.getSelectionModel().getSelectedItem();

	        String nom = ep.getNomc();
	        
	        System.out.println(nom);
	        

	        
	      //  description1.setText("Titre: " + nom + "\n" + "Description: " + dos +  "\n"+ " Adresse:" + adr + "\n" + "De : " + date1.toString() + "à " + date2.toString());

	    }
	
	
	
	
	
	
	
	
	
	
	
}


