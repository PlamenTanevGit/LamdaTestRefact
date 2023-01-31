package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.LocatorType;
import Utils.TestUtil;


public class LoginPage  {
	
	private WebDriver driver;
	private MyAccountPage myAccountPage;

		
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		myAccountPage = new MyAccountPage(driver);

	}

	public WebElement emailAddressField () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-email']");
	}
	
	public WebElement passwordField () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@id='input-password']");
	}

	public WebElement loginButton () {
		return TestUtil.findElement(LocatorType.XPATH, "//input[@value='Login']");
	}
	
	public WebElement warningMessage () {
		return TestUtil.findElement(LocatorType.XPATH, "//div[@class='alert alert-danger alert-dismissible']");
	}	
	
	public WebElement logOutMessage () {
		return TestUtil.findElement(LocatorType.XPATH, "//h1[@class='page-title my-3'][text()=' Account Logout']");
	}
	
	
	public void doLogin (String user, String pass) {	
		TestUtil.typeOnField(emailAddressField(), user);
		TestUtil.typeOnField(passwordField(), pass);
		TestUtil.clickOnElement(loginButton());

	}
	
	
	
	

}
