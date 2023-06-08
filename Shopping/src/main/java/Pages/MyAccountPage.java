package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class MyAccountPage {
	
	private WebDriver driver;
	private TestUtil testUtil;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		this.testUtil = new TestUtil(driver);

	}
	

	public WebElement title () {
		return testUtil.findElement(LocatorType.XPATH,
				"//h2[contains(text(),'My Account')]");
	}
	
	
	
	
	
	

}
