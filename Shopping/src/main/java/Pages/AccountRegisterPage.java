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
	private ConfirmOrderPage confirmOrderPage;
	
	public AccountRegisterPage(WebDriver driver) {
		this.driver = driver;
		this.confirmOrderPage = new ConfirmOrderPage(driver);

	}

	
	public WebElement checkoutTopLeft() {
		return TestUtil.findElement(LocatorType.XPATH, "//li[@class='breadcrumb-item active']");
	}

	public WebElement yourPersonalDetailsWindow() {
		return TestUtil.findElement(LocatorType.XPATH, "//html//div[@id='account']");
	}

	public WebElement firstNameField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@name='firstname']");
	}

	public WebElement lastNameField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-lastname']");
	}

	public WebElement emailField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-email']");
	}

	public WebElement telephoneField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-telephone']");
	}

	public WebElement passwordField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-password']");
	}

	public WebElement confirmPasswordField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-confirm']");
	}

	public WebElement companyField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-company']");
	}

	public WebElement address1Field() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-address-1']");
	}

	public WebElement address2Field() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-address-2']");
	}

	public WebElement cityField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-city']");
	}

	public WebElement postCodeField() {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-payment-postcode']");
	}

	public WebElement countryDropdpwn() {
		return TestUtil.findElement(LocatorType.XPATH, "//select[@id='input-payment-country']");
	}

	public WebElement regionDropdown() {
		return TestUtil.findElement(LocatorType.XPATH, "//select[@id='input-payment-zone']");
	}

	public WebElement privacyPolicyCheckbox() {
		return TestUtil.findElement(LocatorType.XPATH,"//label[@for='input-account-agree']");
	}

	public WebElement termsAndConditionsCheckbox() {
		return TestUtil.findElement(LocatorType.XPATH,"//label[@for='input-agree']");
	}

	public WebElement continueButton() {
		return TestUtil.findElement(LocatorType.CSS, "#button-save");
	}

	public WebElement accountLogoutMessage() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='content']/h1");
	}

	public WebElement accountLogoutContinueButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@class='buttons mb-4']/a");
	}

	public WebElement guestCheckoutRadioButton() {
		return TestUtil.findElement(LocatorType.XPATH, "//label[@for='input-account-guest']");
	}


	/**
	 * Guest Checkout Billing Address Details
	 */

	 public WebElement MyDeliveryBillingAddressCheckbox () {
		return TestUtil.findElement(LocatorType.XPATH, "//label[normalize-space()='My delivery and billing addresses are the same.']");
	}

	 public WebElement ShippingFirstName () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-firstname']");
	}

	public WebElement ShippingLastName () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-lastname']");
	}

	public WebElement ShippingCompany () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-company']");
	}

	public WebElement ShippingAddress1 () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-address-1']");
	}

	public WebElement ShippingAddress2 () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-address-2']");
	}

	public WebElement ShippingCity () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-city']");
	}

	public WebElement ShippingPostCode () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-shipping-postcode']");
	}

	public WebElement ShippingCountry () {
		return TestUtil.findElement(LocatorType.CSS, "#input-shipping-country");
	}

	public WebElement ShippingRegionStateDropdown  () {
		return TestUtil.findElement(LocatorType.CSS, "#input-shipping-zone");
	}


	public void clickOnGuestCheckOut() {
		TestUtil.clickOnElement(guestCheckoutRadioButton());		
	}

	public void clickOnContinue() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 4);
		TestUtil.jSClick(continueButton());
		wait.until(ExpectedConditions.visibilityOf(confirmOrderPage.confirmOrderTitle()));
		Thread.sleep(3500);
	}

	public void checkPrivacyPolicy() throws InterruptedException {
		TestUtil.pseudoClickJavascript("label[for='input-account-agree']", "before");
		Thread.sleep(1500);
	}

	public void checkTermsAndConditions() throws InterruptedException {
		TestUtil.pseudoClickJavascript("label[for='input-agree']", "after");
		Thread.sleep(1500);
	}

	public String setFirstName(String firstName) {
		TestUtil.typeOnField(firstNameField(), firstName);
		return firstName;

	}

	public String setBillngFirstName(String firstName) {
		TestUtil.typeOnField(ShippingFirstName(), firstName);
		return firstName;

	}

	public String setLastName(String lastName) {
		TestUtil.typeOnField(lastNameField(), lastName);
		return lastName;
	}

	public String setBillingLastName(String lastName) {
		TestUtil.typeOnField(ShippingLastName(), lastName);
		return lastName;
	}

	public String setEmail(String email) {
		TestUtil.typeOnField(emailField(), email);
		return email;
	}

	public String setTelephone(String telephone) {
		TestUtil.typeOnField(telephoneField(), telephone);
		return telephone;
	}

	public String setPassword(String password) {
		TestUtil.typeOnField(passwordField(), password);
		return password;
	}

	public String setConfirmPassword(String confirmPassword) {
		TestUtil.typeOnField(confirmPasswordField(), confirmPassword);
		return confirmPassword;
	}

	public String setCompany(String company) {
		TestUtil.typeOnField(companyField(), company);
		return company;
	}

	public String setShippingCompany(String company) {
		TestUtil.typeOnField(ShippingCompany(), company);
		return company;
	}

	public String setAddress1(String address1) {
		TestUtil.typeOnField(address1Field(), address1);
		return address1;
	}

	public String setShippingAddress1(String address1) {
		TestUtil.typeOnField(ShippingAddress1(), address1);
		return address1;
	}

	public String setAddress2(String address2) {
		TestUtil.typeOnField(address2Field(), address2);
		return address2;
	}

	public String setShippingAddress2(String address2) {
		TestUtil.typeOnField(ShippingAddress2(), address2);
		return address2;
	}

	public String setCity(String city) {
		TestUtil.typeOnField(cityField(), city);
		return city;
	}

	public String setShippingCity(String city) {
		TestUtil.typeOnField(ShippingCity(), city);
		return city;
	}

	public String setPostCode(String postCode) {
		TestUtil.typeOnField(postCodeField(), postCode);
		return postCode;

	}

	public String setShippingPostCode(String postCode) {
		TestUtil.typeOnField(ShippingPostCode(), postCode);
		return postCode;

	}
	
	public String selectCountry(String searchedCountry) throws InterruptedException {
			TestUtil.selectDropdownOptionByTagName(countryDropdpwn(), "option", searchedCountry);
			Thread.sleep(250);		
			firstNameField().click();
		
		return searchedCountry;
	}

	public String selectShippingCountry(String searchedShippingCountry) throws InterruptedException {
	
		TestUtil.selectDropdownOptionByTagName(ShippingCountry(), "option", searchedShippingCountry);
		Thread.sleep(750);		
		ShippingFirstName().click();
	
	return searchedShippingCountry;
}

	public String selectRegionState(String searchedBillngRegion) throws InterruptedException{
		TestUtil.selectDropdownOptionByTagName(regionDropdown(), "option", searchedBillngRegion);
		Thread.sleep(750);		
		firstNameField().click();
		return searchedBillngRegion;
	}

	public String selectShippingRegionState(String searchedRegion) throws InterruptedException{
	
		TestUtil.selectDropdownOptionByTagName(ShippingRegionStateDropdown(), "option", searchedRegion);
		Thread.sleep(250);		
		firstNameField().click();
		return searchedRegion;
	}

	public void removeItem() {
		clickOnRemoveButton();
		driver.navigate().refresh();		
	}
	
	public void clickOnRemoveButton() {
		TestUtil.clickOnElement(RemoveButton());		
	}

	public void setQuantity(String quantity) {
		QuantityField().clear();
		QuantityField().sendKeys(quantity);		
		TestUtil.pause(1);
	}
	
	public WebElement RemoveButton () {
		return TestUtil.findElement(LocatorType.CSS, "button:nth-of-type(2) > .fa-times-circle.fas");	

	}
	
	public WebElement QuantityField () {
		return TestUtil.findElement(LocatorType.CSS, ".remove-spin-button");	

	}
	
	public WebElement UNIT_PRICE () {
		return TestUtil.findElement(LocatorType.CSS, "div#checkout-cart > .table > tbody > tr > td:nth-of-type(4)");	

	}
	
	public WebElement TOTAL () {
		return TestUtil.findElement(LocatorType.CSS, "#checkout-cart tbody tr .text-right:nth-of-type(5)");

	}
	

	public WebElement subTotalValue() {
//		return TestUtil.findElement(LocatorType.XPATH, "/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[1]/form[1]//tr[1]/td[2]/strong[1]");
		return driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[5]/div[1]/div[1]/div[1]/form[1]//tr[1]/td[2]/strong[1]") );

	}

	public WebElement flatShippingRateValue() {
		return TestUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(2) > .text-right > strong");

	}

	public WebElement ecoTaxValue() {
		return TestUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(3) > .text-right > strong");

	}

	public WebElement VAT() {
		return TestUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(4) > .text-right > strong");

	}

	public WebElement totalValue(String VAT) {

		switch (VAT) {

		case "VAT_YES":
			TestUtil.findElement(LocatorType.XPATH, "//table[@id='checkout-total']/tbody/tr[5]/td[@class='text-right']");
			break;

		case "VAT_NO":
			TestUtil.findElement(LocatorType.XPATH, "//table[@id='checkout-total']/tbody/tr[3]/td[@class='text-right']");
			break;

		default:
			break;
		}

		return TestUtil.element;
	}
	
	public double getUNIT_PRICE() {
		return TestUtil.getDoubleFromStringValue(UNIT_PRICE().getText());
	}
	
	public double getTOTAL() {
		return TestUtil.getDoubleFromStringValue(TOTAL().getText());
	}

	public double getSubTotalAmount() {
		return TestUtil.getDoubleFromStringValue(subTotalValue().getText());
	}

	public double getFlatShippingRateAmmount() {
		return TestUtil.getDoubleFromStringValue(flatShippingRateValue().getText());
	}

	public double getEcoTaxAmount() {
		return TestUtil.getDoubleFromStringValue(ecoTaxValue().getText());
	}

	public double getVATAmount() {
		return TestUtil.getDoubleFromStringValue(VAT().getText());
	}

	public double getTotalAmount(String VAT) {
		return TestUtil.getDoubleFromStringValue(totalValue(VAT).getText());
	}

}
