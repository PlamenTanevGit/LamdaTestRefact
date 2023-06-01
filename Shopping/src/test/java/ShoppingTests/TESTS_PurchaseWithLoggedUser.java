package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.DriverFactory;
import Utils.TestUtil;

public class TESTS_PurchaseWithLoggedUser extends TestUtil {
	
	/***
	 * The test will do : 
	 * 	- Logging with User
	 *  - Search for Product
	 *  - Add 1 items to the cart ( by click on Add to Cart Button )
	 *  - do DIRECT Checkout with LOGGED USER  Account
	 *  - Fill the Form 
	 *  - Perform Assertions for  Sub Total amount and  Flat Shipping rate
	 *  - Confirm the Order
	 *  - Log Out
	 */
	

	@Test(alwaysRun = true)
	public void LoginAndPurchase() throws InterruptedException {
		int numberOfAddedItems = 3;
		String productPrice = null;
		/**
		 * Navigate to Login page 
		 * and 
		 * Login
		 */
		TestUtil.openUrl(config.getProperty("loginPage"));
		loginPage.doLogin(
				or.getProperty("usernameLogin"), 
				or.getProperty("passwordLogin"));
		TestUtil.assertIsDisplayed(myAccountPage.title());		
		/**
		 * Search for Product 
		 */
		homePage.searchForProducts(or.getProperty("product3"));
		productPrice = searchResultPage.getPriceFromArticle("1");
		
		double price = searchResultPage.getThePriceAmount(productPrice);
		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);
		
		double unitPrice = shoppingCartPage.getUnitPrice();
		/**
		 * Assertions for :
		 *  - Total Value (top)
		 *  - Eco Tax value 
		 *  - Total Value (bottom)  
		 *  
		 *  and select Checkout
		 */		
		Assert.assertEquals(unitPrice, (price));		
		double totalValue = shoppingCartPage.assertTotalTop_value((price * numberOfAddedItems));
		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);		
		shoppingCartPage.assertTotalBottom_value("VAT_YES", totalValue);
		shoppingCartPage.clickCheckout();
		/**
		 * Assertions for VAT label
		 */
		DriverFactory.waitForAjax();
		TestUtil.verifyEqualTexts(checkoutPage.VATlabel(), "VAT (20%):");
		/**
		 * Update the Quantity 
		 * and 
		 * Check the Terms and Conditions
		 */
		checkoutPage.clickOnUpdate();		
		accountRegisterPage.checkTermsAndConditions();		
		double subTotal = accountRegisterPage.getSubTotalAmount();
		double flatShippingRate = accountRegisterPage.getFlatShippingRateAmmount();		
		accountRegisterPage.clickOnContinue();
		double subTotalOrder = confirmOrderPage.getSubTotalAmount();
		double flatShippingRateOrder = confirmOrderPage.getFlatShippingRateAmount();
		/**
		 * Assertions for 
		 *  - Sub Total amount ( mid and final checkout )
		 *  - Flat Shipping rate ( mid and final checkout ) 
		 */
		Assert.assertEquals(subTotal, subTotalOrder);
		Assert.assertEquals(flatShippingRate, flatShippingRateOrder);
		/**
		 * Confirm the Order by click on Confirm Order
		 * then
		 * click on Continue 
		 * and 
		 * Assert Home page
		 */
		confirmOrderPage.confirmOrder();		
		successPage.continiueClick();
		super.assertIsDisplayed(homePage.searchField());
		/**
		 * Log out 
		 */
		topHeader.logOut();
	}

}
