package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class SpecialPage  {
	
	private WebDriver driver;
	private TestUtil testUtil;
	
	public SpecialPage(WebDriver driver) {
		this.driver=driver;
		this.testUtil = new TestUtil();

	}

	public WebElement searchField() {
		return testUtil.findElement(LocatorType.XPATH,"//div[@class='entry-module module-mz_filter d-none d-lg-block']//h3[text()='Filter']");
	}

	
	



}
