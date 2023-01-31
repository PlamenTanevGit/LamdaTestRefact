package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class SpecialPage  {
	
	private WebDriver driver;
	
	public SpecialPage(WebDriver driver) {
		this.driver=driver;

	}

	public WebElement searchField() {
		return TestUtil.findElement(LocatorType.XPATH,"//div[@class='entry-module module-mz_filter d-none d-lg-block']//h3[text()='Filter']");
	}

	
	



}
