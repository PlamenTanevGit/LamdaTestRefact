package Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import Pages.AccountRegisterPage;
import Pages.AddPersonalDetails;
import Pages.CheckoutPage;
import Pages.ConfirmOrderPage;
import Pages.FascadePage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import Pages.SearchResultPage;
import Pages.ShoppingCartPage;
import Pages.SuccessPage;
import Pages.TopHeaderPage;

public class TestUtil {

	public WebDriver driver;
	public WebDriverWait wait;
	public SoftAssert softAssert;
	public WebElement element;
	public JavascriptExecutor js;
	String screenshotName;
	Actions actions;

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
	protected FascadePage fascadePage;
	protected TestBase testBase;

	public TestUtil() {
		this.testBase = new TestBase();

	}

	public void openUrl(String url) {
		TestBase.tDriver.get().navigate().to(url);
		Reporter.log("Successfully opened url '" + url + "'");
	}

	public void assertIsDisplayed(WebElement element) {
		Assert.assertTrue(element.isDisplayed());
	}

	public WebElement locateElement(LocatorType locatorType, String locatorPath) {

		wait = new WebDriverWait(TestBase.tDriver.get(), DriverFactory.TIMEOUT);

		switch (locatorType) {
		case ID:
			return wait.until(ExpectedConditions.visibilityOf(TestBase.tDriver.get().findElement(By.id(locatorPath))));
		case XPATH:
			return wait
					.until(ExpectedConditions.visibilityOf(TestBase.tDriver.get().findElement(By.xpath(locatorPath))));
		case NAME:
			return wait
					.until(ExpectedConditions.visibilityOf(TestBase.tDriver.get().findElement(By.name(locatorPath))));
		case CLASSNAME:
			return wait.until(
					ExpectedConditions.visibilityOf(TestBase.tDriver.get().findElement(By.className(locatorPath))));
		case CSS:
			return wait.until(
					ExpectedConditions.visibilityOf(TestBase.tDriver.get().findElement(By.cssSelector(locatorPath))));
		case LINKEDTEXT:
			return wait.until(
					ExpectedConditions.visibilityOf(TestBase.tDriver.get().findElement(By.linkText(locatorPath))));
		case TAGNAME:
			return wait.until(
					ExpectedConditions.visibilityOf(TestBase.tDriver.get().findElement(By.tagName(locatorPath))));

		default:
			throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorPath);
		}

	}

	public List<WebElement> getElementList(String locator, LocatorType type) {

		List<WebElement> elementList = new ArrayList<WebElement>();
		if (type == LocatorType.ID) {
			elementList = TestBase.tDriver.get().findElements(By.id(locator));

		} else if (type == LocatorType.NAME) {
			elementList = TestBase.tDriver.get().findElements(By.name(locator));
		}

		else if (type == LocatorType.XPATH) {
			elementList = TestBase.tDriver.get().findElements(By.xpath(locator));

		} else if (type == LocatorType.CSS) {
			elementList = TestBase.tDriver.get().findElements(By.cssSelector(locator));

		} else if (type == LocatorType.CLASSNAME) {
			elementList = TestBase.tDriver.get().findElements(By.className(locator));

		} else if (type == LocatorType.TAGNAME) {
			elementList = TestBase.tDriver.get().findElements(By.tagName(locator));

		} else if (type == LocatorType.LINKEDTEXT) {
			elementList = TestBase.tDriver.get().findElements(By.linkText(locator));
		} else if (type == LocatorType.PARTIALLINKTEXT) {
			elementList = TestBase.tDriver.get().findElements(By.partialLinkText(locator));
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

	public WebElement findElement(LocatorType locatorType, String locatorPath) {
		try {
			element = locateElement(locatorType, locatorPath);
		} catch (StaleElementReferenceException e) {
			System.out.println(e);
		}

		return element;
	}

	public WebElement typeOnField(WebElement element, String value) {
		HighLightElement(element);
		element.clear();
		element.sendKeys(value);
		Reporter.log(" Enter value : " + value);

		return element;
	}

	public void HighLightElement(WebElement element) {

		js = (JavascriptExecutor) TestBase.tDriver.get();
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", element);
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
	}

	public void jSClick(WebElement element) {
		wait = new WebDriverWait(TestBase.tDriver.get(), DriverFactory.TIMEOUT);
		js = (JavascriptExecutor) TestBase.tDriver.get();
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].click();", element);
		Reporter.log("Successfully click on element " + element.getText());
	}

	public void clickOnElement(WebElement element) {
		wait = new WebDriverWait(TestBase.tDriver.get(), DriverFactory.TIMEOUT);
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			Reporter.log("Successfully click on element " + element.getText());
		} catch (StaleElementReferenceException e) {
			System.out.println(e);
		}
	}

	public void pseudoClickJavascript(String cssPath, String key) {

		switch (key) {

		case "before":
			((JavascriptExecutor) driver).executeScript("document.querySelector(arguments[0],':before').click();",
					cssPath);
			break;

		case "after":
			((JavascriptExecutor) driver).executeScript("document.querySelector(arguments[0],':after').click();",
					cssPath);
			break;

		default:
			break;
		}

	}

	public String selectDropdownOptionByTagName(WebElement dropdown, String tagName, String searchedOption)
			throws InterruptedException {
		actions = new Actions(TestBase.tDriver.get());
		clickOnElement(dropdown);
		List<WebElement> options = TestBase.tDriver.get().findElements(By.tagName(tagName));
		for (WebElement option : options) {
			if (option.getText().equals(searchedOption)) {
				clickOnElement(option);
				break;
			}
		}
		return searchedOption;
	}

	public String ScreenCapture() throws IOException {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		zoomOutPage(50);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File scrFile = ((TakesScreenshot) TestBase.tDriver.get()).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName;

		File screenshotName = new File(Path);

		FileUtils.copyFile(scrFile, screenshotName);
		Reporter.log("<a href=" + screenshotName + "></a>");
		Reporter.log("<br>  <img src='" + screenshotName + "' height='200' width='200' /><br>");

		return Path;

	}

	public void pageTitleVerify(String expected) {
		String actual = TestBase.tDriver.get().getTitle();
		Assert.assertEquals(actual, expected);
		Reporter.log("Page title  is Verified");
		System.out.println("Page title  is Verified");

	}

	public void zoomOutPage(int percent) {
		JavascriptExecutor js = (JavascriptExecutor) TestBase.tDriver.get();
		js.executeScript("document.body.style.zoom='" + percent + "%'");
	}

	public void verifyEqualTexts(WebElement element, String expected) {

		Assert.assertEquals(element.getText(), expected);
		Reporter.log("Text : " + element.getText() + " - is Verified");
		System.out.println("Text : " + element.getText() + " -  is Verified");

	}

	public void movesToTheElement(WebElement element) {
		actions = new Actions(TestBase.tDriver.get());
		wait = new WebDriverWait(TestBase.tDriver.get(), 5);

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

	public int convertDoubleToInt(double doubleAmount) {
		Double newData = new Double(doubleAmount);
		return newData.intValue();
	}

	public int convertStringToInt(String value) {
		return Integer.parseInt(value);
	}

	public String randomString(int lenght) {
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

	public double getDoubleFromWebElement(WebElement element) {

		String unitPriceStringValueShoppingCart = element.getText();
		String unitPriceStringValueShoppingCartModified = unitPriceStringValueShoppingCart.replace("$", "");
		return Double.parseDouble(unitPriceStringValueShoppingCartModified);
	}

	public double getDoubleFromStringValue(String stringValue) {
		String price1 = stringValue.replace("$", "");
		return Double.parseDouble(price1);
	}

	public String getScreenshot() throws WebDriverException {
		DriverFactory drvierFactory = new DriverFactory();
		File srcFile = ((TakesScreenshot) drvierFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public WebDriver getDriver() {
		return TestBase.tDriver.get();
	}

	public WebElement waitForElementPresntUsingFluentWait(By locator, int timeOut, int pollingTime) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class).withMessage("Element Not Found");
		} catch (NullPointerException e) {
			System.out.println(e);
		}

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

//	public  void pause(int seconds) {
//  try {
//      Thread.sleep(seconds * 1000);
//
//  } catch (Exception e) {
//      System.out.println("After waiting for " + seconds + " seconds");
//  }
//}
//
//public  void pausems(int mseconds) {
//  try {
//      Thread.sleep(mseconds * 100);
//
//  } catch (Exception e) {
//      System.out.println("After waiting for " + (mseconds *100) + " ms seconds");
//  }
//}
//	public  List<WebElement> visiblilityOfAllElements(By locator, int timeout) {
//	wait = new WebDriverWait(driver, timeout);
//	wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
//	return driver.findElements(locator);
//}

//public  WebElement waitForElementToBePresent(By locator, int timeout) {
//	wait = new WebDriverWait(driver, timeout);
//	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//	return getElement(locator);
//}

//public  WebElement waitForElementToBeClickable(By locator, int timeout) {
//	wait = new WebDriverWait(driver, timeout);
//	wait.until(ExpectedConditions.elementToBeClickable(locator));
//	return getElement(locator);
//}
//
//public  WebElement waitForElementToBeVisbile(By locator, int timeout) {
//	WebElement element = getElement(locator);
//	wait = new WebDriverWait(TestBase.tDriver.get(), timeout);
//	wait.until(ExpectedConditions.visibilityOf(element));
//	return element;
//}
//
//public  void clickWhenReady(By locator, int timeout) {
//	wait = new WebDriverWait(TestBase.tDriver.get(), timeout);
//	wait.until(ExpectedConditions.elementToBeClickable(locator));
//	getElement(locator).click();
//}
//
//public  WebElement getElement(By locator) {
//	WebElement element = driver.findElement(locator);
//	return element;
//}
//	public  void select(WebElement select1, String value) {
//
//		Select select = new Select(select1);
//		select.selectByVisibleText(value);
//		Reporter.log("Selecting from dropdown value as : " + value);
//
//	}

//	public  void JSclickOnWebElement(WebElement element) {
//		js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", element);
//	}

//	public  void scrollDown(WebElement Element) {
//		js = (JavascriptExecutor) TestBase.tDriver.get();
//		js.executeScript("arguments[0].scrollIntoView();", Element);
//
//	}

//	public  void assertElementIsNotDisplayed (By locator) {
//		List<WebElement> searchedElemeents = driver.findElements(locator);
//		Assert.assertTrue(searchedElemeents.isEmpty());
//	}

}
