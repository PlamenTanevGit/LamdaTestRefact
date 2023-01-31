package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class MyAccountPage {
	
	private WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;

	}
	

	public WebElement title () {
		return TestUtil.findElement(LocatorType.XPATH,
				"//h2[contains(text(),'My Account')]");
	}
	
	
	
	
	
	

}
