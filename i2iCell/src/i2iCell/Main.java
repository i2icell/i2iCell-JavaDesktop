package i2iCell;

import org.apache.log4j.BasicConfigurator;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

	 @FXML
	 private Pane centerPane;
	 

	
	public static void main(String[] args)  {
		
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		BasicConfigurator.configure();
		Parent root = FXMLLoader.load(getClass().getResource("LoginLayout.fxml"));
		Scene intialScene = new Scene(root);
		

		primaryStage.setScene(intialScene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("i2iCell");
		primaryStage.show();
		

	}
	
	


}

















