package pages.saferailway;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PageBase;

public class RegisterPage extends PageBase {

    private final By emailTextbox = By.cssSelector("#email");
    private final By pwdTextbox = By.cssSelector("#password");
    private final By confirmPwdTextbox = By.cssSelector("#confirmPassword");
    private final By pidTextbox = By.cssSelector("#pid");
    private final By submitBtn = By.cssSelector("[type='submit']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public String createANewAccount(String email) {
        String pid = RandomStringUtils.randomNumeric(10);
        String passWord = RandomStringUtils.randomAlphanumeric(10);

        driver.findElement(emailTextbox).sendKeys(email);
        driver.findElement(pwdTextbox).sendKeys(passWord);
        driver.findElement(confirmPwdTextbox).sendKeys(passWord);
        driver.findElement(pidTextbox).sendKeys(pid);
        driver.findElement(submitBtn).click();

        return passWord;
    }

    public String getConfirmationMessage() {
        return waitForElement(driver, By.cssSelector("#content")).getText().trim();
    }
}
