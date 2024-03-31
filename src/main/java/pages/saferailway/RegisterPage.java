package pages.saferailway;

import helpers.WebDriverHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public String createANewAccount(String email) {
        String pid = RandomStringUtils.randomNumeric(10);
        String passWord = RandomStringUtils.randomAlphanumeric(10);

        driver.findElement(By.cssSelector("#email")).sendKeys(email);
        driver.findElement(By.cssSelector("#password")).sendKeys(passWord);
        driver.findElement(By.cssSelector("#confirmPassword")).sendKeys(passWord);
        driver.findElement(By.cssSelector("#pid")).sendKeys(pid);
        driver.findElement(By.cssSelector("[type='submit']")).click();

        return passWord;
    }

    public String getConfirmationMessage() {
        return WebDriverHelper.waitForElement(driver, By.cssSelector("#content")).getText().trim();
    }
}
