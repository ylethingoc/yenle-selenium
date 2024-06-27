package pages.saferailway;

import org.openqa.selenium.WebDriver;
import pages.PageBase;
import utils.Log;

import static locators.BasePageLocators.SUBMIT_BUTTON;
import static locators.LoginPageLocators.LOGIN_TEXT;
import static locators.LoginPageLocators.PASSWORD_TEXTBOX;
import static locators.LoginPageLocators.USERNAME_TEXTBOX;

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
