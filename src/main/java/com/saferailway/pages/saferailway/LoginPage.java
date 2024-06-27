package com.saferailway.pages.saferailway;

import com.saferailway.pages.PageBase;
import org.openqa.selenium.WebDriver;
import com.saferailway.utils.Log;

import static com.saferailway.locators.BasePageLocators.SUBMIT_BUTTON;
import static com.saferailway.locators.LoginPageLocators.LOGIN_TEXT;
import static com.saferailway.locators.LoginPageLocators.PASSWORD_TEXTBOX;
import static com.saferailway.locators.LoginPageLocators.USERNAME_TEXTBOX;

public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage loginWithAValidAccount(String email, String passWord) {
        Log.info("Login with a valid account");
        waitForElement(driver, LOGIN_TEXT);
        driver.findElement(USERNAME_TEXTBOX).sendKeys(email);
        driver.findElement(PASSWORD_TEXTBOX).sendKeys(passWord);
        driver.findElement(SUBMIT_BUTTON).click();
        return new HomePage(driver);
    }
}
