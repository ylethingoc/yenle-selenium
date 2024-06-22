package pages.guerrillamail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PageBase;
import pages.saferailway.RegisterPage;

public class EmailHomePage extends PageBase {

    private final By usrNameText = By.cssSelector(".editable.button");
    private final By confirmLink = By.cssSelector(".email_body>a");
    public EmailHomePage(WebDriver driver) {
        super(driver);
    }

    public String getAnEmailAddress() {
        return driver.findElement(usrNameText).getText() + "@sharklasers.com";
    }

    public void openTheConfirmationEmail(String email) {
        waitForElement(driver, By.xpath(String.format("//td[contains(text(),'Please confirm your account %s')]", email))).click();
    }

    public RegisterPage clickOnTheConfirmationLink() {
        waitForElement(driver, confirmLink).click();
        return new RegisterPage(driver);
    }
}
