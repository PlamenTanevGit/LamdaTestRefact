package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utils.LocatorType;
import Utils.TestUtil;


public class ShoppingCartPage {
	
	
	private WebDriver driver;
	private CheckoutPage checkoutPage;
	private HomePage homePage;
	private WebDriverWait wait ;
	private TestUtil testUtil;
	
	public ShoppingCartPage(WebDriver driver ) {
		this.driver = driver;		
		this.checkoutPage = new CheckoutPage(driver);
		this.homePage = new HomePage(driver);
		this.wait = new WebDriverWait(driver, 5);
		this.testUtil = new TestUtil(driver);
	}

	
	
	public WebElement shoppingCart_topLeftIcon() {
		return testUtil.findElement(LocatorType.XPATH, "//li[@class='breadcrumb-item active']");	
	}
	
	public WebElement checkoutGrid() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='content']//form");	
	}
	
	public WebElement qunatityField() {
		return testUtil.findElement(LocatorType.CSS,".flex-nowrap.input-group>input");	
	}
	
	public WebElement imageTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//th[@class='text-center']");	
	}
	
	public WebElement productNameTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Product Name']");	
	}
	
	public WebElement modelTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Model']");	
	}
	
	public WebElement quantityTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Quantity']");	
	}
	
	public WebElement unitPriceTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Unit Price']");	
	}
	
	public WebElement totalTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Total']");	
	}
	
	public WebElement updateButton() {
		return testUtil.findElement(LocatorType.XPATH, "//button[@data-original-title='Update']");	
	}
	
	public WebElement removeButton() {
		return testUtil.findElement(LocatorType.XPATH, "//button[@class='btn btn-danger']");	
	}
	
	public WebElement titleLink(String productName) {
		return testUtil.findElement(LocatorType.XPATH, "//td[@class='text-left']//a[contains(text(),'"+productName+"')]");	
	}
	
	public WebElement image(String productName) {
		return testUtil.findElement(LocatorType.XPATH, "//td[@class='text-center']//img[@title='"+productName+"']");	
	}
	
	public WebElement model (String model) {
		return testUtil.findElement(LocatorType.XPATH, "//td[contains(text(),'"+model+"')]");	
	}
	
	public WebElement unitPrice() {
		return testUtil.findElement(LocatorType.XPATH, "/html//table[1]/tbody[1]/tr[1]/td[5]");
	
	}
	
	public WebElement totalPrice() {
		return testUtil.findElement(LocatorType.XPATH, "/html//table[1]/tbody[1]/tr[1]/td[6]");	
	}
	
	public WebElement messageEmptyCart() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='content']/p[.='Your shopping cart is empty!']");	
	}
	
	public WebElement CheckoutButton() {
		return testUtil.findElement(LocatorType.XPATH, "//a[@class='btn btn-lg btn-primary']");	
	}
	
	public WebElement ContinueShoppingButton() {
		return testUtil.findElement(LocatorType.XPATH, "//a[normalize-space()='Continue Shopping']");	
	}
	
	public WebElement ContiniueButton() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@class='buttons']/a");
	}
	
	public WebElement insufficientProductsMessage() {
		return testUtil.findElement(LocatorType.XPATH, "//div[contains(text(),'Products marked with *** are not available')]");
	}
	
	public void clickOnContinueShopping () {
		
		testUtil.clickOnElement(ContinueShoppingButton());
		wait.until(ExpectedConditions.visibilityOf(homePage.searchButton()));
	}	

	public void clickOnContinueButton () {
		testUtil.clickOnElement(ContiniueButton());
		wait.until(ExpectedConditions.visibilityOf(homePage.topRowButtonsSection_shopByCattergory_Button()));
	}
	

	public void clickCheckout () {
			testUtil.clickOnElement(CheckoutButton());			
			wait.until(ExpectedConditions.visibilityOf(checkoutPage.topLeftIcon()));
	}

	
	public void clickOnRemoveQuantity () {
			testUtil.clickOnElement(removeButton());
			wait.until(ExpectedConditions.visibilityOf(messageEmptyCart()));
	}
	
	
	public double  getUnitPrice () {
		return testUtil.getDoubleFromWebElement(unitPrice());				
	}
	
	public double  getTotalPrice () {					
		return testUtil.getDoubleFromWebElement(totalPrice());		
		
	}
		
	public double  getSubTotalValue () {		
		return testUtil.getDoubleFromWebElement(testUtil.findElement(LocatorType.XPATH, "//table[@class='table table-bordered m-0']//td[normalize-space()='Sub-Total:']//following-sibling::td"));				
	}
	
	
	public double  getEcoTaxValue () {
		return testUtil.getDoubleFromWebElement(testUtil.findElement(LocatorType.XPATH, "//table[@class='table table-bordered m-0']//td[normalize-space()='Eco Tax (-2.00):']//following-sibling::td"));			
	}
	
	public double  getVATValue () {
		return testUtil.getDoubleFromWebElement(testUtil.findElement(LocatorType.XPATH, "//table[@class='table table-bordered m-0']//td[normalize-space()='VAT (20%):']//following-sibling::td"));			
	}
	
	public double  getTotalValue (String VAT) {
		double TotalValue = 0;
		String TotalStringValue = null;
		switch (VAT) {
		case "VAT_YES":
			TotalStringValue = testUtil.findElement
			(LocatorType.XPATH, "//div[@class='row mb-3 align-items-end']//table[@class='table table-bordered m-0']/tbody/tr[4]/td[2]").getText();
			break;
		case "VAT_NO":
			TotalStringValue = testUtil.findElement
			(LocatorType.XPATH, "//div[@class='row mb-3 align-items-end']//table[@class='table table-bordered m-0']/tbody/tr[2]/td[2]").getText();
			break;
		default:
			break;
		}
		
		String TotalStringValueModified = TotalStringValue.replace("$", "");
		TotalValue= Double.parseDouble(TotalStringValueModified);	
		
		return TotalValue;	
		
	}
		
	public void componentsVerify (String productName,String model,String quantity,String unitPrice,String totalPrice) {
		
		testUtil.assertIsDisplayed(checkoutGrid());
		testUtil.verifyEqualTexts(imageTitle(), "IMAGE");
		testUtil.verifyEqualTexts(productNameTitle(), "PRODUCT NAME");
		testUtil.verifyEqualTexts(modelTitle(), "MODEL");
		testUtil.verifyEqualTexts(quantityTitle(), "QUANTITY");
		testUtil.verifyEqualTexts(unitPriceTitle(), "UNIT PRICE");
		testUtil.verifyEqualTexts(totalTitle(), "TOTAL");
		
		Assert.assertTrue(image(productName).isDisplayed());
		Assert.assertTrue(titleLink(productName).isDisplayed());
		Assert.assertTrue(model(model).isDisplayed());
		Assert.assertTrue(qunatityField().getAttribute("value").equals(quantity));
		Assert.assertTrue(unitPrice().getText().equals(unitPrice));
		Assert.assertTrue(totalPrice().getText().equals(totalPrice));		
		
	}
	

	public double assertTotalTop_value (double expectedPrice) {
		double TotalGridPrice = getTotalPrice();
		Assert.assertEquals(TotalGridPrice, (expectedPrice));
		return TotalGridPrice;
		
	}
	
	public int assertEcoTaxValue (int expectedValue) {
		int EcoTaxValue = testUtil.convertDoubleToInt(getEcoTaxValue());
		Assert.assertEquals(EcoTaxValue, (expectedValue));		
		return EcoTaxValue;
		
	}
	
	
	public double assertTotalBottom_value (String VAT,double expectedValue) {
		double TotalBottomValue = getTotalValue(VAT);	
		Assert.assertEquals(TotalBottomValue, (expectedValue));
		return TotalBottomValue;
		
	}

	

}
