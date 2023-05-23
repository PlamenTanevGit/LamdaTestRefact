package ShoppingTests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import DataProviders.DP;
import Utils.LocatorType;
import Utils.TestUtil;

public class TESTS_HomePageCSV extends TestUtil {
	/**
	 * This test will do the search for multiple valid items
	 *  and 
	 * Assert that search is successful and items are showing after searched in the search field
	 * 
	 * the test is using dataProvider as CSV frile
	 * 
	 */

	
	@Test(enabled = true, 
			dataProviderClass = DP.class, 
			dataProvider = "getDataFromCSV" 
			)
	public void t2_csvTest (String product, String numberOfResults)
			throws InterruptedException {
		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.enterProductInSearchField(product);

		List<WebElement> Str = super.getElementList(
				"//div[@id='entry_217822']//ul[@class='dropdown-menu autocomplete w-100']/li", LocatorType.XPATH);
		
		
		Assert.assertTrue(Str.size() == TestUtil.convertStringToInt(numberOfResults));

		for (int i = 0; i < Str.size(); i++) {
			System.out.println(Str.get(i).getText());
			Assert.assertTrue(Str.get(i).getText().contains(product));

		}

	}
	
	
	
	@Test(enabled = true, 
			dataProviderClass = DP.class, 
			dataProvider = "getDataFromCSV")
	public void dbg(String product, String numberOfResults)
			throws InterruptedException {
		
		System.out.println(product);
		System.out.println(numberOfResults);
	}
	
		
}
