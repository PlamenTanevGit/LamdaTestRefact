package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class LoginPage  {
	
	private WebDriver driver;
	private TestUtil testUtil;

		
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.testUtil = new TestUtil();

	}

	public WebElement emailAddressField () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-email']");
	}
	
	public WebElement passwordField () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@id='input-password']");
	}

	public WebElement loginButton () {
		return testUtil.findElement(LocatorType.XPATH, "//input[@value='Login']");
	}
	
	public WebElement warningMessage () {
		return testUtil.findElement(LocatorType.XPATH, "//div[@class='alert alert-danger alert-dismissible']");
	}	
	
	public WebElement logOutMessage () {
		return testUtil.findElement(LocatorType.XPATH, "//h1[@class='page-title my-3'][text()=' Account Logout']");
	}
	
	
	public void doLogin (String user, String pass) {	
		testUtil.typeOnField(emailAddressField(), user);
		testUtil.typeOnField(passwordField(), pass);
		testUtil.clickOnElement(loginButton());

	}
	
	
	
	

}
