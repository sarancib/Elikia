package client;

import static client.AffichEntController.ident;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Entreprise;
import entities.Sub;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import service.intrf.EntrepriseServiceRemote;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class ListSubController implements Initializable{
	@FXML
	private AnchorPane anchor;
	@FXML
	private TableView<Sub> table;
	@FXML
	private TableColumn<Sub,String> fname;
	@FXML
	private TableColumn<Sub,String> lname;
	@FXML
	private Label title;

public void initialize(URL location, ResourceBundle rb) {
		
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
			Entreprise ent=proxy.getEmp(ident);
			anchor.setStyle(ent.getStyle());
			table.setStyle(ent.getStyle());
			title.setStyle(ent.getFont());
			fname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			lname.setCellValueFactory(new PropertyValueFactory<>("nom"));
			List<Sub> list=ent.getSubs();
			for(int i=0; i<list.size();i++){
				for(int j=i+1 ;j<list.size();j++){
					if(list.get(i).getId()==list.get(j).getId()){
						list.remove(list.get(j));
						j--;
					}
						
				}
			}
			ObservableList<Sub> data = FXCollections.observableArrayList(list);
			table.setItems(data);
				
			System.out.println("fokk 3lia");
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
    }

}
