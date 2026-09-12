package reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import basePackage.baseClass;


public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        System.out.println("========= Test Execution Started =========");

    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
        test.get().fail(result.getThrowable());
        String screenshot =
		        baseClass.takeScreenshot(result.getMethod().getMethodName());

		test.get().addScreenCaptureFromPath(screenshot);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip(result.getThrowable());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {

        onTestFailure(result);

    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println("========= Report Generated =========");

    }
}
