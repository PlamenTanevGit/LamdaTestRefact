package ShoppingTests;

import org.testng.annotations.Test;

import DataProviders.DP;
import Utils.TestUtil;


public class TESTS_AddRemoveItem extends TestUtil{


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
		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.searchForProducts(productName);
		/**
		 * Adding items to cart 
		 * and 
		 * Assert Checkout button
		 * Assert View Cart Button
		 */
		searchResultPage.addToCart("1");
		TestUtil.assertIsDisplayed(searchResultPage.addToCartPopUp());
		TestUtil.verifyEqualTexts(searchResultPage.checkoutButoon(), "Checkout");
		TestUtil.verifyEqualTexts(searchResultPage.viewCartButton(), "View Cart");
		/**
		 * Select View Cart 
		 * and
		 * Assert correct page title 
		 */
		searchResultPage.selectViewCart();
		TestUtil.pageTitleVerify("Shopping Cart");
		/**
		 * Assertion of shopping cart componenets
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
		TestUtil.assertIsDisplayed(shoppingCartPage.messageEmptyCart());

	}

}
