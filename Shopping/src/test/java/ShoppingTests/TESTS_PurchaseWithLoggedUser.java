package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.DriverFactory;
import Utils.TestUtil;

public class TESTS_PurchaseWithLoggedUser extends TestUtil {

	@Test(alwaysRun = true)
	public void LoginAndPurchase() throws InterruptedException {
		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("loginPage"));
		loginPage.doLogin(
				or.getProperty("usernameLogin"), 
				or.getProperty("passwordLogin"));
		TestUtil.assertIsDisplayed(myAccountPage.title());		

		homePage.searchForProducts(or.getProperty("product1"));
		productPrice = searchResultPage.getPriceFromArticle("1");
		
		double price = searchResultPage.getThePriceAmount(productPrice);
		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);
		
		double unitPrice = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(unitPrice, (price));
		
		double totalValue = shoppingCartPage.assertTotalTop_value((price * numberOfAddedItems));
		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);
		
		shoppingCartPage.assertTotalBottom_value("VAT_YES", totalValue);
		shoppingCartPage.checkout();
		
		DriverFactory.waitForAjax();
		TestUtil.verifyEqualTexts(checkoutPage.VATlabel(), "VAT (20%):");
		
		checkoutPage.clickOnUpdate();
		
		accountRegisterPage.checkTermsAndConditions();
		
		double subTotal = accountRegisterPage.getSubTotalAmount();
		double flatShippingRate = accountRegisterPage.getFlatShippingRateAmmount();		
		accountRegisterPage.continueClick();

		double subTotalOrder = confirmOrderPage.getSubTotalAmount();
		double flatShippingRateOrder = confirmOrderPage.getFlatShippingRateAmount();
		Assert.assertEquals(subTotal, subTotalOrder);
		Assert.assertEquals(flatShippingRate, flatShippingRateOrder);
		
		confirmOrderPage.confirmOrder();
		
		successPage.continiueClick();
		super.assertIsDisplayed(homePage.searchField());

		topHeader.logOut();
	}

}
