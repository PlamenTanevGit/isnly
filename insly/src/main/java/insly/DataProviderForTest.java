package insly;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;


import org.testng.annotations.DataProvider;

public class DataProviderForTest {
	
	
	@DataProvider(name="dataProviderOne")
	public static Object[][] dataProviderOne(Method m) throws IOException {
		Object[][] data = null;

		/*
		 *  if the method = TC_02_Verify_auto_addressFillUp it will recieve bellow data
		 */
		if (m.getName().equals("TC_02_Verify_auto_addressFillUp")) {
			data = new Object[1][5];
//
			data[0][0] = "myFirstTestCompanyName";
			data[0][1] = "Greece";
			data[0][2] = "Insurance Agency";
			data[0][3] = "6-10";
			data[0][4] = "I am a tech guy";

			
		}
		else if (m.getName().equals("TC_03_SignUp_and_Verify_loginURL") ||
				m.getName().equals("TC_04_SignUp_and_verify_LogedIn_as_User") ||
				m.getName().equals("TC_07_SignUp_and_Login")) {
			data = new Object[1][8];
			
			data[0][0] = base.FileWriter(System.getProperty("user.dir")+"\\txt\\randomUrl.txt", randomString());
			data[0][1] = "Greece";
			data[0][2] = "Insurance Agency";
			data[0][3] = "6-10";
			data[0][4] = "I am a tech guy";
			data[0][5] = randomString()+"@email.com";
			data[0][6] = "Some Manager";
			data[0][7] = "123456789";
		
			
		}
		
		else if (m.getName().equals("TC_06_SignUpForm_ErrorMessages2")) {
			data = new Object[1][8];
			
			data[0][0] = "qw";
			data[0][1] = "Greece";
			data[0][2] = "Insurance Agency";
			data[0][3] = "6-10";
			data[0][4] = "I am a tech guy";
			data[0][5] = "qw";
			data[0][6] = "qw";
			data[0][7] = "1";
					
			
		}
		return data;
	
	}
	
	
	public static String randomString () {
		String characters = "abcdefghijklmnopqrstuvwxyz";
		
		String randomString = "";
		int lenght = 8;
		
		Random rand = new Random();
		
		char[] text = new char[lenght];
		
		for (int i = 0; i <lenght; i++) {
			text[i]= characters.charAt(rand.nextInt(characters.length()));
		}
		
		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		return randomString;
	}
}
