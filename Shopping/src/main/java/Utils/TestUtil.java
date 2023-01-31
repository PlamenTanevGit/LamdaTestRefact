package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import Pages.AccountRegisterPage;
import Pages.AddPersonalDetails;
import Pages.CheckoutPage;
import Pages.ConfirmOrderPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import Pages.SearchResultPage;
import Pages.ShoppingCartPage;
import Pages.SuccessPage;
import Pages.TopHeaderPage;

public class TestUtil {

	public TestUtil() {

	}

	// Properties Initialize
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static java.io.FileInputStream fis;
	public static java.io.FileInputStream fis2;

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	public static SoftAssert softAssert;
	public static Assert hardAssert;
	public static WebElement element;
	private static JavascriptExecutor js;
	private static String screenshotName;
	private static Actions actions;
	private static long startTime;
	private static long endTime;

	/** Pages */
	protected HomePage homePage;
	protected SearchResultPage searchResultPage;
	protected ShoppingCartPage shoppingCartPage;
	protected CheckoutPage checkoutPage;
	protected AccountRegisterPage accountRegisterPage;
	protected ConfirmOrderPage confirmOrderPage;
	protected TopHeaderPage topHeader;
	protected SuccessPage successPage;
	protected LoginPage loginPage;
	protected MyAccountPage myAccountPage;
	protected AddPersonalDetails addPersonalDetails;

