package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class CheckoutPage {
	
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement topLeftIcon() {
		return TestUtil.findElement(LocatorType.XPATH,
				"//li[@class='breadcrumb-item active']");
	}
	
	
	public WebElement updateInnerIcon() {
		return TestUtil.findElement(LocatorType.CSS,
				"td:nth-of-type(3)  .input-group-append > button:nth-of-type(1)");
	}
	
	public WebElement VATlabel() {
		return TestUtil.findElement(LocatorType.CSS,
				"#checkout-total tr:nth-of-type(4) td:nth-of-type(1)");
	}

	
	
	public void clickOnUpdate() throws InterruptedException {
		TestUtil.clickOnElement(updateInnerIcon());
		Thread.sleep(300);
		TestUtil.clickOnElement(updateInnerIcon());
		Thread.sleep(2000);
	
	}
	

}
