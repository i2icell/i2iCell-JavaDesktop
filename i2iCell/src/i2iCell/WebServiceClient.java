package i2iCell;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class WebServiceClient {

	private static Logger log = Logger.getLogger(WebServiceClient.class.getName());
	private final String SUCCESS 			=	"1";
	private final String RETURN_PATTERN 	= 	"<ns:return>(.*?)</ns:return>";
	private final String RETURN_TAG_BEGIN 	= 	"<ns:return>";
	private final String RETURN_TAG_END 	= 	"</ns:return";
	
	public boolean login(String userName, String password) {
		
		String serviceUrlString =	readServiceUrlFromFile("login");
		serviceUrlString = serviceUrlString.replace("USERNAME", userName);
		serviceUrlString = serviceUrlString.replace("PASSWORDINPUT", password);

		return getBooleanResultFromResponse(requestFromUrl(serviceUrlString));
	}
	
	public boolean createUser(User user) {
		
		String serviceUrlString =	readServiceUrlFromFile("createUser");
		serviceUrlString = insertUserInputsToString(user, serviceUrlString);
		
		return getBooleanResultFromResponse(requestFromUrl(serviceUrlString));
	}
	
	public boolean changePassword(User user) {
		
		String serviceUrlString = readServiceUrlFromFile("changePassword");
		
		serviceUrlString  =  serviceUrlString.replace("TCNUMBERINPUT", user.getTcNumber());
		serviceUrlString  =  serviceUrlString.replace("USERNAME", user.getPhoneNumber());
		serviceUrlString  =  serviceUrlString.replace("PASSWORDINPUT", user.getPassword());
		
		return getBooleanResultFromResponse(requestFromUrl(serviceUrlString));	
	}
	
	public String[] getUserProfile(String phoneNumber) {
		
		String serviceUrlString =  readServiceUrlFromFile("showProfile");
		serviceUrlString = serviceUrlString.replace("INPUTNUMBER", phoneNumber);
		
		return getReturnValuesFromResponse(requestFromUrl(serviceUrlString));		
	}
	
	public String[] getBalances(String phoneNumber) {
		
		String serviceUrlString =	readServiceUrlFromFile("getBalances");
		serviceUrlString = serviceUrlString.replace("INPUTNUMBER", phoneNumber);
		String[] balances  = getReturnValuesFromResponse(requestFromUrl(serviceUrlString));	
		
		balances[1] = balances[1].substring(0, balances[1].indexOf("."));
		balances[2] = balances[2].substring(0, balances[2].indexOf("."));
		
		return balances;
	}
	
	
	private String requestFromUrl(String serviceUrlString) {

		StringBuilder responseStringBuilder = new StringBuilder();
		URL serviceUrl;
		try {
			serviceUrl = new URL(serviceUrlString);
			URLConnection serviceConnection = serviceUrl.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serviceConnection.getInputStream()));

			String lineOfResponse;
			while ((lineOfResponse = bufferedReader.readLine()) != null) {
				responseStringBuilder.append(lineOfResponse + "\n");				
			}
			bufferedReader.close();
		} catch (Exception e) {
			log.error("Error while requesting from URL : " + serviceUrlString + "\nError : " + e);

		}

		return responseStringBuilder.toString();
	}
	
	private boolean getBooleanResultFromResponse(String response) {

		int beginIndex = response.indexOf(RETURN_TAG_BEGIN) + RETURN_TAG_BEGIN.length();
		int endIndex = response.indexOf(RETURN_TAG_END);

		String result = response.substring(beginIndex, endIndex);

		if (result.equals(SUCCESS))
			return true;
		return false;

	}
		
	private String[] getReturnValuesFromResponse(String response) {
		String result = "";		
		
		Pattern pattern = Pattern.compile(RETURN_PATTERN, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(response);
		while (matcher.find()) {
		    result += matcher.group(1) + ",";
		}
		
		return result.split(",");
		
	}
	
	
	private String readServiceUrlFromFile(String webServiceName) {
		
		File file = new File("src/i2iCell//ServiceLinks.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			String urlString = document.getElementsByTagName(webServiceName).item(0).getTextContent();
			return urlString;			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			log.error("Error while XML file reading: " + e);
			return null;
		}
	
	}
	
	private String insertUserInputsToString(User user, String serviceUrlString) {
		
		serviceUrlString = serviceUrlString.replace("FIRSTNAMEINPUT", user.getFirstName());
		serviceUrlString = serviceUrlString.replace("LASTNAMEINPUT", user.getLastName());
		serviceUrlString = serviceUrlString.replace("PHONENUMBERINPUT", user.getPhoneNumber());
		serviceUrlString = serviceUrlString.replace("EMAILINPUT", user.getMail());
		serviceUrlString = serviceUrlString.replace("PASSWORDINPUT", user.getPassword());
		serviceUrlString = serviceUrlString.replace("BIRTHDAYINPUT", user.getBirthDate());
		serviceUrlString = serviceUrlString.replace("TCNUMBERINPUT", user.getTcNumber());
		
		return serviceUrlString;
	
	}
	

	
}

























