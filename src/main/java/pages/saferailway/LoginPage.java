package pages.saferailway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PageBase;

public class LoginPage extends PageBase {

    private By loginText = By.xpath("//legend[text()='Log in to your accounts']");
    private By userTextBox = By.cssSelector("#username");
    private By pwTextBox = By.cssSelector("#password");
    private By submitBtn = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage loginWithValidAccount(String email, String passWord) {
        waitForElement(driver, loginText);
        driver.findElement(userTextBox).sendKeys(email);
        driver.findElement(pwTextBox).sendKeys(passWord);
        driver.findElement(submitBtn).click();
        return new HomePage(driver);
    }
}
