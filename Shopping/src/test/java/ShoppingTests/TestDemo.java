package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TestDemo extends TestUtil {

	
	@Test(alwaysRun = true)
	public void CheckoutItem_With_VAT() throws InterruptedException {
		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.enterProductInSearchField(or.getProperty("product3"));
		homePage.clickOnSearch();

		productPrice = searchResultPage.getPriceFromArticle("1");

		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPricFromGrid, (priceDouble));

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
		
		accountRegisterPage.clickOnContinue();
		
		double subTotal_confirmOrder = confirmOrderPage.getSubTotalAmount();
		double flatShippingRate_confirmOrder = confirmOrderPage.getFlatShippingRateAmount();
		
		Assert.assertEquals(subTotal_midCheckout, subTotal_confirmOrder);
		Assert.assertEquals(flatShippingRate_midCheckout, flatShippingRate_confirmOrder);	
		
		confirmOrderPage.confirmOrder();		
		successPage.continiueClick();
		TestUtil.assertIsDisplayed(homePage.searchField());
		TestUtil.pageTitleVerify("Your Store");
		
	}
	
	@Test(alwaysRun = true)
	public void t3() throws InterruptedException {
		int numberOfAddedItems = 1;
		String productPrice = null;

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.enterProductInSearchField(or.getProperty("product1"));
		homePage.clickOnSearch();

		productPrice = searchResultPage.getPriceFromArticle("1");

		double priceDouble = searchResultPage.getThePriceAmount(productPrice);

		searchResultPage.addMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPricFromGrid, (priceDouble));
		
	}

	
	
}
