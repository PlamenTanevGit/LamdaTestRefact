package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;

public class SuccessPage {
	
	private WebDriver driver;
	
	public SuccessPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement topLeftIcon() {
		return TestUtil.findElement(LocatorType.XPATH,"//li[@class='breadcrumb-item active']");
	}

	public WebElement successMessageOrder () {
		return TestUtil.findElement(LocatorType.XPATH,  "//div[@id='common-success']//p[text()='Your order has been successfully processed!']");
	}
	
	public WebElement continueButton () {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@id='common-success']//a[text()='Continue']");
	}
	
	
	public void continiueClick() {
		TestUtil.clickOnElement(continueButton());
		
	}
	


}
