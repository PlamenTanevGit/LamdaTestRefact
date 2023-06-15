package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.LocatorType;
import Utils.TestUtil;

public class TopHeaderPage  {

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;
	private TestUtil testUtil;
	
	public TopHeaderPage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, 5);
		this.testUtil = new TestUtil();
	}

	
	public WebElement topHeaderMenusRow() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']");
	}	
	
	public WebElement shopByCattegory() {
		return testUtil.findElement(LocatorType.XPATH,
				"//a[normalize-space()='Shop by Category']");
	}

	public WebElement homeMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//a[normalize-space()='Home']");
	}
	
	public WebElement specialMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='Special']");
	}
	
	public WebElement blogMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='Blog']");
	}
	
	public WebElement megaMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='Mega Menu']");
	}
	
	public WebElement addOnsMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='AddOns']");
	}
	
	public WebElement myAccountMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='My account']");
	}
	
	public WebElement dashboardMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='Dashboard']");
	}
	
	public WebElement  myOrderMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='My order']");
	}
	
	public WebElement returnMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='Return']");
	}
	
	public WebElement trackingMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//a[@class='icon-left both dropdown-item']//span[@class='title'][normalize-space()='Tracking']");
	}
	
	public WebElement myVoucherMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='My voucher']");
	}
	
	public WebElement logoutMenu() {
		return testUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='Logout']");
	}
	
	public void verifyMyAccountLoggedUserDropdown() {
		wait.until(ExpectedConditions.visibilityOf(myAccountMenu()));
        actions.moveToElement(myAccountMenu()).build().perform();
        wait.until(ExpectedConditions.visibilityOf(dashboardMenu()));
        
		testUtil.verifyEqualTexts(dashboardMenu(),"Dashboard");
		testUtil.verifyEqualTexts(myOrderMenu(),"My order");
		testUtil.verifyEqualTexts(returnMenu(),"Return");
		testUtil.verifyEqualTexts(trackingMenu(),"Tracking");
		testUtil.verifyEqualTexts(myVoucherMenu(),"My voucher");
		testUtil.verifyEqualTexts(logoutMenu(),"Logout");
		
	}
	
	
	public void logOut () throws InterruptedException {		
		AccountRegisterPage accountRegisterPage = new AccountRegisterPage(driver);
		LoginPage loginPage = new LoginPage(driver);

		wait.until(ExpectedConditions.visibilityOf(myAccountMenu()));
        actions.moveToElement(myAccountMenu()).build().perform();

        wait.until(ExpectedConditions.visibilityOf(logoutMenu()));
        actions.click(logoutMenu()).build().perform();
        
        wait.until(ExpectedConditions.visibilityOf(accountRegisterPage.accountLogoutMessage()));
        actions.click(accountRegisterPage.accountLogoutContinueButton());
        wait.until(ExpectedConditions.visibilityOf(loginPage.logOutMessage()));
		
	}
	
	public void headerElmentsVerify() {
		testUtil.assertIsDisplayed(topHeaderMenusRow());
		testUtil.assertIsDisplayed(shopByCattegory());
		testUtil.assertIsDisplayed(homeMenu());
		testUtil.assertIsDisplayed(specialMenu());
		testUtil.assertIsDisplayed(blogMenu());
		testUtil.assertIsDisplayed(megaMenu());
		testUtil.assertIsDisplayed(addOnsMenu());
		testUtil.assertIsDisplayed(myAccountMenu());

		testUtil.verifyEqualTexts(shopByCattegory(),"Shop by Category");
		testUtil.verifyEqualTexts(homeMenu(),"Home");
		testUtil.verifyEqualTexts(specialMenu(),"Special");
		testUtil.verifyEqualTexts(blogMenu(),"Blog");
		testUtil.verifyEqualTexts(megaMenu(),"Mega Menu");
		testUtil.verifyEqualTexts(addOnsMenu(),"AddOns");
		testUtil.verifyEqualTexts(myAccountMenu(),"My account");

	}

	
}
