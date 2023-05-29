package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_PurchaseGuest_NotQuanatity extends TestUtil {

	
	@Test(alwaysRun = true,
			description = "This test search for product then do a checkout by selection of country with VAT")
	public void GuestCheckoutItem_With_VAT() throws InterruptedException {
	// test1
		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("homePage"));

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
