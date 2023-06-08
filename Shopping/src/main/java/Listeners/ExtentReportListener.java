package Listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utils.TestUtil;

public class ExtentReportListener extends TestUtil implements ITestListener {

    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";

    public static ExtentTest extentTest;
    public static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> ThreadLocalTest = new ThreadLocal<ExtentTest>();
    public static ExtentReports extentReports;

    public static ExtentReports init() {

        Path path = Paths.get(OUTPUT_FOLDER);
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        /**
         * reporter Config
         */
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setEncoding("utf-8");
        reporter.config().setReportName("Automation Test Results");

        /**
         * report system info
         */
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "Windows");
        extentReports.setSystemInfo("Author", "Plamen TaNev");
        extentReports.setSystemInfo("Build#", "1.1");
        extentReports.setSystemInfo("Team", "Automation Team");
        extentReports.setSystemInfo("ENV NAME ", "- ven02303");
        extentReports.setSystemInfo(" Browser :  ", config.getProperty("browser"));

        return extentReports;
    }

    public synchronized void onStart(ITestContext context) {
        try {
            String methodName = context.getName();
            String clas = context.getClass().getName();
            String logText =  "Test Class_" + clas + "Test Case_" + methodName + "_has Started";
            
//            MyScreenRecorder.startRecording(logText);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        System.out.println("Test Suite started!");

        String methodName = context.getName();
        String clas = context.getClass().getName();
        String logText = "<b>" + "Test Class " + clas + "Test Case:- " + methodName + " has Started " + "</b>";
        Reporter.log(logText);
        ThreadLocalTest.set(extentTest);
//     ThreadLocalTest.get().info(logText);

    }

    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
        
        try {
//            MyScreenRecorder.stopRecording();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        extent.flush();
        ThreadLocalTest.remove();
    }

    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");

        /**
         * 1st way
         */
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        /**
         * 2nd way
         */

//      ExtentTest extentTest = extent
//              .createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName(),
//                      result.getMethod().getDescription());

        ThreadLocalTest.set(extentTest);

        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        /*
         * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
         * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
         */
        extentTest.assignCategory(className);
        ThreadLocalTest.set(extentTest);
        ThreadLocalTest.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " Passed !"));
//      ThreadLocalTest.get().pass("Test passed");
        // ThreadLocalTest.get().pass(result.getThrowable(),
        // MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

        /**
         * Set Color marker = Green
         */
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        ThreadLocalTest.get().pass(m);

        ThreadLocalTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " Failed!"));
        try {

            /**
             * ITestContext and WebDriver
             * added additionally
             */
            ITestContext context = result.getTestContext();
            WebDriver driver = (WebDriver) context.getAttribute("WebDriver");

            TestUtil testUtil = new TestUtil();
            String img6 = testUtil.ScreenCapture();
            ThreadLocalTest.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(img6).build());

        } catch (IllegalArgumentException | SecurityException | IOException e) {
            e.printStackTrace();
        }

        String failureLogg = "TEST CASE FAILED";
        Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        ThreadLocalTest.get().log(Status.FAIL, m);

        String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        ThreadLocalTest.get()
                .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                        + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                        + " \n");

        ThreadLocalTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " Skipped!"));

        try {

              /**
             * ITestContext and WebDriver
             * added additionally
             */
            ITestContext context = result.getTestContext();
            WebDriver driver = (WebDriver) context.getAttribute("WebDriver");

            TestUtil testUtil = new TestUtil();
            String img6 = testUtil.ScreenCapture();
            ThreadLocalTest.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(img6).build());

        } catch (IllegalArgumentException | SecurityException | IOException e) {
            e.printStackTrace();
        }

        String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        ThreadLocalTest.get()
                .fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                        + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                        + " \n");

        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        ThreadLocalTest.get().skip(m);

        ThreadLocalTest.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
