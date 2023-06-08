package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_DirectCheckout extends TestUtil {
	/***
	 * The test will do : 
	 *  - Search for Product
	 *  - Add Multiple items to the cart ( by click on Add to Cart Button )
	 *  - do DIRECT Checkout with Registered Account
	 *  - Fill the Form of Registered Account 
	 *  - verify the VAT value 
	 *  - finish the Order and assert landing on home page
	 */


	@Test(alwaysRun = true,
			description = "The test performs Add to Cart followed by Direct Checkout then filling the form and finish the Order ")
	public void DirectCheckoutItem_With_VAT() throws InterruptedException {

		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(TestUtil.getEnvironmentUrl());

		homePage.enterProductInSearchField(or.getProperty("product3"));
		homePage.clickOnSearch();
		productPrice = searchResultPage.getPriceFromArticle("1");
		double priceDouble = searchResultPage.getThePriceAmount(productPrice);
		searchResultPage.addMultipleItems("1", numberOfAddedItems);
		double unitPriceGrid = shoppingCartPage.getUnitPrice();


		Assert.assertEquals(unitPriceGrid, (priceDouble));
		/**
		 * Fill the Registered account form
		 */
		addPersonalDetails.setFirstName(or.getProperty("firstName")+TestUtil.randomString(5));
		addPersonalDetails.setLastName(or.getProperty("lastName")+TestUtil.randomString(5));
		addPersonalDetails.setEmail(or.getProperty("email")+TestUtil.randomString(5)+"@mm.com");
		addPersonalDetails.setTelephone(or.getProperty("telephone"));
		addPersonalDetails.setPassword(or.getProperty("password"));
		addPersonalDetails.setConfrimPassword(or.getProperty("passwordConfirm"));
		addPersonalDetails.setCompany(or.getProperty("company")+TestUtil.randomString(3));
		addPersonalDetails.setAddress1(or.getProperty("address1")+TestUtil.randomString(5));
		addPersonalDetails.setAddress2(or.getProperty("address2")+TestUtil.randomString(5));
		addPersonalDetails.setCity(or.getProperty("city1"));
		addPersonalDetails.setPostCode(or.getProperty("postCode"));
		addPersonalDetails.selectCountry(or.getProperty("country1"));
		addPersonalDetails.selectRegion(or.getProperty("region1"));
		/**
		 * VAT Assertion
		 */
		TestUtil.verifyEqualTexts(checkoutPage.VATlabel(), "VAT (20%):");
		/**
		 * Click on Update Quantity 
		 * and 
		 * mark Privacy Policy
		 * mark Terms And Conditions		
		 */
		checkoutPage.clickOnUpdate();
		accountRegisterPage.checkPrivacyPolicy();
		accountRegisterPage.checkTermsAndConditions();

		double subTotal_midCheckout  = accountRegisterPage.getSubTotalAmount();
		double flatShippingRate_midCheckout  = accountRegisterPage.getFlatShippingRateAmmount();
		accountRegisterPage.clickOnContinue();

		double subTotal_confirmOrder = confirmOrderPage.getSubTotalAmount();
		double flatShippingRate_confirmOrder = confirmOrderPage.getFlatShippingRateAmount();
		/**
		 * Assert values of 
		 *  - SubTotal
		 *  - Flat Shipping rate
		 *
		 *  from the mid checkout and final checkout 
		 */
		Assert.assertEquals(subTotal_midCheckout, subTotal_confirmOrder);
		Assert.assertEquals(flatShippingRate_midCheckout, flatShippingRate_confirmOrder);
		/**
		 * Confirm the Order 
		 * and 
		 * Assert landing on the home page
		 */
		confirmOrderPage.confirmOrder();
		successPage.continiueClick();
		TestUtil.assertIsDisplayed(homePage.searchField());
	}

}
