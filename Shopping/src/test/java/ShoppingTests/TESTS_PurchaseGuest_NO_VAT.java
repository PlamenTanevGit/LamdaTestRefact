package ShoppingTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AddPersonalDetails;
import Utils.TestUtil;


public class TESTS_PurchaseGuest_NO_VAT extends TestUtil {

	/**
	 *  This test will select GUEST user acount 
	 *   - then will Fill the Guest Form
	 *   - then will select a Country WITHOUT VAT in the checkout form
	 *   - then will Assert the VAT is not Displayed
	 *   - will click on Continue nad move to Confirm Order screen 
	 *   - will Assert the values of the Sub Total amount and Flat Shipping rate amount between 
	 *   mid checkout screen and Confirm order screen
	 *   - will select Confrirm Order and will veirfy the Success message of Confirmed order
	 * 
	 */
	
	@Test(alwaysRun = true,
			description = "This test search for product then do a checkout by selection of country without VAT")
	public void GuestCheckoutItem_Without_VAT() throws InterruptedException {

		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.searchForProducts(or.getProperty("product3"));

		productPrice = searchResultPage.getPriceFromArticle("1");
		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPricFromGrid, (priceDouble));
		
		double TotalTopValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));
		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);
		shoppingCartPage.assertTotalBottom_value("VAT_YES", TotalTopValue);			
		shoppingCartPage.clickCheckout();
		/**
		 * Click on GUEST checkout 
		 */
		accountRegisterPage.clickOnGuestCheckOut();
		/**
		 * Fill the Form
		 */
		addPersonalDetails = new AddPersonalDetails(driver, 
				or.getProperty("firstName")+TestUtil.randomString(5), 
				or.getProperty("lastName")+TestUtil.randomString(5), 
				or.getProperty("email")+TestUtil.randomString(5)+"@mm.com", 
				or.getProperty("telephone"), 				
				or.getProperty("company")+TestUtil.randomString(3), 
				or.getProperty("address1")+TestUtil.randomString(5), 
				or.getProperty("postCode"), 
				or.getProperty("city1"), 
				or.getProperty("country1"), 
				or.getProperty("region1"));
		
//		addPersonalDetails.setFirstName(or.getProperty("firstName")+TestUtil.randomString(5));
//		addPersonalDetails.setLastName(or.getProperty("lastName")+TestUtil.randomString(5));
//		addPersonalDetails.setEmail(or.getProperty("email")+TestUtil.randomString(5)+"@mm.com");
//		addPersonalDetails.setTelephone(or.getProperty("telephone"));
//		addPersonalDetails.setCompany(or.getProperty("company")+TestUtil.randomString(3));
//		addPersonalDetails.setAddress1(or.getProperty("address1")+TestUtil.randomString(5));
//		addPersonalDetails.setAddress2(or.getProperty("address2")+TestUtil.randomString(5));	
//		addPersonalDetails.setPostCode(or.getProperty("postCode"));		
//		addPersonalDetails.setCity(or.getProperty("city1"));
//		addPersonalDetails.selectCountry(or.getProperty("country1"));
//		addPersonalDetails.selectRegion(or.getProperty("region1"));	
		
		
		/**
		 * Click Update button 
		 */
		checkoutPage.clickOnUpdate();
		/***
		 * Assert the element is not Displayed 
		 */
		TestUtil.assertElementIsNotDisplayed(By.cssSelector("#checkout-total tr:nth-of-type(4) td:nth-of-type(1)"));
		
		accountRegisterPage.checkPrivacyPolicy();
		accountRegisterPage.checkTermsAndConditions();
		
		double subTotal_midCheckout  = accountRegisterPage.getSubTotalAmount();
		double flatShippingRate_midCheckout  = accountRegisterPage.getFlatShippingRateAmmount();
		/***
		 * Click on Continue
		 */
		accountRegisterPage.clickOnContinue();		
		double subTotal_confirmOrder = confirmOrderPage.getSubTotalAmount();
		double flatShippingRate_confirmOrder = confirmOrderPage.getFlatShippingRateAmount();
		/**
		 * Assertions :
		 *   - SubTotal Mid CheckOut = Sub Total on Confirm order
		 *   - Flat Shipping Rate Mid CheckOut = Flat Shipping Rate on Confirm order   
		 */
		Assert.assertEquals(subTotal_midCheckout, subTotal_confirmOrder);
		Assert.assertEquals(flatShippingRate_midCheckout, flatShippingRate_confirmOrder);		
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
		
	}
	
}
