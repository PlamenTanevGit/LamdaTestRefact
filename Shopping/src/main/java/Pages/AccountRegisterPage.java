package Pages;

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
		return TestUtil.findElement(LocatorType.XPATH, "//button[@id='button-save']");
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

	public void clickOnGuestCheckOut() {
		TestUtil.clickOnElement(guestCheckoutRadioButton());		
	}

	public void continueClick() {
		WebDriverWait wait = new WebDriverWait(driver, 4);
		TestUtil.clickOnElement(continueButton());
		wait.until(ExpectedConditions.visibilityOf(confirmOrderPage.confirmOrderTitle()));
	}

	public void checkPrivacyPolicy() {
		TestUtil.pseudoClickJavascript("label[for='input-account-agree']", "before");
	}

	public void checkTermsAndConditions() {
		TestUtil.pseudoClickJavascript("label[for='input-agree']", "after");
	}

	public String setFirstName(String firstName) {
		TestUtil.typeOnField(firstNameField(), firstName);
		return firstName;

	}

	public String setLastName(String lastName) {
		TestUtil.typeOnField(lastNameField(), lastName);
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

	public String setAddress1(String address1) {
		TestUtil.typeOnField(address1Field(), address1);
		return address1;
	}

	public String setAddress2(String address2) {
		TestUtil.typeOnField(address2Field(), address2);
		return address2;
	}

	public String setCity(String city) {
		TestUtil.typeOnField(cityField(), city);
		return city;
	}

	public String setPostCode(String postCode) {
		TestUtil.typeOnField(postCodeField(), postCode);
		return postCode;

	}
	
	public String selectCountry(String searchedCountry) throws InterruptedException {
			TestUtil.selectDropdownOptionByTagName(countryDropdpwn(), "option", searchedCountry);
			Thread.sleep(250);		
			firstNameField().click();
		
		return searchedCountry;
	}

	public String selectRegionState(String searchedRegion) throws InterruptedException{
		TestUtil.selectDropdownOptionByTagName(regionDropdown(), "option", searchedRegion);
		Thread.sleep(250);		
		firstNameField().click();
		return searchedRegion;
	}


	public WebElement subTotalValue() {
		return TestUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(1) > .text-right > strong");

	}

	public WebElement flatShippingRateValue() {
		return TestUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(2) > .text-right > strong");

	}

	public WebElement ecoTaxValue() {
		return TestUtil.findElement(LocatorType.CSS, "table#checkout-total > tbody > tr:nth-of-type(3) > .text-right > strong");

	}

	public WebElement VAT() {
		return TestUtil.findElement(LocatorType.XPATH, "table#checkout-total > tbody > tr:nth-of-type(4) > .text-right > strong");

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
