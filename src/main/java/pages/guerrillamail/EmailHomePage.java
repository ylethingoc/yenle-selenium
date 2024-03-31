package pages.guerrillamail;

import helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.saferailway.RegisterPage;

public class EmailHomePage {
    private final WebDriver driver;

    public EmailHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAnEmailAddress() {
        return driver.findElement(By.cssSelector(".editable.button")).getText() + "@sharklasers.com";
    }

    public void openTheConfirmationEmail(String email) {
        WebDriverHelper.waitForElement(driver, By.xpath(String.format("//td[contains(text(),'Please confirm your account %s')]", email))).click();
    }

    public RegisterPage clickOnTheConfirmationLink() {
        WebDriverHelper.waitForElement(driver, By.cssSelector(".email_body>a")).click();
        return new RegisterPage(driver);
    }
}
