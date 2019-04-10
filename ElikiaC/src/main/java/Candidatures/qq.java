package Candidatures;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import static Candidatures.DisplayCFXMLController.ep;

import java.awt.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Interfaces.UserintRemote;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;
import javafx.event.ActionEvent;

public class qq implements  Initializable {
	@FXML
	private Button BtnSend;
	@FXML
	private TextArea n;

	// Event Listener on Button[#BtnSend].onAction
	@FXML
	public void Send(ActionEvent event) throws NamingException {
		
		String jndiName="ElikiaSer-ear/ElikiaSer-ejb/UserImp!Interfaces.UserintRemote"; 
	  	Context context = new InitialContext();
	  	UserintRemote proxy=(UserintRemote) context.lookup(jndiName);
	  	
		
	  	
	  	
		proxy.RefuseCand(ep.getId());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		
	
		
	}
}
