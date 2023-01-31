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
	private AccountRegisterPage accountRegisterPage;
	private LoginPage loginPage;
	
	public TopHeaderPage(WebDriver driver) {
		this.driver = driver;
		this.accountRegisterPage = new AccountRegisterPage(driver);
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, 5);
		this.loginPage = new LoginPage(driver);
	}

	
	public WebElement topHeaderMenusRow() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']");
	}	
	
	public WebElement shopByCattegory() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//a[normalize-space()='Shop by Category']");
	}

	public WebElement homeMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//a[normalize-space()='Home']");
	}
	
	public WebElement specialMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='Special']");
	}
	
	public WebElement blogMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='Blog']");
	}
	
	public WebElement megaMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='Mega Menu']");
	}
	
	public WebElement addOnsMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='AddOns']");
	}
	
	public WebElement myAccountMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217831']//div[normalize-space()='My account']");
	}
	
	public WebElement dashboardMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='Dashboard']");
	}
	
	public WebElement  myOrderMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='My order']");
	}
	
	public WebElement returnMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='Return']");
	}
	
	public WebElement trackingMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//a[@class='icon-left both dropdown-item']//span[@class='title'][normalize-space()='Tracking']");
	}
	
	public WebElement myVoucherMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='My voucher']");
	}
	
	public WebElement logoutMenu() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//span[normalize-space()='Logout']");
	}
	
	public void verifyMyAccountLoggedUserDropdown() {
		wait.until(ExpectedConditions.visibilityOf(myAccountMenu()));
        actions.moveToElement(myAccountMenu()).build().perform();
        wait.until(ExpectedConditions.visibilityOf(dashboardMenu()));
        
		TestUtil.verifyEqualTexts(dashboardMenu(),"Dashboard");
		TestUtil.verifyEqualTexts(myOrderMenu(),"My order");
		TestUtil.verifyEqualTexts(returnMenu(),"Return");
		TestUtil.verifyEqualTexts(trackingMenu(),"Tracking");
		TestUtil.verifyEqualTexts(myVoucherMenu(),"My voucher");
		TestUtil.verifyEqualTexts(logoutMenu(),"Logout");
		
	}
	
	
	public void logOut () throws InterruptedException {		
		wait.until(ExpectedConditions.visibilityOf(myAccountMenu()));
        actions.moveToElement(myAccountMenu()).build().perform();

        wait.until(ExpectedConditions.visibilityOf(logoutMenu()));
        actions.click(logoutMenu()).build().perform();
        
        wait.until(ExpectedConditions.visibilityOf(accountRegisterPage.accountLogoutMessage()));
        actions.click(accountRegisterPage.accountLogoutContinueButton());
        wait.until(ExpectedConditions.visibilityOf(loginPage.logOutMessage()));
		
	}
	
	public void headerElmentsVerify() {
		TestUtil.assertIsDisplayed(topHeaderMenusRow());
		TestUtil.assertIsDisplayed(shopByCattegory());
		TestUtil.assertIsDisplayed(homeMenu());
		TestUtil.assertIsDisplayed(specialMenu());
		TestUtil.assertIsDisplayed(blogMenu());
		TestUtil.assertIsDisplayed(megaMenu());
		TestUtil.assertIsDisplayed(addOnsMenu());
		TestUtil.assertIsDisplayed(myAccountMenu());

		TestUtil.verifyEqualTexts(shopByCattegory(),"Shop by Category");
		TestUtil.verifyEqualTexts(homeMenu(),"Home");
		TestUtil.verifyEqualTexts(specialMenu(),"Special");
		TestUtil.verifyEqualTexts(blogMenu(),"Blog");
		TestUtil.verifyEqualTexts(megaMenu(),"Mega Menu");
		TestUtil.verifyEqualTexts(addOnsMenu(),"AddOns");
		TestUtil.verifyEqualTexts(myAccountMenu(),"My account");

	}

	
}
