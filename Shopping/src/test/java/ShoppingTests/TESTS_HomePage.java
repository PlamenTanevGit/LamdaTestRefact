package ShoppingTests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.LocatorType;
import Utils.TestUtil;

public class TESTS_HomePage extends TestUtil {

	@Test(enabled = true)
	public void t1_verify_HomePageElements() throws InterruptedException {

		TestUtil.openUrl(config.getProperty("homePage"));

		homePage.homePageComponentsVerify();

		homePage.openSideBar();

		String[] elements = { "Components", "Cameras", "Phone, Tablets & Ipod", "Software", "MP3 Players",
				"Laptops & Notebooks", "Desktops and Monitors", "Printers & Scanners", "Mice and Trackballs",
				"Fashion and Accessories", "Beauty and Saloon", "Autoparts and Accessories", "Washing machine",
				"Gaming consoles", "Air conditioner", "Web Cameras" };

		List<WebElement> Str = super.getElementList(
				"//div[@id='widget-navbar-217841']//ul[@class='navbar-nav vertical']//span", LocatorType.XPATH);

		for (int i = 0; i < elements.length; i++) {
			System.out.println(Str.get(i).getText());
			Assert.assertEquals(elements[i], Str.get(i).getText());

		}

	}

}
