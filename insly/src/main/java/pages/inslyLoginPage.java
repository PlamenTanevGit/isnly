package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import insly.base;

public class inslyLoginPage extends base{
	
	
	public WebElement logo() {
		return element = super.findElement("xpath", "//div[@class='login-panel-first']//a//img");
	}
	
	
	public WebElement EmailField(String value) {
		return element = super.findField("xpath", "//input[@id='login_username']", value);
	}
	
	public WebElement PasswordField(String value) {
		return element = super.findField("xpath", "//input[@id='login_password']", value);
	}
	
	public WebElement LANGUAGE() {
		return element = super.findElement("xpath", "//select[@id='login_lang']");
	}
	
	public WebElement LoginButton() {
		return element = super.findElement("xpath", "//button[@id='login_submit']");
	}
	
	public WebElement forgotMyPassword() {
		return element = super.findElement("xpath", "//a[contains(text(),'Forgot my password')]");
	}

	public inslyDashBoardPage clickOnLoginButton () {
		base.click(LoginButton());
		return new inslyDashBoardPage();
	}
	
	public inslyLoginPage() {
		base.assertElementIsDisplayed(logo());
		base.assertElementIsDisplayed(EmailField(""));
		base.assertElementIsDisplayed(PasswordField(""));
		base.assertElementIsDisplayed(LANGUAGE());
		base.assertElementIsDisplayed(LoginButton());
		base.assertElementIsDisplayed(forgotMyPassword());
		Reporter.log("All elements from Login Page are Displayed");
		System.out.println("All elements from Login Page are Displayed");
	}
}
