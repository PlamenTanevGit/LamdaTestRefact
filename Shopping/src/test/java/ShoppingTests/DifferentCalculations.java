package ShoppingTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.AddPersonalDetails;
import Utils.TestBase;
import Utils.TestUtil;


public class DifferentCalculations extends TestBase {
	/***
	 * The tests will do 
	 * - Test 1 - calculations checks for Sub-Total:/ Flat Shipping Rate:/Eco Tax /VAT /Total: for country with VAT 
	 * - Test 2 - calculations checks for Sub-Total:/ Flat Shipping Rate:/Eco Tax /VAT /Total: for country without VAT 
	 */
	
	@DataProvider(name = "calculationsInputsWithVAT")
	public Object[][] getData() {
		return new Object[][] { 
			{ "1,","123.2", "123.2","101.0","5.0","4.0","21.2","131.2" },  // QUANTITY ,UNIT PRICE, TOTAL, Sub-Total,Flat ShippingRate, Eco Tax, VAT value, Total 
			// { "2,","123.2", "246.4","202.0","5.0","6.0","41.4","254.4" },  // QUANTITY ,UNIT PRICE, TOTAL, Sub-Total,Flat ShippingRate, Eco Tax, VAT value, Total 
			// { "3,","123.2", "369.60","303.0","5.0","8.0","61.6","377.6" },  // QUANTITY ,UNIT PRICE, TOTAL, Sub-Total,Flat ShippingRate, Eco Tax, VAT value, Total 
			
		};		
			
	}	
	
	@DataProvider(name = "calculationsInputsWithoutVAT")
	public Object[][] getData2() {
		return new Object[][] { 
			{ "1,","101.0", "101.0","101.0","5.0","106.0" },  // QUANTITY ,UNIT PRICE, TOTAL, Sub-Total,Flat ShippingRate, Total 
			// { "2,","101.0", "202.0","202.0","5.0","207.0" },  // QUANTITY ,UNIT PRICE, TOTAL, Sub-Total,Flat ShippingRate, Total 
			// { "3,","101.0", "303.0","303.0","5.0","308.0" },  // QUANTITY ,UNIT PRICE, TOTAL, Sub-Total,Flat ShippingRate, Total 
			
		};


	}

	@Test(alwaysRun = true,
			dataProvider = "calculationsInputsWithVAT",
			description = "The test performs Assertions on different quantities calculations ")
	public void t1_calculations_VAT_YES_RegisteredAccount (
			String quantity,
			String UNIT__PRICE,
			String TOTAL_,
			String SubTotal,
			String FlatShipping,
			String Eco,
			String VATValue,
			String Total
			) throws InterruptedException {
		
		int numberOfAddedItems = 1;
		String productPrice = null;

		// openUrl(config.getProperty("homePage"));
		openUrl(getEnvironmentUrl());

		homePage.enterProductInSearchField(or.getProperty("product3"));
		homePage.clickOnSearch();
		productPrice = searchResultPage.getPriceFromArticle("1");
		double priceDouble = searchResultPage.getThePriceAmount(productPrice);
		searchResultPage.addMultipleItems("1", numberOfAddedItems);
		double unitPriceGrid = shoppingCartPage.getUnitPrice();


		//  Fill the Registered account form
		addPersonalDetails = new AddPersonalDetails(driver,
				fakerData.getFirstName(),
				fakerData.getLastName(),
				fakerData.getEmail(),
				fakerData.getTelephone(),
				fakerData.getCompany(),
				fakerData.getAddress1(),
				fakerData.getPostCode(),
				or.getProperty("city2"),
				or.getProperty("country2"),
				or.getProperty("region2"));
		
		/**
		 * Provide the Quantity
		 * and
		 * Click on Update Quantity
		 * and
		 * mark Privacy Policy
		 * mark Terms And Conditions
		 */
		accountRegisterPage.setQuantity(quantity);
		checkoutPage.clickOnUpdate();
		/**
		 * Assertion for Unit Price and Sub-Total
		 * - Assertion for Unit Price == Sub-Total
		 * - Assertion for Unit price value
		 * - Assertion for Sub-Total price value
		 */
		fascadePage.assertionsYesVat("VAT_YES", UNIT__PRICE, TOTAL_, SubTotal, FlatShipping, Eco, VATValue, Total);
	
	
		/**
		 * click on Remove to clean the Cart
		 */
		accountRegisterPage.removeItem();
	}
	

