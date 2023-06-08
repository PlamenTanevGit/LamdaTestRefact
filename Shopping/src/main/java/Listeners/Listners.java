package Listeners;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlTest;

import Utils.DriverFactory;
import Utils.TestUtil;


public class Listners extends DriverFactory implements ITestListener, ISuiteListener {

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("Starting test method:" + result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getName() + " is Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		TestUtil testUtil = new TestUtil();
		Reporter.log(result.getName() + " is Failed");

		try {
			Reporter.log("Click to see Screenshot");
			Reporter.log("<a target=\"_blank\" href=" + testUtil.ScreenCapture() + ">Screenshot</a>");

		} catch (IOException e1) {
			e1.printStackTrace();
			Reporter.log("<br>" + "Verification failure : " + e1.getMessage() + "<br>");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getName() + " is Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log(context.getName() + " Starts");

	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log(context.getName() + " Finish");

	}

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Suite Start : " + suite.getName());
		Reporter.log("Suite Start : " + suite.getName());
		for (XmlTest xmlTest : suite.getXmlSuite().getTests()) {
			System.out.println("Test  detected: " + xmlTest.getName());
			Reporter.log("Test  detected: " + xmlTest.getName());

			for (XmlClass xmlClass : xmlTest.getClasses()) {
				System.out.println("Class detected: " + xmlClass.getName());
				Reporter.log("Class detected: " + xmlClass.getName());
			}

			System.out.println("*******Suite Listener End\n");
		}

	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Suite Finish : " + suite.getName());
		Reporter.log("Suite Finish : " + suite.getName());

	}

}
