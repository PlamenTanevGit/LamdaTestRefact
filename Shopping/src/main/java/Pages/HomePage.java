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
	private TestUtil testUtil;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.testUtil = new TestUtil(driver);

	}

	public WebElement searchField() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='entry_217822']//input[@placeholder='Search For Products']");
	}

	public WebElement searchButton() {
		return testUtil.findElement(LocatorType.XPATH, "//button[normalize-space()='Search']");
	}

	public WebElement compareButton() {
		return testUtil.findElement(LocatorType.XPATH, "//a[@aria-label='Compare']");
	}

	public WebElement whishlistButton() {
		return testUtil.findElement(LocatorType.CSS,
				"[class='entry-design design-link flex-grow-0 flex-shrink-0']:nth-of-type(4) svg");
	}

	public WebElement cartButton() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='entry_217825']//a[@role='button']");
	}

	public WebElement topRowButtonsSection() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']");
	}

	public WebElement topRowButtonsSection_shopByCattergory_Button() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']/div[@id='entry_217832']");
	}

	public WebElement topRowButtonsSection_Home_Button() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Home']");
	}

	public WebElement topRowButtonsSection_Special_Button() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Special']");
	}

	public WebElement topRowButtonsSection_Blog_Button() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Blog']");
	}

	public WebElement topRowButtonsSection_MegaMenu_Button() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='Mega Menu']");
	}

	public WebElement topRowButtonsSection_AddOns_Button() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='AddOns']");
	}

	public WebElement topRowButtonsSection_MyAccount_Button() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@class='entry-section container d-none d-md-flex flex-row align-items-center']//span[normalize-space()='My account']");
	}

	public WebElement topCategories(String category) {
		return testUtil.findElement(LocatorType.XPATH,"//div[@id='widget-navbar-217841']//ul[@class='navbar-nav vertical']//span[normalize-space()='"	+ category + "']");
	}
	
	public WebElement components () {
		return testUtil.findElement(LocatorType.XPATH,"//span[normalize-space()='Components']");
	}

	public void openSideBar() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		testUtil.clickOnElement(topRowButtonsSection_shopByCattergory_Button());
		wait.until(ExpectedConditions.visibilityOf(components()));
	}

	public void enterProductInSearchField (String product) {
		testUtil.typeOnField(searchField(), product);
	}
	
	public void clickOnSearch () {
		testUtil.clickOnElement(searchButton());
	}
	
	public void searchForProducts (String product) throws InterruptedException {
		enterProductInSearchField ( product);
		clickOnSearch();
		testUtil.pause(5);
		
	}
	
	public void homePageComponentsVerify() {
		
		testUtil.assertIsDisplayed(searchField());
		testUtil.assertIsDisplayed(searchButton());
		testUtil.assertIsDisplayed(compareButton());
		testUtil.assertIsDisplayed(whishlistButton());
		testUtil.assertIsDisplayed(cartButton());
		testUtil.assertIsDisplayed(topRowButtonsSection());
		testUtil.assertIsDisplayed(topRowButtonsSection_shopByCattergory_Button());
		testUtil.assertIsDisplayed(topRowButtonsSection_Home_Button());
		testUtil.assertIsDisplayed(topRowButtonsSection_Special_Button());
		testUtil.assertIsDisplayed(topRowButtonsSection_Blog_Button());
		testUtil.assertIsDisplayed(topRowButtonsSection_MegaMenu_Button());
		testUtil.assertIsDisplayed(topRowButtonsSection_AddOns_Button());
		testUtil.assertIsDisplayed(topRowButtonsSection_MyAccount_Button());

	}

	

}
