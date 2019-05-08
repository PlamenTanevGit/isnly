package inslyTests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import insly.DataProviderForTest;
import insly.base;
import pages.inslySignUpPage;

public class TC_02_verify_AutoFillUp  extends base{
	/*
	 *  This Test is for Auto Fill up will insert values in the fields using @DataProvider. Then it will verify the presense of the text
	 *  from 'company name' field in to the 'your insly address' field using Image comparator
	 *  In the \\imageInsly\\TC2_Expectedimage.png is loacated image that has completed 'your instly address' field
	 *  after test execution if the field is not completed will throw Assertion Error, because imageComparator compares the 
	 *  TC2_Expectedimage.png with the current input.
	 */
	
	@BeforeMethod
	public void beforeTest() {
		baseUrl = "https://signup.int.staging.insly.training/signup";
		base.openUrl(baseUrl);
		signUpPage = new inslySignUpPage();
		
	}

	@Test(alwaysRun = true
			,dataProvider="dataProviderOne",dataProviderClass=DataProviderForTest.class
			,testName= "TC_02_Verify_auto_addressFillUp"
			,description="Verify the autfillup in the 'your insly address' field and other fields working"
			,enabled=true)
	public void TC_02_Verify_auto_addressFillUp (String companyName,String country,String companyProfile,
			String numberOfEmployees,String descrYourself) throws IOException   
	{
		signUpPage.addCompanyDetails(companyName, country, companyProfile, numberOfEmployees, descrYourself);
		super.imageComparator(System.getProperty("user.dir")+"\\imageInsly\\TC2_Expectedimage.png");
	
	}
}
