package client;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;
import javafx.event.ActionEvent;
import static client.AffichEntController.idoffre;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Requirements;
public class AddreqController {
	@FXML
	private Button addreq;
	@FXML
	private Button delreq;
	@FXML
	private TextField req;

	// Event Listener on Button[#addreq].onAction
	@FXML
	public void addreq(ActionEvent event) throws NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		Requirements req2=new Requirements();
		req2.setDescription(req.getText());
		proxy.affecterReqAOffre(idoffre, req2);
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
	}
	@FXML
	public void delreq(ActionEvent event) throws NamingException {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		proxy.removeReq(req.getText());
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
	}
}
