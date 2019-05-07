package inslyTests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import insly.DataProviderForTest;
import insly.base;
import pages.inslyLoginPage;
import pages.inslySignUpPage;

public class TC_07_SignUp_and_Login  extends base{
	/*This test is End to End test
	 * it makes SignUp new user using the signUp form and makes a Login using the Login Page
	 * after the success login verifies success landing on the Dashboard page.
	 *  
	 * ReportNG is used for Reports:
	 *  full report of tests execution (passed and failed) can be obtained from folder :\test-output\html\index.html
	 */
	
	@BeforeMethod
	public void beforeTest() {
		baseUrl = "https://signup.int.staging.insly.training/signup";
		base.openUrl(baseUrl);
		signUpPage = new inslySignUpPage();
		
	}

	@Test(alwaysRun = true,dataProvider="dataProviderOne",dataProviderClass=DataProviderForTest.class
			,testName= "TC_04_SignUp_and_verify_LogedIn_as_User"
			,description="SignUp_and_verify_LogedIn_as_User"
			,enabled=true)
	public void TC_07_SignUp_and_Login (String companyName,String country,String companyProfile,
			String numberOfEmployees,String descrYourself,String email,String managerName,String phone) throws InterruptedException, IOException  {
		
		// step2 - fill Company section and verify form 
		signUpPage.addCompanyDetails(companyName, country, companyProfile, numberOfEmployees, descrYourself);
				
		// step3  - Go to "Administrator account details" block Fill in "Work e-mail" "Account manager name",phone number
		signUpPage.addAdministratorAccountDetials(email, managerName, phone);
		
		// step3  - click on Suggested secure password and writes the generated password in
		//insly\txt\password.txt file and returns String in the same time
		base.click(signUpPage.suggestSecurePassword());
		
		base.FileWriter(System.getProperty("user.dir")+"\\txt\\password.txt");
		
		
		
		// step3-  click on 'OK' button on the password pop up 
		base.click(signUpPage.OKbtn());
		
		// step 4 -Tick all Terms and conditions
		signUpPage.TermsAndConditionsclickAllCheckBoxes("span", "class", "icon-check-empty");
		
		//step4 - Click on "terms and conditions" link and agree
		base.click(signUpPage.termsAndCondPopup()); 
		base.click(signUpPage.documentContent());
		base.scrollDown(signUpPage.termsAndCondBotomElement());
		base.click(signUpPage.IAgreeBtn());
	
		//step4 - Click on "privacy policy" link and scroll down, close popup
		base.click(signUpPage.privacyPolicyPopup());
		base.click(signUpPage.documentContent());
		base.scrollDown(signUpPage.privacyPolicyBotomElement());
		base.click(signUpPage.privacyPolicyPopUpX());
		
		// step5 -click on SignUp - Insly demo instance start to work
		//this method is from type 'inslyLoadingPage' and will return 'inslyLoadingPage' with all checks in the constructor
		signUpPage.clickOnSignUpButton();
		Thread.sleep(15000);
		
		// step5 - Verify URL matches with URL from step 2 - 
		try {
			base.URLverify("https://"+base.getStoredString(System.getProperty("user.dir")+"\\txt\\randomUrl.txt", 8)+".int.staging.insly.training/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 *  creating object of inslyLoginPage and verifying all elements from the login Page are displayed
		 */
		inslyLoginPage loginPage = new inslyLoginPage();
		// getting the automaticly generated stored Email and put it in the login pagge Email field
		loginPage.EmailField(base.getStoredString(System.getProperty("user.dir")+"\\txt\\email.txt", 18));
		// getting the automaticly generated stored Password and put it in the loginPage Password field
		loginPage.PasswordField(base.getStoredString(System.getProperty("user.dir")+"\\txt\\password.txt", 15));
		
		/*
		 * click on 'Login Button' - bellow method ruturns new 'inslyDashBoardPage' and all checks for the dashboard page are stored
		 * in the constructor
		 */
		loginPage.clickOnLoginButton();
	
		
	}
}
