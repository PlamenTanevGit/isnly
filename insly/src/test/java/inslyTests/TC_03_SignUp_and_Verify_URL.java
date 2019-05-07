package inslyTests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import insly.DataProviderForTest;
import insly.base;
import pages.inslyLoginPage;
import pages.inslySignUpPage;

public class TC_03_SignUp_and_Verify_URL  extends base{
	/*
	 *  This test is using @DataProvider separated in different class for feeding data in the fields 
	 *  It verifies: step2,3,4 and from step5 verifies:'URL matches with URL from step 2'
	 *  also verifies all elements in LoginPage are Displayed
	 */
	
	@BeforeMethod
	public void beforeTest() {
		baseUrl = "https://signup.int.staging.insly.training/signup";
		base.openUrl(baseUrl);
		signUpPage = new inslySignUpPage();
		
	}

	@Test(alwaysRun = true,
			dataProvider="dataProviderOne",dataProviderClass=DataProviderForTest.class
			,testName= "TC_03_AdminAccountFill",
			description="Admin Accoount details form fill,storing the suggested password"
			,enabled=true)
	public void TC_03_SignUp_and_Verify_loginURL (String companyName,String country,String companyProfile,
			String numberOfEmployees,String descrYourself,String email,String managerName,String phone) 
					throws IOException, InterruptedException {
		
		// step2 - fill Company section and verify form 
		signUpPage.addCompanyDetails(companyName, country, companyProfile, numberOfEmployees, descrYourself);
				
		// step3  - Go to "Administrator account details" block Fill in 
		//"Work e-mail" "Account manager name",phone number
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
		base.URLverify("https://"+base.getStoredString(System.getProperty("user.dir")+"\\txt\\randomUrl.txt", 8)+".int.staging.insly.training/login");
		
		/*Verify all LoginPage Objects are displayed
		 *  creating object of inslyLoginPage and verifying all elements from the login Page are displayed
		 *  verification of displayed WebElements are stored in the constructor of inslyLoginPage()
		 */
		inslyLoginPage loginPage = new inslyLoginPage();
		
	}
}
