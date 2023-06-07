package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.DriverFactory;
import Utils.LocatorType;
import Utils.TestUtil;


public class HomePage {
	
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement searchField() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217822']//input[@placeholder='Search For Products']");
	}

	public WebElement searchButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//button[normalize-space()='Search']");
	}

	public WebElement compareButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//a[@aria-label='Compare']");
	}

	public WebElement whishlistButton() {
		return TestUtil.findElement(LocatorType.CSS,
				"[class='entry-design design-link flex-grow-0 flex-shrink-0']:nth-of-type(4) svg");
	}

	public WebElement cartButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='entry_217825']//a[@role='button']");
	}

	public WebElement topRowButtonsSection() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']");
	}

	public WebElement topRowButtonsSection_shopByCattergory_Button() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']/div[@id='entry_217832']");
	}

	public WebElement topRowButtonsSection_Home_Button() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Home']");
	}

	public WebElement topRowButtonsSection_Special_Button() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Special']");
	}

	public WebElement topRowButtonsSection_Blog_Button() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Blog']");
	}

	public WebElement topRowButtonsSection_MegaMenu_Button() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Mega Menu']");
	}

	public WebElement topRowButtonsSection_AddOns_Button() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='AddOns']");
	}

	public WebElement topRowButtonsSection_MyAccount_Button() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='My account']");
	}

	public WebElement topCategories(String category) {
		return TestUtil.findElement(LocatorType.XPATH,"//div[@id='widget-navbar-217841']//ul[@class='navbar-nav vertical']//span[normalize-space()='"	+ category + "']");
	}
	
	public WebElement components () {
		return TestUtil.findElement(LocatorType.XPATH,"//span[normalize-space()='Components']");
	}

	public void openSideBar() {
		WebDriverWait wait = new WebDriverWait(driver, DriverFactory.TIMEOUT);
		TestUtil.clickOnElement(topRowButtonsSection_shopByCattergory_Button());
		wait.until(ExpectedConditions.visibilityOf(components()));
	}

	public void enterProductInSearchField (String product) {
		TestUtil.typeOnField(searchField(), product);
	}
	
	public void clickOnSearch () {
		TestUtil.clickOnElement(searchButton());
	}
	
	public void searchForProducts (String product) throws InterruptedException {
		enterProductInSearchField ( product);
		clickOnSearch();
		TestUtil.pause(5);
		
	}
	
	public void homePageComponentsVerify() {
		
		TestUtil.assertIsDisplayed(searchField());
		TestUtil.assertIsDisplayed(searchButton());
		TestUtil.assertIsDisplayed(compareButton());
		TestUtil.assertIsDisplayed(whishlistButton());
		TestUtil.assertIsDisplayed(cartButton());
		TestUtil.assertIsDisplayed(topRowButtonsSection());
		TestUtil.assertIsDisplayed(topRowButtonsSection_shopByCattergory_Button());
		TestUtil.assertIsDisplayed(topRowButtonsSection_Home_Button());
		TestUtil.assertIsDisplayed(topRowButtonsSection_Special_Button());
		TestUtil.assertIsDisplayed(topRowButtonsSection_Blog_Button());
		TestUtil.assertIsDisplayed(topRowButtonsSection_MegaMenu_Button());
		TestUtil.assertIsDisplayed(topRowButtonsSection_AddOns_Button());
		TestUtil.assertIsDisplayed(topRowButtonsSection_MyAccount_Button());

	}

	

}
