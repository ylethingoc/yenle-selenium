package com.saferailway.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import pages.saferailway.HomePage;
import pages.saferailway.MyTicketPage;
import utils.ConfigParser;
import utils.GlobalVariables;
import utils.JSONUtils;
import utils.Log;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static utils.GlobalVariables.WAIT_TIME_60_SECS;

public class TestBase {

    protected static String timestamp;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private String testClassName;
    protected static GlobalVariables globalVars;
    HomePage homePage;
    MyTicketPage myTicketPage;

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
        ConfigParser configParser = new ConfigParser("src/main/resources/config.properties");
        globalVars = new GlobalVariables(configParser);
        initReport();
    }

    @BeforeClass
    public void setUpClass() {
        initDriver(globalVars.getBrowser(), Boolean.parseBoolean(globalVars.getHeadlessConfig()));
    }

    @AfterClass
    public void removeTicket() {
        myTicketPage = new MyTicketPage(getDriver());
        myTicketPage.removeBookedTickets();
        homePage = new HomePage(getDriver());
        homePage.clickOnLogOutLabel(getDriver());
    }

    @AfterClass
    public void tearDownClass() {
        if (driver.get() != null) {
            getDriver().quit();
            driver.remove();
            Log.info("Browser is closed successfully");
        }
    }

    public void initReport() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HHmmss");
        timestamp = currentDateTime.format(formatter);
        String filePath = "outputs/" + timestamp + "/report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
        sparkReporter.config().setDocumentTitle("Test Report");
        sparkReporter.config().setReportName("SafeRailway Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    public void initDriver(String browser, boolean isHeadless) {
        if (isHeadless)
            browser = "chrome";

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver.set(new SafariDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(WAIT_TIME_60_SECS, TimeUnit.SECONDS);
        Log.info("Browser is initiated successfully");
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    protected ExtentReports getExtentReports() {
        return extentReports;
    }

    protected void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public ExtentTest getExtentTest() {
        return extentTest.get();
    }

    protected void logStep(Status status, String message) {
        if (extentTest != null) {
            extentTest.get().log(status, message);
        }
    }

}
