package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import insly.base;

public class inslySignUpPage extends base {

	
	public WebElement signUpTitle() {
		return element = super.findElement("xpath", "//h1[contains(text(),'Sign up and start using')]");
	}

	public WebElement noCreditCardRequired() {
		return element = super.findElement("xpath",
				"//p[contains(text(),'No credit card required at this point. Cancel anyt')]");
	}

	public WebElement companyNameField(String value) {  
		return super.findField("xpath", "//input[@id='broker_name']", value);
	}

	public WebElement countryDropdown() {
		return findElement("xpath", "//select[@id='broker_address_country']");
	}

	public WebElement yourInslyAddress () {
		return findElement("xpath", "//input[@id='broker_tag']");
	}
	public WebElement yourInslyAddressField (String value) {
		return findField("xpath", "//input[@id='broker_tag']", value);
	} 
	
	public WebElement companyProfileDropdown () { 
		return findElement("xpath", "//select[@id='prop_company_profile']");
	} 
	
	public WebElement numberOfEmployeesDropdown () {
		return findElement("xpath", "//select[@id='prop_company_no_employees']");
	} 
	
	public WebElement howWouldYouDescribeYourselfDropdown () {
		return findElement("xpath", "//select[@id='prop_company_person_description']");
	} 
	
	public WebElement workEmailfield (String value) {
		return findField("xpath", "//input[@id='broker_admin_email']",value);
	} 
	
	public WebElement accountManagerNameField (String value) {
		return findField("xpath", "//input[@id='broker_admin_name']",value);
	} 
	
	public WebElement passwordField (String value) {
		return findField("xpath", "//input[@id='broker_person_password']",value);
	} 
	
	public WebElement passwordRepeatField (String value) {
		return findField("xpath", "//input[@id='broker_person_password_repeat']",value);
	} 
	
	public WebElement phoneField (String value) {
		return findField("xpath", "//input[@id='broker_admin_phone']",value);
	}
	
	public WebElement agreeTermsAndCondCheck () {
		return findElement("xpath", "//body//label[1]");
	}
	
	public WebElement agreePrivacyPolicyCheck () {
		return findElement("xpath", "//body//label[2]");
	}
	
	public WebElement agreeProcessingPersonalData () {
		return findElement("xpath", "//body//label[3]");
	}
	
	/*
	 *  terms and conditions
	 */
	public WebElement termsAndCondPopup () {
		return findElement("xpath", "//a[contains(text(),'terms and conditions')]");
	}
	
	public WebElement documentContent () {
		return findElement("xpath", "//div[@id='document-content']");
	}
	
	public WebElement termsAndCondBotomElement () {
		return findElement("xpath", "//div[contains(text(),'Revision: 1.20141107')]");
	}
	
	public WebElement IAgreeBtn () {
		return findElement("xpath", "//button[@class='primary']");
	}
	
	
	/*
	 *  privacy policy
	 */
	public WebElement privacyPolicyPopup () {
		return findElement("xpath", "//a[contains(text(),'privacy policy')]");
	}
	
	public WebElement privacyPolicyBotomElement () {
		return findElement("xpath", "//div[contains(text(),'Revision: 1.20180525')]");
	}
	
	public WebElement privacyPolicyPopUpX() {
		return findElement("xpath", "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable']//span[@class='icon-close']");
	}
	
	public WebElement signUpBtn () {
		return findElement("xpath", "//button[@id='submit_save']");
	}
	
	public WebElement inviteBtn () {
		return findElement("xpath", "//button[@id='add_newbroker_button']"
				+ "");
	}
	
	public WebElement OKbtn () {
		return findElement("xpath", "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-dialog-buttons']/div[@class='ui-dialog-buttonpane ui-widget-content ui-helper-clearfix']/div/button");
	}
	
	/*
	 *  method for selecting country
	 */
	public void selectCountry(WebElement select1, String value) {
		super.select(select1, value);
	}
	
	
	public inslyLoadingPage clickOnSignUpButton () {
		base.click(signUpBtn());
		return new inslyLoadingPage();
	}
	
	
	

