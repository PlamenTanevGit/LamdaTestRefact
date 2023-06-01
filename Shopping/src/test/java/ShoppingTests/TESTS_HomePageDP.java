package ShoppingTests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.LocatorType;
import Utils.TestUtil;

public class TESTS_HomePageDP extends TestUtil {

	
//	@DataProvider(name = "productInputs")
//	public Object[][] getData() {
//		return new Object[][] { 
//			{ or.getProperty("product1"), or.getProperty("productPrice1"), TestUtil.convertStringToInt(or.getProperty("productCount1")) }, 
//			{ or.getProperty("product2"), or.getProperty("productPrice2"), TestUtil.convertStringToInt(or.getProperty("productCount2")) },};
//	}
	
	@DataProvider(name = "productInputs")
	public Object[][] getData() {
		return new Object[][] { 
			{ "Palm Treo Pro", "$337.99",2 }, 
			{ "HTC Touch HD", "$146.00",8 }};
	}
	
	/**
	 * The test will search multiple products in the search and 
	 *  Assert the size of the result
	 *  Assert the name of the products from the result
	 *  Assert the price of the products from the result
	 */

	@Test(enabled = true, 
			dataProvider = "productInputs",
			description = "The test will search multiple products in the search and Assert the size of the products as results")
	public void t3_search_ProductInTheSearchFieldAndVerifyResults(String productName, String price, int size)
			throws InterruptedException {
		/**
		 * Navigate to the Home Page
		 */
		TestUtil.openUrl(config.getProperty("homePage"));
		/**
		 * Enter product in the Search field and click on Search button
		 */
		homePage.enterProductInSearchField(productName);
		homePage.clickOnSearch();

		/**
		 * Multiple verifictaions on Filter menu
		 */
		searchResultPage.componentsVerify();

		Assert.assertTrue(searchResultPage.searchResultsTitle(productName).getText().equals("Search - " + productName));

		List<WebElement> Str = super.getElementList("//div[@class='row']/div", LocatorType.XPATH);
		/**
		 *  Assertion for
		 *   - size
		 *   - product name
		 *   - price 
		 */
		Assert.assertTrue(Str.size() == size);

		for (int i = 0; i < Str.size(); i++) {
			System.out.println(Str.get(i).getText());
			Assert.assertTrue(Str.get(i).getText().contains(productName));
			Assert.assertTrue(Str.get(i).getText().contains(price));
		}

	}
	


	
}
