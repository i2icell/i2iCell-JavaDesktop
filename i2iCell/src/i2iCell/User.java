package i2iCell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class User {

	private String firstName;
	private String lastName;
	private String tcNumber;
	private String phoneNumber;
	private String birthDate;
	private String password;
	private String mail;
	
	private static final String MAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,12}$";
	private static final String NAME_PATTERN = "[a-zA-Z]+\\.?";
	private static final char TURKISH_PHONE_NUMBER_PREFIX = '5';
	
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

	public boolean isNameValid() {
		
		if(Pattern.matches(NAME_PATTERN, firstName) && Pattern.matches(NAME_PATTERN, lastName))
			return true;
		else return false;	
	}
	
	public boolean isAdult() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		LocalDate start = LocalDate.parse(birthDate, formatter) ;
		LocalDate today = LocalDate.now();
		long years = java.time.temporal.ChronoUnit.YEARS.between( start , today );
		System.out.println("YAÞ " + years);
		if(years < 12.0 || years > 150 )
			return false;
		return true;		
	}
	
	
	public boolean isTcValid() {
		if (tcNumber == null || tcNumber.length() != 11)
			return false;
		else {
			if (isNumeric(tcNumber)) {
				int[] tcArray = stringToIntArray(tcNumber);

				if (tcArray[0] == 0)
					return false;
				if (tcArray[10] % 2 == 1)
					return false;

				if (((tcArray[0] + tcArray[2] + tcArray[4] + tcArray[6] + tcArray[8]) * 7
						- (tcArray[1] + tcArray[3] + tcArray[5] + tcArray[7])) % 10 != tcArray[9])
					return false;

				if ((tcArray[0] + tcArray[1] + tcArray[2] + tcArray[3] + tcArray[4] + tcArray[5] + tcArray[6]
						+ tcArray[7] + tcArray[8] + tcArray[9]) % 10 != tcArray[10])
					return false;

			}
			return true;
		}
	}

	public static int[] stringToIntArray(String string) {
		
		int[] intArray = new int[string.length()];
		
		for(int i = 0; i<string.length(); i++) {
			intArray[i] = Character.getNumericValue(string.charAt(i));		
		}	
		return intArray;			
	}
	
	
	public boolean isPhoneNumberValid() {
		if(phoneNumber.length() != 10 && phoneNumber.charAt(0) != TURKISH_PHONE_NUMBER_PREFIX ) {
			return false;
		}else {
			if(phoneNumber.charAt(0) != '5')
				return false;
			else
				return isNumeric(phoneNumber);
		}
	}
	
	public boolean isBirthDateValid() {		
		if(birthDate ==  null)
			return false;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);			
			dateFormat.parse(birthDate);		
			return isAdult();
			
		} catch (ParseException e) {
			return false;
		}  
	}
	
	public boolean isNumeric(String string) {
		try {
		    Double.parseDouble(string);
		} catch (NumberFormatException e) {
		    return false;
		}
		return true;
	}
	
	public boolean isMailValid() {
	      return Pattern.matches(MAIL_PATTERN,mail);
	}
	
	public boolean isPasswordValid() {	
		return Pattern.matches(PASSWORD_PATTERN, password);				
	}
	
	public String isUserValid() {
			
		if(!isTcValid())
			return "TC Kimlik numarasý geçersiz";	
		if(!isNameValid())
			return "Ad veya soyad hatalý";
		if(!isMailValid())
			return "Email adresi geçersiz";
		if(!isPhoneNumberValid())
			return "Telefon numarasý geçersiz. ";
		if(!isBirthDateValid())
			return "Yaþ sýnýrý aþýldý veya tarih geçersiz.";
		if(!isPasswordValid())
			return "Parola geçersiz, en az bir büyük ve küçük harf ile en az bir rakam içermeli. 8-12 karakter arasýnda olmalý";

		return "VALID";	
	}
}