	/*
	 *  all checkBoxSelection method
	 */
	public void TermsAndConditionsclickAllCheckBoxes(String tagname,String attribute,String text) {
		
		
		List<WebElement> list = driver.findElements(By.tagName(tagname));
		int size = list.size();
		
		for (int i = 0; i < size; i++) {
			list.get(i).getText();
		
		}
	
		for (WebElement webElement : list) {
			
			if (webElement.getAttribute(attribute).equals(text)) {
				base.click(webElement);
				
			}

		}
	}
	
	
	
	
	public void verifyErrorMessage (WebElement element, String expectedMessage) {
		
		base.assertElementIsDisplayed(element);
		base.verifyEqualTexts(element, expectedMessage);
//		Reporter.log(base.getElementText(signUpPage.CompanyErrorMsg())+" is Displayed");
//		System.out.println(base.getElementText(signUpPage.CompanyErrorMsg())+" is Displayed");
	} 
	
	// method for filling Company details
	public void addCompanyDetails (String companyName, String country, String companyProfile, String numberOfEmployees, String descrYourself) {
		companyNameField(companyName);
		base.select(countryDropdown(), country);
		base.select(companyProfileDropdown(), companyProfile);
		base.select(numberOfEmployeesDropdown(), numberOfEmployees);
		base.select(howWouldYouDescribeYourselfDropdown(), descrYourself);
	}
	
