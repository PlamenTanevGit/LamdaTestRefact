package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;

public class ConfirmOrderPage {

	private WebDriver driver;

	public ConfirmOrderPage(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement confirmOrderTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//h1[normalize-space()='Confirm Order']");
	}

	public WebElement productNameTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Product Name']");
	}

	public WebElement modelTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Model']");
	}

	public WebElement quantityTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Quantity']");
	}

	public WebElement priceTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Price']");
	}

	public WebElement totalTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//tr/td[normalize-space() ='Total']");
	}

	public WebElement subTotalLabel() {
		return TestUtil.findElement(LocatorType.XPATH, "//table//strong[text()='Sub-Total:']");
	}

	public WebElement subTotalValue() {
		return TestUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[1]/td[2]");
	}

	public WebElement flatShippingRateLabel() {
		return TestUtil.findElement(LocatorType.XPATH, "//table//strong[text()='Flat Shipping Rate:']");
	}

	public WebElement flatShippingRateValue() {
		return TestUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[2]/td[2]");
	}

	public WebElement confirmOrderTotalLabel() {
		return TestUtil.findElement(LocatorType.XPATH, "//table//strong[text()='Total:']");
	}

	public WebElement confirmOrderTotalValue(String VAT) {

		switch (VAT) {

		case "VAT_YES":
			TestUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[5]/td[2]");
			break;

		case "VAT_NO":
			TestUtil.findElement(LocatorType.XPATH, "//table/tfoot/tr[3]/td[2]");
			break;

		default:
			break;
		}

		return TestUtil.element;
	}

	public WebElement paymentAddressTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//h4[text()='Payment Address']");
	}

	public WebElement paymentAddressBox() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='content']//div[1]//div[1]//div[1]");
	}

	public WebElement shippingAddressTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//h4[text()='Shipping Address']");
	}

	public WebElement shippingAddressBox() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='content']//div[1]//div[1]//div[1]");
	}

	public WebElement shippingMehtodTitle() {
		return TestUtil.findElement(LocatorType.XPATH, "//h4[text()='Shipping Method:']");
	}

	public WebElement shippingMethodBox() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='content']/div[@class='card mb-4']/div");
	}

	public WebElement editButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@class='buttons d-flex justify-content-between']/a");
	}

	public WebElement confirmOrderButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@class='buttons d-flex justify-content-between']/button");
	}

	public void confirmOrder() {
		TestUtil.clickOnElement(confirmOrderButton());
	}

	public double getSubTotalAmount() {
		return TestUtil.getDoubleFromStringValue(subTotalValue().getText());
	}

	public double getFlatShippingRateAmount() {
		return TestUtil.getDoubleFromStringValue(flatShippingRateValue().getText());
	}

	public double getTotalAmount(String VAT) {
		return TestUtil.getDoubleFromStringValue(confirmOrderTotalValue(VAT).getText());
	}

	public void validateConfirmOrderForm(double subTotal, double flatRate, double total) {
		TestUtil.verifyEqualTexts(productNameTitle(), "Product Name");
		TestUtil.verifyEqualTexts(modelTitle(), "Model");
		TestUtil.verifyEqualTexts(quantityTitle(), "Quantity");
		TestUtil.verifyEqualTexts(priceTitle(), "Price");
		TestUtil.verifyEqualTexts(totalTitle(), "Total");

	}

}
