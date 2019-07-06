package i2iCell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;



public class WebServiceClient {
	
	
	private  static Logger log = Logger.getLogger(WebServiceClient.class.getName());
	
	public boolean login(String userName, String password) {
	
		
		String serviceUrlString =	 	"http://68.183.75.84:8080/Calculator/services/DBConnection/"
									+	"callLoginProcedure?inputPhoneNumber=USERNAME&inputPassword=PASSWORDINPUT";
		
		
		serviceUrlString = serviceUrlString.replace("USERNAME", userName);
		serviceUrlString = serviceUrlString.replace("PASSWORDINPUT", password);
		
		
		StringBuilder responseStringBuilder = new StringBuilder();
		
	    URL serviceUrl;
		try 
		{
			serviceUrl = new URL(serviceUrlString);
			URLConnection serviceConnection = serviceUrl.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serviceConnection.getInputStream()));
			
			String lineOfResponse;
		
			while ((lineOfResponse = bufferedReader.readLine()) != null){
				
				responseStringBuilder.append(lineOfResponse + "\n");
				
			}
			bufferedReader.close();
		      
		      
		} catch (Exception e) {
			log.error("ERROR AT WEB SERVICE CLIENT - login method : " + e);
			
		}
		
		
		String response = responseStringBuilder.toString();
		return getResultFromResponse(response);
	}
	
	
	private boolean getResultFromResponse(String response) {
		
		int beginIndex = response.indexOf("<ns:return>") +"<ns:return>".length();
		int endIndex = response.indexOf("</ns:return>") ;
		
		String result =  response.substring(beginIndex,endIndex);
	
		if(result.equals("1"))
			return true;
		return false;
		
		
	}
	
	public String getBalances() {
		
		// TODO
		// Get balances from web service
		
		return "1100";
		
		
	}
	
	
	public boolean createUser(User user) {
		
		// TODO
		// Create new user by web service..
		return false;
	}
	

}
