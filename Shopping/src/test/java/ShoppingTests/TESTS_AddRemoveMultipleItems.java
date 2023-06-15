package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestBase;
import Utils.TestUtil;


public class TESTS_AddRemoveMultipleItems extends TestBase{

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
			description = "This test performs search product and verifies the found product")
	public void t2_shopingCart_AddMultipleItemsVerification() throws InterruptedException {
		int numberOfAddedItems = 3;
		String productPrice = null;
		int EcoTax = numberOfAddedItems*2;
		/**
		 * Navigate to home Page
		 */
		openUrl(getEnvironmentUrl());
		/**
		 * Add products in the search field 
		 * and 
		 * assert the price values for Unit Price
		 */
		homePage.enterProductInSearchField(or.getProperty("product1"));
		homePage.clickOnSearch();
		productPrice = searchResultPage.getPriceFromArticle("1");
		double productPriceDouble = searchResultPage.getThePriceAmount(productPrice);
		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);
		double UnitPriceFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPriceFromGrid, (productPriceDouble));

		double TotalTopValue = shoppingCartPage.assertTotalTop_value((productPriceDouble * numberOfAddedItems));
		/**
		 * Asserting the Eco Tax Value 
		 */
		shoppingCartPage.assertEcoTaxValue(EcoTax);
		/**
		 *  Assertion for Total value
		 */
		shoppingCartPage.assertTotalBottom_value("VAT_YES", TotalTopValue);
		/**
		 * click on Remove Quantity Button 
		 * and 
		 * Assert message for empty shopping cart is Displayed 
		 * "Your shopping cart is empty!"
		 */
		shoppingCartPage.clickOnRemoveQuantity();
		testUtil.assertIsDisplayed(shoppingCartPage.messageEmptyCart());

	}


}
