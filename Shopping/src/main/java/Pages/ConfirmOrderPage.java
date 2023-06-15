package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;

public class ConfirmOrderPage {

	private WebDriver driver;
	private TestUtil testUtil;

	public ConfirmOrderPage(WebDriver driver) {
		this.driver = driver;
		this.testUtil = new TestUtil();

	}

	public WebElement confirmOrderTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//h1[normalize-space()='Confirm Order']");
	}

	public WebElement productNameTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Product Name']");
	}

	public WebElement modelTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Model']");
	}

	public WebElement quantityTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Quantity']");
	}

	public WebElement priceTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Price']");
	}

	public WebElement totalTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Total']");
	}

	public WebElement subTotalLabel() {
		return testUtil.findElement(LocatorType.XPATH, "//table//strong[text()='Sub-Total:']");
	}

	public WebElement subTotalValue() {
		return testUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[1]/td[2]");
	}

	public WebElement flatShippingRateLabel() {
		return testUtil.findElement(LocatorType.XPATH, "//table//strong[text()='Flat Shipping Rate:']");
	}

	public WebElement flatShippingRateValue() {
		return testUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[2]/td[2]");
	}

	public WebElement confirmOrderTotalLabel() {
		return testUtil.findElement(LocatorType.XPATH, "//table//strong[text()='Total:']");
	}
	
	public WebElement successOrderMessage () {
		return testUtil.findElement(LocatorType.XPATH, "//h1[normalize-space()='Your order has been placed!']");
	}

	public WebElement confirmOrderTotalValue(String VAT) {

		switch (VAT) {

		case "VAT_YES":
			testUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[5]/td[2]");
			break;

		case "VAT_NO":
			testUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[3]/td[2]");
			break;

		default:
			break;
		}

		return testUtil.element;
	}

	public WebElement paymentAddressTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//h4[text()='Payment Address']");
	}

	public WebElement paymentAddressBox() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='content']//div[1]//div[1]//div[1]");
	}

	public WebElement shippingAddressTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//h4[text()='Shipping Address']");
	}

	public WebElement shippingAddressBox() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='content']//div[1]//div[1]//div[1]");
	}

	public WebElement shippingMehtodTitle() {
		return testUtil.findElement(LocatorType.XPATH, "//h4[text()='Shipping Method:']");
	}

	public WebElement shippingMethodBox() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='content']/div[@class='card mb-4']/div");
	}

	public WebElement editButton() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@class='buttons d-flex justify-content-between']/a");
	}

	public WebElement confirmOrderButton() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@class='buttons d-flex justify-content-between']/button");
	}
	

	public void confirmOrder() {
		testUtil.jSClick(confirmOrderButton());
	}

	public double getSubTotalAmount() {
		return testUtil.getDoubleFromStringValue(subTotalValue().getText());
	}

	public double getFlatShippingRateAmount() {
		return testUtil.getDoubleFromStringValue(flatShippingRateValue().getText());
	}

	public double getTotalAmount(String VAT) {
		return testUtil.getDoubleFromStringValue(confirmOrderTotalValue(VAT).getText());
	}

	public void validateConfirmOrderForm(double subTotal, double flatRate, double total) {
		testUtil.verifyEqualTexts(productNameTitle(), "Product Name");
		testUtil.verifyEqualTexts(modelTitle(), "Model");
		testUtil.verifyEqualTexts(quantityTitle(), "Quantity");
		testUtil.verifyEqualTexts(priceTitle(), "Price");
		testUtil.verifyEqualTexts(totalTitle(), "Total");

	}


}
