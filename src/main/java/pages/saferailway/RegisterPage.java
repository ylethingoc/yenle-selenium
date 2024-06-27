package pages.saferailway;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import pages.PageBase;
import utils.Log;

import static locators.BasePageLocators.SUBMIT_BUTTON;
import static locators.RegisterPageLocators.CONFIRM_MESSAGE;
import static locators.RegisterPageLocators.CONFIRM_PASSWORD_TEXTBOX;
import static locators.RegisterPageLocators.EMAIL_TEXTBOX;
import static locators.RegisterPageLocators.PASSWORD_TEXTBOX;
import static locators.RegisterPageLocators.PID_TEXTBOX;

public class RegisterPage extends PageBase {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public String createANewAccount(String email) {
        Log.info("Create a new account");
        String pid = RandomStringUtils.randomNumeric(10);
        String passWord = RandomStringUtils.randomAlphanumeric(10);

        driver.findElement(EMAIL_TEXTBOX).sendKeys(email);
        driver.findElement(PASSWORD_TEXTBOX).sendKeys(passWord);
        driver.findElement(CONFIRM_PASSWORD_TEXTBOX).sendKeys(passWord);
        driver.findElement(PID_TEXTBOX).sendKeys(pid);
        driver.findElement(SUBMIT_BUTTON).click();

        return passWord;
    }

    public String getConfirmationMessage() {
        return waitForElement(driver, CONFIRM_MESSAGE).getText().trim();
    }
}
