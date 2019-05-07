package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import insly.base;

public class inslyLoadingPage extends base {

	
	public WebElement signUpTitle() {
		return element = super.findElement("xpath", "//h1[contains(text(),'Sign up and start using')]");
	}

	public WebElement waitingLogo() {
		return element = super.findElement("xpath", "//p[contains(text(),'Wait a little bit while we are building your syste')]");
	}

	

	public inslyLoadingPage() {
		
		base.assertElementIsDisplayed(signUpTitle());
		base.assertElementIsDisplayed(waitingLogo());
		Assert.assertTrue(waitingLogo().getText().contains("Wait a little bit while we are building your system and testing your patience."));
		Reporter.log("All elements from Loading Page are Displayed");
		System.out.println("All elements from Loading Page are Displayed");
	}



}
