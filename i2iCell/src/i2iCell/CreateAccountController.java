package i2iCell;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.DateTimeDateFormat;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateAccountController extends Controller{
	
	private static Logger log = Logger.getLogger(WebServiceClient.class.getName());
	
	@FXML
	private TextField txtRegisterFirstName;
	@FXML
	private TextField txtRegisterTc;
	@FXML
	private TextField txtRegisterLastName;
	@FXML
	private TextField txtRegisterMail;
	@FXML
	private TextField txtRegisterPassword;
	@FXML
	private TextField txtRegisterPhoneNumber;
	@FXML
	private Label labelRegisterMessage;
	
	@FXML
	private DatePicker datePicker;
	
	
	public void onClickRegister() {
		
		
		User user = new User();

		user.setFirstName(txtRegisterFirstName.getText());
		user.setLastName(txtRegisterLastName.getText());
		user.setTcNumber(txtRegisterTc.getText());
		user.setMail(txtRegisterMail.getText());
		user.setPhoneNumber(txtRegisterPhoneNumber.getText());
		user.setPassword(txtRegisterPassword.getText());
		user.setBirthDate(getDate());
		
		
		WebServiceClient webServiceClient = new WebServiceClient();
		
		String validationMessage = user.isUserValid();
		
		if( validationMessage ==  "VALID" )   {
			if(webServiceClient.createUser(user)) {
				alert("Kayýt Baþarýlý", "Kayýt Baþarýlý kullanýcý adý ve þifre ile giriþ yapabilirsiniz.");
				log.info("New user created by using web service");
			}
			else {
				alert("Kayýt Baþarýsýz", "Bu kiþi daha önce kaydolmuþ ya da bilgiler geçersiz");
				
				log.warn("Create user web service denied to create new user..");
			}
		}
		else {
			alert("Eksik giriþ", validationMessage);		
			log.info("User inputs are not valid : " + validationMessage);
		}
		
	}
	
	
	public String getDate() {

		if(datePicker.getValue() != null) {
			LocalDate localDate = datePicker.getValue();		
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
			return formatter.format(localDate);
		}
		return null;
			


		
	}
	
	

}
