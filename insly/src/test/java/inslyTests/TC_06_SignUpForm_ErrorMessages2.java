package inslyTests;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import insly.DataProviderForTest;
import insly.base;
import pages.inslySignUpPage;

public class TC_06_SignUpForm_ErrorMessages2  extends base{
	/*
	 * This test verifies correct error messages are displayed if less than requered data is entered in the fields.
	 * In the ReportNG report are genereated verifications of Displayed messages and the text
	 */
	
	@BeforeMethod
	public void beforeTest() {
		baseUrl = "https://signup.int.staging.insly.training/signup";
		base.openUrl(baseUrl);
		signUpPage = new inslySignUpPage();
		
	}

	@Test(alwaysRun = true,dataProvider="dataProviderOne",dataProviderClass=DataProviderForTest.class
			,testName= "TC_06_SignUpForm_ErrorMessages2"
			,description="Verify correct Error messages are Displayed and CorrectText if less than required data is entered in the fields"
			,enabled=true)
	public void TC_06_SignUpForm_ErrorMessages2 (String companyName,String country,String companyProfile,
			String numberOfEmployees,String descrYourself,String email,String managerName,String phone) 
					throws InterruptedException  {
		/*
		 *  bellow 2 methods feeding data in the fields with @DataProvider
		 */
		signUpPage.addCompanyDetails(companyName, country, companyProfile, numberOfEmployees, descrYourself);
		signUpPage.addAdministratorAccountDetials(email, managerName, phone);
		
		signUpPage.verifyErrorMessage(signUpPage.CompanyErrorMsg2(), "The company name must be at least 3 characters long.");
		
		signUpPage.yourInslyAddressField(companyName);
		signUpPage.yourInslyAddress().sendKeys(Keys.TAB);
		signUpPage.verifyErrorMessage(signUpPage.yourInslyAddressErrorMsg2(), "The address tag must be at least 3 characters long.");
		
		signUpPage.verifyErrorMessage(signUpPage.emailErrorMsg2(), "This does not appear to be a valid e-mail address.");
		
		signUpPage.verifyErrorMessage(signUpPage.accountManagerNameErrorMsg2(), "Please enter your full name.");
		
		signUpPage.verifyErrorMessage(signUpPage.phoneErrorMsg2(), "This does not appear to be a valid phone number.");
		
	}
}
