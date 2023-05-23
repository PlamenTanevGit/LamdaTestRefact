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
	
	public ShoppingCartPage(WebDriver driver ) {
		this.driver = driver;		
		this.checkoutPage = new CheckoutPage(driver);
		this.homePage = new HomePage(driver);
		this.wait = new WebDriverWait(driver, 5);
	}

	
	
	public WebElement shoppingCart_topLeftIcon() {
		return TestUtil.findElement(LocatorType.XPATH, "//li[@class='breadcrumb-item active']");	
	}
	
	public WebElement checkoutGrid() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='content']//form");	
	}
	
	public WebElement qunatityField() {
		return TestUtil.findElement(LocatorType.CSS,".flex-nowrap.input-group>input");	
	}
	
	public WebElement imageTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//th[@class='text-center']");	
	}
	
	public WebElement productNameTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Product Name']");	
	}
	
	public WebElement modelTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Model']");	
	}
	
	public WebElement quantityTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Quantity']");	
	}
	
	public WebElement unitPriceTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Unit Price']");	
	}
	
	public WebElement totalTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//th[normalize-space()='Total']");	
	}
	
	public WebElement updateButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//button[@data-original-title='Update']");	
	}
	
	public WebElement removeButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//button[@class='btn btn-danger']");	
	}
	
	public WebElement titleLink(String productName) {
		return TestUtil.findElement(LocatorType.XPATH, "//td[@class='text-left']//a[contains(text(),'"+productName+"')]");	
	}
	
	public WebElement image(String productName) {
		return TestUtil.findElement(LocatorType.XPATH, "//td[@class='text-center']//img[@title='"+productName+"']");	
	}
	
	public WebElement model (String model) {
		return TestUtil.findElement(LocatorType.XPATH, "//td[contains(text(),'"+model+"')]");	
	}
	
	public WebElement unitPrice() {
		return TestUtil.findElement(LocatorType.XPATH, "/html//table[1]/tbody[1]/tr[1]/td[5]");
	
	}
	
	public WebElement totalPrice() {
		return TestUtil.findElement(LocatorType.XPATH, "/html//table[1]/tbody[1]/tr[1]/td[6]");	
	}
	
	public WebElement messageEmptyCart() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='content']/p[.='Your shopping cart is empty!']");	
	}
	
	public WebElement CheckoutButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//a[@class='btn btn-lg btn-primary']");	
	}
	
	public WebElement ContinueShoppingButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//a[normalize-space()='Continue Shopping']");	
	}
	
	public WebElement ContiniueButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@class='buttons']/a");
	}
	
	public void clickOnContinueShopping () {
		
		TestUtil.clickOnElement(ContinueShoppingButton());
		wait.until(ExpectedConditions.visibilityOf(homePage.searchButton()));
	}	

	public void clickOnContinueButton () {
		TestUtil.clickOnElement(ContiniueButton());
		wait.until(ExpectedConditions.visibilityOf(homePage.topRowButtonsSection_shopByCattergory_Button()));
	}
	

	public void clickCheckout () {
			TestUtil.clickOnElement(CheckoutButton());			
			wait.until(ExpectedConditions.visibilityOf(checkoutPage.topLeftIcon()));
	}

	
	public void clickOnRemoveQuantity () {
			TestUtil.clickOnElement(removeButton());
			wait.until(ExpectedConditions.visibilityOf(messageEmptyCart()));
	}
	
	
	public double  getUnitPrice () {
		return TestUtil.getDoubleFromWebElement(unitPrice());				
	}
	
	public double  getTotalPrice () {					
		return TestUtil.getDoubleFromWebElement(totalPrice());		
		
	}
		
	public double  getSubTotalValue () {		
		return TestUtil.getDoubleFromWebElement(TestUtil.findElement(LocatorType.XPATH, "//table[@class='table table-bordered m-0']//td[normalize-space()='Sub-Total:']//following-sibling::td"));				
	}
	
	
	public double  getEcoTaxValue () {
		return TestUtil.getDoubleFromWebElement(TestUtil.findElement(LocatorType.XPATH, "//table[@class='table table-bordered m-0']//td[normalize-space()='Eco Tax (-2.00):']//following-sibling::td"));			
	}
	
	public double  getVATValue () {
		return TestUtil.getDoubleFromWebElement(TestUtil.findElement(LocatorType.XPATH, "//table[@class='table table-bordered m-0']//td[normalize-space()='VAT (20%):']//following-sibling::td"));			
	}
	
	public double  getTotalValue (String VAT) {
		double TotalValue = 0;
		String TotalStringValue = null;
		switch (VAT) {
		case "VAT_YES":
			TotalStringValue = TestUtil.findElement
			(LocatorType.XPATH, "//div[@class='row mb-3 align-items-end']//table[@class='table table-bordered m-0']/tbody/tr[4]/td[2]").getText();
			break;
		case "VAT_NO":
			TotalStringValue = TestUtil.findElement
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
		
		TestUtil.assertIsDisplayed(checkoutGrid());
		TestUtil.verifyEqualTexts(imageTitle(), "IMAGE");
		TestUtil.verifyEqualTexts(productNameTitle(), "PRODUCT NAME");
		TestUtil.verifyEqualTexts(modelTitle(), "MODEL");
		TestUtil.verifyEqualTexts(quantityTitle(), "QUANTITY");
		TestUtil.verifyEqualTexts(unitPriceTitle(), "UNIT PRICE");
		TestUtil.verifyEqualTexts(totalTitle(), "TOTAL");
		
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
		int EcoTaxValue = TestUtil.convertDoubleToInt(getEcoTaxValue());
		Assert.assertEquals(EcoTaxValue, (expectedValue));		
		return EcoTaxValue;
		
	}
	
	
	public double assertTotalBottom_value (String VAT,double expectedValue) {
		double TotalBottomValue = getTotalValue(VAT);	
		Assert.assertEquals(TotalBottomValue, (expectedValue));
		return TotalBottomValue;
		
	}

	

}
