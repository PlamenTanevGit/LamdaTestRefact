package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class CheckoutPage {
	
	private WebDriver driver;
	private TestUtil testUtil;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.testUtil = new TestUtil(driver);

	}

	public WebElement topLeftIcon() {
		return testUtil.findElement(LocatorType.XPATH,
				"//li[@class='breadcrumb-item active']");
	}
	
	
	public WebElement updateInnerIcon() {
		return testUtil.findElement(LocatorType.CSS,
				"td:nth-of-type(3)  .input-group-append > button:nth-of-type(1)");
	}
	
	public WebElement VATlabel() {
		return testUtil.findElement(LocatorType.CSS,
				"#checkout-total tr:nth-of-type(4) td:nth-of-type(1)");
	}

	
	
	public void clickOnUpdate() throws InterruptedException {
		testUtil.clickOnElement(updateInnerIcon());
		testUtil.pausems(3);
		testUtil.clickOnElement(updateInnerIcon());
		testUtil.pause(3);
	
	}
	

}
