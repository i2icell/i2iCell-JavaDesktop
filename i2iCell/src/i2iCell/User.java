package i2iCell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class User {

	
	private String firstName;
	private String lastName;
	private String tcNumber;
	private String phoneNumber;
	private String birthDate;
	private String password;
	private String mail;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTcNumber() {
		return tcNumber;
	}
	public void setTcNumber(String tcNumber) {
		this.tcNumber = tcNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String eMail) {
		this.mail = eMail;
	}

	
	public boolean isTcValid() {
		if(tcNumber.length() != 11)
			return false;
		else {
			return isNumeric(tcNumber);
		}		
	}
	

	public boolean isPhoneNumberValid() {
		if(phoneNumber.length() != 10 ) {
			return false;
		}else {
			if(phoneNumber.charAt(0) != '5')
				return false;
			else
				return isNumeric(phoneNumber);
		}
	}
	
	public boolean isBirthDateValid() {
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);;			
			Date date1 = dateFormat.parse(birthDate);
			
			return true;
		} catch (ParseException e) {
			return false;
		}  
	}
	
	public boolean isNumeric(String string) {
		try {
		    Integer.parseInt(tcNumber);
		} catch (NumberFormatException e) {
		    return false;
		}
		return true;
	}
	
	public boolean isMailValid() {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return mail.matches(regex);
	}
	
	public boolean isPasswordValid() {
		
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
		
		return Pattern.matches(regex, password);
		
		
	}
	
	public boolean isUserValid() {
		String result = "";
		
		
		if(!isPasswordValid())
			result += "Password is not valid\n";
		if(!isMailValid())
			result += "Mail is not valid\n";
		if(!isBirthDateValid())
			result += "Birth Date is not valid";
		if(!isPhoneNumberValid())
			result += "Phone number is not valid";
		if(!isTcValid())
			result += "TC is not valid";
		
		return result.equals("");
		
		
		
		
	}
	
	
	
	
    
    
	
}
