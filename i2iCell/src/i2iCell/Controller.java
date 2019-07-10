package i2iCell;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller {

	private  static Logger log = Logger.getLogger(WebServiceClient.class.getName());
	private static final String PHONE_NUMBER_PATTERN = "[0-9]+";
	private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9@#$%^&+=._!?\\\\-]+";
	
	@FXML
	private Label labelLoginMessage;
	@FXML
	private TextField txtLoginName;
	@FXML
	private PasswordField txtLoginPassword;

	
	public void onClickCreateAccount(ActionEvent event) {		
		changeScene(event, "SignUpLayout.fxml");
	}
	
	public void onClickBack(ActionEvent	 event) {
		changeScene(event, "LoginLayout.fxml");
	}
	
	protected void changeScene(ActionEvent event, String fxmlFile){
	    
		Parent pane;
		try {
			pane = FXMLLoader.load(getClass().getResource(fxmlFile));
			Scene scene = new Scene( pane );
		   
			Stage stageTheLabelBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stageTheLabelBelongs.setScene(scene);
			log.info("Scene changed to " + fxmlFile);
			
		} catch (IOException e) {
			log.error("Error while changing scene:  " + e  +  "\n while changing to " + fxmlFile);
		}
 
	}
	
	

	public void onClickLogin(ActionEvent event) {
		
		String phoneNumber 	= 	txtLoginName.getText();
		String password 	= 	txtLoginPassword.getText();
		
		WebServiceClient webServiceClient  = new WebServiceClient();
		boolean isLoginSucces = webServiceClient.login(phoneNumber, password);
		
		if(isLoginSucces) {
			
			log.info("User logged in");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ShowBalanceLayout.fxml"));
			
			try {
				loader.load();
				log.info("Scene changed to ShowBalance.fxml");
				
			} catch (IOException e) {
				log.warn("Error while changing screen to ShowBalance  " + e);

			}
			String[] balances = webServiceClient.getBalances(phoneNumber);
			ShowBalanceController showBalanceController = loader.getController();
			showBalanceController.setPhoneNumber(phoneNumber);
			showBalanceController.setBalance(balances[0], balances[1], balances[2]);
			showBalanceController.setUserInfo(webServiceClient.getUserProfile(phoneNumber));
			
			Parent pane = loader.getRoot();
			Scene scene = new Scene( pane );
		   
			Stage stageTheLabelBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stageTheLabelBelongs.setScene(scene);
			
		}	
		else {
			alert("Giriþ baþarýsýz", "Kullanýcý adý veya parola hatalý");
			log.warn("Login failed");
		}
	}
	

	public static void alert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText("i2iCell");
		alert.setContentText(message);
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        log.info("Message closed. Title : " +  title);;
		    }
		});
	}



	
	public void onClickForgotPassword(ActionEvent event) {
		changeScene(event, "ForgotPasswordLayout.fxml");
	}
	
	public void onKeyPressPhoneNumber(KeyEvent event) {
		
		if(!Pattern.matches(PHONE_NUMBER_PATTERN, event.getCharacter())) {
			event.consume();
		}		
		
	}
	public void onKeyPressPassword(KeyEvent event) {
		
		if(!Pattern.matches(PASSWORD_PATTERN, event.getCharacter())) {
			event.consume();
		}		
		
	}

	


	
	
	
	
}
