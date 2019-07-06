package i2iCell;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ShowBalanceController extends Controller implements Initializable {
	
	private  static Logger log = Logger.getLogger(ShowBalanceController.class.getName());
	
	@FXML
	private Label labelSmsBalance;
	@FXML
	private Label labelGbBalance;
	@FXML
	private Label labelMinuteBalance;	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		WebServiceClient wsc =  new WebServiceClient();
		
		try {
			setBalance(wsc.getBalances(), wsc.getBalances(),  wsc.getBalances());								
		} catch (Exception e) {
			log.error("Error while initializing ShowBalance Scene:  " +  e );
		}
		
	}
	
	public void setBalance(String gb, String minute, String sms) {
		
		labelGbBalance.setText(gb);
		labelMinuteBalance.setText(minute);
		labelSmsBalance.setText(sms);
		
	}
	
	public void onClickLogout(ActionEvent event) {
		changeScene(event, "LoginLayout.fxml");
		log.info("User logged out..");
		
	}

}
