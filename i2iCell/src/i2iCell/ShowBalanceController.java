package i2iCell;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ShowBalanceController extends Controller  {
	
	private  static Logger log = Logger.getLogger(ShowBalanceController.class.getName());
	
	private String phoneNumber;
	
	
	@FXML
	private Label labelSmsBalance;
	@FXML
	private Label labelGbBalance;
	@FXML
	private Label labelMinuteBalance;	
	@FXML
	private Label labelShowUserPhone;
	@FXML
	private Label labelShowUserPackage;
	

	
	public void setBalance(String gb, String minute, String sms) {
		
		labelGbBalance.setText(gb);
		labelMinuteBalance.setText(minute);
		labelSmsBalance.setText(sms);
		labelShowUserPhone.setText(phoneNumber);
		labelShowUserPackage.setText("Baþlangýç");
		
		
	}
	
	public void onClickLogout(ActionEvent event) {
		changeScene(event, "LoginLayout.fxml");
		log.info("User logged out..");
		
	}
	
	
	public void onClickHome() {	
		
		WebServiceClient webServiceClient =  new WebServiceClient();
		try {
			String[] balance = webServiceClient.getBalances(phoneNumber);
			setBalance(balance[0], balance[1], balance[2]);								
		} catch (Exception e) {
			alert("Bilgilere eriþilemedi", "Bakiye bilgilerine eriþirken hata oluþtu");
			log.error("Error while refreshing ShowBalance Scene:  " +  e );
		}
		
	}

	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber =  phoneNumber;
	}
	
	public void onClickInfo(ActionEvent event) {
		alert("i2iCELL", " We Do Communication\n i2iCell Mobile Operator Application \n Developed by <<INTERN-TEAM-2019>>");
	}

}
