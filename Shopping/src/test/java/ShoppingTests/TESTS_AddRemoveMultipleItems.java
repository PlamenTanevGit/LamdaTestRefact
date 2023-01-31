package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_AddRemoveMultipleItems extends TestUtil{
	
	@Test(alwaysRun = true, description = "This test performs search product and verifies the found product")	
	public void t2_shopingCart_AddMultipleItemsVerification() throws InterruptedException {
		int numberOfAddedItems = 3;
		String productPrice = null;
		int EcoTax = numberOfAddedItems*2;

		TestUtil.openUrl(config.getProperty("homePage"));
		
		homePage.enterProductInSearchField(or.getProperty("product1"));
		homePage.clickOnSearch();

		productPrice = searchResultPage.getPriceFromArticle("1");

		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPricFromGrid, (priceDouble));

		double TotalTopValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));

		shoppingCartPage.assertEcoTaxValue(EcoTax);

		shoppingCartPage.assertTotalBottom_value("VAT_YES", TotalTopValue);

		shoppingCartPage.clickOnRemoveQuantity();
		shoppingCartPage.clickOnContinueButton();
	}

	
}
