package ShoppingTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.TestUtil;


public class TESTS_PurchaseRegisteredAccount_NO_VAT extends TestUtil {

	
	@Test(alwaysRun = true,
			description = "This test search for product then do a checkout by selection of country without VAT")
	public void CheckoutItem_Without_VAT() throws InterruptedException {
		
		int numberOfAddedItems = 1;
		String productPrice = null;
		/**
		 * Navigat to home page
		 */
		TestUtil.openUrl(config.getProperty("homePage"));
		/**
		 * Enter product and click on Search 
		 * - store product Price in variable
		 * - store Amount in variable
		 */
		homePage.enterProductInSearchField(or.getProperty("product3"));
		homePage.clickOnSearch();
		productPrice = searchResultPage.getPriceFromArticle("1");
		double priceDouble = searchResultPage.getThePriceAmount(productPrice);
		/**
		 * Add items to the Cart
		 *  - store Unit Price from cart
		 */
		searchResultPage.addToCartMultipleItems("1", numberOfAddedItems);

		double UnitPricFromGrid = shoppingCartPage.getUnitPrice();
		Assert.assertEquals(UnitPricFromGrid, (priceDouble));

		double TotalTopValue = shoppingCartPage.assertTotalTop_value((priceDouble * numberOfAddedItems));

		shoppingCartPage.assertEcoTaxValue(numberOfAddedItems * 2);

		shoppingCartPage.assertTotalBottom_value("VAT_YES", TotalTopValue);	
		
		shoppingCartPage.clickCheckout();
		
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
		
		checkoutPage.clickOnUpdate();
		Thread.sleep(1000);
		
		super.assertElementIsNotDisplayed(By.cssSelector("#checkout-total tr:nth-of-type(4) td:nth-of-type(1)"));
		
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
		
		topHeader.logOut();
		TestUtil.assertIsDisplayed(loginPage.logOutMessage());
	}
	
}
