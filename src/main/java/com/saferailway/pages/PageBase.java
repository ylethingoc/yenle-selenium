package com.saferailway.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static com.saferailway.utils.GlobalVariables.WAIT_TIME_30_SECS;

public class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
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

    public static Alert waitForAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_30_SECS);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public static WebElement findElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
        waitForPageLoaded(driver);
    }

    public static WebElement scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
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

    public static void switchToRightTab(WebDriver driver) {
        int numTabs = driver.getWindowHandles().size();
        switchTab(driver, numTabs - 1);
    }

    public static void switchTab(WebDriver driver, int tabNum) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[tabNum].toString());
    }

    protected int getRandomSecond() {
        Random random = new Random();
        return (random.nextInt(3) + 1 ) * 1000;
    }
}
