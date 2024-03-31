package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static helpers.Constants.WAIT_TIME_30_SECS;

public class WebDriverHelper {
    public static WebDriver getWebDriver() {
        WebDriver driver;
        String browser = ConfigurationHelper.getBrowser(); // get browser from config file

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
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

    public static void openTab(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }

    public static void closeTab(WebDriver driver, boolean switchToPrevious) {

        ((JavascriptExecutor) driver).executeScript("window.close()");
        if (switchToPrevious) {
            int numTabs = driver.getWindowHandles().size();
            switchTab(driver, numTabs - 1);
        }

    }

    public static void switchTab(WebDriver driver, int tabNum) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[tabNum].toString());
    }

    public static void switchToLeftTab(WebDriver driver) {
        int numTabs = driver.getWindowHandles().size();
        switchTab(driver, numTabs - 2);
    }

    public static void switchToRightTab(WebDriver driver) {
        int numTabs = driver.getWindowHandles().size();
        switchTab(driver, numTabs - 1);
    }

    public static void waitForPageLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static WebElement waitForElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_30_SECS);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
        waitForPageLoaded(driver);
    }
}