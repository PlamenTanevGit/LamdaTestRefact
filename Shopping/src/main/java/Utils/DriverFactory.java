package Utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Listeners.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static final int TIMEOUT = 10;
	private static final int PAGE_TIMEOUT = 35;
	private static final int SCRIPT_TIMEOUT = 35;
	public  WebDriver driver;
	public  EventFiringWebDriver edriver;

	public  ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public  WebDriver initialise(Browsers browsers) {
		ChromeOptions chromeOptions = new ChromeOptions();
		FirefoxOptions firefoxOptions = new FirefoxOptions();

		switch (browsers) {

		case CHROME:
			WebDriverManager.chromedriver().setup();

			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--window-size=1500,1080");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("--disable-default-apps");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);

			break;

		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			firefoxOptions = getFirefoxOptions();
			driver = new FirefoxDriver(firefoxOptions);

			break;

		case CHROMEOPTION:
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.setExperimentalOption("debuggerAddress", "localhost:20363");
			driver = new ChromeDriver(option);

			break;

		default:
			break;
		}

		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		/*
		 * create an EventFiringWebDriver object that will accepts driver but follows
		 * when events are triggered
		 */
		edriver = new EventFiringWebDriver(driver);

		/*
		 * create an object of Listeners class (that has extended
		 * 'WebDriverEventListener' and holds different listener methods)
		 */
		WebEventListener listeners = new WebEventListener();

		// Register the listeners
		edriver.register(listeners);

		return edriver;

	}

	public  WebDriver getDriver() {
		return tlDriver.get();
	}

	public  void waitForAjax() {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	public static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addArguments("--disable-web-security");
		firefoxOptions.addArguments("--allow-running-insecure-content");
		firefoxOptions.addArguments("-private");
		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		profile.setPreference("pageLoadStrategy", "normal");
		firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
		return firefoxOptions;
	}

}
