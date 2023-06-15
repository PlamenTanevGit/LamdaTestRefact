package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import Pages.AccountRegisterPage;
import Pages.AddPersonalDetails;
import Pages.CheckoutPage;
import Pages.ConfirmOrderPage;
import Pages.FascadePage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import Pages.SearchResultPage;
import Pages.ShoppingCartPage;
import Pages.SuccessPage;
import Pages.TopHeaderPage;

public class TestBase {

	// Properties Initialize
	public static Properties config = new Properties();
	public  Properties or = new Properties();
	public  java.io.FileInputStream fis;
	public  java.io.FileInputStream fis2;
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
	public WebDriver driver;
	public WebDriverWait wait;
	public  SoftAssert softAssert;
	public  WebElement element;
	public JavascriptExecutor js;
	String screenshotName;
	Actions actions;
	long startTime;
	long endTime;

	/** Pages */
	protected HomePage homePage;
	protected SearchResultPage searchResultPage;
	protected ShoppingCartPage shoppingCartPage;
	protected CheckoutPage checkoutPage;
	protected AccountRegisterPage accountRegisterPage;
	protected ConfirmOrderPage confirmOrderPage;
	protected TopHeaderPage topHeader;
	protected SuccessPage successPage;
	protected LoginPage loginPage;
	protected MyAccountPage myAccountPage;
	protected AddPersonalDetails addPersonalDetails;
	protected FascadePage fascadePage;
	protected TestUtil testUtil;
	protected FakerData fakerData;

	protected  final String PROPERTIES_FILE = "config.properties";
	protected  final String ENVIRONMENT_URL_KEY = "environment.url";

	private  final Properties properties = new Properties();

	public TestBase () {
		this.driver = tDriver.get();
		initConfiguration();
	}

	public  void initConfiguration() {

		try (InputStream inputStream = TestUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			config.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fis2 = new FileInputStream(System.getProperty("user.dir") + "\\properties\\or.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			or.load(fis2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public  String getEnvironmentUrl() {
		initConfiguration();
			return properties.getProperty(ENVIRONMENT_URL_KEY);
	}

	public  String openUrl(String url) {
		tDriver.get().navigate().to(url);
		Reporter.log("Successfully opened url '" + url + "'");
		return url;
	}

	
	@BeforeSuite
	public void beforeSuite() {
		Reporter.log("");
		startTime = System.nanoTime();
		softAssert = new SoftAssert();

	}

	@BeforeMethod
	public void beforeClass() {
		DriverFactory drvierFactory = new DriverFactory();
		tDriver.set(drvierFactory.initialise(Browsers.CHROME));
		//this.driver=tDriver.get();
		System.out.println(tDriver.toString());
		System.out.println("running..................");
		Reporter.log(Browsers.FIREFOX + " is Initialised");
		initConfiguration();

		this.homePage = new HomePage(tDriver.get());
		this.searchResultPage = new SearchResultPage(tDriver.get());
		this.shoppingCartPage = new ShoppingCartPage(tDriver.get());
		this.checkoutPage = new CheckoutPage(tDriver.get());
		this.accountRegisterPage = new AccountRegisterPage(tDriver.get());
		this.confirmOrderPage = new ConfirmOrderPage(tDriver.get());
		this.topHeader = new TopHeaderPage(tDriver.get());
		this.successPage = new SuccessPage(tDriver.get());
		this.loginPage = new LoginPage(tDriver.get());
		this.myAccountPage = new MyAccountPage(tDriver.get());
		this.addPersonalDetails = new AddPersonalDetails(tDriver.get());
		this.fascadePage = new FascadePage(tDriver.get());
		this.testUtil = new TestUtil();	
		this.fakerData = new FakerData();
	}

	@AfterMethod
	public void afterClass() {

		if (tDriver.get() != null) {
			tDriver.get().quit();
		}

	}

	@AfterSuite
	public void afterSuite() {
		if (tDriver.get() != null) {
			tDriver.get().quit();
		}
		Reporter.log("Driver quit");
		endTime = System.nanoTime();
		long dur = endTime - startTime;
		long convert = TimeUnit.SECONDS.convert(dur, TimeUnit.NANOSECONDS);
		Reporter.log("Duration of Suite is : " + convert + " sec.");
		System.out.println("Duration of Suite is : " + convert + " sec.");

	}

}
