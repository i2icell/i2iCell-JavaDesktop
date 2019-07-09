package i2iCell;

public class Test {

	public static boolean isNumeric(String string) {
		try {
			Double.parseDouble(string);
		} catch (NumberFormatException e) {
		    return false;
		}
		return true;
	}
	
	public static void main(String[] args)  {
		
		
		User user = new User();
		
		String tc = "45622869024";
		
		
		user.setTcNumber("45622869024");
		
		System.out.println("length: " + tc.length() + "user : " + isNumeric(tc));
		
	}
	
	
}
