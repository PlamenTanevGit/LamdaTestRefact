package ShoppingTests;

import org.testng.annotations.Test;

import Utils.TestUtil;

public class TESTS_Login extends TestUtil {

	@Test(alwaysRun = true, description = "This Test performs Login with valid Credentials")
	public void t1_Login_Valid_UserCredentials() throws InterruptedException {

		TestUtil.openUrl(config.getProperty("loginPage"));

		loginPage.doLogin(
				or.getProperty("usernameLogin"), 
				or.getProperty("passwordLogin"));

		TestUtil.assertIsDisplayed(myAccountPage.title());
		topHeader.logOut();
		TestUtil.assertIsDisplayed(loginPage.logOutMessage());

	}

	@Test(alwaysRun = true, description = "This Test performs Login with Invalid Credentials and Asserts warning message is displayed ")
	public void t2_Login_Invalid_UserCredentials() throws InterruptedException {

		TestUtil.openUrl(config.getProperty("loginPage"));
		loginPage.doLogin(
				or.getProperty("usernameLogin"),
				or.getProperty("passwordLogin2"));

		TestUtil.assertIsDisplayed(loginPage.warningMessage());

	}

}
