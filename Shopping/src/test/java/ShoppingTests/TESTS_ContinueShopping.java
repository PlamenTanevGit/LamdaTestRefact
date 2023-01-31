package ShoppingTests;

import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_ContinueShopping extends TestUtil {

	
	@Test(alwaysRun = true,
			description = "This test search for product then do a checkout by selection of country with VAT")
	public void CheckoutItem_With_VAT() throws InterruptedException {
	
		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("homePage"));
	
		homePage.enterProductInSearchField(or.getProperty("product1"));
		homePage.clickOnSearch();

		productPrice = searchResultPage.getPriceFromArticle("1");

		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		softAssert.assertEquals(UnitPricFromGrid, (priceDouble));

		double TotalTopValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));

		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);

		shoppingCartPage.assertTotalBottom_value("VAT_YES", TotalTopValue);	
		
		shoppingCartPage.clickOnContinueShopping();
		
		TestUtil.pageTitleVerify("Your Store");
	}

	
	
}
