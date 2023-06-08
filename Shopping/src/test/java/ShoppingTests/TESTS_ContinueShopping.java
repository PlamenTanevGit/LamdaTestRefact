package ShoppingTests;

import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_ContinueShopping extends TestUtil {

	/***
	 * The test will do : 
	 *  - Search for Product
	 *  - Add Multiple items to the cart ( by click on Add to Cart Button )
	 *  - for on View Checkout 
	 *  - Remove the Multiple items from the cart ( by click on Remove Quantity Button ) 
	 *  - Assert message for empty shopping cart is Displayed  "Your shopping cart is empty!"
	 *
	 */


	@Test(alwaysRun = true,
			description = "This test search for product then do a checkout by selection of country with VAT")
	public void checkoutItemWithVAT() throws InterruptedException {

		int numberOfAddedItems = 1;
		String productPrice = null;

		/**
		 * Navigate to the  home page
		 * and enter in the Product Search Field a product for search
		 * and click on Search
		 */
		TestUtil.openUrl(TestUtil.getEnvironmentUrl());
		homePage.enterProductInSearchField(or.getProperty("product1"));
		homePage.clickOnSearch();
		/**
		 * store the Price in a variable
		 * store the price amount in a variable
		 */
		productPrice = searchResultPage.getPriceFromArticle("1");
		double priceDouble = searchResultPage.getThePriceAmount(productPrice);
		/**
		 * Adding 1 item 
		 * and
		 * getting the Unit Price in varibale
		 */
		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);
		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		/**
		 * Assertion for Unit price from grid = Price Amount
		 */
		softAssert.assertEquals(UnitPricFromGrid, (priceDouble));
		double TotalTopValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));
		/**
		 * Assertions for Eco Tax Value and Total value including VAT
		 */
		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);
		shoppingCartPage.assertTotalBottom_value("VAT_YES", TotalTopValue);
		/**
		 * Click on Continue Shopping 
		 * and Assert the success landing of Your Store page
		 */
		shoppingCartPage.clickOnContinueShopping();
		TestUtil.pageTitleVerify("Your Store");
	}



}
