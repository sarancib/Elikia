package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.intrf.EntrepriseServiceRemote;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.CheckBox;

import javafx.scene.control.ChoiceBox;
import static client.AffichEntController.ident;;

public class UpdateEntController implements Initializable{
	@FXML
	private AnchorPane anchor;
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
	private ChoiceBox textfont;
	@FXML
	private Label sample;
	@FXML
	private Label logo;
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
	private CheckBox rhchange;
	@FXML
	private CheckBox pmchange;
	public static String nameurl;

	public void initialize(URL url, ResourceBundle rb) {
		String jndiName="Elikia-ear/Elikia-ejb/EntrepriseService!service.intrf.EntrepriseServiceRemote"; 
		Context context;
		try {
			context = new InitialContext();
			EntrepriseServiceRemote proxy=(EntrepriseServiceRemote) context.lookup(jndiName);
	        Entreprise ent=proxy.getEmp(ident);
	        slogan.setText(ent.getSlogan());
	        desc.setText(ent.getDescription());
	        mail.setText(ent.getEmail());
	        anchor.setStyle(ent.getStyle());
	        sample.setStyle(ent.getFont());
	        
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textfont.getItems().addAll(
            "Arial","Algerian","Calibri","Cambria","Elephant","Forte");
        
        }
	// Event Listener on ColorPicker[#textcolor].onAction
	@FXML
	public void changecolor(ActionEvent event) {
		sample.setStyle("-fx-text-fill: #"+ Integer.toHexString(textcolor.getValue().hashCode()));
	}
	// Event Listener on ChoiceBox[#textfont].onAction
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
		proxy.updateEntreprise(ident, nameurl, slogan.getText(), desc.getText(),"-fx-text-fill: #"+ Integer.toHexString(textcolor.getValue().hashCode())+" ; -fx-font-family:"+textfont.getValue(), "-fx-border-color: #"+Integer.toHexString(bordcolor.getValue().hashCode())+" ; -fx-background-color: #"+Integer.toHexString(backcolor.getValue().hashCode()), mail.getText());
		int id=0;
		if(rhchange.isSelected()){
		for(Employe e : proxy.getEmp(ident).getEmployes()){
			if(e.getRole().toString().equals("RH")){
				id=e.getId();
				System.out.println(id);
			}
		}
		proxy.removeEmploye(id);
		Employe emp2=new Employe();
		emp2.setNom(lnamehr.getText());
		emp2.setPrenom(fnamehr.getText());
		emp2.setRole(Role.RH);
		proxy.affecterEmployeAEntreprise(ident, emp2);
		}
		if(pmchange.isSelected()){
		for(Employe e : proxy.getEmp(ident).getEmployes()){
			if(e.getRole().toString().equals("CHEF_PROJET")){
				id=e.getId();
				System.out.println(id);
			}
		}
		proxy.removeEmploye(id);
		Employe emp3=new Employe();
		emp3.setNom(lnamepm.getText());
		emp3.setPrenom(fnamepm.getText());
		emp3.setRole(Role.CHEF_PROJET);
		proxy.affecterEmployeAEntreprise(ident, emp3);
		}
		Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        Stage primaryStage= new Stage();
        FXMLLoader FL = new FXMLLoader(getClass().getResource("AffichEnt.fxml"));
        Parent root = FL.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Company");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	@FXML
	public void addimage(ActionEvent event) {
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
}
