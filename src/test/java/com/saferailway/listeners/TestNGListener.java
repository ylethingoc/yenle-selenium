package com.saferailway.listeners;

import com.aventstack.extentreports.ExtentReports;
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

    ExtentReports extentReports;
    @Override
    public void onStart(ITestContext result) {
        extentReports = TestBase.getExtentReports();
    }

    @Override
    public void onFinish(ITestContext result) {
        extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName());
        TestBase.setExtentTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            WebDriver driver = ((TestBase) result.getInstance()).driver;

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            File theDir = new File("outputs/" + TestBase.timestamp + "/");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            String screenshotPath = "outputs/" + timestamp + "/screenshot.png";
            File destination = new File(screenshotPath);

            FileHandler.copy(source, destination);

            System.out.println("Screenshot captured: " + screenshotPath);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            StringWriter sw = new StringWriter();
            throwable.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            TestBase.extentTest.log(Status.FAIL, exceptionAsString);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        TestBase.extentTest.log(Status.WARNING, "Test Failed but within success percentage");
        TestBase.extentTest.log(Status.WARNING, "Failure details: " + result.getThrowable());
    }

}

