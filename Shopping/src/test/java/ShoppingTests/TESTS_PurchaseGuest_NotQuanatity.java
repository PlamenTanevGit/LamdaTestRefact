package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_PurchaseGuest_NotQuanatity extends TestUtil {
	/**
	 * The test
	 *  - Navigates to Home Page 
	 *	- Search for Product with limited Quantity
	 * 	- Asserts the message for not sufficient products
	 * 
	 *  "Products marked with *** are not available in the desired quantity or not in stock!"
	 */
	
	@Test(alwaysRun = true,
			description = "This test search for product then try to do Checkout and Assert Message for Unavailable products")
	public void GuestCheckoutItem_With_VAT() throws InterruptedException {
	// test1
		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(TestUtil.getEnvironmentUrl());

		homePage.enterProductInSearchField(or.getProperty("product1"));
		homePage.clickOnSearch();

		productPrice = searchResultPage.getPriceFromArticle("1");

		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPricFromGrid, (priceDouble));
		
		/**
		 * Assert Error message Assertion for not sufficient products
		 */
		Assert.assertTrue(shoppingCartPage.insufficientProductsMessage().getText().contains("Products marked with *** are not available in the desired quantity or not in stock!"));

	}

	
	
}
