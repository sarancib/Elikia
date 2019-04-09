package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class NewAdminController implements Initializable{
	@FXML
	private AnchorPane anchor;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private Button next;
	@FXML
	private Button cancel;
	public static String adminnom;
	public static String adminprenom;

	// Event Listener on Button[#next].onAction
	public void initialize(URL url, ResourceBundle rb) {
		Random r = new Random();
		int i=r.nextInt((5 - 1) + 1) + 1;
		anchor.setStyle("-fx-background-image: url('file:C:/images/"+i+".JPG') ; -fx-background-repeat: stretch ; -fx-background-size: 380 260; -fx-background-position: center center ; -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
        }
	@FXML
	public void next(ActionEvent event) throws IOException {
		adminnom=nom.getText();
		adminprenom=prenom.getText();
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("AjoutEnt.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Last Step");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void cancel(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
