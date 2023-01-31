package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utils.LocatorType;
import Utils.TestUtil;

public class SearchResultPage  {
	
	private WebDriver driver;
	private ShoppingCartPage shoppingCartPage;
	private AccountRegisterPage accountRegisterPage;
	private WebDriverWait	wait;
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		this.shoppingCartPage = new ShoppingCartPage(driver);
		this.accountRegisterPage = new AccountRegisterPage(driver);
		this.wait =  new WebDriverWait(driver, 5);
	}

	public WebElement topLeftIcon() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//li[@class='breadcrumb-item active']");
	}

	public WebElement filterMenuTitle() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//h3[normalize-space()='Filter']");
	}
	
	public WebElement filterMenuPriceLabel() {
		return TestUtil.findElement(LocatorType.XPATH,
				"(//div[contains(@class,'mz-filter-group-header')][normalize-space()='Price'])[2]");
	}
	
	public WebElement filterMenuManufacturerLabel() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Manufacturer']");
	}
	
	public WebElement filterMenuSearchLabel() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Search']");
	}
	
	public WebElement filterMenuColorLabel() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Color']");
	}
	
	public WebElement filterMenuAvailabilityLabel() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[@class='mz-filter-group-header '][normalize-space()='Availability']");
	}
	
	public WebElement filterMenuDiscountLabel() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Discount']");
	}
	
	public WebElement filterMenuRatingLabel() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Rating']");
	}
	
	
	public WebElement searchResultsTitle(String product) {
		return TestUtil.findElement(LocatorType.XPATH,
				"//h1[normalize-space()='Search - "+product+"']");
	}
	
	public WebElement product  (String productNum) {
		return TestUtil.findElement(LocatorType.XPATH,
				"//body/div[@class='mz-pure-container']//div[@class='row']/div["+productNum+"]");
	}	
	
	public WebElement addToCartButton() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//button[@class='btn btn-cart cart-28']");
	}
	
	public WebElement addToCartPopUp() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='notification-box-top']");
	}
	
	public WebElement viewCartButton() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='notification-box-top']//a[normalize-space()='View Cart']");
	}
	
	public WebElement checkoutButoon() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//div[@id='notification-box-top']//a[normalize-space()='Checkout']");
	}

	public void addToCartMultipleItems(String itemNumber, int itemsNumber) throws InterruptedException {
		TestUtil.movesToTheElement(product(itemNumber));
		for (int i = 0; i < itemsNumber; i++) {
			TestUtil.clickOnElement(addToCartButton());
			Thread.sleep(150);			
		}
		TestUtil.clickOnElement(viewCartButton());

	}
	
	public void checkoutMultipleItems(String itemNumber, int itemsNumber) throws InterruptedException {
			TestUtil.movesToTheElement(product(itemNumber));
			for (int i = 0; i < itemsNumber; i++) {
				TestUtil.clickOnElement(addToCartButton());
				Thread.sleep(150);			
			}
			TestUtil.clickOnElement(checkoutButoon());	
	
	}
	
	public void addToCart(String itemNumber) {
		TestUtil.movesToTheElement(product(itemNumber));
		TestUtil.clickOnElement(addToCartButton());
		wait.until(ExpectedConditions.visibilityOf(addToCartPopUp()));

	}
	
	public void selectViewCart() {
		TestUtil.movesToTheElement(viewCartButton());
		TestUtil.clickOnElement(viewCartButton());			
		wait.until(ExpectedConditions.visibilityOf(shoppingCartPage.shoppingCart_topLeftIcon()));

	}
	
	public void selectCheckout() {
		TestUtil.movesToTheElement(checkoutButoon());
		TestUtil.clickOnElement(checkoutButoon());			
		wait.until(ExpectedConditions.visibilityOf(accountRegisterPage.yourPersonalDetailsWindow()));
	}
	
	public String getPriceFromArticle (String artcileNumber) {		
		return TestUtil.findElement
	(LocatorType.XPATH, "//div[@class='row']//div[1]//div[1]//div[2]//div[1]//span["+artcileNumber+"]").getText();
		
	}
	
	public double getThePriceAmount (String price) {
		return TestUtil.getDoubleFromStringValue(price);
	}
	
	
	
	public void componentsVerify () {
		
		TestUtil.assertIsDisplayed(topLeftIcon());
		
		Assert.assertTrue(filterMenuTitle().getText().equals("FILTER"));
		Assert.assertTrue(filterMenuPriceLabel().getText().equals("PRICE"));
		Assert.assertTrue(filterMenuManufacturerLabel().getText().equals("MANUFACTURER"));
		Assert.assertTrue(filterMenuSearchLabel().getText().equals("SEARCH"));
		Assert.assertTrue(filterMenuColorLabel().getText().equals("COLOR"));
		Assert.assertTrue(filterMenuAvailabilityLabel().getText().equals("AVAILABILITY"));
		Assert.assertTrue(filterMenuDiscountLabel().getText().equals("DISCOUNT"));
		Assert.assertTrue(filterMenuRatingLabel().getText().equals("RATING"));

	}

	

}
