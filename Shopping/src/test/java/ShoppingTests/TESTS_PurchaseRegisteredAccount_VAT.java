package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AddPersonalDetails;
import Utils.TestUtil;


public class TESTS_PurchaseRegisteredAccount_VAT extends TestUtil {
	
	/**
	 * The Test will perform product search 
	 * with Registered account
	 * on country WITH VAT
	 * will confirm and Assert the Order 
	 * then 
	 * will Logout
	 * 
	 */

	
	@Test(alwaysRun = true,
			description =
			"This test search for product then do a checkout by selection of country with VAT")
	public void CheckoutItem_With_VAT() throws InterruptedException {

		int numberOfAddedItems = 1;
		String productPrice = null;
		/**
		 * Open the home Page URL
		 */
		TestUtil.openUrl(config.getProperty("homePage"));
		
		/**
		 * perform search for Product
		 * and store price in a variable 
		 */
		homePage.searchForProducts(or.getProperty("product3"));
		productPrice = searchResultPage.getPriceFromArticle("1");
		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		/**
		 * Add a 
		 */
		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);
		double unitPrice = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(unitPrice, (priceDouble));

		double totalValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));
		
		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);

		shoppingCartPage.assertTotalBottom_value("VAT_YES", totalValue);	
		
		shoppingCartPage.clickCheckout();
		
		/**
		 * Fill the Form
		 */
		addPersonalDetails = new AddPersonalDetails(driver, 
				or.getProperty("firstName")+TestUtil.randomString(5), 
				or.getProperty("lastName")+TestUtil.randomString(5), 
				or.getProperty("email")+TestUtil.randomString(5)+"@mm.com", 
				or.getProperty("telephone"), 				
				or.getProperty("password"),
				
				or.getProperty("company")+TestUtil.randomString(3), 
				or.getProperty("address1")+TestUtil.randomString(5), 
				or.getProperty("postCode"), 
				or.getProperty("city2"), 
				or.getProperty("country2"), 
				or.getProperty("region2"));
		
	
//		addPersonalDetails.setFirstName(or.getProperty("firstName")+TestUtil.randomString(5));
//		addPersonalDetails.setLastName(or.getProperty("lastName")+TestUtil.randomString(5));
//		addPersonalDetails.setEmail(or.getProperty("email")+TestUtil.randomString(5)+"@mm.com");
//		addPersonalDetails.setTelephone(or.getProperty("telephone"));
//		addPersonalDetails.setPassword(or.getProperty("password"));
//		addPersonalDetails.setConfrimPassword(or.getProperty("passwordConfirm"));		
//		addPersonalDetails.setCompany(or.getProperty("company")+TestUtil.randomString(3));
//		addPersonalDetails.setAddress1(or.getProperty("address1")+TestUtil.randomString(5));
//		addPersonalDetails.setAddress2(or.getProperty("address2")+TestUtil.randomString(5));
//		addPersonalDetails.setCity(or.getProperty("city2"));
//		addPersonalDetails.setPostCode(or.getProperty("postCode"));			
//		addPersonalDetails.selectCountry(or.getProperty("country2"));
//		addPersonalDetails.selectRegion(or.getProperty("region2"));
		
		/**
		 * Assertion for VAT exists in the mid checkout
		 */
		TestUtil.verifyEqualTexts(checkoutPage.VATlabel(), "VAT (20%):");
		checkoutPage.clickOnUpdate();

		/**
		 * checkboxes marking 
		 */
		accountRegisterPage.checkPrivacyPolicy();
		accountRegisterPage.checkTermsAndConditions();
		
		double subTotal_midCheckout  = accountRegisterPage.getSubTotalAmount();
		double flatShippingRate_midCheckout  = accountRegisterPage.getFlatShippingRateAmmount();
		/**
		 * click on Continue and proceed to Order screen
		 */
		accountRegisterPage.clickOnContinue();
		Assert.assertTrue(driver.getTitle().contains("Confirm Order"));
		
		/**
		 * Assertions :
		 *   - SubTotal Mid CheckOut = Sub Total on Confirm order
		 *   - Flat Shipping Rate Mid CheckOut = Flat Shipping Rate on Confirm order   
		 */
		double subTotal = confirmOrderPage.getSubTotalAmount();
		double flatShippingRate = confirmOrderPage.getFlatShippingRateAmount();		
		Assert.assertEquals(subTotal_midCheckout, subTotal);
		Assert.assertEquals(flatShippingRate_midCheckout, flatShippingRate);
		/**
		 * Confirm Order
		 */
		confirmOrderPage.confirmOrder();
		Assert.assertTrue(confirmOrderPage.successOrderMessage().getText().contains("Your order has been placed"));
		/**
		 * click on Continue
		 * and 
		 * - Assert Home Page landing successful
		 * - Assert home page title.
		 */
		successPage.continiueClick();
		TestUtil.assertIsDisplayed(homePage.searchField());
		Assert.assertTrue(driver.getTitle().contains("Your Store"));
		
		/**
		 * LogOut and Assert Log out message
		 */
		topHeader.logOut();
		TestUtil.assertIsDisplayed(loginPage.logOutMessage());
	}

	
	

}
