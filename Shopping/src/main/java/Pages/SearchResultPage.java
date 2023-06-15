package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utils.LocatorType;
import Utils.TestUtil;

public class SearchResultPage  {
	
	private WebDriver driver;
	private WebDriverWait	wait;
	private TestUtil testUtil;
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		this.wait =  new WebDriverWait(driver, 5);
		this.testUtil = new TestUtil();
	}

	public WebElement topLeftIcon() {
		return testUtil.findElement(LocatorType.XPATH,
				"//li[@class='breadcrumb-item active']");
	}

	public WebElement filterMenuTitle() {
		return testUtil.findElement(LocatorType.XPATH,
				"//h3[normalize-space()='Filter']");
	}
	
	public WebElement filterMenuPriceLabel() {
		return testUtil.findElement(LocatorType.XPATH,
				"(//div[contains(@class,'mz-filter-group-header')][normalize-space()='Price'])[2]");
	}
	
	public WebElement filterMenuManufacturerLabel() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Manufacturer']");
	}
	
	public WebElement filterMenuSearchLabel() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Search']");
	}
	
	public WebElement filterMenuColorLabel() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Color']");
	}
	
	public WebElement filterMenuAvailabilityLabel() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[@class='mz-filter-group-header '][normalize-space()='Availability']");
	}
	
	public WebElement filterMenuDiscountLabel() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Discount']");
	}
	
	public WebElement filterMenuRatingLabel() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='mz-filter-content-0']//div[contains(@class,'mz-filter-group-header')][normalize-space()='Rating']");
	}
	
	
	public WebElement searchResultsTitle(String product) {
		return testUtil.findElement(LocatorType.XPATH,
				"//h1[normalize-space()='Search - "+product+"']");
	}
	
	public WebElement product  (String productNum) {
//		return testUtil.findElement(LocatorType.XPATH,
//				"//body/div[@class='mz-pure-container']//div[@class='row']/div["+productNum+"]");
//		

		return testUtil.findElement(LocatorType.CSS,
				".content-products.entry-content.order-4.order-md-3 > div > div:nth-of-type("+productNum+")");
	}	
	
	public WebElement addToCartButton() {
		return testUtil.findElement(LocatorType.XPATH,
				"(//i[@class='fas fa-shopping-cart'])[1]");
	}
	
	public WebElement addToCartPopUp() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='notification-box-top']");
	}
	
	public WebElement viewCartButton() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='notification-box-top']//a[normalize-space()='View Cart']");
	}
	
	public WebElement checkoutButoon() {
		return testUtil.findElement(LocatorType.XPATH,
				"//div[@id='notification-box-top']//a[normalize-space()='Checkout']");
	}

	public void addToCartMultipleItems(String itemNumber, int itemsNumber) throws InterruptedException {
		testUtil.movesToTheElement(product(itemNumber));
		for (int i = 0; i < itemsNumber; i++) {
			testUtil.clickOnElement(addToCartButton());
			// testUtil.pausems(3);	
			testUtil.waitForElementPresntUsingFluentWait(By.cssSelector(".module-title.h4.m-0"), 5, 1);		
		}
		testUtil.clickOnElement(viewCartButton());

	}
	
	public void addMultipleItems(String itemNumber, int itemsNumber) throws InterruptedException {
			testUtil.movesToTheElement(product(itemNumber));
			for (int i = 0; i < itemsNumber; i++) {
				testUtil.clickOnElement(addToCartButton());
				// testUtil.pausems(5);	
				testUtil.waitForElementPresntUsingFluentWait(By.cssSelector(".module-title.h4.m-0"), 5, 1);			
			}
			testUtil.clickOnElement(checkoutButoon());	
	
	}
	
	public void addToCart(String itemNumber) {
		testUtil.movesToTheElement(product(itemNumber));
		testUtil.clickOnElement(addToCartButton());
		wait.until(ExpectedConditions.visibilityOf(addToCartPopUp()));

	}
	
	public void selectViewCart() {
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		testUtil.movesToTheElement(viewCartButton());
		testUtil.clickOnElement(viewCartButton());			
		wait.until(ExpectedConditions.visibilityOf(shoppingCartPage.shoppingCart_topLeftIcon()));

	}
	
	public void selectCheckout() {
		AccountRegisterPage accountRegisterPage = new AccountRegisterPage(driver);
		testUtil.movesToTheElement(checkoutButoon());
		testUtil.clickOnElement(checkoutButoon());			
		wait.until(ExpectedConditions.visibilityOf(accountRegisterPage.yourPersonalDetailsWindow()));
	}
	
	public String getPriceFromArticle (String artcileNumber) {		
		return testUtil.findElement
	(LocatorType.XPATH, "//div[@class='row']//div[1]//div[1]//div[2]//div[1]//span["+artcileNumber+"]").getText();
		
	}
	
	public double getThePriceAmount (String price) {
		return testUtil.getDoubleFromStringValue(price);
	}
	
	
	
	public void componentsVerify () {
		
		testUtil.assertIsDisplayed(topLeftIcon());
		
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
