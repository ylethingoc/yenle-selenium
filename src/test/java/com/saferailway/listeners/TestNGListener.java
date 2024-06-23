package com.saferailway.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saferailway.tests.TestBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TestNGListener extends TestBase implements ITestListener {

    @Override
    public void onStart(ITestContext result) {
    }

    @Override
    public void onFinish(ITestContext result) {
        getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = getExtentReports().createTest(result.getMethod().getMethodName());
        setExtentTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            WebDriver driver = getDriver();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            File theDir = new File("/outputs/" + timestamp + "/");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            String testMethodName = result.getMethod().getMethodName();
            String screenshotPath = "/outputs/" + timestamp + "/" + testMethodName + "-screenshot.png";
            File destination = new File(screenshotPath);

            FileHandler.copy(source, destination);
            Throwable throwable = result.getThrowable();
            if (throwable != null) {
                StringWriter sw = new StringWriter();
                throwable.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                getExtentTest().log(Status.FAIL, exceptionAsString);
                getExtentTest().addScreenCaptureFromPath(screenshotPath, "Screenshot on failure");
            }
            System.out.println("Screenshot captured: " + screenshotPath);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getExtentTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        getExtentTest().log(Status.WARNING, "Test Failed but within success percentage");
        getExtentTest().log(Status.WARNING, "Failure details: " + result.getThrowable());
    }

}
