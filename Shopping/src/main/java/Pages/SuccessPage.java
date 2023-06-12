package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;

public class SuccessPage {
	
	private WebDriver driver;
	private TestUtil testUtil;
	
	public SuccessPage(WebDriver driver) {
		this.driver = driver;
		this.testUtil = new TestUtil();
	}

	public WebElement topLeftIcon() {
		return testUtil.findElement(LocatorType.XPATH,"//li[@class='breadcrumb-item active']");
	}

	public WebElement successMessageOrder () {
		return testUtil.findElement(LocatorType.XPATH,  "//div[@id='common-success']//p[text()='Your order has been successfully processed!']");
	}
	
	public WebElement continueButton () {
		return testUtil.findElement(LocatorType.XPATH, "//div[@id='common-success']//a[text()='Continue']");
	}
	
	
	public void continiueClick() {
		testUtil.clickOnElement(continueButton());
		
	}
	


}
