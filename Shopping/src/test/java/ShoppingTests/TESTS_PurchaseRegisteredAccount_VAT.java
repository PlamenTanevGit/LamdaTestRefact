package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_PurchaseRegisteredAccount_VAT extends TestUtil {

	
	@Test(alwaysRun = true,
			description =
			"This test search for product then do a checkout by selection of country with VAT")
	public void CheckoutItem_With_VAT() throws InterruptedException {

		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.searchForProducts(or.getProperty("product1"));

		productPrice = searchResultPage.getPriceFromArticle("1");

		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);

		double unitPrice = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(unitPrice, (priceDouble));

		double totalValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));
		
		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);

		shoppingCartPage.assertTotalBottom_value("VAT_YES", totalValue);	
		
		shoppingCartPage.checkout();
	
		addPersonalDetails.setFirstName(or.getProperty("firstName")+TestUtil.randomString(5));
		addPersonalDetails.setLastName(or.getProperty("lastName")+TestUtil.randomString(5));
		addPersonalDetails.setEmail(or.getProperty("email")+TestUtil.randomString(5)+"@mm.com");
		addPersonalDetails.setTelephone(or.getProperty("telephone"));
		addPersonalDetails.setPassword(or.getProperty("password"));
		addPersonalDetails.setConfrimPassword(or.getProperty("passwordConfirm"));		
		addPersonalDetails.setCompany(or.getProperty("company")+TestUtil.randomString(3));
		addPersonalDetails.setAddress1(or.getProperty("address1")+TestUtil.randomString(5));
		addPersonalDetails.setAddress2(or.getProperty("address2")+TestUtil.randomString(5));
		addPersonalDetails.setCity(or.getProperty("city1"));
		addPersonalDetails.setPostCode(or.getProperty("postCode"));			
		addPersonalDetails.selectCountry(or.getProperty("country1"));
		addPersonalDetails.selectRegion(or.getProperty("region1"));
		
		TestUtil.verifyEqualTexts(checkoutPage.VATlabel(), "VAT (20%):");
		checkoutPage.clickOnUpdate();

		accountRegisterPage.checkPrivacyPolicy();
		accountRegisterPage.checkTermsAndConditions();
		
		double subTotal_midCheckout  = accountRegisterPage.getSubTotalAmount();
		double flatShippingRate_midCheckout  = accountRegisterPage.getFlatShippingRateAmmount();
		
		accountRegisterPage.continueClick();
		double subTotal = confirmOrderPage.getSubTotalAmount();
		double flatShippingRate = confirmOrderPage.getFlatShippingRateAmount();
		
		Assert.assertEquals(subTotal_midCheckout, subTotal);
		Assert.assertEquals(flatShippingRate_midCheckout, flatShippingRate);
		
		confirmOrderPage.confirmOrder();
		
		successPage.continiueClick();
		TestUtil.assertIsDisplayed(homePage.searchField());
		
		topHeader.logOut();
		TestUtil.assertIsDisplayed(loginPage.logOutMessage());
	}

	
	

}
