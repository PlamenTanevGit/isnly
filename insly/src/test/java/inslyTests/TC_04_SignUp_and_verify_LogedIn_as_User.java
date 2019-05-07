package inslyTests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import insly.DataProviderForTest;
import insly.base;
import pages.inslyDashBoardPage;
import pages.inslyLoginPage;
import pages.inslySignUpPage;

public class TC_04_SignUp_and_verify_LogedIn_as_User  extends base{
	/*
	 * This test is making SignUp and try to verify Dashoard page Open and Logged as User
	 * the verification of Dashboard page immeadiatly after SignUp is not possible because it opens the LoginPage
	 * not Dashboard page.
	 * According to document and step5 expeced result:'/dashboard page opened' , 'You are logged in as user'
	 * 
	 * (ACCORDING TO THE DOCUMENT)
	 * This test will FAIL becasue it cannot verify presense of Elements from Dashboard page after SignUp 
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
	public void TC_04_SignUp_and_verify_LogedIn_as_User (String companyName,String country,String companyProfile,
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
		
		
		/* 
		 * step 5 -'You are logged in as user','/dashboard page opened'
		 *  - this step will fail because after SignUp we are not logedIn in DashboardPage
		 * but to Login Page
		 * Creating object of Dashboard Page and try to verify elements are displayed
		 */
	
		
		inslyDashBoardPage dashboardPage = new inslyDashBoardPage();
		
	}
}
