package com.saferailway.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.saferailway.HomePage;
import pages.saferailway.MyTicketPage;
import utils.ConfigParser;
import utils.GlobalVariables;
import utils.JsonParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static utils.GlobalVariables.WAIT_TIME_30_SECS;

public class TestBase {

    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static String timestamp;
    public static WebDriver driver;
    protected static String email;
    protected static String password;
    protected String browser;
    HomePage homePage;
    MyTicketPage myTicketPage;
    GlobalVariables globalVars;
    JsonParser jsonParser;
    Map<String, String> data;

    public TestBase() {
        LogManager.getRootLogger();
        ConfigParser configParser = new ConfigParser("resources/config.properties");
        globalVars = new GlobalVariables(configParser);
        browser = globalVars.getBrowser();
    }

    @BeforeSuite
    public void setUp() {
        initReport();
        driver = initDriver(browser);
        jsonParser = new JsonParser("resources/testdata.json");
        String testClassName = getTestClassName();
        data = jsonParser.getTestDataForTestClass(testClassName);
        email = globalVars.getEmail();
        password = globalVars.getPassword();
        driver.get(globalVars.getBaseUrl());
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

    protected String getTestClassName() {
        return this.getClass().getName();
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
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

    public WebDriver initDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
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
        return driver;
    }

    public static ExtentReports getExtentReports() {
        return extentReports;
    }

    public static void setExtentTest(ExtentTest test) {
        extentTest = test;
    }

    public static void logStep(Status status, String message) {
        if (extentTest != null) {
            extentTest.log(status, message);
        }
    }
}
