package ShoppingTests;

import org.testng.annotations.Test;

import DataProviders.DP;
import Utils.TestUtil;


public class TESTS_AddRemoveItem extends TestUtil{

	/***
	 * The test will do : 
	 *  - Search for Product
	 *  - Add the item to the cart ( by click on Add to Cart Button )
	 *  - for on View Checkout 
	 *  - Remove the item from the cart ( by click on Remove Quantity Button ) 
	 *  - Assert message for empty shopping cart is Displayed  "Your shopping cart is empty!"
	 */

	@Test(alwaysRun = true,
			dataProviderClass = DP.class,
			dataProvider = "productInputs",
			description = "This test performs search product and verifies the found product"
	)
	public void t1_shoppingCartVerificationAndRemoveProducts(String productName, String model, String quantity,
															 String unitPrice, String totalPrice) throws InterruptedException {
		/**
		 * Navigate to home page
		 * and 
		 * search for products
		 */
		openUrl(getEnvironmentUrl());

		homePage.searchForProducts(productName);
		/**
		 * Adding items to cart 
		 * and 
		 * Assert Checkout button
		 * Assert View Cart Button
		 */
		searchResultPage.addToCart("1");
		assertIsDisplayed(searchResultPage.addToCartPopUp());
		verifyEqualTexts(searchResultPage.checkoutButoon(), "Checkout");
		verifyEqualTexts(searchResultPage.viewCartButton(), "View Cart");
		/**
		 * Select View Cart 
		 * and
		 * Assert correct page title 
		 */
		searchResultPage.selectViewCart();
		pageTitleVerify("Shopping Cart");
		/**
		 * Assertion of shopping cart components
		 *  - productName, 
		 *  - model, 
		 *  - quantity, 
		 *  - unitPrice, 
		 *  - totalPrice
		 */
		shoppingCartPage.componentsVerify(productName, model, quantity, unitPrice, totalPrice);
		/**
		 * click on Remove Quantity Button 
		 * and 
		 * Assert message for empty shopping cart is Displayed 
		 * "Your shopping cart is empty!"
		 */
		shoppingCartPage.clickOnRemoveQuantity();
		assertIsDisplayed(shoppingCartPage.messageEmptyCart());

	}

}
