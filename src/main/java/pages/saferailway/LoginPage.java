package pages.saferailway;

import helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage loginWithAccount(String email, String passWord) {
        WebDriverHelper.waitForElement(driver, By.xpath("//legend[text()='Log in to your account']"));
        driver.findElement(By.cssSelector("#username")).sendKeys(email);
        driver.findElement(By.cssSelector("#password")).sendKeys(passWord);
        driver.findElement(By.cssSelector("[type='submit']")).click();
        return new HomePage(driver);
    }


}
