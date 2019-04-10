package Candidatures;

import javafx.fxml.FXML;
import static Candidatures.DisplayCFXMLController.ep;

import javafx.scene.control.Button;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import Entities.Contact;
import Interfaces.UserintRemote;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

public class MsgController {
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
	  	
		
	  	String m=n.getText();
	  	Contact f = new Contact();
	  	f.setMsg(m);
	  	
	  	proxy.mailEC(ep.getCandidat().getId(), f, 1);
	  	
		proxy.RefuseCand(ep.getId());
		
		
	}
}