	public static void initConfiguration() {
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			config.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fis2 = new FileInputStream(System.getProperty("user.dir") + "\\properties\\or.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			or.load(fis2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void openUrl(String url) {
		driver.navigate().to(url);
		Reporter.log("Successfully opened url '" + url + "'");
	}

	public static void assertIsDisplayed(WebElement element) {
		Assert.assertTrue(element.isDisplayed());
	}

	public static void assertElementIsNotDisplayed (By locator) {
		List<WebElement> searchedElemeents = driver.findElements(locator);
		Assert.assertTrue(searchedElemeents.isEmpty());
	}

	public static WebElement locateElement(LocatorType locatorType, String locatorPath) {

		wait = new WebDriverWait(driver, DriverFactory.TIMEOUT);

		switch (locatorType) {
		case ID:
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locatorPath))));		
		case XPATH: 
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locatorPath))));		
		case NAME:
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locatorPath))));		
		case CLASSNAME:
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(locatorPath))));		
		case CSS:
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locatorPath))));		
		case LINKEDTEXT:
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(locatorPath))));		
		case TAGNAME:
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName(locatorPath))));

		default:
			throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorPath);
		}

	}

	public static List<WebElement> getElementList(String locator, LocatorType type) {
		
		List<WebElement> elementList = new ArrayList<WebElement>();
		if (type==LocatorType.ID) {
			elementList = driver.findElements(By.id(locator));
			
		} else if (type==LocatorType.NAME) {
			elementList = driver.findElements(By.name(locator));		} 
		
		else if (type==LocatorType.XPATH) {
			elementList = driver.findElements(By.xpath(locator));
						
		} else if (type==LocatorType.CSS) {
			elementList = driver.findElements(By.cssSelector(locator));
			
		} else if (type==LocatorType.CLASSNAME) {
			elementList = driver.findElements(By.className(locator));
			
		} else if (type==LocatorType.TAGNAME) {
			elementList = driver.findElements(By.tagName(locator));
			
		} else if (type==LocatorType.LINKEDTEXT) {
			elementList = driver.findElements(By.linkText(locator));
		} 
		else if (type==LocatorType.PARTIALLINKTEXT) {
			elementList = driver.findElements(By.partialLinkText(locator));
		} else {
			System.out.println("Locator type not supported");
		}
		if (elementList.isEmpty()) {
			System.out.println("Element not found with " + type + ": " + locator);

		} else {
			System.out.println("Element found with " + type + ": " + locator);
		}
		return elementList;
	}

	public static WebElement findElement(LocatorType locatorType, String locatorPath) {
		try {
			element = locateElement(locatorType, locatorPath);
		} catch (StaleElementReferenceException e) {
			System.out.println(e);
		}

		return element;
	}

	public static WebElement typeOnField(WebElement element, String value) {
		HighLightElement(element);
		element.clear();
		element.sendKeys(value);
		Reporter.log(" Enter value : " + value);
		
		return element;
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

	public static void jSClick(WebElement element) {
		wait = new WebDriverWait(driver, DriverFactory.TIMEOUT);
		js = (JavascriptExecutor) driver;
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].click();", element);
		Reporter.log("Successfully click on element " + element.getText());
	}

	public static void clickOnElement(WebElement element) {
		wait = new WebDriverWait(driver, DriverFactory.TIMEOUT);
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			Reporter.log("Successfully click on element " + element.getText());
		} catch (StaleElementReferenceException e) {
			System.out.println(e);
		}
	}

	public static void pseudoClickJavascript(String cssPath, String key) {

		switch (key) {
		
		case "before":
			((JavascriptExecutor) driver).executeScript("document.querySelector(arguments[0],':before').click();",cssPath);					
			break;
			
		case "after":
			((JavascriptExecutor) driver).executeScript("document.querySelector(arguments[0],':after').click();",cssPath);
			break;

		default:
			break;
		}

	}
	public static String selectDropdownOptionByTagName(WebElement dropdown, String tagName, String searchedOption) throws InterruptedException {		
		actions = new Actions(driver);
		TestUtil.clickOnElement(dropdown);	
		List<WebElement> options = driver.findElements(By.tagName(tagName));
		for (WebElement option : options) {
			if (option.getText().equals(searchedOption)) {
				TestUtil.clickOnElement(option);
				break;
			}
		}
		return searchedOption;
	}

	public static void JSclickOnWebElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static String ScreenCapture() throws IOException {
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

	public static void pageTitleVerify(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		Reporter.log("Page title  is Verified");
		System.out.println("Page title  is Verified");

	}

	public static void zoomOutPage(int percent) {
		JavascriptExecutor js = (JavascriptExecutor) TestUtil.driver;
		js.executeScript("document.body.style.zoom='" + percent + "%'");
	}

	public static void verifyEqualTexts(WebElement element, String expected) {

		Assert.assertEquals(element.getText(), expected);
		Reporter.log("Text : " + element.getText() + " - is Verified");
		System.out.println("Text : " + element.getText() + " -  is Verified");

	}

	public static void movesToTheElement(WebElement element) {
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, 5);

		try {
			element = wait.until(ExpectedConditions.visibilityOf(element));
			actions.moveToElement(element).build().perform();
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element  was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element  was not clickable " + e.getStackTrace());

		}
	}

	public static int convertDoubleToInt(double doubleAmount) {	
		Double newData = new Double(doubleAmount);
		return newData.intValue();
	}
	
	public static int convertStringToInt (String value) {
		return Integer.parseInt(value);
	}

	public static List<WebElement> visiblilityOfAllElements(By locator, int timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
		return driver.findElements(locator);
	}

	public static WebElement waitForElementToBePresent(By locator, int timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);
	}

	public static WebElement waitForElementToBeClickable(By locator, int timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);
	}

	public static WebElement waitForElementToBeVisbile(By locator, int timeout) {
		WebElement element = getElement(locator);
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public static void clickWhenReady(By locator, int timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}

	public static WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
	}

	public static String randomString(int lenght) {
		String characters = "abcdefghijklmnopqrstuvwxyz";
		String randomString = "";

		Random rand = new Random();
		char[] text = new char[lenght];

		for (int i = 0; i < lenght; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}

		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}
		return randomString;
	}
	
	public static double getDoubleFromWebElement (WebElement element) {
		
		String unitPriceStringValueShoppingCart = element.getText();
		String unitPriceStringValueShoppingCartModified = unitPriceStringValueShoppingCart.replace("$", "");
		return Double.parseDouble(unitPriceStringValueShoppingCartModified);
	}
	
	public static double getDoubleFromStringValue (String stringValue ) {
		String price1 = stringValue.replace("$", "");		
		return  Double.parseDouble(price1);
	}
	
	public String getScreenshot() throws WebDriverException {
		File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	

	@BeforeSuite
	public void beforeSuite() {
		Reporter.log("");
		startTime = System.nanoTime();
		softAssert = new SoftAssert();

	}

	@BeforeClass
	public void beforeClass() {
		driver = DriverFactory.initialise(Browsers.FIREFOX);
		Reporter.log(Browsers.FIREFOX + " is Initialised");
		initConfiguration();

		homePage = new HomePage(driver);
		searchResultPage = new SearchResultPage(driver);
		shoppingCartPage = new ShoppingCartPage(driver);
		checkoutPage = new CheckoutPage(driver);
		accountRegisterPage = new AccountRegisterPage(driver);
		confirmOrderPage = new ConfirmOrderPage(driver);
		topHeader = new TopHeaderPage(driver);
		successPage = new SuccessPage(driver);
		loginPage = new LoginPage(driver);
		myAccountPage = new MyAccountPage(driver);
		addPersonalDetails = new AddPersonalDetails(driver);
	}

	@AfterClass
	public void afterClass() {

		if (driver != null) {
//			driver.quit();
		}

	}

	@AfterSuite
	public void afterSuite() {
		if (driver != null) {
//			driver.quit();
		}
		Reporter.log("Driver quit");
		endTime = System.nanoTime();
		long dur = endTime - startTime;
		long convert = TimeUnit.SECONDS.convert(dur, TimeUnit.NANOSECONDS);
		Reporter.log("Duration of Suite is : " + convert + " sec.");
		System.out.println("Duration of Suite is : " + convert + " sec.");

	}

}
