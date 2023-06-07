package Pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utils.TestUtil;

public class FascadePage  {
	
	private WebDriver driver;
	private ShoppingCartPage shoppingCartPage;
	private AccountRegisterPage accountRegisterPage;
	private HomePage homePage;
	private SearchResultPage searchResultPage;	
	private AddPersonalDetails addPersonalDetails;
	private CheckoutPage checkoutPage;
	
	public FascadePage(WebDriver driver) {
		this.driver = driver;
		this.shoppingCartPage = new ShoppingCartPage(driver);
		this.accountRegisterPage = new AccountRegisterPage(driver);
		this.homePage = new HomePage(driver);
		this.searchResultPage = new SearchResultPage(driver);
		this.checkoutPage = new CheckoutPage(driver);
	}


	public void navigateToHomePage(String homePageUrl) {
		TestUtil.openUrl(homePageUrl);
	}

	public void searchProduct (String product ) {
		homePage.enterProductInSearchField(product);
		homePage.clickOnSearch();

	}

	public double registeredAccountCheckoutProduct(
		String homePageUrl,
		String product,
		int numberOfAddedItems,
		String firstName,
		String lastName,
		String email,
		String telephone,
		String pass,
		String company,
		String address1,
		String postCode,
		String city,
		String country,
		String region,
		String quantity	) throws InterruptedException 	{

		navigateToHomePage(homePageUrl);
		searchProduct(product);
		double priceDouble = searchResultPage.getThePriceAmount(searchResultPage.getPriceFromArticle("1"));
		searchResultPage.addMultipleItems("1", numberOfAddedItems);
		
		/**
		 * Fill the Registered account form
		 */
		addPersonalDetails = new AddPersonalDetails(driver,
		firstName,
		lastName,
		email,
		telephone,
		pass,
		company,
		address1,
		postCode,
		city,
		country,
		region);

		accountRegisterPage.setQuantity(quantity);
		checkoutPage.clickOnUpdate();

		return priceDouble;
	}



	public double registeredAccountGuestProduct(
		String homePageUrl,
		String product,
		int numberOfAddedItems,
		String firstName,
		String lastName,
		String email,
		String telephone, 
		String company,
		String address1,
		String postCode,
		String city,
		String country,
		String region,
		String quantity

		) throws InterruptedException   
		
		{
			
		navigateToHomePage(homePageUrl);
		searchProduct(product);
		double priceDouble = searchResultPage.getThePriceAmount(searchResultPage.getPriceFromArticle("1"));
		searchResultPage.addMultipleItems("1", numberOfAddedItems);
		
		/**
		 * click on Guest Checkout 
		 * and
		 * Fill the Guest account form
		 */
		accountRegisterPage.guestCheckoutRadioButton().click();

		addPersonalDetails = new AddPersonalDetails(driver,
		firstName,
		lastName,
		email,
		telephone,
		company,
		address1,
		postCode,
		city,
		country,
		region);

		accountRegisterPage.setQuantity(quantity);
		checkoutPage.clickOnUpdate();
		
		return priceDouble;
	}

	public double registeredAccountGuestProductShippingAddress(
		String homePageUrl,
		String product,
		int numberOfAddedItems,
		String firstName,
		String lastName,
		String email,
		String telephone, 
		String company,
		String address1,
		String postCode,
		String city,
		String country,
		String region,
		String quantity,

		String shippingFirstName,
		String shippingLastName,
		String shippingCompany,
		String shippingAddress1,
		String shippingAddress2,
		String shippingCity,
		String shippingPostCode,
		String shippingCountry,
		String shippingRegion

		) throws InterruptedException  {

		navigateToHomePage(homePageUrl);
		searchProduct(product);
		double priceDouble = searchResultPage.getThePriceAmount(searchResultPage.getPriceFromArticle("1"));
		searchResultPage.addMultipleItems("1", numberOfAddedItems);
		accountRegisterPage.MyDeliveryBillingAddressCheckbox().click();

		/**
		 * click on Guest Checkout 
		 * and
		 * Fill the Guest account form
		 */
		accountRegisterPage.guestCheckoutRadioButton().click();

		addPersonalDetails = new AddPersonalDetails(driver,
		firstName,
		lastName,
		email,
		telephone,
		company,
		address1,
		postCode,
		city,
		country,
		region,

		shippingFirstName,
		shippingLastName,
		shippingCompany,
		shippingAddress1,
		shippingAddress2,
		shippingCity,
		shippingPostCode,
		shippingCountry,
		shippingRegion
		);

		accountRegisterPage.setQuantity(quantity);
		checkoutPage.clickOnUpdate();
		
		return priceDouble;
	}

	public void assertionsNoVat (String VAT_YES_NO, String UNIT__PRICE, String TOTAL_, String SubTotal, String FlatShipping, String Total) {
		
		double UNIT_PRICE = accountRegisterPage.getUNIT_PRICE();
		double TOTAL = accountRegisterPage.getTOTAL();

		Assert.assertEquals(UNIT_PRICE, TestUtil.getDoubleFromStringValue(UNIT__PRICE));
		Assert.assertEquals(TOTAL, TestUtil.getDoubleFromStringValue(TOTAL_));	
		Assert.assertEquals(TOTAL, UNIT_PRICE);
		/**
		 * Assertion for :
		 * - Sub-Total Amount value
		 * - Flat Shipping Rate Value
		 * - Total Value 
		 */
		Assert.assertEquals(accountRegisterPage.getSubTotalAmount(), TestUtil.getDoubleFromStringValue(SubTotal));
		Assert.assertEquals(accountRegisterPage.getFlatShippingRateAmmount(), TestUtil.getDoubleFromStringValue(FlatShipping));
		Assert.assertEquals(accountRegisterPage.getTotalAmount(VAT_YES_NO), TestUtil.getDoubleFromStringValue(Total));	
	}

	public void assertionsYesVat (String VAT_YES_NO, String UNIT__PRICE, String TOTAL_, String SubTotal, String FlatShipping, String Eco, String VATValue, String Total) {
		
		double UNIT_PRICE = accountRegisterPage.getUNIT_PRICE();
		double TOTAL = accountRegisterPage.getTOTAL();

		Assert.assertEquals(UNIT_PRICE, TestUtil.getDoubleFromStringValue(UNIT__PRICE));
		Assert.assertEquals(TOTAL, TestUtil.getDoubleFromStringValue(TOTAL_));	
		Assert.assertEquals(TOTAL, UNIT_PRICE);
		/**
		 * Assertion for :
		 * - Sub-Total Amount value
		 * - Flat Shipping Rate Value
		 * - Eco Value
		 * - VAT value
		 * - Total Value 
		 */
		Assert.assertEquals(accountRegisterPage.getSubTotalAmount(), TestUtil.getDoubleFromStringValue(SubTotal));
		Assert.assertEquals(accountRegisterPage.getFlatShippingRateAmmount(), TestUtil.getDoubleFromStringValue(FlatShipping));
		Assert.assertEquals(accountRegisterPage.getEcoTaxAmount(), TestUtil.getDoubleFromStringValue(Eco));
		Assert.assertEquals(accountRegisterPage.getVATAmount(), TestUtil.getDoubleFromStringValue(VATValue));
		Assert.assertEquals(accountRegisterPage.getTotalAmount("VAT_YES"), TestUtil.getDoubleFromStringValue(Total));		


	}



	

}