	// method for filling Administrator account details
	public void addAdministratorAccountDetials (String email,String managerName, String phone) {
		workEmailfield(email);
		try {
			base.FileWriter(System.getProperty("user.dir")+"\\txt\\email.txt", email);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		accountManagerNameField(managerName);
		phoneField(phone);
	}
	
	
	
	/*
	 * Texts of the fields
	 * 
	 */

	public WebElement companyNameText() {
		return findElement("xpath", "//span[contains(text(),'Company name')]");
	}

	public WebElement CountryText() {
		return findElement("xpath", "//span[contains(text(),'Country')]");
	}

	public WebElement YourInslyaddressText() {
		return findElement("xpath", "//span[contains(text(),'Your Insly address')]");
	}

	public WebElement numberOfEmployees() {
		return findElement("xpath", "//span[contains(text(),'Number of employees')]");
	}

	public WebElement howWouldYouDescribeYourself() {
		return findElement("xpath", "//span[contains(text(),'Number of employees')]");
	}
	
	public WebElement addtionalTextField() {
		return findElement("xpath", "//div[@class='alert alert-warning']");
	}

	public WebElement workEmail() {
		return findElement("xpath", "//span[contains(text(),'Work e-mail')]");
	}

	public WebElement accountManagerName() {
		return findElement("xpath", "//span[contains(text(),'Account manager name')]");
	}
	
	public WebElement password() {
		return findElement("xpath", "//tr[20]//td[1]//div[1]");
	}
	
	public WebElement suggestSecurePassword() {
		return findElement("xpath", "//a[contains(text(),'suggest a secure password')]");
	}

	public static WebElement generatedSecuredPassword() {
		return	findElement("xpath", "//b");
	}
	
	public WebElement passwordRepeat() {
		return findElement("xpath", "//div[contains(text(),'Password (repeat)')]");
	}

	public WebElement phone() {
		return findElement("xpath", "//span[contains(text(),'Phone')]");
	}

	public WebElement addUser() {
		return findElement("xpath", "//td[contains(text(),'Add user')]");
	}

	public WebElement AdministratorAccountDetails() {
		return findElement("xpath", "//td[contains(text(),'Administrator account details')]");
	}

	public WebElement TermsAndConditions() {
		return findElement("xpath", "//td[contains(text(),'Terms and conditions')]");
	}
	public WebElement Company() {
		return findElement("xpath", "//td[contains(text(),'Company')]");
	}

	/*
	 * WebElements on  Error messages 
	 */
	
	public WebElement CompanyErrorMsg() {
		return findElement("xpath", "//span[contains(text(),'Please enter your company name here.')]");
	}
	
	public WebElement CompanyErrorMsg2() {
		return findElement("xpath", "//span[contains(text(),'The company name must be at least 3 characters lon')]");
	}
	
	public WebElement yourInslyAddressErrorMsg() {
		return findElement("xpath", "//span[contains(text(),'Please choose an address for your Insly database.')]");
	}
	
	public WebElement yourInslyAddressErrorMsg2() {
		return findElement("xpath", "//span[contains(text(),'The address tag must be at least 3 characters long')]");
	}
	
	public WebElement emailErrorMsg() {
		return findElement("xpath", "//span[contains(text(),'Please enter your e-mail address here.')]");
	}
	public WebElement emailErrorMsg2() {
		return findElement("xpath", "//span[contains(text(),'This does not appear to be a valid e-mail address.')]");
	}
	
	public WebElement accountManagerNameErrorMsg() {
		return findElement("xpath", "//span[contains(text(),'Please enter your name here.')]");
	}
	
	public WebElement accountManagerNameErrorMsg2() {
		return findElement("xpath", "//span[contains(text(),'Please enter your full name.')]");
	}
	
	
	public WebElement phoneErrorMsg() {
		return findElement("xpath", "//span[@id='status_broker_admin_phone']");
	}
	
	public WebElement phoneErrorMsg2() {
		return findElement("xpath", "//span[contains(text(),'This does not appear to be a valid phone number.')]");
	}
	
	
	
	
	
	/*
	 *  Constructor with checks for all fields and texts
	 */

	public inslySignUpPage()  {
			base.URLverify("https://signup.int.staging.insly.training/signup");
			base.pageTitleVerify("Insly");
			base.assertElementIsDisplayed(signUpTitle());
			base.assertElementIsDisplayed(noCreditCardRequired());
			base.assertElementIsDisplayed(companyNameField(""));
			base.assertElementIsDisplayed(countryDropdown());
			base.assertElementIsDisplayed(yourInslyAddress());
			base.assertElementIsDisplayed(companyProfileDropdown());
			base.assertElementIsDisplayed(numberOfEmployeesDropdown());
			base.assertElementIsDisplayed(howWouldYouDescribeYourselfDropdown());
			base.assertElementIsDisplayed(workEmailfield(""));
			base.assertElementIsDisplayed(accountManagerNameField(""));
			base.assertElementIsDisplayed(passwordField(""));
			base.assertElementIsDisplayed(passwordRepeatField(""));
			base.assertElementIsDisplayed(phoneField(""));
			base.assertElementIsDisplayed(agreeTermsAndCondCheck());
			base.assertElementIsDisplayed(agreePrivacyPolicyCheck());
			base.assertElementIsDisplayed(agreeProcessingPersonalData());
			base.assertElementIsDisplayed(termsAndCondPopup());
			base.assertElementIsDisplayed(privacyPolicyPopup());
			base.assertElementIsDisplayed(signUpBtn());
			base.assertElementIsDisplayed(inviteBtn());
			base.assertElementIsDisplayed(companyNameText());
			base.assertElementIsDisplayed(CountryText());
			base.assertElementIsDisplayed(YourInslyaddressText());
			base.assertElementIsDisplayed(numberOfEmployees());
			base.assertElementIsDisplayed(howWouldYouDescribeYourself());
			base.assertElementIsDisplayed(workEmail());
			base.assertElementIsDisplayed(accountManagerName());
			base.assertElementIsDisplayed(password());
			base.assertElementIsDisplayed(passwordRepeat());
			base.assertElementIsDisplayed(phone());
			base.assertElementIsDisplayed(addUser());
			base.assertElementIsDisplayed(AdministratorAccountDetails());
			base.assertElementIsDisplayed(TermsAndConditions());
			base.assertElementIsDisplayed(Company());
			base.assertElementIsDisplayed(addtionalTextField());
			base.verifyEqualTexts(addtionalTextField(), "You can invite your colleagues from here and they will get their own user access to your Company account");
			Reporter.log("All elements on Signup page are Displayed");
			System.out.println("All elements on Signup page are Displayed");
			
		
	
	}


}
