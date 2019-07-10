package i2iCell;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Test;

class WebServiceClientTest {

	WebServiceClient webServiceClient = new WebServiceClient();
	
	@Test
	void testLogin() {
		BasicConfigurator.configure();
		boolean result = webServiceClient.login("5327981750", "Melih1234");
		assertEquals(true, result);
		result = webServiceClient.login("5327921750", "Melih1234");
		assertEquals(false, result);
		result = webServiceClient.login("53sda81750", "Meli22h1234");
		assertEquals(false, result);
		
	}

	@Test
	void testGetBalances() {
		
	}

	@Test
	void testCreateUser() {
		
		
		
	}

	@Test
	void testChangePassword() {
		
	}

}
