package com.saferailway.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import pages.saferailway.HomePage;
import pages.saferailway.MyTicketPage;
import utils.ConfigParser;
import utils.GlobalVariables;
import utils.JSONUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static utils.GlobalVariables.WAIT_TIME_30_SECS;

public class TestBase {

    protected ExtentReports extentReports;
    protected ExtentTest extentTest;
    protected String timestamp;
    public static WebDriver driver;
    protected static String testClassName;
    public static String url;
    protected String browser;
    protected String runMode;
    HomePage homePage;
    MyTicketPage myTicketPage;
    GlobalVariables globalVars;

    public TestBase() {
        initReport();
    }

    @DataProvider(name = "dataProvider")
    public Object[][] getDataFromFile(Method method) {
        testClassName = method.getDeclaringClass().getSimpleName();
        Map<String, Map<String, String>> testData = JSONUtils.getTestData(testClassName);
        Object[][] data = new Object[testData.size()][1];
        int index = 0;
        for (Map.Entry<String, Map<String, String>> entry : testData.entrySet()) {
            data[index++][0] = entry.getValue();
        }
        return data;
    }

    @BeforeSuite
    public void setUp() {
        ConfigParser configParser = new ConfigParser("resources/config.properties");
        globalVars = new GlobalVariables(configParser);
        runMode = globalVars.getRunMode();
        browser = globalVars.getBrowser();
        url = globalVars.getBaseUrl();
        initDriver(browser, runMode);
    }

    @BeforeClass
    public void setUpClass() {
        driver.get(url);
    }

    @AfterClass
    public void removeTicket() {
        myTicketPage = new MyTicketPage(driver);
        myTicketPage.removeBookedTickets();
        homePage = new HomePage(driver);
        homePage.clickOnLogOutLabel(driver);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void initReport() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HHmmss");
        timestamp = currentDateTime.format(formatter);
        String filePath = "outputs/" + timestamp + "/report.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    public void initDriver(String browser, String runMode) {
        boolean isHeadless = false;
        if (runMode.equalsIgnoreCase("headless")) {
            browser = "chrome";
            isHeadless = true;
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(WAIT_TIME_30_SECS, TimeUnit.SECONDS);
    }

    protected ExtentReports getExtentReports() {
        return extentReports;
    }

    protected String getTestClassName() {
        return testClassName;
    }

    protected void setExtentTest(ExtentTest test) {
        extentTest = test;
    }

    protected void logStep(Status status, String message) {
        if (extentTest != null) {
            extentTest.log(status, message);
        }
    }

}