	@Test(alwaysRun = true,
			dataProvider = "calculationsInputsWithoutVAT",
			description = "The test performs Assertions on different quantities calculations ")
	public void t2_calculationsNO_VAT_RegisteredAccount (
			String quantity,
			String UNIT__PRICE,
			String TOTAL_,
			String SubTotal,
			String FlatShipping,
			String Total
			) throws InterruptedException {

		int numberOfAddedItems = 1;
		String productPrice = null;

		// openUrl(config.getProperty("homePage"));
		openUrl(getEnvironmentUrl());

		homePage.enterProductInSearchField(or.getProperty("product3"));
		homePage.clickOnSearch();
		productPrice = searchResultPage.getPriceFromArticle("1");
		double priceDouble = searchResultPage.getThePriceAmount(productPrice);
		searchResultPage.addMultipleItems("1", numberOfAddedItems);
		double unitPriceGrid = shoppingCartPage.getUnitPrice();

		/**
		 * Fill the Registered account form
		 */
		addPersonalDetails = new AddPersonalDetails(driver, 
				fakerData.getFirstName(),
				fakerData.getLastName(),
				fakerData.getEmail(),
				fakerData.getTelephone(),
				fakerData.getCompany(),
				fakerData.getAddress1(),
				fakerData.getPostCode(),
				or.getProperty("city1"), 
				or.getProperty("country1"), 
				or.getProperty("region1"));
		
		/**
		 * Provide the Quantity 
		 * and
		 * Click on Update Quantity 
		 * and 
		 * mark Privacy Policy
		 * mark Terms And Conditions		
		 */
		accountRegisterPage.setQuantity(quantity);
		checkoutPage.clickOnUpdate();
		
		fascadePage.assertionsNoVat("VAT_NO",UNIT__PRICE, TOTAL_, SubTotal, FlatShipping, Total);		

		/**
		 * click on Remove to clean the Cart
		 */
		accountRegisterPage.clickOnRemoveButton();
		tDriver.get().navigate().refresh();
	}


	/**
	 * This test is Asserting the calculations for different quantities for NO VAT Country
	 * it uses Data provider with 3 sets of data
	 * it usses Registered Account
	 */
	@Test(alwaysRun = true,
			dataProvider = "calculationsInputsWithoutVAT",
			description = "The test performs Assertions on different quantities calculations ")
	public void t3_facadeRegisteredAccountNoVAT (
			String quantity,
			String UNIT__PRICE,
			String TOTAL_,
			String SubTotal,
			String FlatShipping,
			String Total
			) throws InterruptedException {

		int numberOfAddedItems = 1;
		String productPrice = null;
		// String quantity = "1,";

		double priceDouble = fascadePage.registeredAccountCheckoutProduct(
			openUrl(getEnvironmentUrl()),
			or.getProperty("product3"),
			numberOfAddedItems,
			fakerData.getFirstName(),
			fakerData.getLastName(),
			fakerData.getEmail(),
			fakerData.getTelephone(),
			or.getProperty("password"),
			fakerData.getCompany(),
			fakerData.getAddress1(),
			fakerData.getPostCode(),
			or.getProperty("city1"),
			or.getProperty("country1"),
			or.getProperty("region1"),
			quantity
			);

		fascadePage.assertionsNoVat("VAT_NO",UNIT__PRICE, TOTAL_, SubTotal, FlatShipping, Total);			
		
		/**
		 * click on Remove to clean the Cart
		 */
		accountRegisterPage.clickOnRemoveButton();
		tDriver.get().navigate().refresh();

	}


	@Test(alwaysRun = true,
			dataProvider = "calculationsInputsWithoutVAT",
			description = "The test performs Assertions on different quantities calculations ")
	public void t4_fascadeCalculations_VAT_NO_GuestAccount (
			String quantity,
			String UNIT__PRICE,
			String TOTAL_,
			String SubTotal,
			String FlatShipping,
			String Total
			) throws InterruptedException {


		fascadePage.registeredAccountGuestProduct(
			openUrl(getEnvironmentUrl()),
			or.getProperty("product3"),
			1,
			fakerData.getFirstName(),
			fakerData.getLastName(),
			fakerData.getEmail(),
			fakerData.getTelephone(),
			fakerData.getCompany(),
			fakerData.getAddress1(),
			fakerData.getPostCode(),
			or.getProperty("city1"),
			or.getProperty("country1"),
			or.getProperty("region1"),
			quantity
			);

		fascadePage.assertionsNoVat("VAT_NO",UNIT__PRICE, TOTAL_, SubTotal, FlatShipping, Total);
		
		/**
		 * click on Remove to clean the Cart
		 */
		accountRegisterPage.removeItem();	

	}

	@Test(	enabled = false,
			dataProvider = "calculationsInputsWithoutVAT",
			description = "The test performs Assertions on different quantities calculations ")
	public void t5_facadeGuestAccountShippingAddress_VAT_NO (
			String quantity,
			String UNIT__PRICE,
			String TOTAL_,
			String SubTotal,
			String FlatShipping,
			String Total
			) 		throws InterruptedException {

		fascadePage.registeredAccountGuestProductShippingAddress(
			openUrl(getEnvironmentUrl()),
			or.getProperty("product3"),
			1,
				// Billing Address Details
			fakerData.getFirstName(),
			fakerData.getLastName(),
			fakerData.getEmail(),
			fakerData.getTelephone(),
			fakerData.getCompany(),
			fakerData.getAddress1(),
			fakerData.getPostCode(),
			or.getProperty("city1"),
			or.getProperty("country1"),
			or.getProperty("region1"),
				//quantity
			quantity,
				// Shipping Address Details
			or.getProperty("firstName")+testUtil.randomString(5),
			or.getProperty("lastName")+testUtil.randomString(5),
			or.getProperty("company")+testUtil.randomString(3),
			or.getProperty("address1")+testUtil.randomString(5),
			or.getProperty("address2")+testUtil.randomString(5),
			or.getProperty("city1"),
			or.getProperty("postCode"),

			or.getProperty("country1"),
			or.getProperty("region1")

			);

			fascadePage.assertionsNoVat("VAT_NO",UNIT__PRICE, TOTAL_, SubTotal, FlatShipping, Total);		
		
		/**
		 * click on Remove to clean the Cart
		 */
		accountRegisterPage.removeItem();
	

	}

	
	
	

}
