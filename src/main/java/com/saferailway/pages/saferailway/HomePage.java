package com.saferailway.pages.saferailway;

import com.saferailway.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.saferailway.utils.Log;

import static com.saferailway.locators.BasePageLocators.DYNAMIC_LABEL;

public class HomePage extends PageBase {
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickOnLoginLabel(WebDriver driver) {
        Log.info("Click on 'Login' label");
        driver.findElement(By.xpath(String.format(DYNAMIC_LABEL, "Login"))).click();
        return new LoginPage(driver);
    }

    public BookTicketPage clickBookTicketLabel(WebDriver driver) {
        Log.info("Click on 'Book ticket' label");
        driver.findElement(By.xpath(String.format(DYNAMIC_LABEL, "Book ticket"))).click();
        return new BookTicketPage(driver);
    }

    public RegisterPage clickOnRegisterLabel(WebDriver driver) {
        Log.info("Click on 'Register' label");
        driver.findElement(By.xpath(String.format(DYNAMIC_LABEL, "Register"))).click();
        return new RegisterPage(driver);
    }

    public void clickOnLogOutLabel(WebDriver driver) {
        Log.info("Click on 'Log out' label");
        driver.findElement(By.xpath(String.format(DYNAMIC_LABEL, "Log out"))).click();
    }

}

