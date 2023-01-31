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

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.searchForProducts(productName);

		searchResultPage.addToCart("1");
		TestUtil.assertIsDisplayed(searchResultPage.addToCartPopUp());
		TestUtil.verifyEqualTexts(searchResultPage.checkoutButoon(), "Checkout");
		TestUtil.verifyEqualTexts(searchResultPage.viewCartButton(), "View Cart");

		searchResultPage.selectViewCart();
		TestUtil.pageTitleVerify("Shopping Cart");

		shoppingCartPage.componentsVerify(productName, model, quantity, unitPrice, totalPrice);

		shoppingCartPage.clickOnRemoveQuantity();
		TestUtil.assertIsDisplayed(shoppingCartPage.messageEmptyCart());

	}

}
