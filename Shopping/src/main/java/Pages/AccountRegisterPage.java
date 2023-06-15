package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.LocatorType;
import Utils.TestUtil;

public class AccountRegisterPage {

	private WebDriver driver;
	private TestUtil testUtil;
	
	public AccountRegisterPage(WebDriver driver) {
		this.testUtil = new TestUtil();
		this.driver = testUtil.getDriver();
	}

	
	public WebElement checkoutTopLeft() {
		return testUtil.findElement(LocatorType.XPATH, "//li[@class='breadcrumb-item active']");
	}

	public WebElement yourPersonalDetailsWindow() {
		return testUtil.findElement(LocatorType.XPATH, "//html//div[@id='account']");
	}

	public WebElement firstNameField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@name='firstname']");
	}

	public WebElement lastNameField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-lastname']");
	}

	public WebElement emailField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-email']");
	}

	public WebElement telephoneField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-telephone']");
	}

	public WebElement passwordField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-password']");
	}

	public WebElement confirmPasswordField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-confirm']");
	}

	public WebElement companyField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-company']");
	}

	public WebElement address1Field() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-address-1']");
	}

	public WebElement address2Field() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-address-2']");
	}

	public WebElement cityField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-city']");
	}

	public WebElement postCodeField() {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-postcode']");
	}

	public WebElement countryDropdpwn() {
		return testUtil.findElement(LocatorType.XPATH, "//select[@id='input-payment-country']");
	}

	public WebElement regionDropdown() {
		return testUtil.findElement(LocatorType.XPATH, "//select[@id='input-payment-zone']");
	}

	public WebElement privacyPolicyCheckbox() {
		return testUtil.findElement(LocatorType.XPATH,"//label[@for='input-account-agree']");
	}

	public WebElement termsAndConditionsCheckbox() {
		return testUtil.findElement(LocatorType.XPATH,"//label[@for='input-agree']");
	}

	public WebElement continueButton() {
		return testUtil.findElement(LocatorType.CSS, "#button-save");
	}

	public WebElement accountLogoutMessage() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='content']/h1");
	}

	public WebElement accountLogoutContinueButton() {
		return testUtil.findElement(LocatorType.XPATH, "//div[@class='buttons mb-4']/a");
	}

	public WebElement guestCheckoutRadioButton() {
		return testUtil.findElement(LocatorType.XPATH, "//label[@for='input-account-guest']");
	}


	/**
	 * Guest Checkout Billing Address Details
	 */

	 public WebElement MyDeliveryBillingAddressCheckbox () {
		return testUtil.findElement(LocatorType.XPATH, "//label[normalize-space()='My delivery and billing addresses are the same.']");
	}

	 public WebElement ShippingFirstName () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-firstname']");
	}

	public WebElement ShippingLastName () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-lastname']");
	}

	public WebElement ShippingCompany () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-company']");
	}

	public WebElement ShippingAddress1 () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-address-1']");
	}

	public WebElement ShippingAddress2 () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-address-2']");
	}

	public WebElement ShippingCity () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-city']");
	}

	public WebElement ShippingPostCode () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-postcode']");
	}

	public WebElement ShippingCountry () {
		return testUtil.findElement(LocatorType.CSS, "#input-shipping-country");
	}

	public WebElement ShippingRegionStateDropdown  () {
		return testUtil.findElement(LocatorType.CSS, "#input-shipping-zone");
	}


	public void clickOnGuestCheckOut() {
		testUtil.clickOnElement(guestCheckoutRadioButton());		
	}

	public void clickOnContinue() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 4);
		testUtil.jSClick(continueButton());
		ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
		wait.until(ExpectedConditions.visibilityOf(confirmOrderPage.confirmOrderTitle()));
		testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);
	}

	public void checkPrivacyPolicy() throws InterruptedException {
		testUtil.pseudoClickJavascript("label[for='input-account-agree']", "before");
		testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);
	}

	public void checkTermsAndConditions() throws InterruptedException {
		testUtil.pseudoClickJavascript("label[for='input-agree']", "after");
		testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);
	}

	public String setFirstName(String firstName) {
		testUtil.typeOnField(firstNameField(), firstName);
		return firstName;

	}

	public String setBillngFirstName(String firstName) {
		testUtil.typeOnField(ShippingFirstName(), firstName);
		return firstName;

	}

	public String setLastName(String lastName) {
		testUtil.typeOnField(lastNameField(), lastName);
		return lastName;
	}

	public String setBillingLastName(String lastName) {
		testUtil.typeOnField(ShippingLastName(), lastName);
		return lastName;
	}

	public String setEmail(String email) {
		testUtil.typeOnField(emailField(), email);
		return email;
	}

	public String setTelephone(String telephone) {
		testUtil.typeOnField(telephoneField(), telephone);
		return telephone;
	}

	public String setPassword(String password) {
		testUtil.typeOnField(passwordField(), password);
		return password;
	}

	public String setConfirmPassword(String confirmPassword) {
		testUtil.typeOnField(confirmPasswordField(), confirmPassword);
		return confirmPassword;
	}

	public String setCompany(String company) {
		testUtil.typeOnField(companyField(), company);
		return company;
	}

	public String setShippingCompany(String company) {
		testUtil.typeOnField(ShippingCompany(), company);
		return company;
	}

	public String setAddress1(String address1) {
		testUtil.typeOnField(address1Field(), address1);
		return address1;
	}

	public String setShippingAddress1(String address1) {
		testUtil.typeOnField(ShippingAddress1(), address1);
		return address1;
	}

	public String setAddress2(String address2) {
		testUtil.typeOnField(address2Field(), address2);
		return address2;
	}

	public String setShippingAddress2(String address2) {
		testUtil.typeOnField(ShippingAddress2(), address2);
		return address2;
	}

	public String setCity(String city) {
		testUtil.typeOnField(cityField(), city);
		return city;
	}

	public String setShippingCity(String city) {
		testUtil.typeOnField(ShippingCity(), city);
		return city;
	}

	public String setPostCode(String postCode) {
		testUtil.typeOnField(postCodeField(), postCode);
		return postCode;

	}

	public String setShippingPostCode(String postCode) {
		testUtil.typeOnField(ShippingPostCode(), postCode);
		return postCode;

	}
	
	public String selectCountry(String searchedCountry) throws InterruptedException {
			testUtil.selectDropdownOptionByTagName(countryDropdpwn(), "option", searchedCountry);
			// testUtil.pausems(3);	
			testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);	
			firstNameField().click();
		
		return searchedCountry;
	}

	public String selectShippingCountry(String searchedShippingCountry) throws InterruptedException {
	
		testUtil.selectDropdownOptionByTagName(ShippingCountry(), "option", searchedShippingCountry);
		// testUtil.pausems(8);	
		testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);
		ShippingFirstName().click();
	
	return searchedShippingCountry;
}

	public String selectRegionState(String searchedBillngRegion) throws InterruptedException{
		testUtil.selectDropdownOptionByTagName(regionDropdown(), "option", searchedBillngRegion);
		// testUtil.pausems(8);	
		testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);
		firstNameField().click();
		return searchedBillngRegion;
	}

	public String selectShippingRegionState(String searchedRegion) throws InterruptedException{
	
		testUtil.selectDropdownOptionByTagName(ShippingRegionStateDropdown(), "option", searchedRegion);
		// testUtil.pausems(3);	
		testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);	
		firstNameField().click();
		return searchedRegion;
	}

	public void removeItem() {
		clickOnRemoveButton();
		driver.navigate().refresh();		
	}
	
	public void clickOnRemoveButton() {
		testUtil.clickOnElement(RemoveButton());		
	}

	public void setQuantity(String quantity) {
		QuantityField().clear();
		QuantityField().sendKeys(quantity);		
		// testUtil.pause(1);
		testUtil.waitForElementPresntUsingFluentWait(By.cssSelector("#button-save"), 5, 1);
	}
	
	public WebElement RemoveButton () {
		return testUtil.findElement(LocatorType.CSS, "button:nth-of-type(2) > .fa-times-circle.fas");	

	}
	
	public WebElement QuantityField () {
		return testUtil.findElement(LocatorType.CSS, ".remove-spin-button");	

	}
	
	public WebElement UNIT_PRICE () {
		return testUtil.findElement(LocatorType.CSS, "div#checkout-cart > .table > tbody > tr > td:nth-of-type(4)");	

	}
	
	public WebElement TOTAL () {
		return testUtil.findElement(LocatorType.CSS, "#checkout-cart tbody tr .text-right:nth-of-type(5)");

	}
	

	public WebElement subTotalValue() {
//		return testUtil.findElement(LocatorType.XPATH, "/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[1]/form[1]//tr[1]/td[2]/strong[1]");
		return driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[1]/form[1]//tr[1]/td[2]/strong[1]") );

	}

	public WebElement flatShippingRateValue() {
		return testUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(2) > .text-right > strong");

	}

	public WebElement ecoTaxValue() {
		return testUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(3) > .text-right > strong");

	}

	public WebElement VAT() {
		return testUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(4) > .text-right > strong");

	}

	public WebElement totalValue(String VAT) {

		switch (VAT) {

		case "VAT_YES":
			testUtil.findElement(LocatorType.XPATH, "//table[@id='checkout-total']/tbody/tr[5]/td[@class='text-right']");
			break;

		case "VAT_NO":
			testUtil.findElement(LocatorType.XPATH, "//table[@id='checkout-total']/tbody/tr[3]/td[@class='text-right']");
			break;

		default:
			break;
		}

		return testUtil.element;
	}
	
	public double getUNIT_PRICE() {
		return testUtil.getDoubleFromStringValue(UNIT_PRICE().getText());
	}
	
	public double getTOTAL() {
		return testUtil.getDoubleFromStringValue(TOTAL().getText());
	}

	public double getSubTotalAmount() {
		return testUtil.getDoubleFromStringValue(subTotalValue().getText());
	}

	public double getFlatShippingRateAmmount() {
		return testUtil.getDoubleFromStringValue(flatShippingRateValue().getText());
	}

	public double getEcoTaxAmount() {
		return testUtil.getDoubleFromStringValue(ecoTaxValue().getText());
	}

	public double getVATAmount() {
		return testUtil.getDoubleFromStringValue(VAT().getText());
	}

	public double getTotalAmount(String VAT) {
		return testUtil.getDoubleFromStringValue(totalValue(VAT).getText());
	}

}
