package Candidatures;



import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class fxid extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       
       // Parent root=FXMLLoader.load(getClass().getResource("DisplayCFXML.fxml"));
    	Parent root=FXMLLoader.load(getClass().getResource("Auth.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("afficher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}