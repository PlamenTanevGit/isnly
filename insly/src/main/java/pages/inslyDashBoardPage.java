package pages;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import insly.base;

public class inslyDashBoardPage extends base{

	
	public WebElement userInfo () {
		return findElement("xpath", "//div[@id='user-info']");
	}
	
	public WebElement dashboardPageNavbar () {
		return findElement("xpath", "//nav[@class='navbar navbar-default']");
	}

	public WebElement Calendar () {
		return findElement("xpath", "//a[contains(text(),'Calendar')]");
	}
	
	public WebElement Projects () {
		return findElement("xpath", "//a[contains(text(),'Projects')]");
	}
	
	
	public WebElement Setup () {
		return findElement("xpath", "//a[contains(text(),'Setup')]");
	}
	
	
	public inslyDashBoardPage() {
		
			base.assertElementIsDisplayed(userInfo());
			base.assertElementIsDisplayed(dashboardPageNavbar());
			base.assertElementIsDisplayed(Calendar());
			base.assertElementIsDisplayed(Projects());
			base.assertElementIsDisplayed(Setup());
			
			Reporter.log("All elements on Dashboard page are Displayed");
			System.out.println("All elements on Dashboard page are Displayed");
	
	
		
	}
}

