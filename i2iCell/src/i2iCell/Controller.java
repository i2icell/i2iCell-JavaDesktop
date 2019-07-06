package i2iCell;

import java.io.IOException;

import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

	private  static Logger log = Logger.getLogger(WebServiceClient.class.getName());
	
	@FXML
	private TextField txtRegisterName;
	@FXML
	private TextField txtRegisterNumber;
	@FXML
	private Label labelRegisterMessage;
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
			
		} catch (IOException e) {
			log.error("Error while changing scene:  " + e  +  "\n while changing to " + fxmlFile);
		}
 
	}
	
	
	public void onClickRegister() {
		//TODO.
		//input controls and create user by web service client
		
	}
	
	public void onClickLogin(ActionEvent event) {
		
		String phoneNumber = txtLoginName.getText();
		String password =  txtLoginPassword.getText();
		
		WebServiceClient webServiceClient  = new WebServiceClient();
		boolean isLoginSucces = webServiceClient.login(phoneNumber, password);
		
		if(isLoginSucces) {
			changeScene(event, "ShowBalanceLayout.fxml");
			log.info("Login success");
		}	
		else {
			labelLoginMessage.setText("Kullanýcý adý veya parola yanlýþ");
			log.warn("Login failed");
		}
	}
	

	



	
	public void onClickForgotPassword(ActionEvent event) {
		changeScene(event, "ForgotPasswordLayout.fxml");
	}
	
	
	public void onClickHome() {
		
		//TODO
		//Refresh the balance info...

		
	}


	
	
	
	
}
