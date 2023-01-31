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

	@Test(enabled = true, 
			dataProvider = "productInputs")
	public void t3_search_ProductInTheSearchFieldAndVerifyResults(String productName, String price, int size)
			throws InterruptedException {

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.enterProductInSearchField(productName);
		homePage.clickOnSearch();

		searchResultPage.componentsVerify();

		Assert.assertTrue(searchResultPage.searchResultsTitle(productName).getText().equals("Search - " + productName));

		List<WebElement> Str = super.getElementList("//div[@class='row']/div", LocatorType.XPATH);

		Assert.assertTrue(Str.size() == size);

		for (int i = 0; i < Str.size(); i++) {
			System.out.println(Str.get(i).getText());
			Assert.assertTrue(Str.get(i).getText().contains(productName));
			Assert.assertTrue(Str.get(i).getText().contains(price));
		}

	}
	


	
}
