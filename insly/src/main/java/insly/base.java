package insly;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import pages.inslySignUpPage;

public class base {
	public static final String BROWSER = "chrome";
	public static String baseUrl;
	public static WebElement element;
	public static WebDriver driver;
	public static final int TIMEOUT = 10;
	public static final int PAGE_TIMEOUT = 45;
	public static final int SCRIPT_TIMEOUT = 45;
	public static String screenshotName;
	public static Robot robot;
	public static Actions actions;
	public static JavascriptExecutor js;
	protected inslySignUpPage signUpPage;
	static long startTime;
	static long endTime; 

	public static WebDriver initialise(String browser) {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		ChromeOptions chromeOptions = new ChromeOptions();
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\executables\\chromedriver.exe");
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--disable-web-security");
			chromeOptions.addArguments("--no-proxy-server");
			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--window-size=1920,1080");
			chromeOptions.addArguments("--enable-precise-memory-info");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("--disable-default-apps");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("test-type");
			chromeOptions.addArguments("disable-geolocation");
			chromeOptions.addArguments("enable-strict-powerful-feature-restrictions");
			driver = new ChromeDriver(chromeOptions);

		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\executables\\geckodriver.exe");
			firefoxProfile.setPreference("permissions.default.stylesheet", 2);
			firefoxProfile.setPreference("permissions.default.image", 2);
			firefoxProfile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
			firefoxProfile.setPreference("geo.enabled", false);
			firefoxProfile.setEnableNativeEvents(false);
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setPreference("layers.acceleration.disabled", true);
			driver = new FirefoxDriver(firefoxProfile);
		}
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;

	}

	public static void openUrl(String url) {
		driver.get(url);
		Reporter.log("Successfully opened url '" + url + "'");
	}

	public static void assertElementIsDisplayed(WebElement element) {
		Assert.assertTrue(element.isDisplayed());
		
	}
	
	public static void refresh()    {
        driver.navigate().refresh();

     
    }

	
	public static void imageComparator2(String expectedImagePath) throws IOException {
		Date d = new Date();
		/*
		 * create a path to the expected image
		 */
		File ExImage = new File(expectedImagePath);

		/*
		 * put the expected image in buffered readed to read it
		 */

		BufferedImage expectedImage = ImageIO.read(ExImage);

		/*
		 * making a boolen with the status of actual and expeced are they equal
		 */

		boolean status = Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, 500, true)
				.withName("actual" + d.toString()).equals(expectedImage, 0.033);

		/*
		 * assert that boolean is true or false
		 */

		Assert.assertTrue(status);
	}

	public static void imageComparator(String expectedImagePath) throws IOException {
		Date d = new Date();
		/*
		 * create a path to the existing image that will serve like a expected result
		 * for comparing
		 */
		File EditedImage = new File(expectedImagePath);
		/*
		 * put the existing image in BufferedImage
		 */
		BufferedImage expectedImage = ImageIO.read(EditedImage);
		/*
		 * give the path where the new image will be saved in case they are differences
		 * between the existing image and the one that is shooted with the shutterbug if
		 * they are such differences, new image will be creaded in bellow dir
		 */
		String oldImagePath = System.getProperty("user.dir") + "\\differences\\" + d.getTime();

		boolean status = Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, 500, true).withName("Actual")
				.equalsWithDiff(expectedImage, oldImagePath, 0.08);

		/*
		 * check that boolean is true or false
		 */

		Assert.assertTrue(status);
		Reporter.log(" Images are equal : " + status);
		System.out.println(" Images are equal : " + status);

	}

	public static void click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		js = (JavascriptExecutor) driver;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			//element.click();
			//Reporter.log("Successfully click on element " + element.getText());
		} catch (StaleElementReferenceException e) {
			
			e.printStackTrace();
			
		}
	}

	


	
	
	public static WebElement returnElement(String locatorType, String locatorPath) {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		switch (locatorType.toLowerCase()) {
		case "id":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
		// driver.findElement(By.id(locatorPath));

		case "xpath":
			return driver.findElement(By.xpath(locatorPath));

		case "name":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
		// driver.findElement(By.name(locatorPath));

		case "classname":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
		// driver.findElement(By.className(locatorPath));

		case "cssselector":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
		// driver.findElement(By.cssSelector(locatorPath));

		case "linktext":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
		// driver.findElement(By.linkText(locatorPath));

		case "tagname":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));
		// driver.findElement(By.tagName(locatorPath));

		default:
			throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorPath);
		}

	}

	public static WebElement findElement(String locatorType, String locatorPath) {
//		try {
		element = returnElement(locatorType, locatorPath);
		element.isDisplayed();

//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//		}
		return element;
	}

	public static WebElement findField(String locatorType, String locator, String value) {
//		try {
		element = returnElement(locatorType, locator);
		element.isDisplayed();
		HighLightElement(element);
		element.sendKeys(value);
		Reporter.log(" Enter value : " + value);
//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//		}
		return element;
	}

	public static void keyPressAndReleaseTab () {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_TAB);  // here we simulate PRESS a TAB
		robot.keyRelease(KeyEvent.VK_TAB); // here we simulate RELEASE a TAB
	}
	
	public static void HighLightElement(WebElement element) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", element);
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
	}

	public static void scrollDown(WebElement Element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);

	}

	public static void JSclickOnWebElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static String ReportNGscreenCapture() throws IOException {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		zoomOutPage(50);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName;

		File screenshotName = new File(Path);

		FileUtils.copyFile(scrFile, screenshotName);
		Reporter.log("<a href=" + screenshotName + "></a>");
		Reporter.log("<br>  <img src='" + screenshotName + "' height='200' width='200' /><br>");

		return Path;

	}

	public static void select(WebElement select1, String value) {

		Select select = new Select(select1);
		select.selectByVisibleText(value);
		Reporter.log("Selecting from dropdown value as : " + value);

	}

	public static void URLverify(String expected) {

		String actual = driver.getCurrentUrl();
		Assert.assertTrue(expected.equalsIgnoreCase(actual));

		Reporter.log("URL is Verified");
	}

	public static void pageTitleVerify(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		Reporter.log("Page title  is Verified");
	}

	public static void zoomOutPage(int percent) {
		JavascriptExecutor js = (JavascriptExecutor) base.driver;
		js.executeScript("document.body.style.zoom='" + percent + "%'");
	}

	public static void verifyEqualTexts(WebElement element, String expected) {
	
			Assert.assertEquals(element.getText(), expected);
			Reporter.log("Text : " + element.getText()+ " - is Verified" );
			System.out.println("Text : " + element.getText()+ " -  is Verified" );

		

	}
	
	public static String getElementText(WebElement element) {
		
		return element.getText();
	}

	public static String FileWriter(String filePath) throws IOException {
		element = driver.findElement(By.xpath("//b"));
		File f1 = new File(filePath);
		java.io.FileWriter fw = new java.io.FileWriter(f1);
		String pass = element.getText();
		Reporter.log("The generated Secured Password from insly is  :" + pass);
		fw.write(pass);
		fw.close();
		return pass;

	}

	public static String FileWriter(String filePath,String chosenString) throws IOException {
		File f1 = new File(filePath);
		java.io.FileWriter fw = new java.io.FileWriter(f1);
		
		Reporter.log("The generated String is  :" + chosenString);
		fw.write(chosenString);
		fw.close();
		return chosenString;

	}
	

	
	public static String getStoredString(String filePath, int chars) throws IOException {

		File f1 = new File(filePath);
		FileReader fr = new FileReader(f1);
		char[] chras = new char[chars];
		fr.read(chras);
		String s = new String(chras);
		System.out.println("The stored String is  : " + s);
		fr.close();
		return s;

	}


	@BeforeClass
	public void beforeClass() {
		initialise(BROWSER);
		Reporter.log(BROWSER + " is Initialised");
	}

	@AfterClass
	public void afterTest() {


		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeSuite
	public void beforeSuite() {
		Reporter.log("");
		startTime = System.nanoTime();

	}

	@AfterSuite
	public void afterSuite() {
		if (driver != null) {
			driver.quit();
		}
		Reporter.log("Driver quit");
		endTime = System.nanoTime();
		long dur = endTime - startTime;
		long convert = TimeUnit.SECONDS.convert(dur, TimeUnit.NANOSECONDS);
		Reporter.log("Duration of Suite is : " + convert + " sec.");

	

	}

}
