package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Employe;
import entities.Entreprise;
import entities.Role;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

import service.intrf.EntrepriseServiceRemote;
import javafx.scene.control.ChoiceBox;
import static client.NewAdminController.adminnom;
import static client.NewAdminController.adminprenom;

public class AjoutEntController implements Initializable{
	@FXML
	private Label sample;
	@FXML
	private AnchorPane anchor;
	@FXML
	private TextField name;
	@FXML
	private TextField slogan;
	@FXML
	private TextField mail;
	@FXML
	private TextArea desc;
	@FXML
	private ColorPicker backcolor;
	@FXML
	private ColorPicker bordcolor;
	@FXML
	private ColorPicker textcolor;
	@FXML
	private ChoiceBox<String> textfont;
	@FXML
	private TextField fnamehr;
	@FXML
	private TextField lnamehr;
	@FXML
	private TextField fnamepm;
	@FXML
	private TextField lnamepm;
	@FXML
	private Button ajoutent;
	@FXML
	private Button choose;
	@FXML
	private Button cancel;
	public static String nameurl;

	// Event Listener on ColorPicker[#textcolor].onAction
	public void initialize(URL url, ResourceBundle rb) {
		Random r = new Random();
		int i=r.nextInt((5 - 1) + 1) + 1;
		anchor.setStyle("-fx-background-image: url('file:C:/images/"+i+".JPG') ; -fx-background-repeat: stretch ; -fx-background-size: 590 520; -fx-background-position: center center ; -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
	
        textfont.getItems().addAll(
            "Arial","Algerian","Calibri","Cambria","Elephant","Forte");
        }
	@FXML
	public void changecolor(ActionEvent event) {
		sample.setStyle("-fx-text-fill: #"+ Integer.toHexString(textcolor.getValue().hashCode()));
	}
	// Event Listener on ChoiceBox[#textfont].onDragDetected
	@FXML
	public void changefont(ActionEvent event) {
		sample.setStyle("-fx-font-family:"+textfont.getValue());
	}
	// Event Listener on Button[#ajoutent].onAction
	@FXML
	public void ajoutEnt(ActionEvent event) throws NamingException , InterruptedException, IOException{
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context = new InitialContext();
		EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
		Entreprise ent=new Entreprise();
		ent.setName(name.getText());
		ent.setDescription(desc.getText());
		ent.setFont("-fx-text-fill: #"+ Integer.toHexString(textcolor.getValue().hashCode())+" ; -fx-font-family:"+textfont.getValue());
		ent.setLogo(nameurl);
		ent.setSlogan(slogan.getText());
		ent.setStyle("-fx-border-color: #"+Integer.toHexString(backcolor.getValue().hashCode())+" ; -fx-background-color: #"+Integer.toHexString(bordcolor.getValue().hashCode()));
		ent.setEmail(mail.getText());
		
		Employe emp1=new Employe();
		emp1.setNom(adminnom);
		emp1.setPrenom(adminprenom);
		emp1.setRole(Role.ADMINISTRATEUR);
		Employe emp2=new Employe();
		emp2.setNom(lnamehr.getText());
		emp2.setPrenom(fnamehr.getText());
		emp2.setRole(Role.RH);
		Employe emp3=new Employe();
		emp3.setNom(lnamepm.getText());
		emp3.setPrenom(fnamepm.getText());
		emp3.setRole(Role.CHEF_PROJET);
		ent.addEmploye(emp1);
		ent.addEmploye(emp2);
		ent.addEmploye(emp3);
		proxy.ajouterEntreprise(ent);
		Employe emp =proxy.getEmployeParTitre(adminnom);
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
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Succes");
		alert.setHeaderText("Company created");
		alert.setContentText("You are now the administrator and this is your id( "+emp.getId()+" ) that you can log in with and configure your company");
		alert.show();
	}
	@FXML
	public void choose(ActionEvent event) {
		List<String> extensions = new ArrayList<>();
        extensions.add("*.jpg");
        extensions.add("*.png");
        extensions.add("*.jpeg");
        extensions.add("*.JPG");
        extensions.add("*.PNG");
        extensions.add("*.JPEG");
        extensions.add(".");
		FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("photo", extensions));

        File f = fc.showOpenDialog(null);

        if (f != null) {
            Image i = new Image(f.toURI().toString());
            String uuid = UUID.randomUUID().toString();

            nameurl = uuid + ".png";
            String savedImage = "C:/images/" + uuid + ".png";
            File outputFile = new File(savedImage);
            /**
             * copy of its pixels into a BufferedImage object*
             */
            BufferedImage bImage = SwingFXUtils.fromFXImage(i, null);
            try {
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(nameurl);
	}
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
