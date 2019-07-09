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
					alert("Parolanýz deðiþti", "Yeni parolanýzla giriþ yapabilirsiniz");
				}
				else {
					alert("Parola deðiþmedi", "Bilgilerden bazýlarý hatalý olabilir");
				}
			}else {
				alert(" Geçersiz giriþ", "Telefon numarasý (5** *** ****) olacak þekilde girilmelidir"
						+ " \n TC No 11 hane ve geçerli olmalýdýr \n Parola en az büyük küçük harf ve sayýlardan oluþmalýdýr");
			}
			
		}else {
			alert("Hatalý giriþ", "Ýki parola ayný olmalýdýr, parolalar uyuþmuyor");
		}
		

		
		
		
		
	}
	
	
	
}
