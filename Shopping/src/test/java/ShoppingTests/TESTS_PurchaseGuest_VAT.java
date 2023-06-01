package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_PurchaseGuest_VAT extends TestUtil {
	
	/***
	 * The test will do : 
	 *  - Search for Product
	 *  - Add Multiple items to the cart ( by click on Add to Cart Button )
	 *  - do DIRECT Checkout with GUEST Account
	 *  - Fill the Form of Registered Account 
	 *  - verify the VAT value 
	 *  - finish the Order and assert landing on home page
	 */

	
	@Test(alwaysRun = true,
			description = "This test search for product then do a checkout with GUEST checkout by selection of country with VAT")
	public void GuestCheckoutItem_With_VAT() throws InterruptedException {
	
		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.enterProductInSearchField(or.getProperty("product3"));
		homePage.clickOnSearch();

		productPrice = searchResultPage.getPriceFromArticle("1");

		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPricFromGrid, (priceDouble));

		double totalValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));
		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);
		shoppingCartPage.assertTotalBottom_value("VAT_YES", totalValue);	
		
		shoppingCartPage.clickCheckout();
		/**
		 * Select the GUEST Account and fill the form
		 */
		accountRegisterPage.clickOnGuestCheckOut();

		addPersonalDetails.setFirstName(or.getProperty("firstName")+TestUtil.randomString(5));
		addPersonalDetails.setLastName(or.getProperty("lastName")+TestUtil.randomString(5));
		addPersonalDetails.setEmail(or.getProperty("email")+TestUtil.randomString(5)+"@mm.com");
		addPersonalDetails.setTelephone(or.getProperty("telephone"));
		addPersonalDetails.setCompany(or.getProperty("company")+TestUtil.randomString(3));
		addPersonalDetails.setAddress1(or.getProperty("address1")+TestUtil.randomString(5));
		addPersonalDetails.setAddress2(or.getProperty("address2")+TestUtil.randomString(5));		
		addPersonalDetails.setCity(or.getProperty("city1"));
		addPersonalDetails.setPostCode(or.getProperty("postCode"));			
		addPersonalDetails.selectCountry(or.getProperty("country1"));
		addPersonalDetails.selectRegion(or.getProperty("region1"));
		
		TestUtil.verifyEqualTexts(checkoutPage.VATlabel(), "VAT (20%):");
				
		checkoutPage.clickOnUpdate();

		accountRegisterPage.checkPrivacyPolicy();
		accountRegisterPage.checkTermsAndConditions();
	
		double subTotal_midCheckout  = accountRegisterPage.getSubTotalAmount();
		double flatShippingRate_midCheckout  = accountRegisterPage.getFlatShippingRateAmmount();
		/**
		 * click Continue 
		 * and 
		 * Assert Sub-Total values on Mid and Final checkout
		 * Assert flat Shipping Rate on Mid and Final Checkout 
		 */
		accountRegisterPage.clickOnContinue();		
		double subTotal_confirmOrder = confirmOrderPage.getSubTotalAmount();
		double flatShippingRate_confirmOrder = confirmOrderPage.getFlatShippingRateAmount();		
		Assert.assertEquals(subTotal_midCheckout, subTotal_confirmOrder);
		Assert.assertEquals(flatShippingRate_midCheckout, flatShippingRate_confirmOrder);
		/**
		 * Confirm the Order 
		 * then
		 * click on Continue
		 * then
		 * Assert Home Page landing
		 */
		confirmOrderPage.confirmOrder();
		successPage.continiueClick();
		TestUtil.assertIsDisplayed(homePage.searchField());
	}

	
	
}
