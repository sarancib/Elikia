package client;
import java.util.HashSet;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Style;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.intrf.EntrepriseServiceRemote;

public class salemController{ 
	@FXML
	private ColorPicker color;
	@FXML
	private AnchorPane interfaceId;
	@FXML
	private Button button;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private TextField txt1;

	
	// Event Listener on Button[#button].onMouseClicked
	@FXML
	public void button(MouseEvent event) throws NamingException , InterruptedException{
		interfaceId.setStyle("-fx-border-color: #000000 ; -fx-background-color: #FF0000"	);
		label1.setStyle("-fx-text-fill: #FFFFFF ; -fx-font-family:Arial");

		label2.setStyle("-fx-text-fill: #FFFFFF ; -fx-font-family:Arial");

		label3.setStyle("-fx-text-fill: #FFFFFF ; -fx-font-family:Arial");
		
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
	EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		int i=Integer.parseInt(txt1.getText());
		
		
	}
	public void setColor(ActionEvent event) {
		color.getValue().toString();
		label1.setStyle("-fx-text-fill: #"+ Integer.toHexString(color.getValue().hashCode()));

		label2.setStyle("-fx-text-fill: #"+Integer.toHexString(color.getValue().hashCode()));

		label3.setStyle("-fx-text-fill: #"+Integer.toHexString(color.getValue().hashCode()));
	}
}
