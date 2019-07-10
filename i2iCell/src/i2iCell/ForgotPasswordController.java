package i2iCell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ForgotPasswordController extends Controller {

	
	@FXML
	private TextField txtTcNumber;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtNewPassword;
	@FXML
	private TextField txtComfirmPassword;
	
	public void onClickChangePassword(ActionEvent event) {
		
		if( txtComfirmPassword.getText().equals( txtNewPassword.getText())) {
			
			User user = new User();
			user.setPassword(txtNewPassword.getText());
			user.setTcNumber(txtTcNumber.getText());
			user.setPhoneNumber(txtPhoneNumber.getText());
			
			if(user.isPasswordValid() && user.isTcValid() && user.isPhoneNumberValid()) {
				WebServiceClient webServiceClient = new WebServiceClient();
				if(webServiceClient.changePassword(user)) {
					alert("Parolan�z de�i�ti", "Yeni parolan�zla giri� yapabilirsiniz");
				}
				else {
					alert("Parola de�i�medi", "Bilgilerden baz�lar� hatal� olabilir");
				}
			}else {
				alert(" Ge�ersiz giri�", "Girilen bilgiler formata uygun de�il");
			}
			
		}else {
			alert("Hatal� giri�", "�ki parola ayn� olmal�d�r, parolalar uyu�muyor");
		}
		

		
		
		
		
	}
	
	
	
}
