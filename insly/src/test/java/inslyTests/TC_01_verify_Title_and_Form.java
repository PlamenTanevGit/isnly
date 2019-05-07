package inslyTests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import insly.base;
import pages.inslySignUpPage;

public class TC_01_verify_Title_and_Form  extends base{	
	/* SCOPE:
	 * • "Sign up and start using" title is shown 
	 * • Form looks like in the attached image 
	 * • https://monosnap.com/file/NVCl1Yc9l5xTvyALuBxTgp8FZYp3y7 
	 */

	
	
	@BeforeMethod
	public void beforeTest() {
		baseUrl = "https://signup.int.staging.insly.training/signup";
		base.openUrl(baseUrl);
		
		
	}
	/*
	 *  in the constructor of this Object inslySignUpPage(); are stored checks for all web elements on the signUp page
	 *  including "Sign up and start using" title is shown" , checks for URL,page title and manny more
	 *  'imageComparator' method compares the image : https://monosnap.com/file/NVCl1Yc9l5xTvyALuBxTgp8FZYp3y7 
	 *  and the maked automaticly screenshot of the loaded page and if there are differences it will fail 
	 *  and record a new image in "differences" folder
	 *  In this case there are differences like the phone code in the form and the page is not whole so test Fails
	 */	
		
	@Test(alwaysRun = true, 
	description="TC01 asserts'Sign up and start using' is showen and the form looks accordig the image 0"
			,testName=" TC01"
			,enabled=true)
	public void TC_01_titleCheck_and_FormVerify () throws IOException    {
		
		signUpPage = new inslySignUpPage();
		super.imageComparator(System.getProperty("user.dir")+"\\imageInsly\\TC1_Expected.png");
	
	
	}
	
	
}
