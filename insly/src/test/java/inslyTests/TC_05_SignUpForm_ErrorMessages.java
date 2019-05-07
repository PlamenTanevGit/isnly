package inslyTests;

import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import insly.base;
import pages.inslySignUpPage;

public class TC_05_SignUpForm_ErrorMessages  extends base{
	/*
	 * This test verifies correct error messages are displayed if NO data is entered in the fields.
	 * In the ReportNG report are genereated verifications of Displayed messages and the text
	 */
	
	@BeforeMethod
	public void beforeTest() {
		baseUrl = "https://signup.int.staging.insly.training/signup";
		base.openUrl(baseUrl);
		signUpPage = new inslySignUpPage();
		
	}

	@Test(alwaysRun = true
			,testName= "TC_05_VerifyFieldsErrorMessages"
			,description="Verify correct Error messages are Displayed and CorrectText if no data is entered in the fields"
			,enabled=true)
	public void TC_05_VerifyFieldsErrorMessages ()  {
			
		signUpPage.verifyErrorMessage(signUpPage.CompanyErrorMsg(), "Please enter your company name here.");
		
		signUpPage.yourInslyAddress().sendKeys("");
		signUpPage.yourInslyAddress().sendKeys(Keys.TAB);
		signUpPage.verifyErrorMessage(signUpPage.yourInslyAddressErrorMsg(), "Please choose an address for your Insly database.");

		signUpPage.verifyErrorMessage(signUpPage.emailErrorMsg(), "Please enter your e-mail address here.");
		signUpPage.verifyErrorMessage(signUpPage.accountManagerNameErrorMsg(), "Please enter your name here.");
		signUpPage.verifyErrorMessage(signUpPage.phoneErrorMsg(), "This does not appear to be a valid phone number.");

		
	}
}
